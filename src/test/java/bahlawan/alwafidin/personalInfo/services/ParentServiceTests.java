package bahlawan.alwafidin.personalInfo.services;

import bahlawan.alwafidin.personalInfo.repositories.ParentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileInputStream;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ParentServiceTests {

    @MockBean
    ParentRepository repo;

    @InjectMocks
    ParentService service;

    @Mock
    private ObjectMapper objectMapper;

    private static final String PATH = "C:\\Users\\Ahmed\\Downloads\\Telegram Desktop\\ملف نهائي.xlsx";
    private  static final int SHEET_INDEX = 0;
    private static final int ROW_INDEX = 2;

    @Test
    public void readParentsInfoFroExcelFileTest() {

        try (FileInputStream file = new FileInputStream(PATH)) {
            service.createParentFromExcelFile(file);
            // Verify that saveAll was called with a list of parents
            verify(repo).saveAll(anyList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
