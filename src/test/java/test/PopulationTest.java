package test;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class PopulationTest {
    private static String apiUrl;
    private static ExtentReports extent;
    private static ExtentTest logger;

    @BeforeClass
    public static void setUp() {
        // Read API URL from properties file
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("config.properties"));
            apiUrl = prop.getProperty("api.url");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize ExtentReports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterClass
    public static void tearDown() {
        // Flush and close ExtentReports
        extent.flush();
    }

    @Test(dataProvider = "populationData")
    public void testPopulation(int year, int expectedPopulation) {
        // Hit the API URL
        try {
            URL url = new URL(apiUrl + "?year=" + year);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Read the API response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse response and get population data
            int actualPopulation = parsePopulation(response.toString());

            // Compare population data with expected
            Assert.assertEquals(actualPopulation, expectedPopulation);

            // Log test status to Extent report
            logger = extent.createTest("Year: " + year);
            logger.info("Expected Population: " + expectedPopulation);
            logger.info("Actual Population: " + actualPopulation);
            logger.pass("Population matched for year " + year);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "populationData")
    public Iterator<Object[]> provideData() {
        List<Object[]> data = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\veenana\\TestNG\\PopulationData.xlsx"));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip header row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int year = (int) row.getCell(0).getNumericCellValue();
                int population = (int) row.getCell(1).getNumericCellValue();
                data.add(new Object[]{year, population});
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.iterator();
    }

    private int parsePopulation(String response) {
        // Implement logic to parse API response and extract population data
        return 0; // Placeholder, replace with actual implementation
    }
}

