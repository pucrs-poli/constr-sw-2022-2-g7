FROM maven:3.8.3-openjdk-17
ENV TZ=America/Sao_Paulo
#EXPOSE 8080
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
ENTRYPOINT ["java","-jar", "/app/target/classes-ms-0.0.1-SNAPSHOT.jar"]