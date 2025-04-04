FROM maven:3.9.9 AS build

WORKDIR /app
COPY src ./src
COPY pom.xml .

RUN mvn clean package

FROM tomcat:10.1

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
