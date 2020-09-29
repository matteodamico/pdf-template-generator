FROM maven:3.6-jdk-11-slim AS build
COPY src /usr/src/pdf-template-generator/src
COPY pom.xml /usr/src/pdf-template-generator
RUN mvn -f /usr/src/pdf-template-generator/pom.xml clean package

FROM openjdk:11
RUN mkdir /export
RUN mkdir /template
COPY pdf-template.pdf /template
COPY --from=build /usr/src/pdf-template-generator/target/pdf-template-generator-jar-with-dependencies.jar /usr/pdf-template-generator/pdf-template-generator.jar

VOLUME /export


ENTRYPOINT ["java", "-jar", "/usr/pdf-template-generator/pdf-template-generator.jar"]