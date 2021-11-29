#FROM node:10
#WORKDIR /ui-app
#COPY . .
#RUN npm install
#CMD ["npm","start"]

#--------------------------------
 # Builder Patteren
#--------------------------------


# UI builder 
FROM node:10 as uibuilder
WORKDIR /ui-app 
COPY ui-users/ui-demo-users .
RUN npm run build

# Spring book builder
FROM openjdk:8-jdk-alpine as bkbuilder
FROM maven
WORKDIR /bk-app
COPY bk-users/users .
RUN mvn clean
RUN mvn install
WORKDIR /target
RUN ren *.war bk-users.war

# Deployer
FROM tomcat:9.0
RUN mv /usr/local/tomcat/webapps.dist/* /usr/local/tomcat/webapps
WORKDIR /usr/local/tomcat/webapps

# copy uibuilder build folder to tomcat wabapps
RUN mkdir -p /ui-build
WORKDIR /usr/local/tomcat/webapps/ui-build
COPY --from=uibuilder /ui-app/build .

# copy bkbuilder war file to tomcat wabapps
WORKDIR /usr/local/tomcat/webapps
RUN mkdir -p /bk-build
COPY --from=bkbuilder /*.war .
COPY /bk-users/users/target/*.war .

EXPOSE 8080
CMD ["catalina.sh", "run"]


-----------------------------------------------------------
Create seperate container for UI , Backend and MongoDB
-----------------------------------------------------------
------------------------
UI
------------------------
# UI builder 
FROM node:10 as uibuilder
WORKDIR /ui-app 
COPY . .
RUN npm run build

# Deployer
FROM tomcat:9.0
RUN mv /usr/local/tomcat/webapps.dist/* /usr/local/tomcat/webapps
WORKDIR /usr/local/tomcat/webapps

# copy uibuilder build folder to tomcat wabapps
RUN mkdir -p /ui-build
WORKDIR /usr/local/tomcat/webapps/ui-build
COPY --from=uibuilder /ui-app/build .

EXPOSE 8080
CMD ["catalina.sh", "run"]


----------------------------------------------------------------------
SPRING BOOT
----------------------------------------------------------------------
FROM openjdk:latest

COPY target/*.jar bk-users.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "bk-users.jar"]


------------------------------------------------------------------------

>docker build -t radhanr211/bk-users2 . 
>docker run -it -p8080:8080 --net demo-app_default radhanr211/bk-users2 

-------------------------------------------------------------------------

