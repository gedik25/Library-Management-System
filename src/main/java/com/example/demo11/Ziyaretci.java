package com.example.demo11;

import java.util.ArrayList;
import java.util.List;

public class Ziyaretci extends Kullanici {
    private ArrayList<Kitap> oduncAlinanKitaplar;

    public Ziyaretci(String isimSoyisim, String Sifre) {
        super(isimSoyisim, Sifre);
        this.oduncAlinanKitaplar = new ArrayList<>();
    }

    public void setOduncAlinanKitaplar(ArrayList<Kitap> oduncAlinanKitaplar) {
        this.oduncAlinanKitaplar = oduncAlinanKitaplar;
    }

    public ArrayList<Kitap> getOduncAlinanKitaplar() {
        return oduncAlinanKitaplar;
    }

}
