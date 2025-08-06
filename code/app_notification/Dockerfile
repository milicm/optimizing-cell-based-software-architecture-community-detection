FROM openjdk:17-jdk-slim
WORKDIR /app_notification
COPY app/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
