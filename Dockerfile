FROM openjdk:8-jdk-alpine
MAINTAINER slazhy

RUN apk add --no-cache bash
ADD ./target/*.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["/usr/bin/java"]
CMD ["-Dspring.profiles.active=docker", "-jar", "/app.jar"]
EXPOSE 14000