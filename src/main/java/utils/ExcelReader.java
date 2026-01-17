package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static Object[][] readSheet(String filePath, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheet(sheetName);

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getLastCellNum();

        List<Object[]> data = new ArrayList<Object[]>();

        for (int r = 1; r < rows; r++) {
            Row row = sheet.getRow(r);
            Object[] rowData = new Object[cols];

            for (int c = 0; c < cols; c++) {
                Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String cellValue = "";

                switch (cell.getCellType()) {
                    case STRING:
                        cellValue = cell.getStringCellValue();
                        break;

                    case NUMERIC:
                        // handle numeric values, including integers or doubles
                        double numVal = cell.getNumericCellValue();
                        if (numVal == (long) numVal) {
                            cellValue = String.valueOf((long) numVal);
                        } else {
                            cellValue = String.valueOf(numVal);
                        }
                        break;

                    case BOOLEAN:
                        cellValue = String.valueOf(cell.getBooleanCellValue());
                        break;

                    case FORMULA:
                        // Evaluate formula result as string
                        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
                        CellValue evaluatedValue = evaluator.evaluate(cell);
                        if (evaluatedValue != null) {
                            switch (evaluatedValue.getCellType()) {
                                case STRING:
                                    cellValue = evaluatedValue.getStringValue();
                                    break;
                                case NUMERIC:
                                    cellValue = String.valueOf(evaluatedValue.getNumberValue());
                                    break;
                                case BOOLEAN:
                                    cellValue = String.valueOf(evaluatedValue.getBooleanValue());
                                    break;
                                default:
                                    cellValue = "";
                            }
                        }
                        break;

                    default:
                        cellValue = cell.toString();
                        break;
                }

                rowData[c] = cellValue;
            }

            data.add(rowData);
        }

        wb.close();
        fis.close();

        Object[][] arrayData = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            arrayData[i] = data.get(i);
        }
        return arrayData;
    }
}
