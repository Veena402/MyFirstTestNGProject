package getUserList;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BankEmployeeUpdateTest

{

    public static void main(String[] args) {
        // Define the request body for the PUT request
        String requestBody = "{ \"address\": \"New Address\" }";

        // Send the PUT request
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put("https://api.restful-api.dev/objects?id=ff8081818ead0ebb018eb816be43296e");

        // Print the response status code
        System.out.println("Response Status Code: " + response.getStatusCode());
        // Print the response body
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
