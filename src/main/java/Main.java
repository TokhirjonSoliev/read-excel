import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<EmployeeSalary> salaryList = new ArrayList<>();
        Set<String> set = new HashSet<>();
        try {
            FileInputStream file = new FileInputStream("src/main/resources/salary.xlsx");
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            int skip = 0;
            for (Row row : sheet) {
                skip++;
                if (skip == 1) continue;
                DataFormatter dataFormatter = new DataFormatter();
                Cell cell1 = row.getCell(0);
                Cell cell2 = row.getCell(1);
                Cell cell3 = row.getCell(2);
                String fCV1 = dataFormatter.formatCellValue(cell1);
                String fCV2 = dataFormatter.formatCellValue(cell2);
                String fCV3 = dataFormatter.formatCellValue(cell3);
                if (fCV1.trim().length() == 0 && fCV2.trim().length() == 0) {
                    continue;
                }
                String[] str = fCV3.trim().split(",");

                if (fCV2.trim().length() != 20) {
                    throw new RuntimeException("Some account number is invalid!");
                }
                if (fCV3.trim().length() == 0 || str.length > 2 || (str.length == 2 && str[1].length() > 2)) {
                    System.out.println(fCV3);
                    throw new RuntimeException("salary is not in the form!");
                }

                String salaryAmount = "";
                if (str.length == 2 && str[1].length() == 2) {
                    salaryAmount = str[0] + str[1];
                } else if (str.length == 2 && str[1].length() == 1) {
                    salaryAmount = str[0] + str[1] + "0";
                } else {
                    salaryAmount = str[0] + "00";
                }

                if (Long.parseLong(salaryAmount) > Integer.MAX_VALUE) {
                    throw new RuntimeException("Salary in the list is higher than max!");
                }
                int amount = Integer.parseInt(salaryAmount);
                salaryList.add(new EmployeeSalary(fCV1, fCV2, amount));
            }
            for (EmployeeSalary employeeSalary : salaryList) {
                set.add(employeeSalary.getAccountNumberCard());
            }
            if (salaryList.size() != set.size()) {
                throw new RuntimeException("Someone's accountNumberCard used more than one!");
            } else {
                for (EmployeeSalary employeeSalary : salaryList) {
                    System.out.println(employeeSalary);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
