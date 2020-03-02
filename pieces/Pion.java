package pieces;
import java.awt.Color;
import joueur.*;

public class Pion extends Piece {

    public Pion(Color c) {
        super(c, "P");
    }

    public int mouvement(Case[][] cases, Case depart, Case arrive) {
        //Les pions ne pouvant pas reculer, il est nécessaire de déterminer avant toute chose sa couleur.
        //Déplacement pour pion blanc
        if (super.getCouleur() == Color.white) {
            //Si le pion n'a pas été déplacé, il peut avancer de deux cases.
            if (depart.getY() == 1) {
                if ((arrive.getY() - depart.getY() == 2) && (arrive.getX() - depart.getX() == 0)) {
                    if (arrive.verifierPiece() == false) {
                        return 1;
                    } else {
                        return 5;
                    }
                }
            }
            //Déplacement standard
            if ((arrive.getY() - depart.getY() == 1) && (arrive.getX() - depart.getX() == 0)) {
                if (arrive.verifierPiece() == false) {
                    return 1;
                } else {
                    return 5;
                }
            }
            //Condition d'attaque
            if (arrive.getY() - depart.getY() == 1 && Math.abs(depart.getX() - arrive.getX()) == 1) {
                if (arrive.verifierPiece()) {
                    if (mangerPossible(arrive)) {
                        return 2;
                    } else {
                        return 5;
                    }
                }
            }
        }

        //Déplacement pour pion noir
        if (super.getCouleur() == Color.black) {
            //Si le pion n'a pas été déplacé, il peut avancer de deux cases.
            if (depart.getY() == 6) {
                if ((depart.getY() - arrive.getY() == 2) && (depart.getX() - arrive.getX() == 0)) {
                    if (arrive.verifierPiece() == false) {
                        return 1;
                    } else {
                        return 5;
                    }
                }
            }
            //Déplacement standard
            if ((depart.getY() - arrive.getY() == 1) && (depart.getX() - arrive.getX() == 0)) {
                if (arrive.verifierPiece() == false) {
                    return 1;
                } else {
                    return 5;
                }
            }
            //Condition d'attaque
            if (depart.getY() - arrive.getY() == 1 && Math.abs(depart.getX() - arrive.getX()) == 1) {
                if (arrive.verifierPiece()) {
                    if (mangerPossible(arrive)) {
                        return 2;
                    } else {
                        return 5;
                    }
                }
            }
        }
        return 3;
    }
}
