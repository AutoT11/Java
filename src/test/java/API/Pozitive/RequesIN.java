package API.Pozitive;

import API.reqresIN.models.register.RegisterRequest;
import API.reqresIN.models.register.RegisterResponse;
import org.junit.jupiter.api.Test;

import static API.specs.PasswordSpecs.PasswordSpecs;
import static io.restassured.RestAssured.post;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class RequesIN {

    @Test
    void register () {

        //String passwordRandom = TestPasswordGenerator.uniquePassword();

        RegisterRequest registerRequest = new RegisterRequest(

                "eve.holt@reqres.in",
                "pistol"

        );

        RegisterResponse response = given()
                .spec(PasswordSpecs())
                .body(registerRequest)
                .when()
                .post("/api/register")
                .then()
                .statusCode(200)
                .extract()
                .as(RegisterResponse.class);

        assertThat(response.getId(), notNullValue());
        assertThat(response.getToken(), notNullValue());

    }

}
