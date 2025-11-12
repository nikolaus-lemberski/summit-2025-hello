# Build
FROM --platform=linux/amd64 registry.access.redhat.com/ubi9/openjdk-21 as builder

COPY pom.xml .
COPY src src

RUN mvn -B package

# Run
FROM --platform=linux/amd64 registry.access.redhat.com/ubi9/openjdk-21-runtime

COPY --from=builder /home/default/target/summit-hello-1.0.0-SNAPSHOT-runner.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "summit-hello-1.0.0-SNAPSHOT-runner.jar"]