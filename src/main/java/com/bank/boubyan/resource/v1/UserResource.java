package com.bank.boubyan.resource.v1;

import com.bank.boubyan.exception.UserNotFoundException;
import com.bank.boubyan.model.dto.LibraryMSResponse;
import com.bank.boubyan.model.dto.UserDTO;
import com.bank.boubyan.model.dto.UserResponse;
import com.bank.boubyan.model.mapper.UserMapper;
import com.bank.boubyan.repository.UserRepository;
import com.bank.boubyan.service.security.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(UserResource.USER_RESOURCE)
public class UserResource {
    public static final String USER_RESOURCE = "/api/v1/auth";

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public UserResource(UserRepository userRepository,
                        JWTService jwtService,
                        UserMapper userMapper,
                        PasswordEncoder passwordEncoder,
                        AuthenticationManager authenticationManager
                        ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<LibraryMSResponse<UserResponse>> register(@RequestBody UserDTO userDTO){
        var user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        var token = jwtService.generate(Map.of(), user);
        var userResponse = new UserResponse(user.getEmail(), token);

        var response = new LibraryMSResponse.Builder<UserResponse>()
                .data(userResponse)
                .moreDetails("Generate The JWT Token")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LibraryMSResponse<UserResponse>> login(@RequestBody UserDTO userDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        );
        var user = userRepository
                .findByEmail(userDTO.getEmail())
                .orElseThrow(()-> new UserNotFoundException("User WIth email " + userDTO.getEmail() + " Is Not Found"));

        var token = jwtService.generate(Map.of(), user);
        var userResponse = new UserResponse(user.getEmail(), token);
        var response = new LibraryMSResponse.Builder<UserResponse>()
                .data(userResponse)
                .moreDetails("Login And Return JWT")
                .build();
        return ResponseEntity.ok(response);
    }
}
