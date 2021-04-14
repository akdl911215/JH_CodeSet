package kr.junghyun.api.user.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserDto {
    private String userId;
    private String username;
    private String password;
    private String birth;
    private String email;
}
