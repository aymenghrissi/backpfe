FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD backcnstn1/target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]
