package ignis.ignis.domain.user.service;

import ignis.ignis.domain.feed.domain.Feed;
import ignis.ignis.domain.user.controller.dto.request.LoginRequest;
import ignis.ignis.domain.user.controller.dto.request.SignupRequest;
import ignis.ignis.domain.user.controller.dto.response.LoginResponse;
import ignis.ignis.domain.user.controller.dto.response.UserInfoResponse;
import ignis.ignis.domain.user.domain.User;
import ignis.ignis.domain.user.domain.repository.UserRepository;
import ignis.ignis.domain.user.facade.UserFacade;
import ignis.ignis.global.security.jwt.JwtTokenProvider;
import ignis.ignis.global.security.jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;

    @Transactional
    public LoginResponse login(LoginRequest request) {

        if (!userRepository.existsByEmail(request.getEmail())) {
            User user = User.builder()
                    .userName(request.getUserName())
                    .email(request.getEmail())
                    .re(0)
                    .profileUrl(request.getProfileUrl())
                    .build();

            userRepository.save(user);
        }

        return LoginResponse.builder()
                .isSignedUp(userRepository.existsByEmailAndAgeIsNotNull(request.getEmail()))
                .accessToken(jwtTokenProvider.getToken(request.getEmail()).getAccessToken())
                .build();
    }

    @Transactional
    public void signup(SignupRequest request) {
        User user = userFacade.getCurrentUser();

        if (user.getAge() != null) throw new RuntimeException();

        user.signup(request);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserInfoResponse userFind() {
        User user = userFacade.getCurrentUser();
        return new UserInfoResponse(user);
    }
}
