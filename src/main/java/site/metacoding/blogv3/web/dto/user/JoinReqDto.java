package site.metacoding.blogv3.web.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.blogv3.domain.user.User;

@NoArgsConstructor
@AllArgsConstructor
@Data // Getter, Setter, ToString
public class JoinReqDto {

    // 들어올 때 알아서 사이즈 해줌.
    @Pattern(regexp = "[a-zA-Z1-9]{4,20}", message = "유저네임은 한글 못들어가유~")
    @NotBlank
    private String username;

    @Size(min = 4, max = 20)
    @NotBlank // NotNull 과 NotEmpty를 포함하고 있는 Notblank
    private String password;

    @Size(min = 10, max = 60)
    @NotBlank
    @Email
    private String email;

    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;

    }
}
