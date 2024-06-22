FROM openjdk:21
EXPOSE 8080
WORKDIR /app
COPY build/libs/cs489-final-project-0.0.1-SNAPSHOT.jar /app/final-app.jar
ENTRYPOINT ["java","-jar", "final-app.jar"]