package site.metacoding.blogv3.config.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.domain.user.User;
import site.metacoding.blogv3.domain.user.UserRepository;
import site.metacoding.blogv3.handler.ex.CustomException;

@RequiredArgsConstructor
@Service // Ioc 컨테이너에 등록됨
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username" + username);
        System.out.println("DB에 확인 들어간다~~");

        Optional<User> userOp = userRepository.findByUsername(username);

        if (userOp.isPresent()) {
            // return null; // return 하면 principal 안으로 들어가는데 null 이면 인증이 안됨.
            return new LoginUser(userOp.get());
        } else {
            throw new CustomException("유저네임 없음");
        }

    }

}
