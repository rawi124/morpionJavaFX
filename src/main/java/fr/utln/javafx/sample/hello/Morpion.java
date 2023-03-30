package fr.utln.javafx.sample.hello;

//import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.Random;


/**

 * @author Ben Amira Rawia
 */
@SuppressWarnings("Convert2Lambda")
public class Morpion extends Application {

   @Override
   public void start(Stage stagePrincipale) {
      Controller.affichage(stagePrincipale);
   }

}