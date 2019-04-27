package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;


public class Controller {

    public Controller() {
    }

    @FXML
    void initialize()
    {
        pokaz_gracza.setText("O");
        restart_button.setStyle("-fx-background-color:\n" +
                "        linear-gradient(#f0ff35, #a9ff00),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);\n" +
                "    -fx-background-radius: 6, 5;\n" +
                "    -fx-background-insets: 0, 1;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-text-fill: #395306;");
        grid_pane.setStyle("-fx-background-color: #2f4f4f;");

    }
    @FXML
    Button pokaz_gracza;
    @FXML
    void Wybor(ActionEvent a)
    {


        if(gracz==false)gracz=true;
        else if(gracz==true)gracz=false;

        //System.out.println(gracz);

        if (gracz==true) ((Button)a.getSource()).setText("X");
        else if (gracz==false) ((Button)a.getSource()).setText("O");

        ((Button)a.getSource()).setDisable(true);
        przyciski.add((Button)a.getSource());


        String s=((Button)a.getSource()).getId();
        Integer w=Integer.parseInt(s.substring(s.indexOf('_')+1));

        if(gracz==true)
        {
            gracz_1.add(w);
            licznik=licznik+0.5;
            sprawdzam(gracz_1,trafienie_true);
            pokaz_gracza.setText("O");


        }

        if(gracz==false)
        {
            gracz_2.add(w);
            licznik=licznik+0.5;
            sprawdzam(gracz_2,trafienie_false);
            pokaz_gracza.setText("X");

        }

    }


    void sprawdzam(ArrayList gracz,int trafienia)
    {
        for(int i=0;i<gracz.size();i++)
        {
            for(int q=0;q<=7;q++)
            {
                for (int r=0;r<3;r++)
                {
                    if(gracz.contains(liczby[q][r]))
                    {
                        trafienia++;
                        if (trafienia==3) oknow_dialogowe();

                    }


                }

                trafienia=0;
            }

        }

        liczby[0][0]=1;
        liczby[0][1]=4;
        liczby[0][2]=7;
        liczby[1][0]=2;
        liczby[1][1]=5;
        liczby[1][2]=8;
        liczby[2][0]=3;
        liczby[2][1]=6;
        liczby[2][2]=9;
        liczby[3][0]=1;
        liczby[3][1]=2;
        liczby[3][2]=3;
        liczby[4][0]=4;
        liczby[4][1]=5;
        liczby[4][2]=6;
        liczby[5][0]=7;
        liczby[5][1]=8;
        liczby[5][2]=9;
        liczby[6][0]=1;
        liczby[6][1]=5;
        liczby[6][2]=9;
        liczby[7][0]=3;
        liczby[7][1]=5;
        liczby[7][2]=7;

    }
    @FXML
    void resetuj()
    {

        gracz_1.clear();
        gracz_2.clear();
        licznik=0;
        trafienie_false=0;
        trafienie_true=0;
        for(Button kwa: przyciski)
        {
            kwa.setDisable(false);
            kwa.setText("");
        }

    }

         void oknow_dialogowe()
    {
        Alert wygrana=new Alert(Alert.AlertType.NONE);

        wygrana.setTitle("Wygrana!");
        wygrana.setHeaderText("Gratuluje,udało Ci się!");
        ButtonType opcja_jeszcze_raz=new ButtonType("Jeszcze raz!");
        ButtonType opcja_wyjdz=new ButtonType("Wyjdź");
        wygrana.getButtonTypes().addAll(opcja_jeszcze_raz,opcja_wyjdz);
        Optional decyzja=wygrana.showAndWait();
        if (decyzja.get()==opcja_wyjdz) System.exit(0);
        if (decyzja.get()==opcja_jeszcze_raz) resetuj();



    }



    //true-gracz pierwszy false-gracz drugi
    Boolean gracz=true;
    @FXML
    Button restart_button;
    @FXML
    GridPane grid_pane;
    private int trafienie_true=0;
    private int trafienie_false=0;
    private double licznik=0;
    private int[][] liczby=new int[8][3];
    private ArrayList<Integer> gracz_1=new ArrayList();
    private ArrayList<Integer> gracz_2=new ArrayList();
    private ArrayList<Button> przyciski=new ArrayList<>();

}
