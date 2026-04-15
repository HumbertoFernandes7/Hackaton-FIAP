FROM openjdk:26-ea-jdk-bookworm AS build

WORKDIR /app


RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:26-ea-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar application.jar

ENTRYPOINT ["java", "--enable-preview", "-jar", "application.jar"]