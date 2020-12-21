package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class Controller {
    @FXML
    private ToggleGroup FunctionGroup=new ToggleGroup();

    @FXML
    private RadioButton RadioF1=new RadioButton("Function 1");

    @FXML
    private RadioButton RadioF2= new RadioButton("Function 2");


}

/*CalcValue1.setOnAction(event ->
        {
            double pi=3.1415;
            double y=0, x=0, z=0;
            double numerator=Math.sin(pi*y*y)+Math.log(y*y);
            double denominator=Math.sin(pi*z*z)+Math.sin(x)+Math.log(z*z)+(x*x)+Math.exp(Math.cos(z*x));
            System.out.println(""+numerator/denominator);
        System.out.println("pidor");

        });*/
