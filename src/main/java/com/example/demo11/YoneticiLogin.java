package com.example.demo11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class YoneticiLogin {

    @FXML
    private TextField YAdi;

    @FXML
    private Button YgLoginButton;

    @FXML
    private Label YgWrongL;

    @FXML
    private PasswordField Ysifre;

    @FXML
    private ImageView backIconYG;

    @FXML
    private Pane manepane;

    @FXML
    private void YgLoginBClik(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        Javafx m = new Javafx();

        // KullanıcıYonetimi nesnesi
        KullaniciYonetimi kullaniciYonetimi = new KullaniciYonetimi();

        // Giriş bilgileri
        String kullaniciAdi = YAdi.getText().strip();
        String sifre = Ysifre.getText().strip();

        // Giriş kontrolü
        if (kullaniciYonetimi.girisKontrolY(kullaniciAdi, sifre)) {
            YgWrongL.setText("Giriş Başarılı.");
            m.changeScene("YoneticiEkran.fxml");
        } else if (kullaniciAdi.isEmpty() || sifre.isEmpty()) {
            YgWrongL.setText("Alanları Boş Bıraktınız!");
        } else {
            YgWrongL.setText("Yanlış Kullanıcı Adı Veya Şifre");
        }
    }

    @FXML
    void backiconYgclik(MouseEvent event) throws IOException {
        Javafx m=new Javafx();
        m.changeScene("Javafx.fxml");

    }


}
