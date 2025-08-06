FROM openjdk:17-jdk-slim
WORKDIR /app_payment
COPY app/*.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]
