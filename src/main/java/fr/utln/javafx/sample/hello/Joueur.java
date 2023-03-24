package fr.utln.javafx.sample.hello;

import javafx.scene.paint.Color;

public class Joueur {
    private Color couleur ;
    private char symbole ;

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setSymbole(char symbole) {
        this.symbole = symbole;
    }

    public Color getCouleur() {
        return couleur;
    }

    public char getSymbole() {
        return symbole;
    }
    public Joueur(char symbole, Color couleur){
        this.couleur = couleur ;
        this.symbole = symbole ;
    }
}
