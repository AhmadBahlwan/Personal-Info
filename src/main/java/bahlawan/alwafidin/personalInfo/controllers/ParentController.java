package bahlawan.alwafidin.personalInfo.controllers;

import bahlawan.alwafidin.personalInfo.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class ParentController {

    @Autowired
    ParentService parentService;

    @PostMapping("/add-info-from-file")
    public ResponseEntity<?> addInfoFromFile(@RequestPart MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        parentService.createParentFromExcelFile(inputStream);

        return ResponseEntity.ok("Done");
    }


}
