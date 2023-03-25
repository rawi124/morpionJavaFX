package fr.utln.javafx.sample.hello;
import javafx.application.Application;

//import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.Random;


/**
 * The JavaFX Hello World application.
 * @author Ben Amira Rawia
 */
@SuppressWarnings("Convert2Lambda")
public class HelloWorldApplication extends Application {
   private static String joueurs ="XO";
   Random random = new Random();
   int nb = random.nextInt(2);//generer un entier aleatoire 0 ou 1 0 donnera X et 1 donnera 0
   public char joueurActuel = joueurs.charAt(nb);//le joueurActuel sera au debut soit X soit 0
   private Button btnDebut = new Button("Commencer partie");
   private Label  bienvenue = new Label("");
   private Label statutJeu = new Label("Debut partie");
   private GridPane disposition = new GridPane();
   private Scene scene = new Scene(disposition, 750, 550);
   TextField nom1 = new TextField();//le nom du premier utilisateur
   TextField nom2 = new TextField();//le nom du deuxieme utilisateur
   TextField tailleGrille = new TextField();//la grille de taille n*n
   final ColorPicker selectionneurCouleur1 = new ColorPicker();
   final ColorPicker selectionneurCouleur2 = new ColorPicker();
   final Button btnAcceuil = new Button("Acceuil");
   final Button btnActualiser = new Button("Nouvelle partie ");
   @Override
   public void start(Stage stagePrincipale) {
      selectionneurCouleur1.getValue();
      Color couleurJoueur2 = selectionneurCouleur2.getValue();
      Color couleurJoueur1 = selectionneurCouleur1.getValue();

      //ajoute les composantes graphiques a la borderPane
      disposition.setPadding(new Insets(100, 100, 100, 100));
      disposition.setVgap(5);
      disposition.setHgap(5);
      disposition.getChildren().addAll(nom1, nom2, btnDebut, tailleGrille, selectionneurCouleur1, selectionneurCouleur2);

      GridPane.setConstraints(bienvenue, 0, 6);
      GridPane.setColumnSpan(bienvenue, 2);
      disposition.getChildren().add(bienvenue);

      //espace de declaration du TextField pour le joueur 1
      nom1.setPromptText("Joueur 1");
      nom1.setPrefColumnCount(10);
      GridPane.setConstraints(nom1, 0, 0);

      //espace de declaration du TextField pour le joueur 2
      nom2.setPrefColumnCount(15);
      nom2.setPromptText("Joueue 2");
      GridPane.setConstraints(nom2, 0, 2);
      GridPane.setConstraints(btnDebut, 0, 7);

      //espace de declaration du TextField pour la taille de la grille
      tailleGrille.setPrefColumnCount(20);
      tailleGrille.setPromptText("taille de la grille");
      GridPane.setConstraints(tailleGrille, 0, 3);

      GridPane.setConstraints(selectionneurCouleur1, 0, 5);
      GridPane.setConstraints(selectionneurCouleur2, 0, 4);

      nom1.setStyle("    -fx-min-width: 40; -fx-min-height: 40;");
      nom1.setFont(Font.font( 20));//pour la taille des caracteres

      nom2.setStyle("    -fx-min-width: 40; -fx-min-height: 40;");
      nom2.setFont(Font.font( 20));//pour la taille des caracteres

      tailleGrille.setStyle("    -fx-min-width: 40; -fx-min-height: 40;");
      tailleGrille.setFont(Font.font( 20));//pour la taille des caracteres

      btnDebut.setStyle("-fx-min-width: 100; -fx-max-height: 100;");
      btnActualiser.setStyle("-fx-min-width: 100; -fx-max-height: 100;");
      btnAcceuil.setStyle("-fx-min-width: 100; -fx-max-height: 100;");

      btnActualiser.setOnAction(new EventHandler<ActionEvent>(){
         @Override
         public void handle(ActionEvent actionEvent) {
            nouvellePartie(stagePrincipale);

         }
      });

      btnAcceuil.setOnAction(new EventHandler<ActionEvent>(){
         @Override
         public void handle(ActionEvent actionEvent) {
            nouvellePartie(stagePrincipale);

         }
      });
      btnDebut.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e) {
            if (!nom1.getText().equals("")) {
               if (!nom2.getText().equals("")) {
                  if (!tailleGrille.getText().equals("")) {
                     if (nom2.getText().equals(nom1.getText())) {
                        bienvenue.setText("veuillez saisir deux noms differents !");
                     } else {
                        nouvellePartie(stagePrincipale);
                     }
                  } else {
                     bienvenue.setText("veuillez saisir la taille de la grille! ");
                  }
               } else {
                  bienvenue.setText("veuillez saisir le nom du second joueur");
               }
            }
            else{
                     bienvenue.setText("veuillez saisir le nom du premier joueur");
                  }
            }});
      disposition.setStyle("-fx-background-color: #9E9E9E");
      bienvenue.setFont(Font.font( 25));//pour la taille des caracteres
      bienvenue.setTextFill(Color.WHITESMOKE);//la couleur du texte
      stagePrincipale.setTitle("MORPION");
      stagePrincipale.setScene(scene);
      stagePrincipale.show();

   }
   public void nouvellePartie(Stage stagePrincipale){
      char joueuer1 = 'X';
      char  joueur2 = 'O';

      GridPane grilleJeu = new GridPane();
      BorderPane borderPane = new BorderPane();

      statutJeu.setStyle("-fx-background-color: #9E9E9E");
      statutJeu.setStyle("-fx-min-width: 100; -fx-max-height: 100;");
      borderPane.setBottom(btnActualiser);
      borderPane.setCenter(grilleJeu);
      borderPane.setLeft(statutJeu);

      Scene scene2 = new Scene(borderPane, 1200, 1200);
      int n = Integer.parseInt(tailleGrille.getText());
      Cell[][] grille = new Cell[n][n];
      for(int i = 0; i < n;i++){
         for(int j = 0 ; j < n ;j ++)
         {
            grille[i][j] = new Cell(n, grille);
            Circle clip = new Circle(100, 100, 50);
            grilleJeu.add(grille[i][j], j, i);
         }
      }

      stagePrincipale.setScene(scene2);
   }
   public static boolean diag1(char joueur, int n, Cell[][] grille){
      for (int i = 0 ; i  < n ; i++){
         if(grille[i][i].getSymbole() != joueur ){
            return false;
         }
      }
      return true ;
   }
   public static boolean diag2(char joueur, int n, Cell[][] grille){
      int j = n-1 ;
      for (int i = 0 ; i  < n ; i++){
         if(grille[i][j].getSymbole() != joueur ){
            return false;
         }
         j --;
      }
      return true ;
   }
   public static boolean colonne(char joueur, int n, Cell[][] grille){

      for (int i = 0 ; i  < n ; i++){
         for(int j = 0; j < n ; j++){
            if(grille[i][j].getSymbole() != joueur ){
               return false;}
         }
         return true ;
      }
      return true ;
   }
   public static boolean ligne(char joueur, int n, Cell[][] grille){
      for (int i = 0 ; i  < n ; i++){
         for(int j = 0; j < n ; j++){
            if(grille[j][i].getSymbole() != joueur ){
               return false;}
         }
         return true ;
      }
      return true ;
   }
   public static boolean gagnant(char joueur, int n, Cell[][] grille){
      return diag1(joueur,n , grille) || diag2(joueur,n , grille)|| colonne(joueur,n , grille)|| ligne(joueur,n , grille);
   }
   public boolean casesPleines(int n, Cell[][] grille){
      /*verifier si toutes les cellules ont ete jouées */
      for(int i = 0 ; i < n ; i++){
         for (int j = 0 ; j < n ; j++){
            if (grille[i][j].getSymbole() == ' ' ){
               return false;
            }
         }
         return true ;
      }
      return false ;
   }

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
         //un joueur est defini par son symbole

         if(symbole == 'X'){
            Line diag1 = new Line(10,10,this.getWidth()-10,  this.getHeight()-10);
            diag1.endXProperty().bind(this.widthProperty().subtract(10));
            diag1.endYProperty().bind(this.heightProperty().subtract(10));
            diag1.setFill(selectionneurCouleur1.getValue());
            Line diag2 = new Line(10, this.getHeight()-10, this.getWidth()-10, 10);
            diag2.endXProperty().bind(this.widthProperty().subtract(10));
            diag2.startYProperty().bind(this.heightProperty().subtract(10));
            diag2.setFill(selectionneurCouleur1.getValue());
            getChildren().addAll(diag1, diag2);
         }
         else{//pour apres traiter cas des X et O
            Ellipse clip = new Ellipse(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2 - 10, this.getHeight()/2-10);
            clip.setFill(selectionneurCouleur2.getValue());
            getChildren().addAll(clip);
         }

      }
      public void click(int n, Cell[][] grille){
         if(joueurActuel != ' ' && symbole == ' ' ) {
            setSymbole(joueurActuel);
            if (gagnant(joueurActuel, n, grille)){
               statutJeu.setText(joueurActuel + "gagant");
               joueurActuel= ' ';
            }
            else{
               joueurActuel = (joueurActuel == 'X') ? 'O' : 'X';
            }
         }
      }
   }

}