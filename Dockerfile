FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/stats-0.0.1-SNAPSHOT.jar
WORKDIR /project
COPY ${JAR_FILE} /project/app.jar
ENTRYPOINT ["java","-jar","/project/app.jar"]