FROM openjdk:8-jdk-alpine

ARG JAR_FILE

ARG WAIT_FOR_APP

ENV WAIT_FOR_APP=${WAIT_FOR_APP}

COPY wait-for wait-for

RUN chmod 755 wait-for

COPY ${JAR_FILE} app.jar

ENTRYPOINT ./wait-for $WAIT_FOR_APP -t 3600 -- java -jar /app.jar --spring.profiles.include=docker
