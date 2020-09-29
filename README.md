# pdf-template-generator
A Java containerized application to fill out your own PDF form.

### Introduction
Pdf-template-generator populate a PDF document with Java using the PDFBox library. 
It is containerized so you can run it directly.
The output will be generated in the export folder.
To create your own template use LibreOffice - Draw that should be already present in Ubuntu distribution.

### Usage

1.Create the docker image with 

`$docker build -t pdfgenerator . `

2.Run the container with:

`$docker run -v $(pwd)/export:/export pdfcreator `

3.See the pdf file in the export folder in your local file system
