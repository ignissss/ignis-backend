package ignis.ignis.domain.user.controller;

import ignis.ignis.domain.user.controller.dto.request.LoginRequest;
import ignis.ignis.domain.user.controller.dto.request.SignupRequest;
import ignis.ignis.domain.user.controller.dto.response.LoginResponse;
import ignis.ignis.domain.user.controller.dto.response.UserInfoResponse;
import ignis.ignis.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "User", description = "User API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(description = "로그인")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }

    @Operation(description = "회원가입")
    @PatchMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequest request) {
        userService.signup(request);
    }

    @Operation(description = "내정보 확인")
    @GetMapping
    public UserInfoResponse findUser() {
        return userService.userFind();
    }
}
