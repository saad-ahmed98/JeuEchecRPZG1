package joueur;
import java.awt.Color;
import java.util.Scanner;
import pieces.*;

public class Partie {
	///variables
    private Case[][] cases = new Case[8][8]; //matrice 8x8 représentant l'echequier
    private Joueur[] joueurs = new Joueur[2]; 
    private static Scanner input; //on l'utilisera à chaque fois qu'il y a une saisie à faire
    
    ///constructeur
    //aucun autre constructeur de Partie necessaire pour notre programme
    public Partie() {
    }
    
    ///getters et setters
    public Case[][] getCases() {
        return cases;
    }

    public void setCases(Case[][] cases) {
        this.cases = cases;
    }
    
    public Joueur[] getJoueurs() {
        return joueurs;
    }
    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }
    
    //methode pour verifier si un string est un nombre entier ou pas
    //on l'utilisera quand l'utilisateur saisi les coordonnées des cases de l'echequier
    public boolean estNombre(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    //methode pour verifier si la case saisie par l'utilisateur se trouve dans l'echequier 8x8
    public boolean caseExiste(int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7)
            return true;
        else
            return false;
    }

    //methode pour initialiser la partie d'echecs
    public void creerParti() {
    	//initialisation des joueurs
        joueurs[0] = new Joueur(Color.white); //joueur 1 sera le blanc
        joueurs[1] = new Joueur(Color.black); //joueur 2 sera le noir

        //initialisation de l'echequier
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++)
                cases[i][j] = new Case(j, i);
        }
        
        //initialisation des pieces sur l'echequier
        int k = 0; //compteur
        //initialisation des pieces du j1 sur les 2 premieres lignes
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                cases[i][j].setP(joueurs[0].getPieces()[k]);
                k++;
            }
        }
        k = 0; //compteur remis à 0
        //initialisation des pieces du j2 sur les 2 dernieres lignes
        for (int i = 7; i > 5; i--) {
            for (int j = 0; j < cases[i].length; j++) {
                cases[i][j].setP(joueurs[1].getPieces()[k]);
                k++;
            }
        }
        
        //affectation des roi à leur propres joueurs
        joueurs[0].setRoi((Roi) cases[0][4].getP()); //roi blanc
        joueurs[0].getRoi().setCoord(4, 0); //affectation des coordonnées de depart du blanc
        joueurs[1].setRoi((Roi) cases[7][4].getP()); //roi noir
        joueurs[1].getRoi().setCoord(4, 7); //affectation des coordonnées de depart du noir
    }

   //methode renvoyant une matrice 8x8 de String
   //chaque case contient soit null si case vide (pas de piece) soit une chaine correspondant à la piece
    public String[][] affichageEchequier() {
        String P[][] = new String[8][8];
        
        String idt;//variable correspondante à la chaine qu'on mettra dans la matrice
        
        for (int i = 0; i < P.length; i++) {
            for (int j = 0; j < P[i].length; j++) {
            	//si la case n'a pas de piece alors sur la matrice on met "__" (ce sera utilisé à l'affichage sur le cmd)
                if (cases[i][j].getP() == null)
                    P[i][j] = "__";
                else {
                    if (cases[i][j].getP().getCouleur() == Color.white) {
                    	//si joueur 1 alors idt = id de la piece + 1 (ex= P1 pour pion du j1)
                        idt = cases[i][j].getP().getId() + "1";
                        P[i][j] = idt;
                    } else if (cases[i][j].getP().getCouleur() == Color.black) {
                    	//si joueur 2 alors 2
                        idt = cases[i][j].getP().getId() + "2";
                        P[i][j] = idt;
                    }
                }
            }
        }
        return P;
    }
    
    
    ///methodes pour l'affichage directe sur le cmd
    
    //methode pour afficher le menu sur lequel l'utilisateur tombera dès l'execution du programme
    public void afficherMenu() {
        System.out.println("\n\t\tWelcome to Cheh-ss\n\nPlease type the number of one of the options to continue. [1/2/3]\n\n\t\t 1.New Game\n\n\t\t 2.Credits\n\n\t\t 3.Exit ");
    }
    
    //methode pour afficher le nom des utilisateurs qui ont contribué à la creation de ce programme
    //a afficher uniquement si l'utilisateur saisi le numero correspondant (voir main)
    public void afficherCredits() {
        System.out.println("\n\t  Cheh-ss was brought to you by:\n\n\t\tAhmed Saad el din\n\t\tBen El Bey Yessine\n\t\tBremond Audran\n\t\tBijou Arnaud\n\t\tKoson-Bourreau Geoffrey\n\nIf you liked the game don't forget to leave a like and subscribe :P");
    }

    //methode pour afficher sur l'état de l'echequier pendant le jeu
    //prend en parametre la chaine de caracteres renvoyé par affichageEchequier
    public void afficherPartie(String[][] P) {
        String[] l = {"a","b","c","d","e","f","g","h"}; //l'echequier affichera sur les cotés horizontals, lettres allant de "a" à "h"...
        System.out.println("\n    1    2    3    4    5    6    7    8\n   ____ ____ ____ ____ ____ ____ ____ ____");     //...et sur les cotés verticals nombres allant de 1 à 8
        int j = 0;
        for (int i = 0; i < P.length; i++) {
            System.out.println("  |    |    |    |    |    |    |    |    | ");
            System.out.println(l[i] + " |_" + P[i][j] + "_|_" + P[i][j + 1] + "_|_" + P[i][j + 2] + "_|_" + P[i][j + 3] + "_|_" + P[i][j + 4] + "_|_" + P[i][j + 5] + "_|_" + P[i][j + 6] + "_|_" + P[i][j + 7] + "_| " + l[i]);
        }
        System.out.println("\n     1    2    3    4    5    6    7    8");
    }
    
    //methode pour la partie en elle-même
    //a appeler uniquement si l'utilisateur saisi le numero correspondant (voir main)
    public void jeu(Partie p) {
    	///variables
        String[] corresp = {"a","b","c","d","e","f","g","h"}; //tableau des lettres acceptées si l'utilisateur les saisies 
        String dx, dy; //variables à utiliser pour diviser la saisie en 2, dy sera la lettre et dx sera le numero
        int x1 = -1, y1 = -1; //variables qui vont stoquer les coordonnées en numero de la case de depart saisie par l'utilisateur
        int x2 = -1, y2 = -1; //meme chose mais pour case d'arrivée
        int ret = 0; //variable dans laquelle on stock le code de retour du mouvement souhaité par l'utilisateur
        //booleans pour les while (voir suite)
        boolean mouvement = false;
        boolean finjeu = false;
        boolean recomjeu = true;
        boolean saisie = false;
        
        p.creerParti();
        p.afficherPartie(p.affichageEchequier()); //on affiche l'état initial du jeu
        while (recomjeu) //tant que les joueurs veulent rejouer
        	{
            while (!finjeu) //tant que le jeu n'est pas fini
            {
                for (int i = 0; i < 2; i++) //chaque joeuur joue un tour, j1 commence en premier
                {
                    if (!finjeu) //si le jeu est deja fini, on fini la boucle sans jouer
                    {
                        System.out.println("\nPlayer " + (i + 1) + " plays.");
                        mouvement = false; //reinitialisé à faux avec un nouveau tour
                        while (!mouvement) //tant que le joueur ne saisit pas un mouvement correcte
                        {
                            System.out.println("\nType the position of the piece to move. [Letter followed by a number]"); //saisie case depart
                            String s = input.nextLine();
                            if (s.length() == 2) //verifie si saisie est 2 caracteres max
                            {
                                dy = s.substring(0, 1);
                                dx = s.substring(1, 2);
                                for (int j = 0; j < corresp.length; j++) {
                                    if (dy.toLowerCase().equals(corresp[j])) //verification si premier caractere est accepté, maj ou min n'ont pas d'importance
                                        x1 = j;
                                }
                                if (estNombre(dx)) //verification si deuxieme caractere est un nombre 
                                {
                                    y1 = Integer.parseInt(dx) - 1;
                                    if (p.caseExiste(x1, y1)) //verification si la case saisie correctement existe sur l'echequier
                                    {
                                    	//on refais donc la meme procedure pour la case d'arrivé
                                        System.out.println("\nType the position of the target [Letter followed by a number]"); //saisie case depart
                                        s = input.nextLine();
                                        if (s.length() == 2) {
                                            dy = s.substring(0, 1);
                                            dx = s.substring(1, 2);
                                            for (int j = 0; j < corresp.length; j++) {
                                                if (dy.equals(corresp[j]))
                                                    x2 = j;
                                            }
                                            if (estNombre(dx)) {
                                                y2 = Integer.parseInt(dx) - 1;
                                                if (p.joueurs[i].verifierAppartenance(p.cases[x1][y1].getP())) {
                                                    if (p.caseExiste(x2, y2)) mouvement = true;//si les deux cases ont été saisies correctement alors le mouvement est juste
                                                    else System.out.println("\nInvalid target position. Repeat \n(The case doesn't exist)");
                                                } else System.out.println("\nYou don't own the piece on the start position or there is no piece on the case. Repeat");
                                            } else System.out.println("\nInvalid target position input. Repeat\n(Only 2 digits allowed)");
                                        } else System.out.println("\nInvalid target position. Repeat\n(Second digit isn't a number)");
                                    } else System.out.println("\nInvalid start position. Repeat\n(The case doesn't exist)");
                                } else System.out.println("\nInvalid start position. Repeat\n(Second digit isn't a number)");
                            } else System.out.println("\nInvalid start position input.\n(Only 2 digits allowed)");
                            
                            if (mouvement) //si mouvement saisie est correcte
                            {
                                ret = p.cases[x1][y1].getP().mouvement(p.cases, p.cases[x1][y1], p.cases[x2][y2]); //on essaye le mouvement et on affecte à "ret" le code de retour
                                switch (ret) {

                                    case 1: //deplacement sans manger de piece
                                        p.cases[x1][y1].getP().deplacement(p.cases[x1][y1], p.cases[x2][y2]);
                                        if (!(p.joueurs[i].getRoi()).enEchec(p.cases)) //on verifie si le roi du joueur qui à joué est en echec après le deplacement ou pas
                                        {
                                            System.out.println("\nThe movement was successful."); // si pas en echec alors OK
                                            if (i == 0) //si j1 qui joue
                                            {
                                                if ((p.joueurs[1].getRoi()).enEchec(p.cases)) //verification si j2 en echec
                                                {
                                                    if ((p.joueurs[1].getRoi()).enEchecMat(p.cases)) //si j2 en echec alors on verifie si c'est un echec et mat
                                                    {
                                                        finjeu = true; //si c'est le cas, alors on fini la partie
                                                        System.out.println("\nPlayer 2's king is in checkmate.\nPlayer 1 wins.");
                                                    }
                                                    System.out.println("\nPlayer 2's king is in check.");
                                                }
                                            } else if (i == 1) //si j2 qui joue
                                            {
                                                if ((p.joueurs[0].getRoi()).enEchec(p.cases)) {
                                                    if ((p.joueurs[0].getRoi()).enEchecMat(p.cases)) {
                                                        finjeu = true;
                                                        System.out.println("\nPlayer 1's king is in checkmate.\nPlayer 2 wins.");
                                                    } else System.out.println("\nPlayer 1's king is in check.");
                                                }
                                            }
                                        } else //si roi du joueur qui joue en echec alors annuler le mouvement
                                        {
                                            p.cases[x2][y2].getP().annulerDeplac(p.cases[x2][y2], p.cases[x1][y1]);
                                            mouvement = false;//le joueur devra resaisir son mouvement
                                            System.out.println("\nThe movement was not possible.Your king would be in check.\nRepeat");
                                        }
                                        break;

                                    case 2: //deplacement en mangeant une piece, pas de difference du "case 1" apart les messages affichés à l'écran
                                        p.cases[x1][y1].getP().deplacement(p.cases[x1][y1], p.cases[x2][y2]);
                                        if (!(p.joueurs[i].getRoi()).enEchec(p.cases)) {
                                            System.out.println("\nThe movement was successful, an opponent's piece was eaten");
                                            if (i == 0) {
                                                if ((p.joueurs[1].getRoi()).enEchec(p.cases)) {
                                                    if ((p.joueurs[1].getRoi()).enEchecMat(p.cases)) {
                                                        finjeu = true;
                                                        System.out.println("\nPlayer 2's king is in checkmate.\nPlayer 1 wins.");
                                                    } else System.out.println("\nPlayer 2's king is in check.");
                                                }
                                            } else if (i == 1) {
                                                if ((p.joueurs[0].getRoi()).enEchec(p.cases)) {
                                                    if ((p.joueurs[0].getRoi()).enEchecMat(p.cases)) {
                                                        finjeu = true;
                                                        System.out.println("\nPlayer 1's king is in checkmate.\nPlayer 2 wins.");
                                                    } else System.out.println("\nPlayer 1's king is in check.");
                                                }
                                            }
                                        } else {
                                            p.cases[x2][y2].getP().annulerDeplac(p.cases[x2][y2], p.cases[x1][y1]);
                                            mouvement = false;
                                            System.out.println("\nThe movement was not possible.Your king would be in check.\nRepeat");
                                        }
                                        break;
                                        
                                    //cases où le deplacement n'est pas possible, le joueur devra resaisir son jeu
                                    case 3: //si case d'arrivé n'est pas dans la portée de la piece sur la case de depart
                                        mouvement = false;
                                        System.out.println("\nThe movement is impossible, target position out of reach.\nRepeat");
                                        break;

                                    case 4: //si case d'arrivé hors portée car une piece est sur le chemin (pour tour, fou et reine)
                                        mouvement = false;
                                        System.out.println("\nThe movement is impossible, target position out of reach. Piece in between.\nRepeat");
                                        break;

                                    case 5: //si case d'arrivé à une piece de la meme couleur
                                        mouvement = false;
                                        System.out.println("\nThe movement is impossible, target position has a piece of the same color.\nRepeat");
                                        break;
                                }
                                p.afficherPartie(p.affichageEchequier()); //afficher état de l'echequier après le deplacement meme si cela était un echec
                            }
                        }
                    }
                }
            }
            //while pour commencer une nouvelle partie si la précedente est finie
            while (!saisie) {
                System.out.println("\nStart a new game? [Y/N]");
                input = new Scanner(System.in);
                dx = input.nextLine();
                if (dx.length() == 1 && dx.toUpperCase().equals("Y")) {
                    p.jeu(p);
                    saisie = true;
                } else if (dx.length() == 1 && dx.toUpperCase().equals("N")) {
                    saisie = true;
                    recomjeu = false;
                } else System.out.println("\nIncorrect input.");
            }
        }
    }

    public static void main(String[] args) {
        Partie p = new Partie();
        p.afficherMenu();
        input = new Scanner(System.in);
        boolean saisie = false;
        while (!saisie) //tant que l'utilisateur n'a pas fait une saisie acceptée
        {
            String s = input.nextLine();

            if (s.length() == 1) {
                switch (s) {
                    case "1": //commencer un nouveau jeu
                        while (!saisie) {
                            System.out.println("\nStart a new game? [Y/N]");
                            input = new Scanner(System.in);
                            s = input.nextLine();
                            if (s.length() == 1 && s.toUpperCase().equals("Y")) {
                                p.jeu(p);
                                saisie = true;
                            }
                            if (s.length() == 1 && s.toUpperCase().equals("N")) {
                                p.afficherMenu();
                                saisie = true;
                            } else System.out.println("\nIncorrect input. \n(must be Y or N)");
                        }
                        saisie = false;
                        break;
                        
                    case "2": //afficher les credits, cela ne met pas fin au programme et on revient sur le menu initial
                        p.afficherCredits();
                        p.afficherMenu();
                        break;

                    case "3": //terminer l'execution programme
                        saisie = true;
                        break;

                    default:
                        System.out.println("\nIncorrect input. Repeat \n(Must be between 1 and 3)");
                }

            } else System.out.println("\nIncorrect input. Repeat\n(Only 1 digit numbers allowed)");
        }
        System.out.println("\nThanks for using this program!"); //message affiché quand le programme se termine
    }
    public void promotion(int j) {
        boolean saisie = false;

        for(int i=0;i<8;i++) {
            if(!this.getCases()[0][i].getP().equals(null)) {
            if(this.getCases()[0][i].getP().getId().equals("P") && j==1) {
                while(!saisie) {
                 System.out.println("A pawn can be promoted. Which new type of piece do you want? [C/T/F/D]");
                 input = new Scanner(System.in);
                 String dx = input.nextLine();
                 switch (dx) {

                 case "T": 
                     Tour t1=new Tour(Color.black);
                     this.getCases()[0][i].setP(t1);
                     saisie=true;
                     break;
                 case "C":
                 Cavalier c1=new Cavalier(Color.black);
                 this.getCases()[0][i].setP(c1);
                 saisie=true;
                 break;
                 case "F":
                     Fou f1=new Fou(Color.black);
this.getCases()[0][i].setP(f1);
                       saisie=true;
                 break;
                 case "D":
                     Reine r1=new Reine(Color.black);
                      this.getCases()[0][i].setP(r1);
                      saisie=true;
                 break;
                 default : System.out.println("Wrong input. Repeat.");
        }
                }
            }
if(!this.getCases()[7][i].getP().equals(null)) {
            if(this.getCases()[7][i].getP().getId().equals("P") && j==0) {
                while(!saisie) {
                 System.out.println("A pawn can be promoted. Which new type of piece do you want? [C/T/F/D]");
                 input = new Scanner(System.in);
                 String d = input.nextLine();
                 switch (d) {
case "T": 
                     Tour t1=new Tour(Color.white);
                     this.getCases()[7][i].setP(t1);
                     saisie=true;
                     break;
                 case "C":
                 Cavalier c1=new Cavalier(Color.white);
                 this.getCases()[7][i].setP(c1);
                 saisie=true;
                 break;
                 case "F":
                     Fou f1=new Fou(Color.white);
                       this.getCases()[7][i].setP(f1);
                       saisie=true;
                 break;
                 case "D":
                     Reine r1=new Reine(Color.white);
                      this.getCases()[7][i].setP(r1);
                      saisie=true;
                 break;
                 default : System.out.println("Wrong input. Repeat.");
            }
                }
            }
            }
        }
    }
    }

}