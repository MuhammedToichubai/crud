package crud.service;

import crud.config.jwt.JwtService;
import crud.dto.SimpleResponse;
import crud.dto.request.LoginRequest;
import crud.dto.request.RegisterRequest;
import crud.dto.response.LoginResponse;
import crud.dto.response.RegisterResponse;
import crud.models.Role;
import crud.models.User;
import crud.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Mukhammed Asantegin
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostConstruct
    private void saveAdmin() {
        userRepo.save(
                User.builder()
                        .name("Mukhammed")
                        .email("mukhammed.codes@gmail.com")
                        .phoneNumber("+996997997750")
                        .role(Role.ADMIN)
                        .password(passwordEncoder.encode("spring"))
                        .build()
        );

    }

    @Override
    public RegisterResponse signUp(RegisterRequest request) {
        boolean exists = userRepo.existsByEmail(request.email());
        if (exists) throw new RuntimeException(String.format("Email : %s already exist",request.email()));
        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();
        userRepo.save(user);

        String newToken = jwtService.createToken(user);
        log.info("User successfully saved!");
        return RegisterResponse.builder()
                .token(newToken)
                .simpleResponse(
                        SimpleResponse.builder()
                                .httpStatus(HttpStatus.OK)
                                .message("Successfully saved!")
                                .build())
                .build();

    }

    @Override
    public LoginResponse signIn(LoginRequest request) {
        User user = userRepo.findByEmail(request.email());

        String encodePassword = user.getPassword();
        String password = request.password();

        boolean matches = passwordEncoder.matches(password, encodePassword);

        if (!matches) throw new RuntimeException("Invalid password");

        String token = jwtService.createToken(user);
        return LoginResponse.builder()
                .token(token)
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
