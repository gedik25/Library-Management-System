package com.example.demo11;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class ZiyaretciEkran implements Initializable {


    @FXML
    private ListView<String> booksList;
    @FXML
    private ListView<String> booksList1;

    @FXML
    private TextField author;

    @FXML
    private TextField book;

    @FXML
    private TextField isbn;

    @FXML
    private TextField category;

    @FXML
    private TextField search;
    @FXML
    private TextField search1;

    @FXML
    private ImageView backIconZE;

    @FXML
    private void backZeClik(MouseEvent event) throws IOException {
        Javafx m = new Javafx();
        m.changeScene("ZiyaretciLogin.fxml");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadBooks();
        } catch (IOException e) {
            e.printStackTrace();
        }

        booksList.setFixedCellSize(50.0);
    }

    @FXML
    protected void searchBook() throws IOException {

        String search_text = search.getText().strip().toLowerCase();
        loadBooks();
        if (search_text.length() >= 3) {
            ArrayList<String> results = new ArrayList<>();

            for(String book: booksList.getItems()) {
                if (book.toLowerCase().contains(search_text)) results.add(book);
            }

            booksList.getItems().clear();
            if (results.size() > 0) {
                for(String found_book: results) booksList.getItems().add(found_book);
                booksList.refresh();
            }
        }
    }

    @FXML
    protected void searchBook1() throws IOException {

        String search_text = search1.getText().strip().toLowerCase();
        loadBooks();
        if (search_text.length() >= 3) {
            ArrayList<String> results = new ArrayList<>();

            for(String book: booksList1.getItems()) {
                if (book.toLowerCase().contains(search_text)) results.add(book);
            }

            booksList1.getItems().clear();
            if (results.size() > 0) {
                for(String found_book: results) booksList1.getItems().add(found_book);
                booksList1.refresh();
            }
        }
    }



    public static ArrayList<String> listFilesForFolder(final File folder) throws IOException {
        ArrayList<String> al = new ArrayList<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            String read = Files.readAllLines(Paths.get(fileEntry.getPath())).get(0);
            al.add(read.strip());
        }
        return al;
    }

    public void loadBooks() throws IOException {
        Path p = Paths.get("src/main/data/kitap");
        final File folder = new File(String.valueOf(p));
        ArrayList<String> al = listFilesForFolder(folder);
        booksList.getItems().clear();
        for(String book: al) {
            booksList.getItems().add(book);
        }
        booksList.refresh();
    }



    public static void changeScene() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Javafx.class.getResource("ZiyaretciLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // scene

        Stage stage = Javafx.getPrimaryStage();
        stage.hide();
        stage.setTitle("Ziyarteci Sayfa");
        stage.setScene(scene);
        stage.show();
    }



    public void kitapOduncAl(ActionEvent event) {
        try {
            // Get the selected index from booksList
            int selectedIndex = booksList.getSelectionModel().getSelectedIndex();

            // Check if an item is selected
            if (selectedIndex != -1) {
                // Get the selected item text
                String selectedItem = booksList.getItems().get(selectedIndex);

                // Check if the book is already in the borrowed list
                if (booksList1.getItems().contains(selectedItem)) {
                    throw new KitapZatenOduncException("Bu kitap zaten ödünç alınmış!");
                }

                // Add the selected item to booksList1
                booksList1.getItems().add(selectedItem);
                booksList1.refresh();
            } else {
                // Show an error message if no item is selected
                System.out.println("Lütfen ödünç almak istediğiniz kitabı seçin!");
            }
        } catch (KitapZatenOduncException e) {
            // Show a warning dialog
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hata");
            alert.setHeaderText("Kitap zaten ödünç alınmış!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    public void kitapTeslim(ActionEvent event) {
        // booksList1'den seçili olan kitabın indeksini al
        int selectedIndex = booksList1.getSelectionModel().getSelectedIndex();

        // Eğer bir kitap seçilmişse
        if (selectedIndex != -1) {
            // Seçili kitabı listeden kaldır
            booksList1.getItems().remove(selectedIndex);
        } else {
            // Hiçbir kitap seçilmediyse hata mesajı veya uyarı verebilirsiniz
            System.out.println("Lütfen teslim etmek istediğiniz kitabı seçin!");
        }
    }
}



