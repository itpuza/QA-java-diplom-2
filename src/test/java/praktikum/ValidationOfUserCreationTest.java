package praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ValidationOfUserCreationTest {

    private UserClient userClient;
    private User user;
    private int expectedStatus;
    private String expectedErrorMessage;

    @Before
    public void setUp() {
        userClient = new UserClient();
    }

    public ValidationOfUserCreationTest(User user, int expectedStatus, String expectedErrorMessage) {
        this.user = user;
        this.expectedStatus = expectedStatus;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {User.getUserWithoutEmail(), 403, "Email, password and name are required fields"},
                {User.getUserWithoutName(), 403, "Email, password and name are required fields"},
                {User.getUserWithoutPassword(), 403, "Email, password and name are required fields"},
        };
    }

    @Test
    @DisplayName("Checking field validation for user creation")
    public void checkFieldsValidationOfUserCreationTest() {
        ValidatableResponse response = userClient.createUser(user);
        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertEquals("Некорректный код статуса", expectedStatus, statusCode);
        assertEquals("Некорректное сообщение об ошибке", expectedErrorMessage, errorMessage);
    }

}