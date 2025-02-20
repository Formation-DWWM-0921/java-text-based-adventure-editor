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

## Mission 2 - Gérer les passages entre les lieux

<!-- TODO -->

## Mission 3 - Gérer les commandes

Implémenter les routes permettant de lire, créer, modifier et supprimer des commandes, en prêtant attention aux points suivants:

- Toutes les commandes doivent être affichées sur une même page, indépendamment de leur type, **mais** le type de chaque commande doit apparaître sur la page. Le type de chaque commande doit également apparaître dans sa page de détails.
- On doit pouvoir choisir le type d'une commande au moment de sa création, **mais** on ne doit pas pouvoir le modifier par la suite (si l'on souhaite faire cela, alors il faudra supprimer la commande existante et la recréer).
- Il faut trouver un moyen de faire en sorte que, lorsque l'utilisateur valide un formulaire de création de commande, une commande du bon type soit créée.
- Il faut trouver un moyen de faire en sorte que l'utilisateur puisse avoir accès à un formulaire particulier pour les commandes interactives (**ItemCommand**) qui ont une propriété particulière inexistante pour les autres types.

<details>
<summary>INDICE</summary>

[Afficher le nom d'une classe avec Thymeleaf](https://stackoverflow.com/questions/58052326/how-to-get-an-entity-class-name-with-thymeleaf-spring-mvc-in-html-page)
</details>

### BONUS

- Si l'utilisateur rentre un nom de commande déjà existant lors de la création ou de la modification d'une commande, il doit être redirigé sur la formulaire avec un message d'erreur explicite (au lieu d'avoir une page erreur 500).
- La liste des toutes les commandes doit faire apparaître le message par défaut des commandes interactives (**ItemCommand**).

## Mission 4 - Gérer les effets

Implémenter les routes permettant de lire, créer, modifier et supprimer des effets, en prêtant attention aux points suivants:

- Chaque page d'élément interactif (**Item**) doit afficher, pour chaque commande, la liste des effets qui lui sont associés.

<details>
<summary>EXEMPLE</summary>

# Item #8

- **Name:** lift
- **Room:** [bedroom]()
- **Visible:** Yes

## Effects

### On command "use"

| # | Type | Arguments | Edit | Delete |
|---|---|---|---|---|
| 5 | Message | "You call the lift." | [Edit]() | [Delete]() |
| 6 | Change Room | [attic]() | [Edit]() | [Delete]() |

### On command "open"

> No programmed effects.

### On command "talk to"

> No programmed effects.

### On command "pick up"

> No programmed effects.

</details>

- Chaque section correspondant à une commande, et indiquant la liste des effets lorsque cette commande est utilisée sur l'élément interactif, doit contenir un bouton "créer un nouvel effet" qui amène sur un formulaire permettant de rentrer les détails de l'effet (par exemple, pour un **MessageEffect**, le message à afficher).
- L'ordre d'exécution des effets doit toujours rester cohérent (lorsqu'un nouvel effet est créé, il doit avoir une nouvelle valeur d'ordre, lorsqu'un effet est supprimé, il faut réordonner les autres effets).

### BONUS

- Lors de l'ajout d'un nouvel effet, l'élément interactif et la commande concernés ne doivent **pas** être sélectionnables manuellement. Ils doivent être déterminés automatiquement en fonction de la page d'origine.
- Il doit être possible de changer l'ordre d'exécution des effets. L'ordre d'exécution doit toujours rester cohérent.
