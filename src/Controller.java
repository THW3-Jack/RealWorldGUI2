import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    public Button next;
    public Button prev;
    public TextField title;
    public TextField author;
    public TextField language;
    public TextField publish;
    public TextField sales;
    public TextField genre;

    public void initialize() {
        // get next book
        Book nextBook = Book.getCurrent();

        title.setText(nextBook.getTitle());
        author.setText(nextBook.getAuthor());
        language.setText(nextBook.getLanguage());
        publish.setText(nextBook.getPublish());
        sales.setText(nextBook.getSales());
        genre.setText(nextBook.getGenre());

    }

    public void nextPressed() {
        Book nextBook = Book.getNext();

        title.setText(nextBook.getTitle());
        author.setText(nextBook.getAuthor());
        language.setText(nextBook.getLanguage());
        publish.setText(nextBook.getPublish());
        sales.setText(nextBook.getSales());
        genre.setText(nextBook.getGenre());

    }

    public void prevPressed() {


    }

}
