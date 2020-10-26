package it.mattdmc.pdfcreator.controller;

import it.mattdmc.pdfcreator.message.ResponseMessage;
import it.mattdmc.pdfcreator.service.Pdfcreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class PdfController {
    @Autowired
    Pdfcreator pdfcreator;

    private static final String text = "Ciao %s, tu sei speciale";

    @GetMapping("/hello")
    public String sayHi(@RequestParam(value = "name", defaultValue = "Ragazzo") String name) {
        return String.format(text, name);
    }

    @PostMapping("/pdfcreate")
    public ResponseEntity<?> pdffiller(@RequestParam("file") MultipartFile pdftemplate,@RequestParam Map<String,String> allRequestParams) {
        String message = "";
        try {
            Resource file = pdfcreator.fill(pdftemplate,allRequestParams);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (Exception e) {
            message = "Could not upload the file: " + pdftemplate.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }


    }

}