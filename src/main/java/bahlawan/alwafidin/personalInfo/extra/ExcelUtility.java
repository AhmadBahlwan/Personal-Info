package bahlawan.alwafidin.personalInfo.extra;

import bahlawan.alwafidin.personalInfo.entities.Member;
import bahlawan.alwafidin.personalInfo.entities.Parent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.*;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

public class ExcelUtility {

    private ExcelUtility() {};

    private static final String PATH = "C:\\Users\\Ahmed\\Downloads\\Telegram Desktop\\ملف نهائي.xlsx";
    private static final int[] HEADERS = {1, 3, 4, 5, 6, 7, 9};
    private static final int FIRST_MEMBER_INDEX = 12;
//    public void readFromExcelFile() {
//        try (FileInputStream file = new FileInputStream(PATH)) {
//            Workbook workbook = new XSSFWorkbook(file);
//
//            Sheet sheet = workbook.getSheetAt(0);
//            int i = 0;
//            for (Row row : sheet) {
//                Map<String, Object> parentInfo = getParentInfoFromExcelRow(row, i);
//
//                String json = objectMapper.writeValueAsString(parentInfo);
//
//                // Deserialize the JSON string into a Parent object
//                Parent parent = objectMapper.readValue(json, Parent.class);
//
//                i = FIRST_MEMBER_INDEX;
//
//                Set<Member> familyMembers = new HashSet<>();
//                boolean hasMembers = StringUtil.isNotBlank(row.getCell(i).getStringCellValue());
//                while (hasMembers) {
//                    Member member = createMemberFromExcelRow(row, i);
//                    i += 3;
//                    familyMembers.add(member);
//                    hasMembers = StringUtil.isNotBlank(row.getCell(i).getStringCellValue());
//                }
//                parent.setFamilyMembers(familyMembers);
//                i = 0;
//            }
//            System.out.println(i);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static Map<String, Object> getParentInfoFromExcelRow(Row row, int colmnIndex) {
        Map<String, Object> info = new HashMap<>();
        try {
            System.out.println(row.getCell(HEADERS[colmnIndex]).getStringCellValue());
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
                ? nationalNumberCell.getNumericCellValue() : ""));
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
                ? nationalNumberCell.getNumericCellValue() : ""));
        memberData.put("birthdate", row.getCell(columnIndex).getDateCellValue());

        return memberData;
    }

}
