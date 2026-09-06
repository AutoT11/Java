package API.Negaive;

import groovy.util.logging.PlatformLog;
import io.restassured.response.Response;
import net.bytebuddy.build.Plugin;
import org.junit.jupiter.api.Test;
import API.models.Заявки.Post.PostRequest;
import API.models.Заявки.Post.PostResponse;
import untils.TestNumberGenerator;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.post;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static API.specs.ApiSpecs.requestSpec;
import static io.qameta.allure.Allure.step;

public class ApiTestNegative {

    @Test
    @DisplayName("Ошибка при создании поста с пустым заголовком")
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


    @Test
    @DisplayName("Проверка отсутсвия пользователя под несуществующим id")

    void getNonExistentUserTest() {

        Response response = given()
                .spec(requestSpec())
                .when()
                .get("/users/11")
                .then()
                .statusCode(404)
                .extract()
                .response();

        assertThat(response.asString(), equalTo("{}"));

    }

}
