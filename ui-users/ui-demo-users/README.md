docker compose -mongo, mongo-express

services: 
  mongodb: 
    environment: 
      - MONGO_INITDB_ROOT_USERNAME=root
      - MONGO_INITDB_ROOT_PASSWORD=root
    image: mongo
    ports: 
      - "27017:27017"
  mongo-client: 
    environment: 
      - "ME_CONFIG_MONGODB_ADMINUSERNAME=root"
      - "ME_CONFIG_MONGODB_ADMINPASSWORD=root"
      - "ME_CONFIG_MONGODB_PORT=27017"
      - "ME_CONFIG_MONGODB_SERVER=mongodb"
    image: mongo-express
    ports: 
      - "8080:8081"
    depends_on: 
      - "mongodb"
    restart: unless-stopped
version: "3"

-------------------------------
ap1:

FROM node:10

WORKDIR /ui-app

COPY . .

RUN npm install


CMD ["npm","start"]
-----------------------------
above approch is not feasle becuase of size, so we need to chekc for 2nd approach



ap2: