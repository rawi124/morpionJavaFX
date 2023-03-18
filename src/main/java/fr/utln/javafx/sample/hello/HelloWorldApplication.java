package fr.utln.javafx.sample.hello;

import javafx.application.Application;

//import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;



/**
 * The JavaFX Hello World application.
 * @author Julien SEINTURIER
 */
@SuppressWarnings("Convert2Lambda")
public class HelloWorldApplication extends Application {
	
   private Button btnDebut = new Button("Commencer partie");
   
   private Button btnBienvenue = new Button("Dites 'Bienvenue !'");
   
   private Label  bienvenue = new Label("");
   
   private GridPane disposition = new GridPane();
   

   
   private Scene scene = new Scene(disposition, 750, 550);

   TextField nom1 = new TextField();//le nom du premier utilisateur
   TextField nom2 = new TextField();//le nom du deuxieme utilisateur

   
   @Override
   public void start(Stage stagePrincipale) {

      //ajoute les composantes graphiques a la borderPane
      disposition.setPadding(new Insets(100, 100, 100, 100));
      disposition.setVgap(5);
      disposition.setHgap(5);
      disposition.getChildren().addAll(nom1, nom2, btnDebut);

      GridPane.setConstraints(bienvenue, 0, 3);
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
      GridPane.setConstraints(btnDebut, 1, 0);



      btnDebut.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent e) {
            if ((nom1.getText() != null && nom2.getText() != null)) {
               if (nom2.getText().equals(nom1.getText())) {
                  bienvenue.setText(
                          "veuillez saisir deux noms differents !");
               } else {
                  bienvenue.setText("vous pouvez commencer la partie !");
                  GridPane jeu = new GridPane();
                  Scene scene2 = new Scene(jeu, 600, 400);
                  stagePrincipale.setScene(scene2);

               }
            } else {
               bienvenue.setText("veuillez remplir les champs !");

            }
         }

      });



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


}