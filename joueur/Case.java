package joueur;
import pieces.*;

public class Case {
	///variables
    private Piece p; //piece contenue dans la case, celle-la est null si case vide
    //coordonnés sur l'echequier
    private int x;
    private int y;

    ///constructeur
    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    ///getters et setters
    public Piece getP() {
        return p;
    }

    public void setP(Piece p) {
        this.p = p;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    //methode pour verifier si la case contient une piece ou pas
    public boolean verifierPiece() {
        if (p == null)
            return false;
        return true;
    }

}