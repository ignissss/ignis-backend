package ignis.ignis.domain.user.service;

import ignis.ignis.domain.user.controller.dto.request.LoginRequest;
import ignis.ignis.domain.user.domain.User;
import ignis.ignis.domain.user.domain.repository.UserRepository;
import ignis.ignis.global.security.jwt.JwtTokenProvider;
import ignis.ignis.global.security.jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse login(LoginRequest request) {
        if (!userRepository.existsByToken(request.getToken())) {
            User user = User.builder()
                    .token(request.getToken())
                    .userName(request.getUserName())
                    .profileUrl(request.getProfileUrl())
                    .build();

            userRepository.save(user);
        }

        return jwtTokenProvider.getToken(request.getUserName());
    }
}
