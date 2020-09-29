package it.mattdmc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class App {
    public static void main(String[] args) {
        try {
            PDDocument pDDocument = PDDocument.load(new File("/template/pdf-template.pdf"));
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            List<Property> items = getItems();

            PDField field;
            for (Property item: items){
                field = pDAcroForm.getField(item.getName());
                field.setValue(item.getValue());
            }                  
            pDDocument.save("/export/pdf-java-output.pdf");
            pDDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Property> getItems() {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream("/template/pdf-properties.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String strLine;
        ArrayList<Property> items = new ArrayList<Property>();
        Property prop;
        try {
            while ((strLine = reader.readLine()) != null) {
                prop=new Property();
                int index=strLine.indexOf("=");
                String name= strLine.substring(0,index);
                String value= strLine.substring(index+1);
                prop.setName(name);
                prop.setValue(value);
                items.add(prop);       
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
    
}
