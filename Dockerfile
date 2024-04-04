FROM openjdk:21 as build
WORKDIR /app
COPY . ./
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM openjdk:21
WORKDIR /app
COPY --from=build /app/target/crud-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "crud-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
