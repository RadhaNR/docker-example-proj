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
