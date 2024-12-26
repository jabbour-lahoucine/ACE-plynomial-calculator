# Système de calcul automatique des racines de polynomes avec factorisation symbolique

## Table des Matières

1.  [Introduction](#1-introduction)
2.  [Problématique](#2-problématique)
3.  [Objectifs du Projet](#3-objectifs-du-projet)
4.  [Solution Proposée](#4-solution-proposée)
    1.  [Calcul des Racines Numériques](#41-calcul-des-racines-numériques)
    2.  [Factorisation Symbolique](#42-factorisation-symbolique)
    3.  [Visualisation Graphique des Polynômes](#43-visualisation-graphique-des-polynômes)
    4.  [Historique des Calculs](#44-historique-des-calculs)
    5.  [Interface Utilisateur Intuitive](#45-interface-utilisateur-intuitive)
    6.  [API RESTful pour la Communication Backend-Frontend](#46-api-restful-pour-la-communication-backend-frontend)
    7.  [Gestion des Microservices](#47-gestion-des-microservices)
    8.  [Service de Découverte des Microservices (Eureka)](#48-service-de-découverte-des-microservices-eureka)
    9.  [Déploiement avec Docker](#49-déploiement-avec-docker)
5.  [Technologies Utilisées](#5-technologies-utilisées)
6.  [Structure des Microservices](#6-structure-des-microservices)
7.  [Fonctionnalités](#7-fonctionnalités)
8.  [Guide d'Installation](#8-guide-dinstallation)
    1.  [Installation et Configuration](#81-installation-et-configuration)
    2.  [Guide d'Utilisation](#82-guide-dutilisation)
9.  [Conclusion](#9-conclusion)

________________________________________

## 1. Introduction

La résolution des équations polynomiales, particulièrement celles de degré supérieur, constitue un défi pour de nombreux utilisateurs. Les méthodes comme la méthode de Newton ou la factorisation symbolique sont efficaces, mais elles nécessitent une bonne maîtrise des concepts mathématiques sous-jacents. Ce projet a pour objectif de développer un outil automatisé, facile à utiliser et accessible, permettant de résoudre ces équations, tout en intégrant des méthodes numériques et symboliques adaptées aux utilisateurs non experts.

## 2. Problématique

La résolution manuelle des équations polynomiales, en particulier celles avec des coefficients complexes ou irrationnels, est un processus long et susceptible d'erreurs. Les méthodes de résolution traditionnelles, bien que puissantes, ne sont pas toujours accessibles aux utilisateurs non spécialisés. Il est donc nécessaire de proposer un outil capable d’automatiser cette tâche, tout en garantissant des résultats précis et facilement compréhensibles.

## 3. Objectifs du Projet

*   **Résolution des Polynômes:** Développer un système capable de résoudre des équations polynomiales de n'importe quel degré.
*   **Interface Accessible:** Créer une interface utilisateur simple permettant d'entrer un polynôme et de recevoir les racines et la factorisation.
*   **Backend Robuste:** Implémenter une architecture solide avec Spring Boot pour le backend et React pour le frontend.
*   **Microservices:** Mettre en place une architecture de microservices pour une gestion modulaire et évolutive du système.
*   **Déploiement avec Docker:** Conteneuriser l'application pour une installation facile et une gestion simplifiée.

## 4. Solution Proposée

Pour répondre à ces besoins, nous proposons un système qui comprend plusieurs fonctionnalités clés :

### 4.1. Calcul des Racines Numériques

Le système calcule les racines réelles et complexes des polynômes, quel que soit leur degré. Cela inclut l’utilisation de méthodes numériques adaptées, telles que la méthode de Newton, pour obtenir des résultats précis.

### 4.2. Factorisation Symbolique

La factorisation symbolique permet de décomposer un polynôme en facteurs plus simples, facilitant ainsi la compréhension et la résolution de l’équation.

### 4.3. Visualisation Graphique des Polynômes

Le système génère un graphique représentant le polynôme entré par l'utilisateur, avec les racines affichées visuellement sur la courbe. Cela permet aux utilisateurs de mieux comprendre les résultats obtenus.

### 4.4. Historique des Calculs

Une fonctionnalité d’historique est disponible pour que l’utilisateur puisse consulter les polynômes résolus et leurs résultats passés. Cela facilite la révision et la comparaison des résultats obtenus.

### 4.5. Interface Utilisateur Intuitive

L'interface utilisateur est simple et intuitive, permettant à l'utilisateur d’entrer un polynôme, de voir les résultats des racines, de visualiser la courbe et de naviguer facilement dans l’historique des calculs.

### 4.6. API RESTful pour la Communication Backend-Frontend

L'application utilise une API RESTful pour faciliter la communication entre le frontend (React) et le backend (Spring Boot). Cela permet une gestion fluide des requêtes et une séparation claire entre les différents composants du système.

### 4.7. Gestion des Microservices

Le système est construit autour d’une architecture de microservices. Cela permet une gestion indépendante des différentes fonctionnalités telles que le calcul des racines, la factorisation, et la gestion de l’historique des calculs.

### 4.8. Service de Découverte des Microservices (Eureka)

Eureka est utilisé pour la découverte automatique des microservices, permettant à chaque service de se localiser et de communiquer avec les autres de manière fluide.

### 4.9. Déploiement avec Docker

Le système est conteneurisé avec Docker, ce qui permet un déploiement simplifié sur différents environnements. Cela garantit également la portabilité de l’application.

## 5. Technologies Utilisées

*   **Spring Boot:** Framework Java pour la création de l’API backend.
*   **React:** Bibliothèque JavaScript pour le développement de l’interface utilisateur dynamique.
*   **Maven:** Outil de gestion de projet et de dépendances pour le backend.
*   **Docker:** Outil de conteneurisation pour faciliter le déploiement et la gestion des environnements.
*   **Eureka:** Service de découverte des microservices.

## 6. Structure des Microservices

*   **coefficient-service:** Service de calcul des coefficients des polynômes.
*   **discovery-service (Eureka):** Service de découverte des microservices.
*   **factorization-service:** Responsable de la factorisation des polynômes.
*   **gateway-service:** Sert de point d'entrée pour toutes les requêtes externes.
*   **roots-service:** Service pour le calcul des racines des polynômes.
*   **polynomial-api:** API pour la gestion des polynômes.

## 7. Fonctionnalités

*   **Calcul des Racines des Polynômes:** Trouver les racines réelles et complexes des polynômes.
*   **Factorisation Symbolique:** Décomposer les polynômes pour simplifier leur résolution.
*   **Visualisation Graphique:** Générer des courbes et afficher les racines sur les graphiques.
*   **Historique des Calculs:** Sauvegarder les calculs précédents et les résultats.
*   **Interface Utilisateur:** Interface simple pour une interaction fluide avec le système.
*   **API RESTful:** Pour faciliter la communication entre le frontend et le backend.

## 8. Guide d'Installation

### 8.1. Installation et Configuration

**Docker:**

1.  Lancer tous les services

    ```
    cd ACE_microservice_project
    docker-compose up --build
    ```

### 8.2. Guide d'Utilisation

1.  Accéder à l'interface utilisateur:
    *   Ouvrir le navigateur à l'adresse: `http://localhost:3004`
2.  Ajouter un Polynôme:
    *   Saisir un polynôme dans le formulaire.
    *   Obtenir automatiquement les racines et la factorisation du polynôme.
    *   Visualiser le graphique du polynôme.
3.  Consulter l'historique des calculs:
    *   Accéder à la section historique pour revoir les résultats précédents.

## 9. Conclusion

Ce projet offre une solution pratique et accessible pour résoudre des équations polynomiales de manière automatique, tout en fournissant des outils visuels et un historique des calculs. En combinant des méthodes numériques et symboliques, il permet aux utilisateurs de résoudre des problèmes complexes sans nécessiter une expertise en mathématiques.
