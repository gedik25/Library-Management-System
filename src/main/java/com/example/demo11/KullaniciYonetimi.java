package com.example.demo11;

public class KullaniciYonetimi {
    private static Ziyaretci[][] kullanicilar = new Ziyaretci[0][2];
    private static Yonetici[][] yoneticiler = new Yonetici[0][2];
    public KullaniciYonetimi() {
        // Başlangıçta iki hazır kullanıcı eklenir.
        yoneticiler = new Yonetici[2][2];
        yoneticiler[0][0] = new Yonetici("Ali", "12345");
        yoneticiler[1][0] = new Yonetici("Tolga", "54321");

    }


    // Kullanıcı ekleme metodu
    public void kullaniciEkle(String isimSoyisim, String sifre) {
        Ziyaretci[][] yeniKullanicilar = new Ziyaretci[kullanicilar.length + 1][2];

        for (int i = 0; i < kullanicilar.length; i++) {
            yeniKullanicilar[i][0] = kullanicilar[i][0];
            yeniKullanicilar[i][1] = kullanicilar[i][1];
        }

        yeniKullanicilar[kullanicilar.length][0] = new Ziyaretci(isimSoyisim, sifre);
        kullanicilar = yeniKullanicilar;
    }


    // Kullanıcı bilgilerini kontrol etme metodu
    public boolean girisKontrol(String isimSoyisim, String sifre) {
        for (int i = 0; i < kullanicilar.length; i++) {
            Ziyaretci kullanici = kullanicilar[i][0];
            if (kullanici.getIsimSoyisim().equals(isimSoyisim) &&
                    kullanici.getSifre().equals(sifre)) {
                return true;
            }
        }
        return false;
    }
    //Yonetici Giris Kontrol
    public boolean girisKontrolY(String isimSoyisim, String sifre) {
        for (int i = 0; i < yoneticiler.length; i++) {
            Yonetici kullanici = yoneticiler[i][0];
            if (kullanici.getIsimSoyisim().equals(isimSoyisim) &&
                    kullanici.getSifre().equals(sifre)) {
                return true;
            }
        }
        return false;
    }

    // Kullanıcı listesini yazdırma (debugging için)
    public void kullaniciListesiniYazdir() {
        for (int i = 0; i < kullanicilar.length; i++) {
            Ziyaretci kullanici = kullanicilar[i][0];
            System.out.println("İsim ve Soyisim: " + kullanici.getIsimSoyisim() +
                    ", Şifre: " + kullanici.getSifre());
        }
    }
}
