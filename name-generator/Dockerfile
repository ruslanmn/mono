FROM amazoncorretto:21-alpine-jdk
LABEL authors="kmean"

WORKDIR /ruslanus
COPY /name-generator/target/name-generator-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -jar app.jar
EXPOSE 8080