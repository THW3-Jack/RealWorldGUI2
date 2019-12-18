import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

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

    private final String savedFilePath = "/tmp/ChangedBookData";

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

        File savedDataFile = new File(savedFilePath);
        if (savedDataFile.exists()) {
            try {
                FileInputStream file = new FileInputStream(savedDataFile);
                Book.restore(file);
                sLabel.setText("Data restored");
                file.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

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

        title.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Book.getCurrent().setTitle(newvalue);
        });

        author.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Book.getCurrent().setAuthor(newvalue);
        });

        language.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Book.getCurrent().setLanguage(newvalue);
        });

        publish.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Book.getCurrent().setPublish(newvalue);
        });

        sales.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Book.getCurrent().setSales(newvalue);
        });

        genre.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Book.getCurrent().setGenre(newvalue);
        });

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


    public void save() {
        try {
            FileOutputStream out = new FileOutputStream(savedFilePath);
            Book.saveData(out);

            sLabel.setText("All data saved to: " + savedFilePath);

        } catch (Exception ex) {
            ex.printStackTrace();

        }


    }


}