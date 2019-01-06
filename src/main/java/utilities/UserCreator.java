package utilities;

import domain.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserCreator {

    public static void createUserIfNotExists(String uname, String uemail, String upassword) {
        RestAssured.baseURI = "http://calories-calc.herokuapp.com/api";
        RestAssured.basePath = "/session";

        User user = new User();
        user.setEmail(uemail);
        user.setPassword(upassword);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(user)
                .post();

        if (response.getStatusCode() == 401) {
            RestAssured.basePath = "/registration";

            user.setUserName(uname);
            user.setPasswordConfirmation(upassword);

            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .body(user)
                    .post().then().statusCode(201);
        }
    }
}
