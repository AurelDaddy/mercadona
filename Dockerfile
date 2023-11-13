#FROM maven:3.9.4-eclipse-temurin-17
#WORKDIR /app
#COPY target/demo1-0.0.1-SNAPSHOT.jar /app/demo1-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app/demo1-0.0.1-SNAPSHOT.jar"]

FROM maven:3.9.5-eclipse-temurin-17-alpine AS MAVEN_BUILD

COPY src /home/app/src
COPY pom.xml /home/app

WORKDIR /home/app

RUN mvn clean package

# execution

FROM eclipse-temurin:17-jre

COPY --from=MAVEN_BUILD /home/app/target/*.war /usr/local/lib/app.war

EXPOSE 8080

ENTRYPOINT ["java","-jar","/usr/local/lib/app.war"]