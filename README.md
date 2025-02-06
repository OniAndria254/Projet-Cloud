# Guide d'utilisation du projet Docker

## Introduction
Ce projet utilise Docker pour exécuter une application Laravel et une application Spring Boot avec PostgreSQL comme base de données.

## Services Docker
Le projet comprend les services suivants :

1. **Laravel (laravel_app)** :
   - Conteneur exécutant l'application Laravel.
   - Utilise Apache comme serveur web.
   - Se connecte à PostgreSQL via le service `db`.
   - Effectue automatiquement les migrations et le peuplement de la base de données au démarrage.

2. **Spring Boot (spring_app)** :
   - Conteneur exécutant l'application Spring Boot.
   - Se connecte à PostgreSQL via le service `db`.
   - Communique avec l'API Laravel via `http://laravel_app:80`.

3. **PostgreSQL (db)** :
   - Conteneur de base de données PostgreSQL 16.
   - Stocke les données dans un volume persistant `pgdata`.

## Configuration et Lancement

### 1. Construire et démarrer les conteneurs
Dans le terminal, exécutez la commande suivante :
```sh
docker-compose up --build
```
Cela va :
- Construire les images Docker si nécessaire.
- Démarrer les services en arrière-plan.

### 2. Accéder aux applications
- **Spring Boot** : `http://localhost:8081/`
- **Laravel** (via l'API Spring Boot) : `http://laravel_app`

### 3. Base de données PostgreSQL
- Hôte : `db`
- Port interne : `5432`
- Port accessible depuis l'hôte : `5433`
- Utilisateur : `postgres`
- Mot de passe : `secret`
- Nom de la base de données : `cloud`

## Exigences pour l'inscription
Lors de l'inscription, le mot de passe doit contenir **au moins 6 caractères**.

## Commandes utiles
- **Arrêter les conteneurs** :
  ```sh
  docker-compose down -v
  ```
- **Vérifier les logs d'un service** :
  ```sh
  docker logs -f laravel_app
  ```
- **Se connecter au conteneur Laravel** :
  ```sh
  docker exec -it laravel_app bash
  ```
- **Se connecter au conteneur PostgreSQL** :
  ```sh
  docker exec -it laravel_db psql -U postgres -d cloud
  ```

