FROM public.ecr.aws/amazonlinux/amazonlinux:latest

RUN dnf update -y && dnf install -y java-21-amazon-corretto-devel

COPY /build/libs/mongo-application-1.0-SNAPSHOT-all.jar /home/ec2-user/mongo-application-1.0-SNAPSHOT-all.jar

CMD ["java", "-jar", "/home/ec2-user/mongo-application-1.0-SNAPSHOT-all.jar"]