FROM openjdk:17
ARG JAR_FILE=.jar
COPY ${JAR_FILE} rat-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "rat-0.0.1-SNAPSHOT.jar"]
