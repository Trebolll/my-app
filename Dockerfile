FROM openjdk:17-jdk-slim
MAINTAINER MorevM.V
WORKDIR /app
COPY target/my-app-1.0-SNAPSHOT.jar app.jar
EXPOSE 8180
EXPOSE 5005
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
ENTRYPOINT ["java", "-jar", "app.jar"]
