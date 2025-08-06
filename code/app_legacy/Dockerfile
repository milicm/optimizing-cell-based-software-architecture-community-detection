FROM openjdk:17-jdk-slim
WORKDIR /app_legacy
COPY app/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
