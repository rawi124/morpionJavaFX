package fr.utln.javafx.sample.hello;
import javafx.application.Application;

//import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.stage.Popup;
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
   private Button btnQuitter = new Button("Quitter");
   private Label  morpion = new Label("MORPION");
   private Label  bienvenue = new Label("");
   private Label statutJeu = new Label("Debut partie");

   private Label scoreJoueur1 = new Label();
   public Label scoreJoueuer2 = new Label();
   public Label nbTour = new Label("nombre de parties jouées = 0");
   private GridPane disposition = new GridPane();
   private Scene scene = new Scene(disposition, 750, 1000);
   TextField nom1 = new TextField();//le nom du premier utilisateur
   TextField nom2 = new TextField();//le nom du deuxieme utilisateur
   TextField tailleGrille = new TextField();//la grille de taille n*n
   final ColorPicker selectionneurCouleur1 = new ColorPicker();
   final ColorPicker selectionneurCouleur2 = new ColorPicker();
   final Button btnAcceuil = new Button("Acceuil");
   final Button btnActualiser = new Button("Nouvelle partie ");
   final Image img = new Image(getClass().getResourceAsStream("/imgs/unnamed.png"));
   final ImageView imageView = new ImageView(img);
   final HBox image = new HBox(imageView);
   int scoreJX = 0 ;
   int scoreJO = 0 ;
   int nombreP = 0 ;
   Popup popup = new Popup();


   @Override
   public void start(Stage stagePrincipale) {
      selectionneurCouleur1.getValue();

      Color couleurJoueur2 = selectionneurCouleur2.getValue();
      Color couleurJoueur1 = selectionneurCouleur1.getValue();

      //ajoute les composantes graphiques a la borderPane
      disposition.setPadding(new Insets(100, 100, 100, 100));
      disposition.setVgap(5);
      disposition.setHgap(5);
      disposition.getChildren().addAll(image, morpion, nom1, nom2, btnDebut, tailleGrille, selectionneurCouleur1, selectionneurCouleur2);

      GridPane.setConstraints(morpion, 0, 1);
      GridPane.setConstraints(image, 0, 0);
      morpion.setFont(Font.font( 50));//pour la taille des caracteres
      morpion.setTextFill(Color.web("#6A8A82"));//la couleur du texte
      GridPane.setHalignment(morpion, javafx.geometry.HPos.CENTER);



      //espace de declaration du TextField pour le joueur 1
      nom1.setPromptText("Joueur 1");
      nom1.setPrefColumnCount(5);
      nom1.setStyle("    -fx-min-width: 20; -fx-min-height: 40;");
      nom1.setFont(Font.font( 20));//pour la taille des caracteres
      GridPane.setConstraints(nom1, 0, 5);

      //espace de declaration du TextField pour le joueur 2
      nom2.setPrefColumnCount(15);
      nom2.setPromptText("Joueuer 2");
      GridPane.setConstraints(nom2, 0, 6);
      nom2.setStyle("    -fx-min-width: 40; -fx-min-height: 40;");
      nom2.setFont(Font.font( 20));//pour la taille des caracteres

      //espace de declaration du TextField pour la taille de la grille
      tailleGrille.setPrefColumnCount(20);
      tailleGrille.setPromptText("taille de la grille");
      GridPane.setConstraints(tailleGrille, 0, 7);

      GridPane.setConstraints(selectionneurCouleur1, 0, 8);
      GridPane.setConstraints(selectionneurCouleur2, 0, 9);
      GridPane.setHalignment(selectionneurCouleur1, javafx.geometry.HPos.CENTER);
      GridPane.setHalignment(selectionneurCouleur2, javafx.geometry.HPos.CENTER);

      GridPane.setConstraints(bienvenue, 0, 10);
      GridPane.setColumnSpan(bienvenue, 2);
      disposition.getChildren().add(bienvenue);

      tailleGrille.setStyle("    -fx-min-width: 40; -fx-min-height: 40;");
      tailleGrille.setFont(Font.font( 20));//pour la taille des caracteres

      btnDebut.setStyle("-fx-min-width:200; -fx-max-height: 200;");
      GridPane.setConstraints(btnDebut, 0, 11);
      GridPane.setHalignment(btnDebut, javafx.geometry.HPos.CENTER);
      GridPane.setHalignment(image, javafx.geometry.HPos.CENTER);

      btnActualiser.setStyle("-fx-min-width: 100; -fx-max-height: 100;");
      btnAcceuil.setStyle("-fx-min-width: 100; -fx-max-height: 100;");

      btnActualiser.setOnAction(new EventHandler<ActionEvent>(){
         @Override
         public void handle(ActionEvent actionEvent) {
            Random random = new Random();
            int nb = random.nextInt(2);//generer un entier aleatoire 0 ou 1 0 donnera X et 1 donnera 0
            joueurActuel = joueurs.charAt(nb);
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


      disposition.setStyle("-fx-background-color: #282726");
      bienvenue.setFont(Font.font( 25));//pour la taille des caracteres
      bienvenue.setTextFill(Color.web("#6A8A82"));//la couleur du texte
      stagePrincipale.setTitle("MORPION");
      stagePrincipale.setScene(scene);
      stagePrincipale.show();
   }

   public void nouvellePartie(Stage stagePrincipale){

      GridPane grilleJeu = new GridPane();
      BorderPane borderPane = new BorderPane();
      FlowPane composants = new FlowPane(10, 10 ,statutJeu, scoreJoueuer2, scoreJoueur1, nbTour, btnActualiser);

      statutJeu.setStyle("-fx-background-color: #9E9E9E");
      statutJeu.setStyle("-fx-min-width: 100; -fx-max-height: 100;");

      scoreJoueuer2.setStyle("-fx-background-color: #9E9E9E");
      scoreJoueuer2.setStyle("-fx-min-width: 100; -fx-max-height: 100;");
      scoreJoueuer2.setText("joueur 1 : "+nom1.getText());
      scoreJoueur1.setText("joueur 2 : "+nom2.getText());

      scoreJoueur1.setStyle("-fx-background-color: #9E9E9E");
      scoreJoueur1.setStyle("-fx-min-width: 100; -fx-max-height: 100;");

      borderPane.setCenter(grilleJeu);
      borderPane.setBottom(composants);

      Scene scene2 = new Scene(borderPane, 1000, 1000);
      int n = Integer.parseInt(tailleGrille.getText());
      Cell[][] grille = new Cell[n][n];
      for(int i = 0; i < n;i++){
         for(int j = 0 ; j < n ;j ++)
         {
            grille[i][j] = new Cell(n, grille);
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
   public static boolean lignes(char joueur, int n, Cell[][] grille){

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

   public static boolean colonnes(char joueur, int n, Cell[][] grille){
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
   public static boolean gagnant(char joueur, int n, Cell[][] grille){
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
               statutJeu.setText(joueurActuel + " vient de gagner le tour ! BRAVO");
               if (joueurActuel == 'O') {
                  scoreJO += 1 ;
                  scoreJoueuer2.setText("score joueur "+ nom1.getText()+" est a "+scoreJO);
                  scoreJoueur1.setText("score joueur "+ nom2.getText()+" est a "+scoreJX);
               }
               else{
                  scoreJX += 1 ;
                  scoreJoueur1.setText("score joueur "+ nom2.getText()+" est a "+scoreJX);
                  scoreJoueuer2.setText("score joueur "+ nom1.getText()+" est a "+scoreJO);
               }
               nombreP += 1 ;
               nbTour.setText("nombre de parties jouées = "+nombreP);
               joueurActuel= ' ';
            }
            else{
               joueurActuel = (joueurActuel == 'X') ? 'O' : 'X';
            }
         }
         if(casesPleines(n, grille)){
            nombreP += 1 ;
            nbTour.setText("nombre de parties jouées = "+nombreP);
            joueurActuel= ' ';
         }
      }
   }

}