package fr.utln.javafx.sample.hello;

import fr.utln.javafx.sample.hello.Cell;


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
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.Random;


/**
 * The JavaFX Hello World application.
 * @author Julien SEINTURIER
 */
@SuppressWarnings("Convert2Lambda")
public class HelloWorldApplication extends Application {
   private String joueurs ="XO";
   Random random = new Random();
   int nb = random.nextInt(1);
   private char joueurActuel = joueurs.charAt(nb);
   private Button btnDebut = new Button("Commencer partie");
   private Label  bienvenue = new Label("");
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
      /*
      bienvenue.setFont(Font.font( 25));//pour la taille des caracteres
      bienvenue.setTextFill(Color.WHITESMOKE);//la couleur du texte
      btnBonjour.setOnAction(evt -> { bienvenue.setText("Bonjour à tous !"); });//Bouton 1
      btnBienvenue.setOnAction(evt -> { bienvenue.setText("Bienvenue à tout le monde !"); });//bouton 2
      boutons.setAlignment(Pos.CENTER);
      boutons.setPadding(new Insets(100, 0, 10, 0));//pour espacer les composantes
      boutons.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
      disposition.setTop(boutons);
      disposition.setStyle("-fx-background-color: #FAEBD7");
      stage.setTitle("MORPION");
      stage.setScene(scene);
      stage.show();
      */
   }
   public void nouvellePartie(Stage stagePrincipale){

      selectionneurCouleur1.getValue();
      Color couleurJoueur2 = selectionneurCouleur2.getValue();
      Color couleurJoueur1 = selectionneurCouleur1.getValue();

      Joueur joueuer1 = new Joueur('X',couleurJoueur1);
      Joueur joueur2 = new Joueur('O', couleurJoueur2);

      GridPane grilleJeu = new GridPane();
      BorderPane borderPane = new BorderPane();
      Label statutJeu = new Label("Debut partie");
      statutJeu.setStyle("-fx-background-color: #9E9E9E");
      statutJeu.setStyle("-fx-min-width: 100; -fx-max-height: 100;");
      borderPane.setBottom(btnActualiser);
      borderPane.setCenter(grilleJeu);
      borderPane.setLeft(statutJeu);
      //borderPane.setRight(statutJeu);
      Scene scene2 = new Scene(borderPane, 1200, 1200);
      int n = Integer.parseInt(tailleGrille.getText());
      Cell[][] grille = new Cell[n][n];
      for(int i = 0; i < n;i++){
         for(int j = 0 ; j < n ;j ++)
         {
            grille[i][j] = new Cell();
            Circle clip = new Circle(100, 100, 50);
            clip.setFill(couleurJoueur1);
            //clip.setOnMouseClicked(mouseEvent-> System.out.printf("Bouton %s cliqué sur le nœud, %d click(s) %f x %f.",mouseEvent.getButton(), mouseEvent.getClickCount(), mouseEvent.getX(), mouseEvent.getY()).println());
            //grilleJeu.add(clip, j, i);
            grilleJeu.add(grille[i][j], j, i);
         }
      }

      stagePrincipale.setScene(scene2);
   }
   public void Acceuil(){

   }
   public boolean is(int n, Cell[][] grille){
      for(int i = 0 ; i < n ; i++){
         for (int j = 0 ; j < n ; j++){
            if (grille[i][j].getJoueur().getSymbole() == ' ' ){
               return false;
            }
         }
         return true ;
      }
      return false ;
   }




}