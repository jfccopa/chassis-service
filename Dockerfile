FROM openjdk:11

COPY target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT exec java -Djava.security.egd==file:/dev/./urandom -jar /app.jar