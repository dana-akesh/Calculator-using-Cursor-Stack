package com.example.project2;

import DataStructure.CursorArray;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static DataStructure.CursorArray.isBalancedDelimiter;
import static DataStructure.InfixToPostfix.evaluatePostfix;
import static DataStructure.InfixToPostfix.infixToPostfix;

public class Driver extends Application {

    public static void main(String[] args) {
        try {
            Application.launch();
//            System.out.println(infixToPostfix("4+2*3"));
//            System.out.println(infixToPostfix("1/2*(3+(4—5))"));
//            System.out.println(infixToPostfix("(4—5)"));
//            System.out.println(infixToPostfix("(44—5)"));
//            System.out.println(infixToPostfix("(44—5/2)"));
//            System.out.println(infixToPostfix("7—"));
//            System.out.println(infixToPostfix("log(9)"));
//            System.out.println(infixToPostfix("Cos(9)"));
//            System.out.println(infixToPostfix("Sin(Cos(8))"));
//            System.out.println(infixToPostfix("LN(3)"));
//            System.out.println(infixToPostfix(""));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        VBox calculator = new VBox(20);
        calculator.setAlignment(Pos.CENTER);
        calculator.setStyle("-fx-background-color: rgba(218,218,218,0.45);");

        VBox screen = new VBox(0);
        calculator.setPadding(new Insets(15, 15, 30, 12));
        screen.setStyle("-fx-border-color: lightGray;");

        TextField expression = new TextField();
        expression.setEditable(false);
        expression.setMinSize(300, 50);
        expression.setStyle("-fx-border-color: lightgray; -fx-background-color: white;");

        TextField postfix = new TextField();
        postfix.setEditable(false);
        postfix.setMinSize(300, 50);
        postfix.setStyle("-fx-border-color: lightgray; -fx-background-color: white;");

        TextField result = new TextField();
        result.setEditable(false);
        result.setMinSize(300, 50);
        result.setStyle(" -fx-background-color: white; -fx-border-color: lightgray;");
        screen.getChildren().addAll(new Label("Expression:"), expression, new Label("Postfix:"), postfix, new Label("Result:"), result);

        GridPane buttons = new GridPane();
        buttons.setHgap(5);
        buttons.setVgap(5);
        buttons.setAlignment(Pos.CENTER);

        Button sinB = new Button("Sin");
        buttons.add(sinB, 1, 1);
        sinB.setMinSize(40, 20);
        sinB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        sinB.setOnAction(e -> {
            expression.appendText("Sin(");
        });

        Button cosB = new Button("Cos");
        buttons.add(cosB, 2, 1);
        cosB.setMinSize(40, 20);
        cosB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        cosB.setOnAction(e -> {
            expression.appendText("Cos(");
        });

        Button tanB = new Button("Tan");
        buttons.add(tanB, 3, 1);
        tanB.setMinSize(40, 20);
        tanB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        tanB.setOnAction(e -> {
            expression.appendText("Tan(");
        });

        Button leftBracketB = new Button("(");
        buttons.add(leftBracketB, 4, 1);
        leftBracketB.setMinSize(40, 20);
        leftBracketB.setStyle("-fx-alignment: center; -fx-background-color: white; -fx-font-weight: bold");
        leftBracketB.setOnAction(e -> {
            expression.appendText("(");
        });

        Button rightBracketB = new Button(")");
        buttons.add(rightBracketB, 5, 1);
        rightBracketB.setMinSize(40, 20);
        rightBracketB.setStyle("-fx-alignment: center; -fx-background-color: white; -fx-font-weight: bold");
        rightBracketB.setOnAction(e -> {
            expression.appendText(")");
        });

        Button factorialB = new Button("!");
        buttons.add(factorialB, 6, 1);
        factorialB.setMinSize(40, 20);
        factorialB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        factorialB.setOnAction(e -> {
            expression.appendText(" !");
        });

        Button offB = new Button("OFF");
        buttons.add(offB, 7, 1);
        offB.setMinSize(40, 20);
        offB.setStyle("-fx-alignment: center; -fx-background-color: lightgreen; -fx-font-weight: bold");
        offB.setOnAction(e -> System.exit(0));

        Button piB = new Button("π");
        buttons.add(piB, 1, 2);
        piB.setMinSize(40, 40);
        piB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        piB.setOnAction(e -> {
            expression.appendText(String.valueOf(3.14159265359));
        });

        Button exponentsB = new Button("y^x");
        buttons.add(exponentsB, 2, 2);
        exponentsB.setMinSize(40, 40);
        exponentsB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        exponentsB.setOnAction(e -> {
            expression.appendText("^");
        });

        Button squareB = new Button("x^2");
        buttons.add(squareB, 1, 3);
        squareB.setMinSize(40, 40);
        squareB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        squareB.setOnAction(e -> {
            expression.appendText("^2");
        });

        Button squareRootB = new Button("√x");
        buttons.add(squareRootB, 2, 3);
        squareRootB.setMinSize(40, 40);
        squareRootB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        squareRootB.setOnAction(e -> {
            expression.appendText(" √ ");
        });

        Button exponentialB = new Button("e^x");
        buttons.add(exponentialB, 1, 4);
        exponentialB.setMinSize(40, 40);
        exponentialB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        exponentialB.setOnAction(e -> {
            expression.appendText(Math.exp(1) + "^");
        });

        Button naturalLogarithmicB = new Button("LN");
        buttons.add(naturalLogarithmicB, 2, 4);
        naturalLogarithmicB.setMinSize(40, 40);
        naturalLogarithmicB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        naturalLogarithmicB.setOnAction(e -> {
            expression.appendText("LN(");
        });

        Button logarithmsB = new Button("log");
        buttons.add(logarithmsB, 1, 5);
        logarithmsB.setMinSize(40, 40);
        logarithmsB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        logarithmsB.setOnAction(e -> {
            expression.appendText("log(");
        });

        Button fractionB = new Button("1/x");
        buttons.add(fractionB, 2, 5);
        fractionB.setMinSize(40, 40);
        fractionB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        fractionB.setOnAction(e -> {
            expression.appendText("1 /");
        });

        Button negationB = new Button("(-)");
        buttons.add(negationB, 7, 2);
        negationB.setMinSize(40, 40);
        negationB.setStyle("-fx-alignment: center; -fx-background-color: lightblue; -fx-font-weight: bold");
        negationB.setOnAction(e -> {
            expression.appendText("-");
        });

        Button CAB = new Button("CA");
        buttons.add(CAB, 7, 3);
        CAB.setMinSize(40, 40);
        CAB.setStyle("-fx-alignment: center; -fx-background-color: rgba(255,0,0,0.44); -fx-font-weight: bold");
        CAB.setOnAction(e -> {
            expression.clear();
            postfix.clear();
            result.clear();
        });

        Button CB = new Button("C");
        buttons.add(CB, 7, 4);
        CB.setMinSize(40, 40);
        CB.setStyle("-fx-alignment: center; -fx-background-color: #ffdfa9; -fx-font-weight: bold");
        CB.setOnAction(e -> {
            if (!expression.getText().isEmpty())
                expression.setText(expression.getText().substring(0, expression.getText().length() - 1));
        });

        Button backB = new Button("<-");
        buttons.add(backB, 7, 5);
        backB.setMinSize(40, 40);
        backB.setStyle("-fx-alignment: center; -fx-background-color: lightpink; -fx-font-weight: bold");
        backB.setOnAction(e -> {
        });

        Button plusB = new Button("+");
        buttons.add(plusB, 6, 5);
        plusB.setMinSize(40, 40);
        plusB.setStyle("-fx-alignment: center; -fx-background-color: lightyellow; -fx-font-weight: bold");
        plusB.setOnAction(e -> {
            expression.appendText("+");
        });

        Button subtractB = new Button("—");
        buttons.add(subtractB, 6, 4);
        subtractB.setMinSize(40, 40);
        subtractB.setStyle("-fx-alignment: center; -fx-background-color: lightyellow; -fx-font-weight: bold");
        subtractB.setOnAction(e -> {
            expression.appendText("—");
        });

        Button multiplyB = new Button("*");
        buttons.add(multiplyB, 6, 3);
        multiplyB.setMinSize(40, 40);
        multiplyB.setStyle("-fx-alignment: center; -fx-background-color: lightyellow; -fx-font-weight: bold");
        multiplyB.setOnAction(e -> {
            expression.appendText("*");
        });

        Button divideB = new Button("/");
        buttons.add(divideB, 6, 2);
        divideB.setMinSize(40, 40);
        divideB.setStyle("-fx-alignment: center; -fx-background-color: lightyellow; -fx-font-weight: bold");
        divideB.setOnAction(e -> {
            expression.appendText(" / ");
        });

        Button modB = new Button("%");
        buttons.add(modB, 5, 5);
        modB.setMinSize(40, 40);
        modB.setStyle("-fx-alignment: center; -fx-background-color: lightyellow; -fx-font-weight: bold");
        modB.setOnAction(e -> {
            expression.appendText(" % ");
        });

        Button dotB = new Button(".");
        buttons.add(dotB, 4, 5);
        dotB.setMinSize(40, 40);
        dotB.setStyle("-fx-alignment: center; -fx-background-color: white; -fx-font-weight: bold");
        dotB.setOnAction(e -> {
            expression.appendText(".");
        });

        Button equalsB = new Button("=");
        equalsB.setMinSize(300, 40);
        equalsB.setStyle("-fx-alignment: center; -fx-background-color: lightyellow; -fx-font-weight: bold");
        equalsB.setOnAction(e -> {
            //  todo here
            try {
                if (expression.getText().trim() != null || !expression.getText().trim().isEmpty()) {
                    if (isBalancedDelimiter(expression.getText().trim())) {
                        postfix.setText(infixToPostfix(expression.getText().trim()));
                        result.setText(" " + evaluatePostfix(infixToPostfix(expression.getText().trim())));
                    } else {
                        failAlert("Missing bracket.");
                    }
                }
            } catch (Exception e1) {
                failAlert("An Error Occurred");
            }
        });

        Button zeroB = new Button("0");
        buttons.add(zeroB, 3, 5);
        zeroB.setMinSize(40, 40);
        zeroB.setStyle("-fx-alignment: center; -fx-background-color: pink; -fx-font-weight: bold");
        zeroB.setOnAction(e -> {
            expression.appendText("0");
        });

        Button oneB = new Button("1");
        buttons.add(oneB, 3, 4);
        oneB.setMinSize(40, 40);
        oneB.setStyle("-fx-alignment: center; -fx-background-color: pink; -fx-font-weight: bold");
        oneB.setOnAction(e -> {
            expression.appendText("1");
        });

        Button twoB = new Button("2");
        buttons.add(twoB, 4, 4);
        twoB.setMinSize(40, 40);
        twoB.setStyle("-fx-alignment: center; -fx-background-color: pink; -fx-font-weight: bold");
        twoB.setOnAction(e -> {
            expression.appendText("2");
        });

        Button threeB = new Button("3");
        buttons.add(threeB, 5, 4);
        threeB.setMinSize(40, 40);
        threeB.setStyle("-fx-alignment: center; -fx-background-color: pink; -fx-font-weight: bold");
        threeB.setOnAction(e -> {
            expression.appendText("3");
        });

        Button fourB = new Button("4");
        buttons.add(fourB, 3, 3);
        fourB.setMinSize(40, 40);
        fourB.setStyle("-fx-alignment: center; -fx-background-color: pink; -fx-font-weight: bold");
        fourB.setOnAction(e -> {
            expression.appendText("4");
        });

        Button fiveB = new Button("5");
        buttons.add(fiveB, 4, 3);
        fiveB.setMinSize(40, 40);
        fiveB.setStyle("-fx-alignment: center; -fx-background-color: pink; -fx-font-weight: bold");
        fiveB.setOnAction(e -> {
            expression.appendText("5");
        });

        Button sixB = new Button("6");
        buttons.add(sixB, 5, 3);
        sixB.setMinSize(40, 40);
        sixB.setStyle("-fx-alignment: center; -fx-background-color: pink; -fx-font-weight: bold");
        sixB.setOnAction(e -> {
            expression.appendText("6");
        });

        Button sevenB = new Button("7");
        buttons.add(sevenB, 3, 2);
        sevenB.setMinSize(40, 40);
        sevenB.setStyle("-fx-alignment: center; -fx-background-color: pink; -fx-font-weight: bold");
        sevenB.setOnAction(e -> {
            expression.appendText("7");
        });

        Button eightB = new Button("8");
        buttons.add(eightB, 4, 2);
        eightB.setMinSize(40, 40);
        eightB.setStyle("-fx-alignment: center; -fx-background-color: pink; -fx-font-weight: bold");
        eightB.setOnAction(e -> {
            expression.appendText("8");
        });

        Button nineB = new Button("9");
        buttons.add(nineB, 5, 2);
        nineB.setMinSize(40, 40);
        nineB.setStyle("-fx-alignment: center; -fx-background-color: pink; -fx-font-weight: bold");
        nineB.setOnAction(e -> {
            expression.appendText("9");
        });

        calculator.getChildren().addAll(screen, buttons, equalsB);
        Scene scene = new Scene(calculator, 380, 540);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void failAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.show();
    }

}
