package crud.dto.response;

import crud.dto.SimpleResponse;
import lombok.Builder;

/**
 * @author Mukhammed Asantegin
 */
@Builder
public record RegisterResponse(String token, SimpleResponse simpleResponse) {}
