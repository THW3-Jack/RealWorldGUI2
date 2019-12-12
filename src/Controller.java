import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;

public class Controller {
Stage primaryStage;


    public Button next;
    public Button prev;
    public Button filechooser;
    public Button imagechooser;
    public TextField title;
    public TextField author;
    public TextField language;
    public TextField publish;
    public TextField sales;
    public TextField genre;

    public Label sLabel;

    public void filePressed() {
        FileChooser importData = new FileChooser();
        File dataFile = importData.showOpenDialog(this.primaryStage);
        if (dataFile != null) {
            // Update model
            Book.importBooks(dataFile);
            // Table will be updated automatically
            // Update status
            sLabel.setText("Imported data from " + dataFile.toURI().toString());
        }

        //get next book
        Book nextBook = Book.getCurrent();

        if (nextBook == null) {
            return;
        }

        title.setText(nextBook.getTitle());
        author.setText(nextBook.getAuthor());
        language.setText(nextBook.getLanguage());
        publish.setText(nextBook.getPublish());
        sales.setText(nextBook.getSales());
        genre.setText(nextBook.getGenre());

    }

    public void imagePressed() {



    }

    public void initialize() {
         //get next book
        Book nextBook = Book.getCurrent();

        if (nextBook == null) {
            return;
        }

        title.setText(nextBook.getTitle());
        author.setText(nextBook.getAuthor());
        language.setText(nextBook.getLanguage());
        publish.setText(nextBook.getPublish());
        sales.setText(nextBook.getSales());
        genre.setText(nextBook.getGenre());

    }

    public void nextPressed() {
        Book nextBook = Book.getNext();

        if (nextBook == null) {
            return;
        }

        title.setText(nextBook.getTitle());
        author.setText(nextBook.getAuthor());
        language.setText(nextBook.getLanguage());
        publish.setText(nextBook.getPublish());
        sales.setText(nextBook.getSales());
        genre.setText(nextBook.getGenre());

    }

    public void prevPressed() {
        Book nextBook = Book.getPrev();

        if (nextBook == null) {
            return;
        }

        title.setText(nextBook.getTitle());
        author.setText(nextBook.getAuthor());
        language.setText(nextBook.getLanguage());
        publish.setText(nextBook.getPublish());
        sales.setText(nextBook.getSales());
        genre.setText(nextBook.getGenre());

    }

}