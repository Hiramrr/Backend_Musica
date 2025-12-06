FROM rrojano/spring-boot
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

CMD ["java", "-jar", "target/BackendMusica-0.0.1-SNAPSHOT.jar"]
