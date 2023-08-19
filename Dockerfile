FROM adoptopenjdk/openjdk11:jre-11.0.12_7-alpine
ARG JAR_FILE=./target/gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /usr/app/gateway.jar
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","gateway.jar"]