package crud.service;

import crud.dto.request.LoginRequest;
import crud.dto.request.RegisterRequest;
import crud.dto.response.LoginResponse;
import crud.dto.response.RegisterResponse;

/**
 * @author Mukhammed Asantegin
 */
public interface AuthService {

    RegisterResponse signUp(RegisterRequest signUpRequest);

    LoginResponse signIn(LoginRequest signInRequest);
}
