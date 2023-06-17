package fr.utln.javafx.sample.hello.models;

import fr.utln.javafx.sample.hello.Controller;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class Cell extends Pane {
    private char symbole = ' ';
    public Cell(int n, Cell[][] grille){
        setStyle("-fx-border-color: black");
        this.setPrefSize(300, 300);
        this.setOnMouseClicked(e ->click(n,grille));
    }
    public char getSymbole() {
        return symbole;
    }

    public void setSymbole(char symbole) {
        this.symbole = symbole;
        if(symbole == 'X'){
            Line diag1 = new Line(10,10,this.getWidth(),  this.getHeight());
            diag1.setStrokeWidth(10);
            diag1.endXProperty().bind(this.widthProperty().subtract(10));
            diag1.endYProperty().bind(this.heightProperty().subtract(10));
            diag1.setStroke(Controller.selectionneurCouleur1.getValue());
            //diag1.setFill(Controller.selectionneurCouleur1.getValue());
            Line diag2 = new Line(10, this.getHeight()-10, this.getWidth()-10, 10);
            diag2.endXProperty().bind(this.widthProperty().subtract(10));
            diag2.setStrokeWidth(10);
            diag2.startYProperty().bind(this.heightProperty().subtract(10));
            diag2.setStroke(Controller.selectionneurCouleur1.getValue());
            getChildren().addAll(diag1, diag2);
        }
        else{
            Ellipse clip = new Ellipse(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2 - 10, this.getHeight()/2-10);
            clip.setFill(Controller.selectionneurCouleur2.getValue());
            getChildren().addAll(clip);
        }
    }
    public  boolean diag1(char joueur, int n, Cell[][] grille){
        for (int i = 0 ; i  < n ; i++){
            if(grille[i][i].getSymbole() != joueur ){
                return false;
            }
        }
        return true ;
    }
    public  boolean diag2(char joueur, int n, Cell[][] grille){
        int j = n-1 ;
        for (int i = 0 ; i  < n ; i++){
            if(grille[i][j].getSymbole() != joueur ){
                return false;
            }
            j --;
        }
        return true ;
    }
    public  boolean lignes(char joueur, int n, Cell[][] grille){
        for (int i = 0 ; i  < n ; i++){
            int tmp = 0;
            for(int j = 0; j < n ; j++){
                if(grille[i][j].getSymbole() == joueur ){
                    tmp ++;}
            }
            if(tmp == n){return true;}
        }
        return false ;
    }
    public boolean colonnes(char joueur, int n, Cell[][] grille){
        for(int i = 0; i < n; i++) {
            int tmp = 0 ;
            for(int j = 0 ; j < n ; j++){
                if(grille[j][i].getSymbole() == joueur ) {
                    tmp++;}
            }
            if(tmp == n ){

                return  true;}
        }
        return false ;
    }
    public boolean gagnant(char joueur, int n, Cell[][] grille){
        return diag1(joueur,n , grille) || diag2(joueur,n , grille)|| colonnes(joueur,n , grille)|| lignes(joueur,n , grille);
    }
    public boolean casesPleines(int n, Cell[][] grille){
        /*verifier si toutes les cellules ont ete jouées */
        int tmp = 0;
        for(int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < n ; j++){
                if (grille[i][j].getSymbole() != ' ' ){
                    tmp += 1;
                }
            }
        }
        if (tmp == n*n){
            return true;
        }
        return false;
    }
    public void click(int n, Cell[][] grille){
        if(Controller.joueurActuel != ' ' && symbole == ' ' ) {
            setSymbole(Controller.joueurActuel);
            if (gagnant(Controller.joueurActuel, n, grille)){
                Controller.statutJeu.setText(Controller.joueurActuel + " vient de gagner le tour ! BRAVO");
                if (Controller.joueurActuel == 'O') {
                    Controller.scoreJO += 1 ;
                    Controller.scoreJoueuer2.setText("score joueur "+ Controller.nom1.getText()+" est a "+Controller.scoreJO);
                    Controller.scoreJoueur1.setText("score joueur "+ Controller.nom2.getText()+" est a "+Controller.scoreJX);
                }
                else{
                    Controller.scoreJX += 1 ;
                    Controller.scoreJoueur1.setText("score joueur "+ Controller.nom2.getText()+" est a "+Controller.scoreJX);
                    Controller.scoreJoueuer2.setText("score joueur "+ Controller.nom1.getText()+" est a "+Controller.scoreJO);
                }
                Controller.nombreP += 1 ;
                Controller.nbTour.setText("nombre de parties jouées = "+Controller.nombreP);
                Controller.joueurActuel= ' ';
            }
            else{
                Controller.joueurActuel = (Controller.joueurActuel == 'X') ? 'O' : 'X';
            }
        }
        if(casesPleines(n, grille)){
            Controller.nombreP += 1 ;
            Controller.nbTour.setText("nombre de parties jouées = "+Controller.nombreP);
            Controller.joueurActuel= ' ';
        }
    }
}