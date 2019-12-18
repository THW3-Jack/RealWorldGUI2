import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Book implements Serializable {
    static private final long serialVersionUID = 2L;

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

    static void saveData(FileOutputStream file) {

        try {

            ObjectOutputStream out = new ObjectOutputStream(file);


            // 1. Save the number of albums in list
            out.writeInt(books.size());
            // 2. Save each album object
            for (int i = 0; i < books.size(); i = i + 1) {
                out.writeObject(books.get(i));
            }
            // 3. Write a long number to create buffer between albums objects and avatar image
            out.writeLong(100L);
            // 4. Save the avatar
            //****ImageIO.write(SwingFXUtils.fromFXImage(avatar, null), "png", out);
            // 5. Write a long number to create buffer between avatar image and end of file
            out.writeLong(300L);

            // Done saving; close the object stream
            out.close();
        } catch (Exception ex) {
            System.out.println("Save exception: ");
            ex.printStackTrace();
        }


    }

    static void restore(FileInputStream file) {

        try {

            ObjectInputStream in = new ObjectInputStream(file);
            int numberOfBooks = in.readInt();
            for (int i = 0; i < numberOfBooks; i = i + 1) {
                Book nextBook = (Book)in.readObject();
                books.add(nextBook);
            }


        } catch (Exception ex) {
            System.out.println("Restore exception: ");
            ex.printStackTrace();
        }

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

        if (currentBookNum == books.size() - 1) {
            currentBookNum = 0;
        } else {
            currentBookNum = currentBookNum + 1;
        }
        return books.get(currentBookNum);
    }
    static Book getPrev() {
        if (books.size() == 0) {
            return null;
        }
        if (currentBookNum == 0) {
            currentBookNum = books.size() - 1;
        } else {
            currentBookNum = currentBookNum - 1;
        }
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