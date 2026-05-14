FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY PORTOFOLIO/pom.xml PORTOFOLIO/pom.xml
RUN mvn -pl PORTOFOLIO dependency:go-offline
COPY PORTOFOLIO PORTOFOLIO
RUN mvn -pl PORTOFOLIO clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/PORTOFOLIO/target/portfolio-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
