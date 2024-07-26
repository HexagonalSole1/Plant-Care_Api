FROM openjdk:17
ADD target/PlantCareApi-0.0.1-SNAPSHOT.jar PlantCare.jar
ENTRYPOINT ["java","-jar","PlantCare.jar"]