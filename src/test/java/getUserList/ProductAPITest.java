package getUserList;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductAPITest {

    @Test
    public void verifyProductDetails() {
        given()
                .when()
                .get("https://dummyjson.com/products/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("iPhone 9"))
                .body("price", equalTo(549))
                .body("brand", equalTo("Apple"))
                .body("images.size()", equalTo(5))
                .body("images", everyItem(endsWith(".jpg")));
    }
}

