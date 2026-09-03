package API.Negaive;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import API.models.Заявки.Post.PostRequest;
import API.models.Заявки.Post.PostResponse;
import untils.TestNumberGenerator;

import static io.restassured.RestAssured.post;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static API.specs.ApiSpecs.requestSpec;

public class ApiTestNegative {

    @Test
    //@DisplayName("Ошибка при создании поста с пустым заголовком")
    void postTest() {

        String titleRandom = TestNumberGenerator.uniqueTitle();

        PostRequest postRequest = new PostRequest(

                -1,
                titleRandom,
                "Кредит особый"
        );

        Response response = given()
                .spec(requestSpec())
                .body(postRequest)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        PostResponse postResponse = response.as(PostResponse.class);

        assertThat(postResponse.getUserId(), equalTo(99998));
        assertThat(postResponse.getId(), notNullValue());
        assertThat(postResponse.getTitle(), equalTo(titleRandom));
        assertThat(postResponse.getBody(), equalTo("Кредит особый"));


    }
}
