FROM openjdk:17-jdk-slim
WORKDIR /app_order
COPY app/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
