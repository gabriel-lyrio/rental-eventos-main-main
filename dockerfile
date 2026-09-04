FROM eclipse-temurin:25-jdk AS build

WORKDIR /app

COPY rental-eventos-main-main/renatal_eventos/pom.xml .
COPY rental-eventos-main-main/renatal_eventos/.mvn ./.mvn
COPY rental-eventos-main-main/renatal_eventos/mvnw .

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline -DskipTests

COPY rental-eventos-main-main/renatal_eventos/src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]