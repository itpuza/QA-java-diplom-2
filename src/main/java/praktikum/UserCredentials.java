package praktikum;

import org.apache.commons.lang3.RandomStringUtils;

public class UserCredentials {

    public String email;
    public String password;

    public UserCredentials() {
    }

    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserCredentials from(User user) {
        return new UserCredentials(user.email, user.password);
    }

    public UserCredentials setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserCredentials setPassword(String password) {
        this.password = password;
        return this;
    }

    public static UserCredentials getUserPermissionWithNotValidCredentials() {
        return new UserCredentials().setEmail(RandomStringUtils.randomAlphabetic(15))
                .setPassword(RandomStringUtils.randomAlphabetic(10));
    }

}