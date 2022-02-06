package praktikum;

import com.github.javafaker.Faker;

public class User {

    public String email;
    public String password;
    public String name;
    public static Faker faker = new Faker();

    public User() {
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User getRandomUser() {
        final String email = faker.internet().emailAddress();
        final String password = faker.internet().password();
        final String name = faker.name().name();
        return new User(email, password, name);
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public static User getUserWithoutPassword() {
        return new User().setEmail(faker.internet().emailAddress()).setName(faker.name().name());
    }

    public static User getUserWithoutEmail() {
        return new User().setPassword(faker.internet().password()).setName(faker.name().name());
    }

    public static User getUserWithoutName() {
        return new User().setName(faker.internet().emailAddress()).setPassword(faker.internet().password());
    }

}