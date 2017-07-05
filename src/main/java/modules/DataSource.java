package modules;

/**
 * Created by Lokesh_GnanaSekhar on 6/30/2017.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataSource {

    public static String[][] getDataForTestCase(String testcaseName)
            throws IOException {

        String fileName = "./src/test/java/testdata/TestData.xlsx";
        String testcaseToFind = testcaseName;

        // Provide the file where the test data exists
        InputStream inputstream = new FileInputStream(fileName);

		/*
		 * Read the first data sheet in the workbook. XSSFWorkbook is used for
		 * .xlsx and xml HSSFWorkbook is used for .xls
		 */
        XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Finds from which row onwards testcase data starts
        int testcaseStartsFromRow = findTestCase_StartingRow(sheet,
                testcaseToFind);
        // Gets Max rows in a excel sheet
        int maxRowsInSheet = sheet.getLastRowNum();

        // Gets the Test data from all over the excel sheet
        String testcaseData[][] = getData(sheet, testcaseStartsFromRow,
                maxRowsInSheet, testcaseToFind);

        /** Display Test case Data **/

        for (String[] row : testcaseData) {
            for (String col : row) {
                System.out.print(col + "|");
            }
            System.out.println("");
            System.out.println("----------------------");
        }

        return testcaseData;

    }

    public static List<Map<String, String>> getDataForTestCase_Optimized(
            String testcaseName) throws IOException {

        String fileName = "./src/test/java/testdata/TestData.xlsx";
        String testcaseToFind = testcaseName;

        // Provide the file where the test data exists
        InputStream inputstream = new FileInputStream(fileName);

		/*
		 * Read the first data sheet in the workbook. XSSFWorkbook is used for
		 * .xlsx and xml HSSFWorkbook is used for .xls
		 */
        XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Finds from which row onwards testcase data starts
        int testcaseStartsFromRow = findTestCase_StartingRow(sheet,
                testcaseToFind);
        // Gets Max rows in a excel sheet
        int maxRowsInSheet = sheet.getLastRowNum();

        // Gets the Test data from all over the excel sheet
        List<Map<String, String>> testcaseData = getData_Optimized(sheet,
                testcaseStartsFromRow, maxRowsInSheet, testcaseToFind);

        /** Display Test case Data **/
		/*
		 * for (Map row : testcaseDataList) { System.out.println(row + "|"); }
		 */
        return testcaseData;

    }

    /*
     * Gets the test data from all over the excel sheet
     */
    private static String[][] getData(XSSFSheet sheet, int startFromRow,
                                      int endRow, String testcaseName) {

		/*
		 * Hold test case data as an array of strings Each array represents a
		 * test case data row
		 */
        ArrayList<String[]> testcaseDataList = new ArrayList<String[]>();
        DataFormatter dataFormatter = new DataFormatter();

        // Get the heading column length
        int headingRowNum = startFromRow - 1;
        Row headingRow = sheet.getRow(headingRowNum);
        int lastHeadingCol = headingRow.getLastCellNum();

        // Looping from provided start Row till end of the sheet
        for (int rowNum = startFromRow; rowNum <= endRow; rowNum++) {
            Row row = sheet.getRow(rowNum);

            // Verify whether a given row is not null and equals to test case
            if (row != null
                    && row.getCell(0).getStringCellValue().trim()
                    .equals(testcaseName)) {

                // creating test data row . This is similar to excel row of test
                // data
                String[] testcaseDataRow = new String[lastHeadingCol-1];

                // Iterating a row till the last column
                for (int col = 1; col < lastHeadingCol; col++) {
                    // Depending on cell type we need to convert the data
                    int colType = row.getCell(col, Row.CREATE_NULL_AS_BLANK)
                            .getCellType();

                    if (colType == Cell.CELL_TYPE_NUMERIC) {
                        testcaseDataRow[col-1] = dataFormatter.formatCellValue(row
                                .getCell(col));
                    }
                    if (colType == Cell.CELL_TYPE_STRING) {
                        testcaseDataRow[col-1] = row.getCell(col)
                                .getStringCellValue();


                    }
                    if (colType == Cell.CELL_TYPE_BLANK) {
                        testcaseDataRow[col-1] = "";

                    }

                }
                // Adding each row in the list
                testcaseDataList.add(testcaseDataRow);

            }
        }

        // Creating a two dimensional array from the list
        String[][] testcaseData = new String[testcaseDataList.size()][];
        for (int i = 0; i < testcaseDataList.size(); i++) {
            testcaseData[i] = testcaseDataList.get(i);
        }

        return testcaseData;

    }

    /*
     * Gets the test data from all over the excel sheet in an Optimized way
     */
    private static List<Map<String, String>> getData_Optimized(XSSFSheet sheet,
                                                               int startFromRow, int endRow, String testcaseName) {

		/*
		 * Hold test case data as a key value pairs Each array represents a test
		 * case data row
		 */
        List<Map<String, String>> testcaseDataList = new ArrayList<Map<String, String>>();

		/*
		 * Get the column names to store it as a key value pairs
		 */
        int headingRowNum = startFromRow - 1;
        Row headingRow = sheet.getRow(headingRowNum);
        int lastHeadingCol = headingRow.getLastCellNum();
        String headings[] = new String[lastHeadingCol];

        for (int col = 0; col < lastHeadingCol; col++) {
            // Depending on cell type we need to convert the data
            int colType = headingRow.getCell(col, Row.CREATE_NULL_AS_BLANK)
                    .getCellType();

            if (colType == Cell.CELL_TYPE_NUMERIC) {
                headings[col] = Double.toString(headingRow.getCell(col)
                        .getNumericCellValue());

            }
            if (colType == Cell.CELL_TYPE_STRING) {
                headings[col] = headingRow.getCell(col).getStringCellValue();

            }
            if (colType == Cell.CELL_TYPE_BLANK) {
                headings[col] = "";

            }

        }

        // Looping from provided start Row till end of the sheet
        for (int rowNum = startFromRow; rowNum <= endRow; rowNum++) {
            Row row = sheet.getRow(rowNum);

            // Verify whether a given row is not null and equals to test case
            if (row != null
                    && row.getCell(0).getStringCellValue().trim()
                    .equals(testcaseName)) {

                // creating test data row . This is similar to excel row of test
                // data
                Map<String, String> testcaseDataRow = new HashMap<String, String>();

                // Iterating a row till the last column
                for (int col = 0; col < lastHeadingCol; col++) {
                    // Depending on cell type we need to convert the data
                    int colType = row.getCell(col, Row.CREATE_NULL_AS_BLANK)
                            .getCellType();

                    if (colType == Cell.CELL_TYPE_NUMERIC) {

                        testcaseDataRow.put(headings[col], Double.toString(row
                                .getCell(col).getNumericCellValue()));


                    }
                    if (colType == Cell.CELL_TYPE_STRING) {
                        testcaseDataRow.put(headings[col], row.getCell(col)
                                .getStringCellValue());


                    }
                    if (colType == Cell.CELL_TYPE_BLANK) {
                        testcaseDataRow.put(headings[col], " ");

                    }

                }
                // Adding each row in the list
                testcaseDataList.add(testcaseDataRow);

            }
        }
        return testcaseDataList;

    }

    /*
     * Return row number in a sheet from where the test data starts for a given
     * test case
     */
    private static int findTestCase_StartingRow(XSSFSheet sheet,
                                                String testcaseToFind) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    if (cell.getRichStringCellValue().getString().trim()
                            .equals(testcaseToFind)) {
                        return row.getRowNum();
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String args[]) throws IOException {
        getDataForTestCase("selectCarryoutStore");
        // getDataForTestCase_Optimized("tc02");

    }
}