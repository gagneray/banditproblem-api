FROM maven:3.8-adoptopenjdk-11 AS build
# clone dependency from git and install
RUN apt-get update && apt-get install git
RUN mkdir /usr/src/lib
WORKDIR /usr/src/lib
RUN git clone git://github.com/gagneray/reinforcement-learning.git
RUN mvn install -DskipTests -f ./reinforcement-learning/pom.xml

# build banditproblem-api
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests
# build docker image with jar package et set entrypoint
FROM adoptopenjdk/openjdk11:alpine-jre
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=build /project/target/banditproblem-api-*.jar /app/app.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
ENTRYPOINT ["java","-jar","./app.jar"]
