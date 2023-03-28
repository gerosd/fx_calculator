package com.example.gui_calculator;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {
    // Create a TextField object to display the calculation result
    TextField textField = new TextField();

    @Override
    public void start(Stage stage) {
        // Create a GridPane object to lay out the calculator buttons
        GridPane gridPane = new GridPane();

        // Set the horizontal and vertical gap between buttons
        gridPane.setHgap(13);
        gridPane.setVgap(13);

        // Create Text objects to display "Result:" and "Neg - negative number(-)"
        Text text = new Text("Result: ");
        Text otrText = new Text("Neg - negative number(-)");

        // Create Button objects for all the calculator buttons
        Button clr = new Button("CLR");
        Button one = new Button("1");
        Button two = new Button("2");
        Button three = new Button("3");
        Button four = new Button("4");
        Button five = new Button("5");
        Button six = new Button("6");
        Button seven = new Button("7");
        Button eight = new Button("8");
        Button nine = new Button("9");
        Button zero = new Button("0");
        Button plus = new Button("+");
        Button minus = new Button("-");
        Button neg = new Button("отр");
        Button times = new Button("*");
        Button division = new Button("/");
        Button equals = new Button("=");
        Button point = new Button(",");
        Button c = new Button("c");

        // Add all the buttons and Text objects to the GridPane
        gridPane.add(point, 1, 4);
        gridPane.add(text, 0, 0); //just add
        gridPane.add(equals, 3, 4);
        gridPane.add(one, 1, 1);
        gridPane.add(two, 2, 1);
        gridPane.add(three, 3, 1);
        gridPane.add(four, 1, 2);
        gridPane.add(five, 2, 2);
        gridPane.add(six, 3, 2);
        gridPane.add(seven, 1, 3);
        gridPane.add(otrText, 0, 5);
        GridPane.setColumnSpan(otrText, 6);
        gridPane.add(eight, 2, 3);
        gridPane.add(nine, 3, 3);
        gridPane.add(zero, 2, 4);
        gridPane.add(clr, 5, 1);
        gridPane.add(plus, 4, 1);
        gridPane.add(textField, 1, 0);
        GridPane.setColumnSpan(textField, 5);
        gridPane.add(minus, 4, 2);
        gridPane.add(times, 4, 3);
        gridPane.add(division, 4, 4);
        gridPane.add(c, 0, 1);
        gridPane.add(neg, 0, 2);

        // Set the horizontal and vertical alignment of buttons 2, 5, and
        GridPane.setHalignment(two, HPos.CENTER);
        GridPane.setValignment(two, VPos.CENTER);

        GridPane.setHalignment(five, HPos.CENTER);
        GridPane.setValignment(five, VPos.CENTER);

        GridPane.setHalignment(eight, HPos.CENTER);
        GridPane.setValignment(eight, VPos.CENTER);

        // Binding actions with TextField to buttons
        neg.setOnAction(event -> textField.appendText(" -"));
        one.setOnAction(event -> textField.appendText("1"));
        two.setOnAction(event -> textField.appendText("2"));
        three.setOnAction(event -> textField.appendText("3"));
        four.setOnAction(event -> textField.appendText("4"));
        five.setOnAction(event -> textField.appendText("5"));
        six.setOnAction(event -> textField.appendText("6"));
        seven.setOnAction(event -> textField.appendText("7"));
        eight.setOnAction(event -> textField.appendText("8"));
        nine.setOnAction(event -> textField.appendText("9"));
        zero.setOnAction(event -> textField.appendText("0"));
        plus.setOnAction(event -> textField.appendText(" + "));
        minus.setOnAction(event -> textField.appendText(" - "));
        times.setOnAction(event -> textField.appendText(" * "));
        division.setOnAction(event -> textField.appendText(" / "));
        point.setOnAction(event -> textField.appendText("."));
        equals.setOnAction(event -> equalsAction());

        c.setOnAction(event -> cAction());

        clr.setOnAction(event -> textField.clear());
        gridPane.setPadding(new Insets(10,10,10,10));

        // Set scene
        Scene scene = new Scene(gridPane);
        stage.setTitle("Calculator");
        stage.getIcons().add(new Image("\\images\\Calculator-icon.png"));
        stage.setMaxHeight(290);
        stage.setMaxWidth(290);
        stage.setMinWidth(290);
        stage.setMinHeight(290);
        stage.setScene(scene);
        stage.show();
    }

    private void cAction() {
        // Get the current text in the text field
        String txt = textField.getText();

        // If the text is not empty and does not end with a space, remove the last character
        if (!txt.isEmpty() && !(txt.endsWith(" "))) {
            textField.setText(txt.substring(0, txt.length() - 1));
        }
        // If the text ends with a space, remove the last three characters
        else if ((txt.endsWith(" "))) {
            textField.setText(txt.substring(0, txt.length() - 3));
        }
    }

    private void equalsAction() {
        try {
            // Get the current text in the text field
            String expression = textField.getText();

            // Calculate the result of the expression using the Calculator class
            textField.setText(Double.toString(Calculator.calculate(expression)));

        } catch (Exception e) {
            // If an exception is thrown while calculating the result, display "Error" in the text field
            textField.setText("Error");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}