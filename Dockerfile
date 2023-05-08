FROM openjdk:8
EXPOSE 9092
ADD target/aa-svc-0.0.1-SNAPSHOT.jar aa-svc-0.0.1-SNAPSHOT.jar
COPY src/main/resources/icredittechnocrats-firebase-adminsdk-5rpv9-3df4ddfc79.json /
ENTRYPOINT ["java","-Duser.timezone=UTC","-jar","/aa-svc-0.0.1-SNAPSHOT.jar"]