import java.net.SecureCacheResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.stream.Stream;

import BookLibrary.Model.*;

public class Main
{
    public static void main(String[] args)
    {
        List<Book> books = new ArrayList<Book>();
        // ArrayList<String> LibraryBooks = new ArrayList<String>();
        books.add(new Book("Harry Potter", "Joanne Rowling", "IDODNR-32423-3244", 1999));
        books.add(new Book("Children of Captain Grant", "Jules Gabriel Verne", "DNERN-2341-241211", 1854));
        books.add(new Book("The Witcher", "Andjey Sapkovsky", "MEMORE-612-1424531", 1989));

        Scanner in = new Scanner(System.in);
        while (true)
        {
            System.out.println("1 - add new book.");
            System.out.println("2 - print list of the books.");
            System.out.println("3 - search the books and display details of it");
            System.out.println("4 - delete particular book by ISBN");
            String str = in.nextLine();
            if (str.equals("1"))
            {
                books.add(addNewBook(in));
            }
            if (str.equals("2"))
            {
                System.out.println(books);
            }

            if (str.equals("3"))
            {
                List<Book> listOfBooks = searchBooks(in, books);
                System.out.println(listOfBooks);
            }
            }

            if (str.equals("4")) {
                books = deleteBook(in, books);

            }

            if (str.equals("5")) {
                break;

            }
        }
        in.close();
    }
    private static Book addNewBook(Scanner in)
    {
        System.out.println("Enter book name:");
        String bookName = in.nextLine();
        System.out.println("Enter author name:");
        String authorName = in.nextLine();
        System.out.println("Enter ISBN:");
        String isbn = in.nextLine();
        System.out.println("Enter publication year:");
        int publicationYear = in.nextInt();
        return new Book(bookName, authorName, isbn, publicationYear);
    }
    private static List<Book> searchBooks(Scanner in, List<Book> books)
    {
        System.out.print("Please enter the name of the book: ");
        String search = in.nextLine();
        Stream<Book> sBooks = books.stream();
        List<Book> result = sBooks.filter(book -> book.getName().contains(search)).toList();
        return result;
    }

    private static List<Book> deleteBook(Scanner in, List<Book> books)
    {
        System.out.println("Please enter ISBN of the book: ");
        String isbn = in.nextLine();
        Stream<Book> sBooks = books.stream();
        List<Book> result = sBooks.filter(book -> !book.getIsbn().equals(isbn)).toList();
        return result;
    }

}

