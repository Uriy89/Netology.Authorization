FROM mediasol/openjdk17-slim-jprofiler

EXPOSE 8080

COPY target/authorizationService-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]