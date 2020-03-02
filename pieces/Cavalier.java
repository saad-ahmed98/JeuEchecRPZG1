package pieces;
import joueur.*;
import java.awt.Color;

public class Cavalier extends Piece {
    public Cavalier(Color c) {
        super(c, "C");
    }

    public int mouvement(Case[][] cases, Case depart, Case arrive) {
        if ((Math.abs(depart.getX() - arrive.getX()) == 2 && Math.abs(depart.getY() - arrive.getY()) == 1) || (Math.abs(depart.getX() - arrive.getX()) == 1 && Math.abs(depart.getY() - arrive.getY()) == 2)) {
            if (!arrive.verifierPiece())
                return 1;//deplacement normal
            if (mangerPossible(arrive)) {
                return 2;//mangé
            } else {
                return 5;//meme couleur
            }
        } else return 3;//hors portée
    }
}