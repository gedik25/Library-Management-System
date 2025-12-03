package com.example.demo11;

public abstract class Kullanici {
    private String isimSoyisim;
    private String sifre;

    public Kullanici(String isimSoyisim, String sifre) {
        this.isimSoyisim = isimSoyisim;
        this.sifre = sifre;
    }

    public String getIsimSoyisim() {
        return isimSoyisim;
    }

    public String getSifre() {
        return sifre;
    }
}
