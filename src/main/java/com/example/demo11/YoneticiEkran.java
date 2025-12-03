package com.example.demo11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class YoneticiEkran implements Initializable {


    @FXML
    private TextField ZkayitSifre;

    @FXML
    private TextField Zkayitad;

    @FXML
    private TextField author;

    @FXML
    private ImageView backIconYE;

    @FXML
    private TextField book;

    @FXML
    private ListView<String> booksListY; // Tip olarak String kullanıldı

    @FXML
    private TextField category;

    @FXML
    private Button deleteY;

    @FXML
    private Button editY;

    @FXML
    private TextField isbn;

    @FXML
    private TextField searchY;



    private final ObservableList<String> books = FXCollections.observableArrayList();

    @FXML
    private void backYeclik(MouseEvent event) throws IOException {
        Javafx m = new Javafx();
        m.changeScene("YoneticiLogin.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadBooks();
        } catch (IOException e) {
            e.printStackTrace();
        }

        booksListY.setItems(books);
        booksListY.setFixedCellSize(50.0);
    }


    @FXML
    protected void searchBook() throws IOException {
        String searchText = searchY.getText().strip().toLowerCase();
        if (searchText.length() >= 3) {
            ObservableList<String> filteredBooks = FXCollections.observableArrayList();
            for (String book : books) {
                if (book.toLowerCase().contains(searchText)) {
                    filteredBooks.add(book);
                }
            }
            booksListY.setItems(filteredBooks);
        } else {
            booksListY.setItems(books);
        }
    }

    @FXML
    protected void editBookY() throws IOException {
        int selectedIndex = booksListY.getSelectionModel().getSelectedIndex();

        if (selectedIndex != -1) {
            String bookToEdit = books.get(selectedIndex);
            String[] parts = bookToEdit.split("-");
            if (parts.length > 2) {
                String oldIsbn = parts[2];

                // Düzenlenecek kitap için işlem
                EditBook eb = new EditBook();
                String newBookData = eb.getResult();

                if (newBookData != null) {
                    Path oldFilePath = Paths.get("src/main/data/kitap/" + oldIsbn + ".txt");
                    Files.deleteIfExists(oldFilePath);

                    String newIsbn = newBookData.split("-")[2];
                    Path newFilePath = Paths.get("src/main/data/kitap/" + newIsbn + ".txt");

                    Files.write(newFilePath, newBookData.getBytes());

                    loadBooks();
                    searchY.setText("");
                }
            }
        }
    }

    @FXML
    protected void deleteBookY() throws IOException {
        int selectedIndex = booksListY.getSelectionModel().getSelectedIndex();

        if (selectedIndex != -1) {
            String bookToDelete = books.get(selectedIndex);
            String[] parts = bookToDelete.split("-");
            if (parts.length > 2) {
                String oldIsbn = parts[2];
                Path filePath = Paths.get("src/main/data/kitap/" + oldIsbn + ".txt");
                Files.deleteIfExists(filePath);

                loadBooks();
                searchY.setText("");
            }
        }
    }

    @FXML
    protected void addItemY() throws IOException {
        String authorText = author.getText();
        String bookText = book.getText();
        String isbnText = isbn.getText();
        String categoryText = category.getText();

        if (authorText.isEmpty() || bookText.isEmpty() || isbnText.isEmpty() || categoryText.isEmpty()) {
            System.out.println("Tüm alanları doldurun.");
            return;
        }
        KutuphaneSistemi kutuphane=new KutuphaneSistemi();
        Kitap kitap=new Kitap(authorText,bookText,isbnText,categoryText);
        kitap.kitapEkle();
        kutuphane.getKitapListesi().add(kitap);



        author.clear();
        book.clear();
        isbn.clear();
        category.clear();

        loadBooks();
    }

    public static ArrayList<String> listFilesForFolder(final File folder) throws IOException {
        ArrayList<String> bookData = new ArrayList<>();
        File[] files = folder.listFiles();

        if (files != null) {
            for (File fileEntry : files) {
                List<String> lines = Files.readAllLines(fileEntry.toPath());
                if (!lines.isEmpty()) {
                    bookData.add(lines.get(0).strip());
                }
            }
        }
        return bookData;
    }

    public void loadBooks() throws IOException {
        Path folderPath = Paths.get("src/main/data/kitap");
        File folder = folderPath.toFile();

        books.clear();
        books.addAll(listFilesForFolder(folder));
        booksListY.setItems(books);
    }

    public static void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Javafx.class.getResource("YoneticiEkran.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = Javafx.getPrimaryStage();
        stage.hide();
        stage.setTitle("Kütüphane Yönetim Sistemi");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void ZEkleY() {
        // Kullanıcı adı ve şifre alanlarından bilgileri al
        String kullaniciAdi = Zkayitad.getText();
        String sifre = ZkayitSifre.getText();

        // Boş alan kontrolü
        if (kullaniciAdi.isEmpty() || sifre.isEmpty()) {
            System.out.println("Kullanıcı adı ve şifre alanları boş bırakılamaz.");
            return;
        }

        // KullanıcıYonetimi sınıfından bir nesne oluştur
        KullaniciYonetimi kullaniciYonetimi = new KullaniciYonetimi();

        // Kullanıcı ekleme
        kullaniciYonetimi.kullaniciEkle(kullaniciAdi, sifre);

        // Kullanıcı bilgilerini konsola yazdır (debugging için)
        kullaniciYonetimi.kullaniciListesiniYazdir();

        // Girdi alanlarını temizle
        Zkayitad.clear();
        ZkayitSifre.clear();

        System.out.println("Kullanıcı başarıyla eklendi!");
    }
}
