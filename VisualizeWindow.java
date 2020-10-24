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


public class VisualizeWindow{
  //holds the evolving reference string text
  public static Label referenceString;
  //holds the evolving main memory contents
  public static Label mainMemory;
  //displays the number of page faults so far
  public static Label faults;
  //holds the object for the algorithm presently being traversed
  public static Algorithms algo;
  
  //creates window to display a step-by-step visualization
  public void start(Stage stage, Algorithms algo, int[] referenceString,int memorySize,String replacementAlgorithm){
    this.algo = algo;
    //format window
    stage.setTitle("Step-By-Step Visualization");
    stage.centerOnScreen();
    GridPane pane =initializePane();
    //populate text
    Label mainMemoryText = new Label("Main Memory");
    Label referenceStringText = new Label("Reference String");
    this.faults = new Label("Faults: 0");
    this.mainMemory = new Label("\t");
    this.referenceString = new Label(arrayToString(referenceString));
    //create buttons
    Button b1 = nextButton();
    //add text & buttons to pane
    pane.add(mainMemory,0,1);
    pane.add(this.referenceString,0,4);
    pane.add(this.faults, 0, 6);
    pane.add(mainMemoryText, 0, 0);
    pane.add(referenceStringText, 0, 3);
    pane.add(new Label("\r\n"),0,2);
    pane.add(new Label("\r\n"),0,5);
    pane.add(new Label("\r\n"),0,7);
    pane.add(b1, 5, 8);
    //set alignments
    pane.setHalignment(b1, HPos.RIGHT);
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
    return pane;
  }
  

  //initialize next Button
  //when clicked, the button advances the algorithm one step
  public static Button nextButton(){
    Button b1 = new Button( "       Next        ");
    b1.setPadding(new Insets(10));
    b1.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent event){
        try{
          algo.demandPage();
          referenceString.setText(arrayToString(algo.referenceString.toArray(new Page[0])));
          System.out.println("Made it");
          System.out.println(arrayToString(algo.mainMemory.toArray(new Page[0])));
          mainMemory.setText(arrayToString(algo.mainMemory.toArray(new Page[0])));
          faults.setText("Faults: " + algo.faults);
        } catch(Exception e){ e.printStackTrace(); System.out.println(e.getMessage() + "   " + "   " + e.getCause());}
      }
    });
    return b1;
  }
  
  
  //converts an array to a formatted string
  public static String arrayToString(int[] array){
     if(array.length==0)
       return "";
     return Arrays.toString(array).substring(1,Arrays.toString(array).length()-1);
  }
  
  //converts an array to a formatted string
  public static String arrayToString(Page[] array){
    if(array.length==0)
       return "";
    return Arrays.toString(array).substring(1,Arrays.toString(array).length()-1);
  }

}