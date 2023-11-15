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

Afin de deployer en local, vous devez juste installer les dependances.
Pour cela, allez à la racine du projet et lancez la commande
- ```mvn install```
puis, lancez le projet via 
- ```mvn spring-boot:run```

## Technologies utilisées

Ce projet Spring Boot 3

## Autres infos

Ce projet peut être connecté au front ayant pour repository [frontend](https://github.com/AurelDaddy/front-end-bloc3). 
La base de données est public pour le moment, mais vous pouvez switcher sur une base de données en local postgreSql (voir application.properties)

