package praktikum;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {
    private static final String ORDER_PATH = "api/orders";

    @Step("Create a user")
    public ValidatableResponse createOrder(IngredientsHashes ingredients, String token) {
        return given()
                .headers("Authorization", token)
                .spec(getBaseSpec())
                .body(ingredients)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step("Get a list of orders")
    public ValidatableResponse getOrderList(String token) {
        return given()
                .headers("Authorization", token)
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }

}