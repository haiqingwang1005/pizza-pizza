# Pizza Pizza Server

## Team Member
* Lu Bao, github username: lubao, email: bao.lu1@husky.neu.edu
* Fan Fan, github username: ffhere, email: fan.fa@husky.neu.edu
* Haiqing Wang, github username: hwang1005, email: wang.haiq@husky.neu.edu

## Overview  
This server was generated by the [swagger-codegen](https://github.com/swagger-api/swagger-codegen) project.  
By using the [OpenAPI-Spec](https://github.com/swagger-api/swagger-core), you can easily generate a server stub.  
This is an example of building a swagger-enabled server in Java using the SpringBoot framework.  

The underlying library integrating swagger to SpringBoot is [springfox](https://github.com/springfox/springfox)  

Start your server as an simple java application  

You can view the api documentation in swagger-ui by pointing to  
http://localhost:8080/  

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

## Run test
1. Enter spring-server-generated folder and run command: `mvn test`

## Live Heroku cloud instance
https://cs5500pizza.herokuapp.com/cs5500/Pizza_Pizza/1.0.0/swagger-ui.html

## Link to supporting documents
1. Final project report: https://docs.google.com/document/d/1-QAWLcwZAPoLt1BdlnGit397xVZk8L9QGixyXlEDK78/edit?usp=sharing
2. Design doc: https://docs.google.com/document/d/1jfNpP0dIn1G8h2Zbwp45uCUYdaccwqIHluzIr3Zfe9M/edit?usp=sharing

## Link to final presentation
1. Presentation video on Youtube: https://youtu.be/quO5xw2XDf8
2. Presentation slides: https://drive.google.com/file/d/1z4Hk2ow8PU9HdCdgoGL4OlhY06Fjed4i/view?usp=sharing

## Link to front-end UI
1. Our website: https://cs5500pizza-pizza.glitch.me/
2. Our design doc: https://docs.google.com/document/d/1ASQeTQnEATPlP_PrO4RLNEgMoTXEhYU5zS5rZ0pBoMg/edit?usp=sharing

## Local Develop tips
1. Enter spring-server-generated folder and build as command: `mvn install`
2. Run and deploy to local host: `mvn spring-boot:run`
3. Play: Go to http://localhost:8080/cs5500/Pizza_Pizza/1.0.0/


# API documentation
Please check https://cs5500pizza.herokuapp.com/cs5500/Pizza_Pizza/1.0.0/swagger-ui.html#/
