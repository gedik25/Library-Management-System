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

public class ZiyaretciLogin {

    @FXML
    private TextField ZAdi;

    @FXML
    private Label ZgWrongL;

    @FXML
    private Button ZgLoginButton;

    @FXML
    private PasswordField Zsifre;

    @FXML
    private ImageView backIconZG;

    @FXML
    private Pane manepane;

    @FXML
    private void ZgLoginBClik(ActionEvent event) throws IOException {
    checkLogin();
    }

    private void checkLogin() throws IOException {
        Javafx m = new Javafx();

        // KullanıcıYonetimi nesnesi
        KullaniciYonetimi kullaniciYonetimi = new KullaniciYonetimi();

        // Giriş bilgileri
        String kullaniciAdi = ZAdi.getText().strip();
        String sifre = Zsifre.getText().strip();

        // Giriş kontrolü
        if (kullaniciYonetimi.girisKontrol(kullaniciAdi, sifre)) {
            ZgWrongL.setText("Giriş Başarılı.");
            m.changeScene("ZiyaretciEkran.fxml");
        } else if (kullaniciAdi.isEmpty() || sifre.isEmpty()) {
            ZgWrongL.setText("Alanları Boş Bıraktınız!");

        } else {
            ZgWrongL.setText("Yanlış Kullanıcı Adı Veya Şifre");

        }
    }


    @FXML
    void backiconZgclik(MouseEvent event) throws IOException {
        Javafx m = new Javafx();
        m.changeScene("Javafx.fxml");

    }

}
