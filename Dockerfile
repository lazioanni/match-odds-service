FROM amazoncorretto:17

WORKDIR /app

COPY target/match-service-0.0.1-SNAPSHOT.jar /app/match-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/match-service.jar"]