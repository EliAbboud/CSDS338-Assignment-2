import javafx.scene.control.*;
import javafx.scene.Node.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.application.*;
import javafx.geometry.*;
import java.awt.Color;
import javafx.scene.text.*;
import javafx.event.*;
import java.util.*;
import java.util.stream.*;
import javafx.collections.*;
import javafx.*;
import java.io.*;
import com.sun.javafx.scene.control.skin.Utils;
import java.lang.reflect.*;
import javafx.scene.input.KeyEvent;


public class RunWindow{
  
  //create GUI
  public void start(Stage stage, int fault, int stringLength, String algoType){
    //format window
    stage.setTitle("Simulation Result");
    stage.centerOnScreen();
    GridPane pane =initializePane();
    //populate text
    Label prompt = new Label("There were " + fault+" page faults for a reference string of length "+stringLength +", using the "+algoType+" replacement algorithm.");
    pane.add(prompt,0,0);
    //display window
    Scene scene = new Scene(pane, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    stage.sizeToScene(); 
    stage.setScene(scene);
    stage.show();
  }
  
  
  
  //initialize pane
  public static GridPane initializePane(){
    GridPane pane = new GridPane();
    pane.setPadding(new Insets(20));
    //pane.setGridLinesVisible(true);
    return pane;
  }
  


  
  
  


}