package fr.utln.javafx.sample.hello;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Cell extends Pane {
    private Joueur joueur ;
    private Color couleur ;
    public Cell(){
        setStyle("-fx-border-color: black");
        this.setPrefSize(300, 300);
        this.setOnMouseClicked(e ->click(this.getJoueur()));
    }
    public Joueur getJoueur() {
        return joueur;
    }
    public void click(Joueur joueuer){
        setJoueur(joueuer);
    }
    public boolean gagnant(Joueur joueur, int n, Cell[][] grille ){
        //si tour gagné sur la premiere diagonale
        for (int i = 0 ; i  < n ; i++){
            if(grille[i][i].getJoueur().getSymbole() != joueur.getSymbole() ){
                return false;
            }
        }
        return true ;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
        Circle clip = new Circle(this.getHeight()-100, this.getWidth()-100, 30);
        if(joueur.getSymbole() == 'X'){
            clip.setFill(joueur.getCouleur());
        }
        else{//pour apres traiter cas des X et O
            clip.setFill(joueur.getCouleur());
        }
        getChildren().addAll(clip);
    }
}
