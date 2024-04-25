package getUserList;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.DataProvider;

public class BankEmployeeTest {

    @Test(dataProvider = "employeeData")
    public void testBankEmployee(String name, int year, String dob, String address, String salary) {
        // POST request to create Bank Employee record
        String postBody = "{ \"name\": \"" + name + "\", \"data\": { \"year\": " + year + ", \"DOB\": \"" + dob + "\", \"Address\": \"" + address + "\", \"Salary\": \"" + salary + "\" } }";
        ExtractableResponse<Response> postResponse = given()
                .contentType("application/json")
                .body(postBody)
                .when()
                .post("https://api.restful-api.dev/objects")
                .then()
                .statusCode(200)
                .extract();

        Integer id = postResponse.path("data.id");
        if (id != null) {
            // GET request to retrieve Bank Employee record
            given()
                    .queryParam("id", id)
                    .when()
                    .get("https://api.restful-api.dev/objects")
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(name))
                    .body("data.year", equalTo(year))
                    .body("data.DOB", equalTo(dob))
                    .body("data.Address", equalTo(address))
                    .body("data.Salary", equalTo(salary));
        } else {
            System.out.println("ID is null, unable to proceed with GET request.");
        }
    }
    @DataProvider(name = "employeeData")
    public Object[][] employeeData() {
        return new Object[][] {
                {"Sam Sundar", 2024, "03-03-1985", "Kolkata 1234", "10000"}
                // Add more test data if needed
        };
    }

}
