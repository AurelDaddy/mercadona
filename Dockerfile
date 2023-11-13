FROM maven:3.9.4-eclipse-temurin-17
COPY . .
EXPOSE 8080
ENTRYPOINT ("java","-jar","./target/demo1-0.0.1-SNAPSHOT.jar")

