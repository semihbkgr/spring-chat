FROM openjdk:17-alpine
WORKDIR /spring-chat
COPY . .
RUN ./mvnw package -Dbroker=redis
ENTRYPOINT ["java","-jar","./target/spring-chat.jar"]
