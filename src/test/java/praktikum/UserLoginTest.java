package praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

public class UserLoginTest {

    private User user;
    private UserClient userClient;

    @Before
    public void setUp(){
        user = User.getRandomUser();
        userClient = new UserClient();
        userClient.createUser(user);
    }

    @Test
    @DisplayName("Checking the successful authorization of the user")
    public void checkUserLoginTest() {
        ValidatableResponse response = userClient.loginUser(UserCredentials.from(user));
        int statusCode = response.extract().statusCode();
        String accessToken = response.extract().path("accessToken");
        String refreshToken = response.extract().path("refreshToken");

        assertThat ("Некорректный код статуса", statusCode, equalTo(200));
        assertThat("Отсутствует refreshToken в теле ответа", refreshToken, notNullValue());
        assertTrue("Некорректный accessToken в теле ответа", accessToken.startsWith("Bearer"));
    }

    @Test
    @DisplayName("Checking the invalid authorization of the user")
    public void checkUserLoginWithInvalidCredentialsTest() {
        ValidatableResponse response = userClient.loginUser(UserCredentials.getUserPermissionWithNotValidCredentials());
        int statusCode = response.extract().statusCode();
        boolean isNotValidUserNotCreated = response.extract().path("message").equals("email or password are incorrect");

        assertThat("Некорректный код статуса", statusCode, equalTo(401));
        assertThat("Удалось авторизоваться пользователем с некорректными данными", isNotValidUserNotCreated);
    }

}