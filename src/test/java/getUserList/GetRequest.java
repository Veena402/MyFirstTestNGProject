package getUserList;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequest {
    @Test
    public void getRequestCheck() {
        given()
                .when()
                .get("https://dummyjson.com/products/1")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .assertThat()
                .body("id", equalTo(1));



    }
}
