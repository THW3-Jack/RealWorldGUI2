import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Book {

    static public ArrayList<Book> books;
    static private int currentBookNum;

    private String title;
    private String author;
    private String sales;
    private String publish;
    private String language;
    private String genre;

    public Book(String title, String author, String language, String publish, String sales, String genre){
        this.author = author;
        this.genre = genre;
        this.language = language;
        this.publish = publish;
        this.sales = sales;
        this.title = title;
        currentBookNum = 0;
    }

    static ArrayList<Book> readBooks() {
        if (books != null) {
            // albums have already been read from file
            return books;
        }

        // create array list class field where albums will be stored
        books = new ArrayList<Book>();

        try {
            // scan data file line-by-line
            File bookDataFile = new File("res/Book Data");
            Scanner scanner = new Scanner(bookDataFile);
            while (scanner.hasNextLine()){
                String str = scanner.nextLine();
                Scanner lineScanner = new Scanner(str);
                lineScanner.useDelimiter("#");
                // scan data files line by separating text between #

                // first 4 data values are always present in each line
                String title = lineScanner.next();
                String author = lineScanner.next();
                String language = lineScanner.next();
                String publish = lineScanner.next();
                String sales = lineScanner.next();
                String genre = lineScanner.next();
                // Some lines have certified sales value, then claimed sales value
                //  But some lines have only claimed sales value, no certified sales value
                // try reading first sales amount as a float (since certified sales value is a float)

                Book newBook = new Book(title, author, language, publish, sales, genre);
                books.add(newBook);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return books;

    }
    static void describeBook() {
        if (books == null) {
            // read the albums from file
            readBooks();
        }

        for (int i = 0; i < books.size(); i++) {
            books.get(i).describe();
        }

        // Example of how to use forEach() instead of for loop
        // albums.forEach((album) -> album.describe());
    }

    static Book getCurrent() {
        readBooks();
        return books.get(currentBookNum);
    }

    static Book getNext() {
        readBooks();
        currentBookNum = currentBookNum + 1;
        return books.get(currentBookNum);
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void describe() {
        System.out.println(title + " is a " + genre + " book " + " by: " + author + ". Writen in " + language + " and it was first published in " + publish + " and sold a total of" + sales + " copies.");


    }


}