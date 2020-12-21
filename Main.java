package sample;

import javafx.application.Application;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;
import java.util.Scanner;

import javafx.scene.layout.FlowPane;


public class Main extends Application {

    private static double x;
    private static double y;
    private static double z;
    double pi=3.1415;
    public void DisplayMem(Label label, Label label2, Label label3, Double mem[])
    {
       label.setText("Mem1: "+mem[0]);
       label2.setText("Mem2: "+mem[1]);
       label3.setText("Mem3: "+mem[2]);
    }
    public double value1(double x, double y, double z)
    {
        double numerator=Math.sin(pi*y*y)+Math.log(y*y);
        double denominator=Math.sin(pi*z*z)+Math.sin(x)+Math.log(z*z)+(x*x)+Math.exp(Math.cos(z*x));
        return numerator/denominator;
    }
    public double value2(double x, double y, double z)
    {
        double numerator=Math.pow(Math.cos(Math.exp(y))+Math.exp(y*y+(1/(Math.pow(x,0.5)))),0.25);
        double denominator=Math.pow(Math.cos(pi*Math.pow(z,3))+Math.log(Math.pow((1+z),2)),Math.sin(y));
        return numerator/denominator;
    }
    public void MemPlus(String r, Double mem[], Double Func, Label label)
    {
        if(r.equals("Mem1"))
        {
            mem[0]=mem[0]+Func;
            label.setText(""+mem[0]);
        } else if(r.equals("Mem2")) {
            mem[1] = mem[1]+Func;
            label.setText(""+mem[1]);
        }else if(r.equals("Mem3"))
        {
            mem[2]=mem[2]+Func;
            label.setText(""+mem[2]);
        }
    }
    public void MemC(String r, Double mem[], Label label)
    {
        if(r.equals("Mem1"))
        {
            mem[0]=null;
            label.setText(""+mem[0]);
        } else if(r.equals("Mem2")) {
            mem[1] = null;
            label.setText(""+mem[1]);
        }else if(r.equals("Mem3"))
        {
            mem[2]=null;
            label.setText(""+mem[2]);
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Label selectedLbl = new Label();
        ToggleGroup FunctionGroup=new ToggleGroup();
        FileInputStream inputstream = new FileInputStream("C:\\Users\\Денис\\Pictures\\Function1.bmp");
        Image image = new Image(inputstream);
        FileInputStream inputstream1 = new FileInputStream("C:\\Users\\Денис\\Pictures\\Function2.bmp");
        Image image1 = new Image(inputstream1);
        ImageView imageView = new ImageView();
        Label Memory1Display= new Label();
        Label Memory2Display= new Label();
        Label Memory3Display= new Label();
        RadioButton RadioF1=new RadioButton("Function 1");
        RadioButton RadioF2= new RadioButton("Function 2");

        RadioF1.setToggleGroup(FunctionGroup);
        RadioF2.setToggleGroup(FunctionGroup);
        final Double[] FunctionResult=new Double[1];
        RadioF1.setOnAction(event ->
        {
            selectedLbl.setText("Value = " + value1(x, y, z));
            FunctionResult[0]=value1(x,y,z);
            imageView.setImage(image);
        });
        RadioF2.setOnAction(event ->
        {
            selectedLbl.setText("Value = " + value2(x, y, z));
            FunctionResult[0]=value2(x,y,z);
            imageView.setImage(image1);
        });
        //MC M+
        Button SelectM1=new Button("mem1");
        Button SelectM2 = new Button("mem2");
        Button SelectM3 = new Button("mem3");
        ToggleGroup Memory = new ToggleGroup();
        RadioButton ButtonOnMem1=new RadioButton("mem1");
        RadioButton ButtonOnMem2 = new RadioButton("mem2");
        RadioButton ButtonOnMem3 = new RadioButton("mem3");
        ButtonOnMem1.setToggleGroup(Memory);
        ButtonOnMem2.setToggleGroup(Memory);
        ButtonOnMem3.setToggleGroup(Memory);
        final Double[] mem1 = new Double[3];
        final Double[] temp = new Double[1];
        TextField textField = new TextField();
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                temp[0] =Double.parseDouble(newValue);
            }
        });
        SelectM1.setOnAction(event ->
        {
            mem1[0] =temp[0];
            DisplayMem(Memory1Display,Memory2Display,Memory3Display,mem1);
        });
        SelectM2.setOnAction(event ->
        {
            mem1[1] =temp[0];
            DisplayMem(Memory1Display,Memory2Display,Memory3Display,mem1);
        });
        SelectM3.setOnAction(event ->
        {
            mem1[2] =temp[0];
            DisplayMem(Memory1Display,Memory2Display,Memory3Display,mem1);
        });
        Button M = new Button("M+");
        Button MC = new Button("MC");
        final String[] Sel = {""};
        ButtonOnMem1.setOnAction(event ->
        {
            Sel[0] = "Mem1";DisplayMem(Memory1Display,Memory2Display,Memory3Display,mem1);
        });
        ButtonOnMem2.setOnAction(event ->
        {
            Sel[0] = "Mem2";DisplayMem(Memory1Display,Memory2Display,Memory3Display,mem1);
        });
        ButtonOnMem3.setOnAction(event ->
        {
            Sel[0] = "Mem3";DisplayMem(Memory1Display,Memory2Display,Memory3Display,mem1);
        });

        MC.setOnAction(event ->
        {
            MemC(Sel[0], mem1, selectedLbl);
            DisplayMem(Memory1Display,Memory2Display,Memory3Display,mem1);
        });
        M.setOnAction(event ->
        {
            MemPlus(Sel[0], mem1, FunctionResult[0], selectedLbl);
            DisplayMem(Memory1Display, Memory2Display, Memory3Display, mem1);
        }
        );
        //
        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10);
        root.getChildren().addAll(RadioF1, RadioF2, selectedLbl, imageView, SelectM1, SelectM2, SelectM3,
                ButtonOnMem1, ButtonOnMem2, ButtonOnMem3, M, MC, textField, Memory1Display, Memory2Display, Memory3Display);
        root.setPadding(new Insets(10));
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.println("Select x, y, z");
        Scanner s=new Scanner(System.in);
        x = s.nextDouble();
        y = s.nextDouble();
        z = s.nextDouble();
        Application.launch(args);
    }
}
