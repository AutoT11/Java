package API.Pozitive;

import API.models.Заявки.Patch.PatchRequest;
import API.models.Заявки.Patch.PatchResponse;
import API.models.Заявки.Put.PutRequest;
import API.models.Заявки.Put.PutResponse;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import API.models.Заявки.Post.PostRequest;
import API.models.Заявки.Post.PostResponse;
import untils.TestNumberGenerator;

import static io.restassured.RestAssured.post;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.everyItem;
import static API.specs.ApiSpecs.requestSpec;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import java.util.Map;

public class ApiTest {


    @Test
    void getTest() {

        Response response = given()
                .spec(requestSpec())
                .when()
                .get("/users")
                .then()
                //.log().body()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> userID = jsonPath.get(
                "find { it.id == 1}"
                );

                assertThat(userID.get("id"), equalTo(1));
                assertThat(userID.get("name"), equalTo("Leanne Graham"));
                assertThat(userID.get("username"), equalTo("Bret"));
                assertThat(userID.get("email"), equalTo("Sincere@april.biz"));
                assertThat(userID.get("phone"), equalTo("1-770-736-8031 x56442"));
                assertThat(userID.get("website"), equalTo("hildegard.org"));

        Map<String, Object> address =
                (Map<String, Object>) userID.get("address");

                assertThat(address.get("street"), equalTo("Kulas Light"));
                assertThat(address.get("suite"), equalTo("Apt. 556"));
                assertThat(address.get("city"), equalTo("Gwenborough"));
                assertThat(address.get("zipcode"), equalTo("92998-3874"));

        Map<String, Object> geo =
                (Map<String, Object>) address.get("geo");

                assertThat(geo.get("lat"), equalTo("-37.3159"));
                assertThat(geo.get("lng"), equalTo("81.1496"));

        Map<String, Object> company =
                (Map<String, Object>) userID.get("company");

                assertThat(company.get("name"), equalTo("Romaguera-Crona"));
                assertThat(company.get("catchPhrase"), equalTo("Multi-layered client-server neural-net"));
                assertThat(company.get("bs"), equalTo("harness real-time e-markets"));

    }

    @Test //С использованием рандомных значений
    void postTest() {

        String titleRandom = TestNumberGenerator.uniqueTitle();

        PostRequest postRequest = new PostRequest(

                99998,
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

    @Test //С использованием рандомных значений
    void putTest () {

        String titleRandom = TestNumberGenerator.uniqueTitle();

        int postID = 1;

        PutRequest putRequest = new PutRequest(

                99888,
                titleRandom,
                "Кредитка особенная"

        );

        Response response = given()
                .pathParam("postID", postID)
                .spec(requestSpec())
                .body(putRequest)
                .when()
                .put("/posts/{postID}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        PutResponse putResponse = response.as(PutResponse.class);

        assertThat(putResponse.getUserId(), equalTo(99888));
        assertThat(putResponse.getId(), equalTo(1));
        assertThat(putResponse.getTitle(), equalTo(titleRandom));
        assertThat(putResponse.getBody(), equalTo("Кредитка особенная"));


    }

    @Test //С использованием рандомных значений
    void patchTest () {

        String titleRandom = TestNumberGenerator.uniqueTitle();

        int postID = 1;

       PatchRequest patchRequest = new PatchRequest(

               titleRandom

       );


        Response response = given()
                .pathParam("postID", postID)
                .spec(requestSpec())
                .body(patchRequest)
                .when()
                .patch("/posts/{postID}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        PatchResponse patchResponse = response.as(PatchResponse.class);

        assertThat(patchResponse.getUserId(), equalTo(1));
        assertThat(patchResponse.getId(), equalTo(1));
        assertThat(patchResponse.getTitle(), equalTo(titleRandom));
        assertThat(patchResponse.getBody(), notNullValue());



    }

    @Test
    void deleteTest () {

        int postID = 1;

        Response response = given()
                .spec(requestSpec())
                .pathParam("postID", postID)
                .when()
                .delete("/posts/{postID}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        assertThat(response.asString(), equalTo("{}"));


    }

    @Test
    void getPostsByUserIdTest () {

        Response response = given()
                .spec(requestSpec())
                .queryParam("userId", 1)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();

        List<Integer> userIds = jsonPath.getList("userId");

        assertThat(userIds, everyItem(equalTo(1)));

    }

}

