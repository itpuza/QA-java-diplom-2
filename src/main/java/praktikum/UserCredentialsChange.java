package praktikum;

import com.github.javafaker.Faker;

public class UserCredentialsChange {

    public String email;
    public String password;
    private String name;
    private static Faker faker = new Faker();

    public UserCredentialsChange() {
    }

    public UserCredentialsChange(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static UserCredentialsChange from(User user) {
        return new UserCredentialsChange(user.email, user.password, user.name);
    }

    public UserCredentialsChange setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserCredentialsChange setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserCredentialsChange setName(String name) {
        this.name = name;
        return this;
    }

    public static UserCredentialsChange changeUserCredentials() {
        return new UserCredentialsChange().setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password())
                .setName(faker.name().name());
    }

}