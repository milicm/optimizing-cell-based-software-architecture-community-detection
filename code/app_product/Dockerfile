FROM openjdk:17-jdk-slim
WORKDIR /app_product
COPY app/*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
