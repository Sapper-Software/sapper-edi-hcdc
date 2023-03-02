FROM gradle:7.5.1 AS BUILD
WORKDIR /usr/app/
COPY . . 
COPY ./gradle.properties /.gradle/
# RUN apt-get update
# RUN apt-get install protobuf-compiler -y
RUN chmod +x ./gradlew
RUN gradle wrapper 
# RUN gradle -q projects
RUN gradle build -x test
