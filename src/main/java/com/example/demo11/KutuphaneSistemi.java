package com.example.demo11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class KutuphaneSistemi {
    private ArrayList<Kitap> kitapListesi;

    public KutuphaneSistemi() {
        this.kitapListesi = new ArrayList<>();
    }

    public ArrayList<Kitap> getKitapListesi() {
        return kitapListesi;
    }

}
