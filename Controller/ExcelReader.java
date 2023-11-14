package Controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Users.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
    public enum ROLE {
        STAFF, STUDENT
    };

    public static ArrayList<User> readExcel(String filepath, ROLE role) {
        try {
            ArrayList<User> allUsers = new ArrayList<>();
            FileInputStream fileInputStream = new FileInputStream(new File(filepath));
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            workbook.close();
            int i = 0;
            HashMap<Integer, ArrayList<String>> data = new HashMap<>();
            boolean firstRow = true;
            for (Row row : sheet) {
                if (firstRow) {
                    firstRow = false;
                    continue;
                }
                ArrayList<String> arr = new ArrayList<>();
                boolean isEmptyRow = true;
                for (Cell cell : row) {
                    String cellValue = cell.getStringCellValue().trim();
                    if (cellValue.contains("@") && cellValue.endsWith(";")) {
                        cellValue = cellValue.substring(0, cellValue.length() - 1);
                    }
                    arr.add(cellValue.toUpperCase());
                    if (!cellValue.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
                if (!isEmptyRow) {
                    data.put(i, arr);
                    i++;
                }
            }
            for (Map.Entry<Integer, ArrayList<String>> entry : data.entrySet()) {
                int rowNum = entry.getKey();
                ArrayList<String> rowData = entry.getValue();
                System.out.print("Row " + rowNum + ": ");
                for (String value : rowData) {
                    System.out.print(value + " | ");
                }
                System.out.println();
            }
            for (Map.Entry<Integer, ArrayList<String>> entry : data.entrySet()) {
                ArrayList<String> arrayRef = entry.getValue();
                User temp;
                if (role == ROLE.STAFF) {
                    temp = new Staff(arrayRef.get(0), arrayRef.get(1), Faculty.getFacultyType(arrayRef.get(2)));
                } else {
                    temp = new Student(arrayRef.get(0), arrayRef.get(1), Faculty.getFacultyType(arrayRef.get(2)));
                }
                allUsers.add(temp);
            }
            fileInputStream.close();
            return allUsers;
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }
    }

}
