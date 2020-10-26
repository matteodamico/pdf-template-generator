# PDF template generator
This is a Java Spring-boot application that exposes one REST microservice to fill information within Pdf file.

-----------
### REQUIREMENTS
- JDK installed to compile the sourcecode
- Maven
-----------

### Usage
1. Create your pdf form file with your preferred editor (i.e. LibreOffice Draw).
**Note** Save each textbox obj with a properly name to use it later.
2. Run the spring-boot application.
3. Send the pdf template file with a POST request to http://localhost:8080/pdfcreate adding each property that you want fill within the document as request parameters.

Example:
http://localhost:8080/pdfcreate?txt_1=abc&txt_2=def

or use the cURL command:

`$curl --location --request POST 'http://localhost:8080/pdfcreate?txt_1=abc&txt_2=def' \
--form 'file=@/home/matt/Projects/template/pdf-template.pdf' -o my_pdf_file.pdf`

**NOTE** this application is currently not scalable due to its own approach to delete temporary folder before process a new request.


### Easter egg
http://127.0.0.1:8080/hello

http://127.0.0.1:8080/hello?name=YOURNAME

-----------


