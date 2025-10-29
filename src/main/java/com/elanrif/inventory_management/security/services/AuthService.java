package com.elanrif.inventory_management.security.services;

import com.elanrif.inventory_management.entities.RefreshToken;
import com.elanrif.inventory_management.entities.Role;
import com.elanrif.inventory_management.entities.User;
import com.elanrif.inventory_management.enums.ERole;
import com.elanrif.inventory_management.exception.TokenRefreshException;
import com.elanrif.inventory_management.payload.request.LoginRequest;
import com.elanrif.inventory_management.payload.request.SignupRequest;
import com.elanrif.inventory_management.payload.response.JwtResponse;
import com.elanrif.inventory_management.payload.response.MessageResponse;
import com.elanrif.inventory_management.payload.response.TokenRefreshResponse;
import com.elanrif.inventory_management.repository.RoleRepository;
import com.elanrif.inventory_management.repository.UserRepository;
import com.elanrif.inventory_management.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<?> authenticateAndGenerateJwt(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(org.springframework.security.core.GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt,
                refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    // Default overload: generate JWT by default
    public ResponseEntity<?> registerAndGenerateJwt(SignupRequest signUpRequest) {
        return registerAndGenerateJwt(signUpRequest, true);
    }

    // Existing implementation updated to use primitive boolean
    public ResponseEntity<?> registerAndGenerateJwt(SignupRequest signUpRequest, boolean generateJwt) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .address(signUpRequest.getAddress())
                .phone(signUpRequest.getPhone())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "moderator":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        User newUser = userRepository.save(user);

        // Authenticate with raw password from signup request (not encoded one)
        if (generateJwt) {
            return authenticateAndGenerateJwt(
                    new LoginRequest(signUpRequest.getEmail(), signUpRequest.getPassword())
            );
        }
        return ResponseEntity.ok(newUser);
    }

    public ResponseEntity<?> refreshToken(String requestRefreshToken) {
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getEmail());
                    System.out.println("refreshtoken: generated new access token for user -> [" + user.getEmail() + "]");
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> {
                    System.err.println("refreshtoken: token not found in DB -> [" + requestRefreshToken + "]");
                    return new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!");
                });
    }

    public ResponseEntity<?> logoutCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("No authenticated user"));
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        refreshTokenService.deleteByUserId(userDetails.getId());
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }
}
