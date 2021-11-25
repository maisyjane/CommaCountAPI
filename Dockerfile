FROM openjdk:16
ADD target/comma_counter_-0.0.1-SNAPSHOT.jar comma_counter_-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","comma_counter_-0.0.1-SNAPSHOT.jar"]