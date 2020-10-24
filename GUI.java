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


public class GUI extends Application{
  //holds user input for the maximum number of frames in memory
  public static ComboBox frames;
  //holds user input for the desired algorithm
  public static ComboBox replacementAlgorithm;
  //holds user input for the desired reference string length
  public static ComboBox referenceCount;
  //holds user input for the pages' upper bound 
  public static ComboBox pageBound;
  //holds user input indicating the desired probability distribution
  public static ComboBox pageProbabilities;
  
  //initializes landing pane
  public void start(Stage stage){
    //creates window
    formatStage(stage);
    GridPane pane =initializePane();
    //populate text
    Label simulator = new Label("Simulator");
    Label frames = new Label("Frames in Main Memory: ");
    Label replacementAlgorithm = new Label("Replacement Algorithm: ");
    Label referenceCount = new Label("Number of References: ");
    Label pageBound = new Label("Page Upper Bound: ");
    Label pageProbabilities = new Label("Probability Distribution: ");
    //create buttons
    Button b1 = runButton();
    Button b2 = visualizeButton();
    //add text & buttons to pane
    pane.add(simulator, 3, 0);
    pane.add(new Label("\r\n"),0,1);
    pane.add(frames, 1, 2);
    pane.add(new Label("\r\n"),0,3);
    pane.add(replacementAlgorithm, 1, 4);
    pane.add(new Label("\r\n"),0,5);
    pane.add(referenceCount, 1, 6);
    pane.add(pageBound, 4, 2);
    pane.add(pageProbabilities, 4, 4);
    pane.add(new Label("\r\n"),0,7);
    pane.add(b1, 2, 8);
    pane.add(b2, 4, 8);
    //create and add combo boxes
    List<Integer> range = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
    List<String> stringRange = range.stream().map(Object::toString).collect(Collectors.toList());
    this.frames = createComboBox(pane, 2, 2, stringRange.toArray(new String[0]));
    this.pageBound = createComboBox(pane, 5, 2, stringRange.toArray(new String[0]));
    this.referenceCount = createComboBox(pane, 2, 6, stringRange.toArray(new String[0]));
    this.pageProbabilities = createComboBox(pane, 5, 4, "Part A", "Part B", "Part C");
    this.replacementAlgorithm = createComboBox(pane, 2, 4, "Clock", "FIFO");
    //set alignments
    pane.setHalignment(simulator, HPos.CENTER);
    pane.setHalignment(frames, HPos.RIGHT);
    pane.setHalignment(replacementAlgorithm, HPos.RIGHT);
    pane.setHalignment(referenceCount, HPos.RIGHT);
    pane.setHalignment(pageBound, HPos.RIGHT);
    pane.setHalignment(pageProbabilities, HPos.RIGHT);
    pane.setHalignment(b1, HPos.RIGHT);
    //display window
    Scene scene = new Scene(pane, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    stage.sizeToScene(); 
    stage.setScene(scene);
    stage.show();
  }
  
  
  //format & center stage
  public static void formatStage(Stage stage){
    stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
      public void handle(WindowEvent event){
        stage.hide();
        System.exit(0);
      }});
    stage.setTitle("Page Replacement Alogorithm Simulator");
    stage.centerOnScreen();
  }
  
  //initialize pane
  public static GridPane initializePane(){
    GridPane pane = new GridPane();
    pane.setPadding(new Insets(20));
    return pane;
  }
  

  //initializes run Button
  //if the run button is clicked, the appropriate algorithm is executed and a pop-up window is created to display the result
  public static Button runButton(){
    Button b1 = new Button( "       Run        ");
    b1.setPadding(new Insets(10));
    b1.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent event){
        try{
          int[] referenceString = ReferenceStringGenerator.referenceString(referenceCount.getValue(), pageBound.getValue(), pageProbabilities.getValue());
          Algorithms algo = new Algorithms(replacementAlgorithm.getValue(), frames.getValue(), referenceString);
          (new RunWindow()).start(new Stage(),algo.run(),Integer.parseInt((String)referenceCount.getValue()),(String)replacementAlgorithm.getValue());
        } catch(Exception e){ e.printStackTrace(); System.out.println(e.getMessage() + "   " + "   " + e.getCause());}
      }
    });
    return b1;
  }
  
  //initialize Visualize Button
  //if the Visualize button is clicked, a step-by-step simulation is shown
  public static Button visualizeButton(){
    Button b2 = new Button("  Visualize  ");
    b2.setPadding(new Insets(10));
    b2.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent event){
        try{
        int[] referenceString = ReferenceStringGenerator.referenceString(referenceCount.getValue(), pageBound.getValue(), pageProbabilities.getValue());
        Algorithms algo = new Algorithms(replacementAlgorithm.getValue(), frames.getValue(), referenceString);
       (new VisualizeWindow()).start(new Stage(), algo, referenceString, Integer.parseInt((String)frames.getValue()), (String)replacementAlgorithm.getValue());
        } catch(Exception e){ e.printStackTrace(); System.out.println(e.getMessage() + "   " + "   " + e.getCause());}
      }
    });
    return b2;
  }
  
  //more convenient method for creating ComboBoxes
  public static ComboBox createComboBox(GridPane pane, int x, int y, String ...contents){ 
    ComboBox comboBox = new ComboBox();
    comboBox.getItems().addAll(contents);
    comboBox.setPromptText("            ");
    comboBox.setEditable(false);        
    pane.add(comboBox, x, y);
    return comboBox;
  }
  


  
  public static void main(String[] args){
    launch(args);
  }
}