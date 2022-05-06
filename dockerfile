FROM openjdk:17-alpine
COPY ./. ./spring-chat
WORKDIR ./spring-chat
RUN ./mvnw package
ENTRYPOINT ["java","-jar","./target/spring-chat.jar"]
