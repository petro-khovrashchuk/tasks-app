FROM eclipse-temurin:26-jdk-jammy AS build

WORKDIR /workspace

RUN apt-get update \
    && apt-get install -y --no-install-recommends curl unzip ca-certificates \
    && rm -rf /var/lib/apt/lists/*

COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .

RUN chmod +x mvnw

COPY src/ src/

RUN ./mvnw -DskipTests clean package

FROM eclipse-temurin:26-jre-jammy AS runtime

WORKDIR /app

RUN groupadd --system spring \
    && useradd --system --create-home --uid 10001 --gid spring spring

COPY --from=build --chown=spring:spring /workspace/target/tasks-app-1.0-SNAPSHOT.jar /app/app.jar

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
