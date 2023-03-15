FROM openjdk:8
EXPOSE 9092
ADD target/aa-svc-0.0.1-SNAPSHOT.jar aa-svc-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Duser.timezone=UTC","-jar","/aa-svc-0.0.1-SNAPSHOT.jar"]