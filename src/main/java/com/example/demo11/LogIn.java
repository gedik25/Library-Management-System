package com.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LogIn {

    @FXML
    private Pane manepane;

    @FXML
    private Button yoneticibutton;

    @FXML
    private Button ziyaretcibutton;

    @FXML
    public void ybuttonclik(ActionEvent event) throws IOException {
        Javafx m=new Javafx();
        m.changeScene("YoneticiLogin.fxml");

    }

    @FXML
    public void zbutonclik(ActionEvent event) throws IOException {
        Javafx m=new Javafx();
        m.changeScene("ZiyaretciLogin.fxml");

    }



}
