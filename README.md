# Jeu d'aventure texte - l'éditeur

Le but de ce projet est de créer une application permettant de modifier les contenus de notre jeu d'aventure texte en ligne de commandes.

## Mission 1 - Gérer les lieux

### 1. Créer/modifier/supprimer des lieux

Dans une classe `RoomController`, implémenter les méthodes permettant d'obtenir les routes suivantes:

| Méthode HTTP | URI | Description |
|---|---|---|
| `GET` | `/rooms` | Affiche la liste de tous les lieux existants. |
| `GET` | `/rooms/{id}` | Affiche les informations détaillées d'un lieu en fonction de son identifiant en base de données. |
| `GET` | `/rooms/create` | Affiche un formulaire permettant de créer un nouveau lieu. |
| `POST` | `/rooms/create` | Crée un nouveau lieu en base de données à partir des informations fournies dans le formulaire. |
| `GET` | `/rooms/{id}/edit` | Affiche un formulaire permettant de modifier un lieu existant en fonction de son identifiant en base de données. |
| `POST` | `/rooms/{id}/edit` | Modifie un lieu existant en fonction de son identifiant en base de données à partir des informations fournies dans le formulaire. |
| `POST` | `/rooms/{id}/delete` | Supprime un lieu existant en fonction de son identifiant en base de données. |

### 2. Créer/modifier/supprimer des éléments interactifs

Dans une classe `ItemController`, implémenter les méthodes permettant d'obtenir les routes suivantes:

| Méthode HTTP | URI | Description |
|---|---|---|
| `GET` | `/items` | Affiche la liste de tous les éléments interactifs existants. |
| `GET` | `/items/{id}` | Affiche les informations détaillées d'un élément interactif en fonction de son identifiant en base de données. |
| `GET` | `/items/create` | Affiche un formulaire permettant de créer un nouveau élément interactif. |
| `POST` | `/items/create` | Crée un nouveau élément interactif en base de données à partir des informations fournies dans le formulaire. |
| `GET` | `/items/{id}/edit` | Affiche un formulaire permettant de modifier un élément interactif existant en fonction de son identifiant en base de données. |
| `POST` | `/items/{id}/edit` | Modifie un élément interactif existant en fonction de son identifiant en base de données à partir des informations fournies dans le formulaire. |
| `POST` | `/items/{id}/delete` | Supprime un élément interactif existant en fonction de son identifiant en base de données. |

### BONUS

- La page permettant d'afficher les détails d'un lieu doit également afficher la liste des éléments interactifs qui s'y trouvent, et permettre d'accéder directement à chaque élément de manière individuelle.
