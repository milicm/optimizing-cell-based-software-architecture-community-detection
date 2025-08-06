FROM openjdk:17-jdk-slim
WORKDIR /app_inventory
COPY app/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
