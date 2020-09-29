# pdf-template-generator
A Java containerized application to fill out your own PDF form.

### Introduction
Pdf-template-generator populate a PDF document with Java using the PDFBox library. 
It is containerized so you can run it directly.
The output will be generated in the export folder.
To create your own template use LibreOffice - Draw that should be already present in Ubuntu distribution.


### Usage

1. Create your pdf form file with your preferred editor (i.e. LibreOffice Draw). 

   Store it in the template folder.
   
   **Note** Save each textbox obj with a properly name to use it later.

2. Fill the *properties.txt* file using the following schema:

    `txt_1= text to display in the form`

   where `txt_1` is the name of textbox present in the pdf-template file.

3. Create the docker image with 

   `$docker build -t pdfgenerator . `

4. Run the container with:

   `$docker run -v $(pwd)/export:/export pdfcreator `


5. See the pdf file in the export folder in your local file system
