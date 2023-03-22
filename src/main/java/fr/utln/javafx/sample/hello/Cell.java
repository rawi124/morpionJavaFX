package fr.utln.javafx.sample.hello;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Cell extends Pane {
    private char joueur = ' ';
    private Color couleur ;
    public Cell(Color couleur){
        setStyle("-fx-border-color: black");
        this.setPrefSize(300, 300);
        this.setOnMouseClicked(e ->click(couleur));
    }
    public char getJoueur() {
        return joueur;
    }
    public void setJoueur(Color couleur){
        this.couleur = couleur ;

    }
    public void click(Color couleur){
        Circle clip = new Circle(100, 100, 50);
        clip.setFill(couleur);
        getChildren().addAll(clip);
    }
    public boolean gagnant(char joueur, int n, Cell[][] grille ){
        //si tour gagné sur la premiere diagonale
        for (int i = 0 ; i  < n ; i++){
            if(grille[i][i].getJoueur() != joueur ){
                return false;
            }
        }
        return true ;
    }
}
