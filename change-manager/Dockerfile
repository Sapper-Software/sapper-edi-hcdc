# FROM gradle:7.5.1 AS BUILD
# WORKDIR /usr/app/
# COPY . . 
# RUN chmod +x ./gradlew
# RUN gradle wrapper 
# RUN gradle build -x test

# FROM adoptopenjdk/openjdk11:alpine-jre
# WORKDIR /var
# EXPOSE 80
# COPY --from=BUILD usr/app/build/libs/sapper-discovery-service-0.0.1-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 80
ARG JAR_FILE=./build/libs/change-manager-1.4-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
