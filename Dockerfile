FROM public.ecr.aws/amazonlinux/amazonlinux:latest

RUN dnf update -y && dnf install -y java-21-amazon-corretto-devel

./gradlew shadowJar

# Run the web service on container startup.
CMD ["java", "-jar", "/build/libs/mongo-application-1.0-SNAPSHOT-all.jar"]