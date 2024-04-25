package readxl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.*;

import java.io.FileInputStream;

public class ReadSimpleXLFile
{

    // Workbook - Interface to instantiate different XL file.[xls/xlsx]

// Sheet - Interface to read the sheet inside the Workbook

// Row - Interface used to identify the row inside the sheet.

// Cell - Interface to identify the corresponding Cell of a given Row.

// CLASSES INSIDE APACHE POI

// XSSFWorkbook - Class which will implement Workbook interface for the XL file.

// HSSFWorkbook - Class which will implement Workbook interface for the XL file

// XSSFSheet - Class representing a Sheet interface.

// HSSFSheet - Class representing a Sheet interface.

// XSSFRow - Class representing a Row interface.

// HSSFRow - Class representing a Row interface.

// XSSFCell - Class representing a Cell interface.

// HSSFCell - Class representing a Cell interface.
    @Test
    public   void read_and_print_simpleXL()
{
    try
    {

        String XLFilePath = System.getProperty("usr.dir")+"TestingBook.xlsx";
       // "C:\\Users\\veenana\\Music\\TestingBook.xlsx";

        FileInputStream myxlfile= new FileInputStream(XLFilePath);
        Workbook workbook = new XSSFWorkbook(myxlfile);
        Sheet sheet =  workbook.getSheet("veena001");
         int lastrow = sheet.getLastRowNum();
         System.out.println("The last row which has data "+ lastrow);


        // Loop for row iteration
         for (int i =0; i<=lastrow;i++)
         {
             Row row = sheet.getRow(i);
             int lastcell = row.getLastCellNum();

             //// Loop for row iteration
             for (int j=0;j<lastcell;j++)
             {
                 Cell cell = row.getCell(j);
                 String value = cell.getStringCellValue();
                 System.out.println("The XL Value is "+ value);
             }
         }
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }

}

    public static void main(String[] args)
    {
        read_and_print_XLasperTestData("TC001", "Email");

    }

    public static void read_and_print_XLasperTestData(String testcasename, String columnname)
    {
        try
        {

            String XLFilePath = "C:\\Users\\veenana\\Music\\TestingBook.xlsx";
            FileInputStream myxlfile= new FileInputStream(XLFilePath);
            Workbook workbook = new XSSFWorkbook(myxlfile);
            Sheet sheet =  workbook.getSheet("veena001");
            int lastrow = sheet.getLastRowNum();

            System.out.println("The last row which has data "+ lastrow);
            // Loop for row iteration
            for (int i =0; i<=lastrow;i++)
            {
                Row row = sheet.getRow(i);
                //get the last column which has data x
                int lastcell = row.getLastCellNum();
                Cell cell = row.getCell(0);
                String runtimetestcasename = cell.getStringCellValue();
                System.out.println("THE first column value is "+ runtimetestcasename);
                if (runtimetestcasename.equals(testcasename)) {


                    //// Loop for column iteration..........
                    Row rowname= sheet.getRow(0);

                    for (int j=0;j<lastcell;j++)
                    {
                      Cell cellnew = row.getCell(j);
                    String  runTimeCallValue  = cellnew.getStringCellValue();
                    if (runTimeCallValue.equals(columnname))
                    {
                        String value = cell.getStringCellValue();
                        System.out.println("The XL Value is "+ value);
                    }

                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
