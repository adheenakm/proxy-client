FROM openjdk:17
WORKDIR /app
COPY target/ship-proxy.jar ship-proxy.jar
CMD ["java", "-jar", "ship-proxy.jar"]
EXPOSE 8080

