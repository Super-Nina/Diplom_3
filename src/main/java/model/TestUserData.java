package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TestUserData {
    private final String email;
    private final String password;
    private final String accessToken;
}
