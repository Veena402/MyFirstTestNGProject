package getUserList;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompanyDataUpdater {

    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/company_database";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    @Test
    public void updateCompanyDataTest() {
        // Make a GET request to the API to retrieve company data
        Response response = RestAssured.get("https://fake-json-api.mock.beeceptor.com/companies");

        // Parse JSON response and update company data in the database
        if (response.getStatusCode() == 200) {
            JSONArray companiesArray = new JSONArray(response.getBody().asString());
            for (int i = 0; i < companiesArray.length(); i++) {
                JSONObject companyObject = companiesArray.getJSONObject(i);
                int id = companyObject.getInt("id");
                String name = companyObject.getString("name");
                String address = companyObject.getString("address");
                String zip = companyObject.getString("zip");

                // Update company data in the database
                updateCompanyData(id, name, address, zip);
            }
            System.out.println("Company data updated successfully!");
        } else {
            System.out.println("Failed to fetch company data from the API.");
        }
    }

    // Method to update company data in the database
    private static void updateCompanyData(int id, String name, String address, String zip) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Prepare SQL statement to update company data
            String sql = "INSERT INTO Companies_Data (id, name, address, zip) VALUES (?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE name = VALUES(name), address = VALUES(address), zip = VALUES(zip)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set parameters for the SQL statement
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, zip);

            // Execute the update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
