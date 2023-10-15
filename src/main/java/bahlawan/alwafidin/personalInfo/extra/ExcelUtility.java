package bahlawan.alwafidin.personalInfo.extra;

import org.apache.poi.ss.usermodel.*;

import java.util.*;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

public class ExcelUtility {

    private ExcelUtility() {};

    private static final String PATH = "C:\\Users\\Ahmed\\Downloads\\Telegram Desktop\\ملف نهائي.xlsx";
    private static final int[] HEADERS = {1, 3, 4, 5, 6, 7, 9};
    public static Map<String, Object> getParentInfoFromExcelRow(Row row, int colmnIndex) {
        Map<String, Object> info = new HashMap<>();
        try {
            info.put("firstName", row.getCell(HEADERS[colmnIndex]).getStringCellValue().split(" ")[0]);
            info.put("middleName", row.getCell(HEADERS[colmnIndex]).getStringCellValue().split(" ")[1]);
            info.put("lastName", row.getCell(HEADERS[colmnIndex]).getStringCellValue().split(" ")[2]);
        }catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        colmnIndex++;
        info.put("birthdate", row.getCell(HEADERS[colmnIndex++]).getDateCellValue());
        Cell nationalNumberCell = row.getCell(HEADERS[colmnIndex++], CREATE_NULL_AS_BLANK);
        info.put("nationalNumber", String.valueOf(nationalNumberCell.getCellType().equals(CellType.BLANK)
                ? "" : (long) nationalNumberCell.getNumericCellValue()));
        System.out.println(info.get("nationalNumber"));
        info.put("familyBookId", String.valueOf((int) row.getCell(HEADERS[colmnIndex++], CREATE_NULL_AS_BLANK).getNumericCellValue()));
        info.put("phoneNumber", String.valueOf((int) row.getCell(HEADERS[colmnIndex++]).getNumericCellValue()));
        info.put("alternatePhoneNumber", String.valueOf((int) row.getCell(HEADERS[colmnIndex++]).getNumericCellValue()));
        info.put("address", row.getCell(HEADERS[colmnIndex]).getStringCellValue());
        info.put("familyMembers", new HashSet<>());

        return info;
    }


    public static Map<String, Object> getMemberInfoFromExcelRow(Row row, int columnIndex) {
        Map<String, Object> memberData = new HashMap<>();
        //System.out.println(row.getCell(columnIndex).getStringCellValue());
        try {
            memberData.put("firstName", row.getCell(columnIndex).getStringCellValue().split(" ")[0]);
            memberData.put("middleName", row.getCell(columnIndex).getStringCellValue().split(" ")[1]);
            memberData.put("lastName", row.getCell(columnIndex).getStringCellValue().split(" ")[2]);
        }catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        columnIndex++;
        Cell nationalNumberCell = row.getCell(columnIndex++, CREATE_NULL_AS_BLANK);
        memberData.put("nationalNumber", String.valueOf(nationalNumberCell.getCellType().equals(CellType.BLANK)
                ? "" : (long) nationalNumberCell.getNumericCellValue()));
        memberData.put("birthdate", row.getCell(columnIndex).getDateCellValue());
        memberData.put("isParent", false);

        return memberData;
    }

}
