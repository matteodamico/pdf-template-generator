package it.mattdmc.pdfcreator.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;


import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;

@Service
public class PdfcreatorImpl implements Pdfcreator {
    private final Path templatePath = Paths.get("tmp");
    private final String prefixFilledFilename = "filled-";

    @Override
    public Resource fill(MultipartFile pdf, Map<String, String> params) {
        try {
            if (Files.exists(templatePath))
                deleteDirectoryStream(templatePath);
            else
                Files.createDirectory(templatePath);
            Files.copy(pdf.getInputStream(), this.templatePath.resolve(pdf.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

        try {
            PDDocument pDDocument = PDDocument.load(new File(this.templatePath.resolve(pdf.getOriginalFilename()).toUri()));
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            PDField field;
            for (String paramName : params.keySet()) {
                field = pDAcroForm.getField(paramName);
                if (field != null)
                    field.setValue(params.get(paramName));
            }
            pDDocument.save(this.templatePath.resolve(prefixFilledFilename + pdf.getOriginalFilename()).toString());
            pDDocument.close();
            return new UrlResource(this.templatePath.resolve(prefixFilledFilename + pdf.getOriginalFilename()).toUri());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    void deleteDirectoryStream(Path path) throws IOException {
        Files.walk(path)
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
    }


}
