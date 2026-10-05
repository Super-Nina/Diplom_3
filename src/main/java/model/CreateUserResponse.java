package model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponse {
    private boolean success;
    private UserResponseInfo user;
    private String accessToken;
    private String refreshToken;
}
