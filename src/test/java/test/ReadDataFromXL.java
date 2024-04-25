package test;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import static java.sql.Types.NUMERIC;
import static org.codehaus.groovy.syntax.Types.STRING;


public class ReadDataFromXL
{
    public static void main(String[] args) throws Exception
    {
//        File file = new File("C:\\Users\\veenana\\TestNG\\data1.xlsx");
//        FileInputStream fis = new FileInputStream(file);
//        XSSFWorkbook workbook = new XSSFWorkbook(fis);
//        XSSFSheet sheet = workbook.getSheet("veena001");
//
//        // String cellvalue = sheet.getRow(0).getCell(0).getStringCellValue();
//        //  System.out.println("The cellvalue is "+cellvalue);
//
//        //to get physical number of rows
//        int rowcount = sheet.getPhysicalNumberOfRows();
//        System.out.println(rowcount);
//
//        for(int i=0; i<= rowcount ;i++)
//        {
//
//            XSSFRow row = sheet.getRow(i);
//            System.out.println(row);
//            int cellcount=row.getPhysicalNumberOfCells();
//
//
//            for(int j =0; j<cellcount; j++)
//            {
//                XSSFCell cell = row.getCell(j);
//                //row.getCell(j);
//                String  cellvalue = getCellValue(cell);
//                 String.valueOf(getCellValue(cell));
//                System.out.println("||"+ cellvalue );
//
//            }
//
//        }
//        workbook.close();
//        fis.close();
//
//

        try {
            FileInputStream fis = new FileInputStream(new File("C:\\\\Users\\\\veenana\\\\TestNG\\\\data1.xlsx"));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                for (int i = 0; i < row.getLastCellNum()-1; i++) {
                    System.out.print(row.getCell(i) + "\t");
                }
                System.out.println();
            }
            workbook.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getCellValue(XSSFCell cell)
    {
        switch (cell.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell))
                {
                    return cell.getDateCellValue().toString(); // If the cell contains a date value
                } else
                {
                    return String.valueOf(cell.getNumericCellValue()); // If the cell contains a numeric value
                }
            case STRING:
                return cell.getStringCellValue();
            default:
                return ""; // For other cell types, return an empty string
        }
    }


}


