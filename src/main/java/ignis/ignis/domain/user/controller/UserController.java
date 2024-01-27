package ignis.ignis.domain.user.controller;

import ignis.ignis.domain.user.controller.dto.request.LoginRequest;
import ignis.ignis.domain.user.controller.dto.request.SignupRequest;
import ignis.ignis.domain.user.service.UserService;
import ignis.ignis.global.security.jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }

    @PatchMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequest request) {

    }
}
