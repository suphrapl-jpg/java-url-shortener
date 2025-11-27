
FROM eclipse-temurin:17-jdk
COPY target/urlshortener-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","/app.jar"]
