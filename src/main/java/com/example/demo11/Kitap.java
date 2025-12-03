package com.example.demo11;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Kitap {
    // Kitap bilgileri
    private String kitapAdi;
    private String yazar;
    private String kategori;
    private String ISBN;

    // Constructor
    public Kitap(String yazar,String kitapAdi, String ISBN ,String kategori ) {

        this.kitapAdi = kitapAdi;
        this.yazar = yazar;
        this.kategori = kategori;
        this.ISBN = ISBN;
    }

    // Getter ve Setter metodları

    public String getKitapTuru() {
        return kategori;
    }

    public void setKitapTuru(String kitapTuru){
        this.kategori = kitapTuru;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }


    public void kitapEkle() throws IOException {

        String data = String.format("%s-%s-%s-%s", yazar, kitapAdi, ISBN,kategori );
        Path filePath = Paths.get("src/main/data/kitap/" + ISBN + ".txt");
        Files.write(filePath, data.getBytes());
    }


}