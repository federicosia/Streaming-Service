FROM eclipse-temurin:19
ARG JAR_FILE=target/*.jar
ARG APP_PROP=src/main/resources/*.properties

COPY ${JAR_FILE} app.jar
COPY ${APP_PROP} app.properties

EXPOSE 8079
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.config.location=app.properties"]