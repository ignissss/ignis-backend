package ignis.ignis.domain.user.controller;

import ignis.ignis.domain.user.controller.dto.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public void login(@RequestBody LoginRequest request) {

    }
}
