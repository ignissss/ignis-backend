package ignis.ignis.domain.user.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    private String token;
    @NotBlank
    private String userName;
    @NotBlank
    private String profileUrl;
}
