package ignis.ignis.domain.user.controller.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    private String userName;

    @Min(value = 10)
    @Max(value = 100)
    private Integer age;
}
