package crud.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Mukhammed Asantegin
 */
public record LoginRequest(@Email String email, @NotBlank String password) {}
