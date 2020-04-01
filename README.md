# Pizza-Pizza Server

## Overview  
This server was generated by the [swagger-codegen](https://github.com/swagger-api/swagger-codegen) project.  
By using the [OpenAPI-Spec](https://github.com/swagger-api/swagger-core), you can easily generate a server stub.  
This is an example of building a swagger-enabled server in Java using the SpringBoot framework.  

The underlying library integrating swagger to SpringBoot is [springfox](https://github.com/springfox/springfox)  

Start your server as an simple java application  

Change default port value in application.properties

## Setup instructions
1. Create a new heroku app. 
    1. Go to [Heroku website](https://dashboard.heroku.com/apps), create or sign in with your developer account.
    2. On the [dashboard page](https://dashboard.heroku.com/apps), click the 'New' button on the top-right corner and create a new application
    3. Input the necessary information and follow the Heroku guide to finish creating new application.
2. Integrate with MongoDB.
    1. From the Heroku [dashboard page](https://dashboard.heroku.com/apps), click the new application just created.
    2. Click the "Resources" tab. On the "Add-ons" section, input "MongoDB" and chose the "mLab MongoDB". 
    3. Chose the "Sandbox-Free" one, and click "Provision".
    4. Now the MongoDB is ready. For detail, please refer to our [integration menu](https://drive.google.com/file/d/1t-NGswKJuKyeh8JcYwNaUFvojBf1FSiW/view?usp=sharing).
3. Push and Deploy.
    1. From the Heroku [dashboard page](https://dashboard.heroku.com/apps), click the application name.
    2. Click the "Deploy" tab.
    3. On the section "Deployment method", choose "Heroku Git"
    4. On the section "Deploy using Heroku Git", follow the commands. 
4. Or you can just connect the github repository to Heroku to deploy

## Live Heroku cloud instance
https://haiqingpizza.herokuapp.com/haiqingpizza/swagger-ui.html


## Local Develop tips
1. Enter spring-server-generated folder and build as command: `mvn install`
2. Run and deploy to local host: `mvn spring-boot:run`
3. Play: Go to http://localhost:9080/haiqingpizza/
