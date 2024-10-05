FROM openjdk:17

RUN mkdir app

ADD ./target/cryptography_challenge-1.0.0.jar cryptography_challenge.jar

COPY ./target/cryptography_challenge-1.0.0.jar /app/cryptography_challenge.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "/cryptography_challenge.jar"]

EXPOSE 8080