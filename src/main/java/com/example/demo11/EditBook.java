package com.example.demo11;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class EditBook {

    private String result;

    public String getResult() {
        return this.result;
    }

    public EditBook() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Düzenle");
        dialog.setHeaderText("Kitabı Düzenle");

        ButtonType confirm = new ButtonType("Onayla");
        dialog.getDialogPane().getButtonTypes().add(confirm);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        TextField author = new TextField();
        author.setPromptText("Yazar");

        TextField title = new TextField();
        title.setPromptText("İsim");

        TextField isbn = new TextField();
        isbn.setPromptText("ISBN");

        TextField category = new TextField();
        category.setPromptText("Kategori");

        grid.add(new Label("Yazar:"), 0, 0);
        grid.add(author, 1, 0);

        grid.add(new Label("Başlık:"), 0, 1);
        grid.add(title, 1, 1);

        grid.add(new Label("ISBN:"), 0, 2);
        grid.add(isbn, 1, 2);

        grid.add(new Label("Kategori:"), 0, 3);
        grid.add(category, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirm) {
                return author.getText() + "-" + title.getText() + "-" + isbn.getText() + "-" + category.getText();
            }
            return null;
        });

        Optional<String> rslt = dialog.showAndWait();
        if (rslt.isPresent() ) {
            this.result = rslt.get();
        }
        else this.result = null;
    }
}
