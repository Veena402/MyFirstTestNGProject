package trainingRA;

import com.aventstack.extentreports.gherkin.model.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured.*;
import reusable.RecresJsonBody;

import static io.restassured.RestAssured.given;

public class CreateNewUser
{

    RecresJsonBody RJB;
    @BeforeClass
    public void setup()
    {
       RJB = new  RecresJsonBody();

    }

    @Test
    public void create_new_user()
    {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(RJB.createuserjsonbody())
                .when()
                .post("https://regres.in/api/users");

    }

    @AfterClass
    public void tearDown()
    {

    }
}
