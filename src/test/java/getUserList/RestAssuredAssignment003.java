package getUserList;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class RestAssuredAssignment003 {

    private static final String BASE_URL = "https://api.restful-api.dev";

    @Test
    public void performCRUDOperationsForBankEmployees() {
        FileInputStream file = null;
        Workbook workbook = null;
        try {
            file = new FileInputStream("C:\\Users\\veenana\\Music\\BankEmployees.xlsx");
            workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                // Read employee details from Excel
                String name = row.getCell(0).getStringCellValue();
                int year = (int) row.getCell(1).getNumericCellValue();
                String dob = row.getCell(2).getStringCellValue(); // Assuming date of birth is a string
                String address = row.getCell(3).getStringCellValue();
                double salary = row.getCell(4).getNumericCellValue();

                // Create employee
                Response response = RestAssured.given()
                        .contentType("application/json")
                        .body("{ \"name\": \"" + name + "\", \"data\": { \"year\": " + year + ", \"DOB\": \"" + dob + "\", \"Address\": \"" + address + "\", \"Salary\": " + salary + " } }")
                        .post(BASE_URL + "/objects");

                Assert.assertEquals(response.getStatusCode(), 201);
                String employeeId = response.getBody().jsonPath().getString("id");

                // Update employee's address
                String updatedAddress = "New Address";
                Response putResponse = RestAssured.given()
                        .contentType("application/json")
                        .body("{ \"name\": \"" + name + "\", \"data\": { \"year\": " + year + ", \"DOB\": \"" + dob + "\", \"Address\": \"" + updatedAddress + "\", \"Salary\": " + salary + " } }")
                        .put(BASE_URL + "/objects/" + employeeId);

                Assert.assertEquals(putResponse.getStatusCode(), 200);
                Assert.assertEquals(putResponse.getBody().jsonPath().getString("Address"), updatedAddress);

                // Delete employee
                Response deleteResponse = RestAssured.delete(BASE_URL + "/objects/" + employeeId);
                Assert.assertEquals(deleteResponse.getStatusCode(), 200);
                Assert.assertEquals(deleteResponse.getBody().jsonPath().getString("message"), "Object with id = " + employeeId + " has been deleted.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
