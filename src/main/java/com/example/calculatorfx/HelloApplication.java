package com.example.calculatorfx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HelloApplication extends Application {

    Label label = new Label("");
    GridPane buttons = new GridPane();
    ListView<String> history = new ListView<>();

    public void start(Stage stage) throws IOException {
        stage.setMinWidth(400);
        stage.setMinHeight(200);
        stage.setMaxHeight(200);
        stage.setResizable(true);
        HBox root = new HBox();
        Scene scene =new Scene(root);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Calculator");
        //making vbox for the calculation
        VBox calculation = new VBox();

        calculation.setMaxWidth(50);
        calculation.setMaxHeight(50);
        //adding label and buttons to the vbox
        label.setMinHeight(30);
        label.setMinWidth(100);
        label.setAlignment(Pos.TOP_RIGHT);
        calculation.getChildren().add(label);
        calculation.getChildren().add(buttons);
        //adding number buttons
        for(int i=0; i<3; i++)
        {
            for(int c=0;c<3;c++) {
                buttons.add(createCalculatorButton(Integer.toString(c + 1)), c, i);
            }
            i++;
            for(int a=0;a<3;a++){
                buttons.add(createCalculatorButton(Integer.toString(a+4)),a,i);
            }
            i++;
            for(int b=0;b<3;b++){
                buttons.add(createCalculatorButton(Integer.toString(b+7)),b,i);
            }
        }
        buttons.add(createCalculatorButton("+"),3,0);
        buttons.add(createCalculatorButton("-"),3,1);
        buttons.add(createCalculatorButton("*"),3,2);
        buttons.add(Clear("c"),0,3);
        buttons.add(createCalculatorButton("0"),1,3);
        buttons.add(createCalculatorButton("/"),2,3);
        buttons.add(EqualsButton("="),3,3);
        stage.setScene(scene);
//making a list of the history from a listview
        history.setMinWidth(300);
        history.setMaxWidth(300);
        history.setMinHeight(120);
        history.setMaxHeight(120);
        history.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>
                () {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {

                //newValue will be the string that got clicked
                label.setText(newValue);
                isnew=false;
            }
        });
        root.getChildren().add(history);
        root.getChildren().add(calculation);
        stage.show();

    }

    //The following is functions

    //this field checks if the label is new or empty
    private boolean isnew =true;

    //method that checks to see if the expression in the label is right
    private boolean isExpressionCorrect(){
        if(label.getText().matches("^[0-9]+([\\+\\-\\*\\/][0-9]+)*")){
         return true;
        }
        return false;
    }

    //method that adds a clear button
    private Button Clear(String character){
        Button clear = new Button(character);
        clear.minHeight(100);
        clear.minWidth(50);
        clear.setOnAction(actionEvent -> {
            label.setText("");
            isnew=true;
        });
        return clear;
    }

    //method that adds on equal button
    private Button EqualsButton(String character){
        Button equals = new Button(character);
        equals.minHeight(100);
        equals.minWidth(50);
        equals.setOnAction(actionEvent -> {
            //Evaluate goes here
            history.getItems().add(label.getText());
            isnew=true;
        });
        return equals;
    }



    //styles the label
    private void styleLabel(){
        if(isExpressionCorrect()){
            label.getStyleClass().clear();
            label.getStyleClass().addAll("lbl","lbl-success");
        }
        else{
            label.getStyleClass().clear();
            label.getStyleClass().addAll("lbl","lbl-danger");
        }
    }
    //method that adds the rest off the buttons
    private Button createCalculatorButton(String character){
     Button button = new Button(character);
     button.minHeight(100);
     button.minWidth(50);
     button.setOnAction(actionEvent -> {
     if(isnew){
         label.setText("");
         label.setText(character+"");
         isnew=false;
         styleLabel();
     }
     else {
         label.setText(label.getText() + character);
         styleLabel();
     }
     });

        return button;
    }

    public static void main(String[] args) {launch(args);}
}