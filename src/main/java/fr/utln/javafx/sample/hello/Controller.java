package fr.utln.javafx.sample.hello;

import fr.utln.javafx.sample.hello.models.Cell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.Random;


public class Controller  {

    public static String joueurs ="XO";
    public Random random = new Random();
    public static char joueurActuel = 'X';//le joueurActuel sera au debut soit X soit 0
    public static Button btnDebut = new Button("Commencer partie");
    public static Button btnQuitter = new Button("Quitter");
    public static Label  morpion = new Label("MORPION");
    public static Label  bienvenue = new Label("");
    public static Label statutJeu = new Label("Debut partie");

    public static Label scoreJoueur1 = new Label();
    public static Label scoreJoueuer2 = new Label();
    public static Label nbTour = new Label("nombre de parties jouées = 0");
    public static GridPane disposition = new GridPane();
    public static Scene scene = new Scene(disposition, 750, 600);
    public static TextField nom1 = new TextField();//le nom du premier utilisateur
    public static TextField nom2 = new TextField();//le nom du deuxieme utilisateur
    public static TextField tailleGrille = new TextField();//la grille de taille n*n
    public static ColorPicker selectionneurCouleur1 = new ColorPicker();
    public static ColorPicker selectionneurCouleur2 = new ColorPicker();
    public static Button btnAcceuil = new Button("Acceuil");
    public static Button btnActualiser = new Button("Nouvelle partie ");

    public static int scoreJX = 0 ;
    public static int scoreJO = 0 ;
    public static int nombreP = 0 ;
    Popup popup = new Popup();

    public static void affichage(Stage stagePrincipale){
        selectionneurCouleur1.getValue();

        Color couleurJoueur2 = selectionneurCouleur2.getValue();
        Color couleurJoueur1 = selectionneurCouleur1.getValue();

        disposition.setPadding(new Insets(100, 100, 100, 100));
        disposition.setVgap(5);
        disposition.setHgap(5);
        disposition.getChildren().addAll( morpion, nom1, nom2, btnDebut, tailleGrille, selectionneurCouleur1, selectionneurCouleur2);
        GridPane.setConstraints(morpion, 0, 1);
        morpion.setFont(Font.font( 50));
        morpion.setTextFill(Color.web("#6A8A82"));//la couleur du texte
        GridPane.setHalignment(morpion, javafx.geometry.HPos.CENTER);
        nom1.setPromptText("Joueur 1");
        nom1.setPrefColumnCount(5);
        nom1.setStyle("    -fx-min-width: 20; -fx-min-height: 40;");
        nom1.setFont(Font.font( 20));
        GridPane.setConstraints(nom1, 0, 5);
        nom2.setPrefColumnCount(15);
        nom2.setPromptText("Joueuer 2");
        GridPane.setConstraints(nom2, 0, 6);
        nom2.setStyle("    -fx-min-width: 40; -fx-min-height: 40;");
        nom2.setFont(Font.font( 20));
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
        btnActualiser.setStyle("-fx-min-width: 100; -fx-max-height: 100;");
        btnAcceuil.setStyle("-fx-min-width: 100; -fx-max-height: 100;");
        btnActualiser.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                Random random = new Random();
                int nb = random.nextInt(2);
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
    public static void nouvellePartie(Stage stagePrincipale){
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
                grille[i][j].setStyle("-fx-background-color: linear-gradient(to bottom, #81A3A7, #C2D3DA ,#F1F3F2 ); -fx-border-width: 2 ;-fx-border-color: #777CA8;");
                grilleJeu.add(grille[i][j], j, i);
            }
        }
        stagePrincipale.setScene(scene2);
    }

    public static String getJoueurs() {
        return joueurs;
    }
    public Random getRandom() {
        return random;
    }
    public char getJoueurActuel() {
        return joueurActuel;
    }
    public Button getBtnDebut() {
        return btnDebut;
    }
    public Button getBtnQuitter() {
        return btnQuitter;
    }
    public Label getMorpion() {
        return morpion;
    }
    public Label getBienvenue() {
        return bienvenue;
    }
    public Label getStatutJeu() {
        return statutJeu;
    }
    public Label getScoreJoueur1() {
        return scoreJoueur1;
    }
    public Label getScoreJoueuer2() {
        return scoreJoueuer2;
    }
    public Label getNbTour() {
        return nbTour;
    }
    public GridPane getDisposition() {
        return disposition;
    }
    public Scene getScene() {
        return scene;
    }
    public TextField getNom1() {
        return nom1;
    }
    public TextField getNom2() {
        return nom2;
    }
    public TextField getTailleGrille() {
        return tailleGrille;
    }
    public ColorPicker getSelectionneurCouleur1() {
        return selectionneurCouleur1;
    }
    public ColorPicker getSelectionneurCouleur2() {
        return selectionneurCouleur2;
    }
    public Button getBtnAcceuil() {
        return btnAcceuil;
    }
    public Button getBtnActualiser() {
        return btnActualiser;
    }
    public int getScoreJX() {
        return scoreJX;
    }
    public int getScoreJO() {
        return scoreJO;
    }
    public int getNombreP() {
        return nombreP;
    }
    public Popup getPopup() {
        return popup;
    }
}