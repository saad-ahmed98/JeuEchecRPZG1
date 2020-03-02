Ceci est un jeu d'échecs en lignes de commande devoloppé par le groupe RPZG1.<br>
Le jeu en soit est en anglais mais le code et les commentaires sont en français.<br>
Aucun code a servi d'inspiration pour ce projet.

## Démarrer le jeu

Pour pouvoir joueur au jeu, il faut suivre les étapes suivantes :

1) Ouvrir le projet sous un compilateur Java (Eclipse récommendé).
2) Aller dans la classe Partie.java et compiler.

Le menu principal va ainsi s'afficher et l'utilisateur sera confronté au choix entre 3 options : Commencer une nouvelle partie, régarder les credits ou quitter l'application.

## Comment joueur une partie

En saisissant 1 au clavier, une nouvelle partie sera demarré entre deux joueurs:

- Aucune IA est disponible.
- L'échequier s'affichera au debut de la partie et à la fin de chaque tour d'un joueur.
- Les pièces sont symbolisées par un ID sur l'échequier suivi du numéro du joueur auquel elles appartiennent (1 pour joueur 1 et 2 pour joueur 2).

| Symbole | Correspondance |
| ------- | -------------- |
|  P      | Pion           |
|  F      | Fou            |
|  C      | Cavalier       |
|  D      | Dame/Reine     |
|  R      | Roi            |
|  T      | Tour           |

- Chaque joueur saisi les coordonnées de la case de la pièce qu'il veut bouger et ensuite celles de la case de destination.
- Tout mouvement incorrecte ou saisie invalide forcent le joueur à refaire la saisie.
- Chaque mouvement sera notifié sur l'affichage, si un roi est en échec le système va le signaler.
- Si un pion atteint l'autre bout de l'échequier, il à droit à une promotion.
- Si un joueur met en échec et mat l'autre joueur, il gagne et les joueurs peuvent recommencer une autre partie.

## Contributeurs

AHMED Saad el din (Chef de projet)<br>
BEN EL BEY Yessine<br>
BIJOU Arnaud<br>
BREMOND Audran (Sous-chef)<br>
KOSON-BOURREAU Géoffrey




