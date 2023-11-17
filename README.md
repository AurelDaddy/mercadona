## Projet Mercadona

## Présentation

Solution back-end dans le cadre du projet de fin d'année Bachelor développeur d'application web java

## Pré-requis

Afin de pouvoir lancer le projet en local, vous devez avoir ces outils sur votre machine : 
    - [Java JDK 17](https://adoptium.net/temurin/releases/)
    - [maven](https://dlcdn.apache.org/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.zip)
    

Je vous conseil les IDEs suivants
- [IntelliJ](https://www.jetbrains.com/fr-fr/idea/download/) (pour le back)
- [Visual Studio Code](https://code.visualstudio.com/) (pour le front)


## Deploiement en local
la base de donnée utilisée est postgresql comme spécifié dans application.yml
Comme il y a des variables d'environnement, il est important de configurer spring.datasource.url,
spring.datasource.username et spring.datasource.password avec votre propre base de donnée.

Afin de deployer en local, vous devez juste installer les dependances.
Pour cela, allez à la racine du projet et lancez la commande
- ```mvn install```
puis, lancez le projet via 
- ```mvn spring-boot:run```

Une base de donnée va se charger automatiquement grâce à data.sql

Vous pouvez ajouter un utilisateur admin comme ceci ou bien utiliser celui-ci dans signin {
username: "James"
password: "123merca"
}


Vous pourrez ensuite vous authentifier et effectuer des tests sur Postman en checkant qu'il y ait bien le cookie après avoir signin:

![image](https://github.com/AurelDaddy/mercadona/assets/140730521/ea7640fd-daa2-4849-9a8a-f0cdbaf57210)


## Technologies utilisées

Spring Boot 3

## Autres infos

Ce projet peut être connecté au front ayant pour repository [frontend](https://github.com/AurelDaddy/front-end-bloc3). 

