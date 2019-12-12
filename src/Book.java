import java.io.Serializable;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Book implements Serializable {

    static public ArrayList<Book> books = new ArrayList<Book>();
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

    static ArrayList<Book> importBooks(File dataFile) {

        try {
            // scan data file line-by-line
            Scanner scanner = new Scanner(dataFile);
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

        for (int i = 0; i < books.size(); i++) {
            books.get(i).describe();
        }

        // Example of how to use forEach() instead of for loop
        // albums.forEach((album) -> album.describe());
    }

    static Book getCurrent() {
        if (books.size() == 0) {
            return null;
        }

        return books.get(currentBookNum);
    }

    static Book getNext() {
        if (books.size() == 0) {
            return null;
        }

        currentBookNum = currentBookNum + 1;
        return books.get(currentBookNum);
    }
    static Book getPrev() {
        if (books.size() == 0) {
            return null;
        }
        currentBookNum = currentBookNum - 1;
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