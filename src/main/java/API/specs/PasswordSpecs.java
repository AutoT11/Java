package API.specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;


public class PasswordSpecs {

    public static RequestSpecification PasswordSpecs() {

        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .setContentType(ContentType.JSON)
                .build();

    }
}
