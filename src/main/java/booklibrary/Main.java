package booklibrary;

import booklibrary.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args)
    {
        List<Book> books = new ArrayList<Book>();
        // ArrayList<String> LibraryBooks = new ArrayList<String>();
        books.add(new Book("Harry Potter", "Joanne Rowling", "IDODNR-32423-3244", 1999));
        books.add(new Book("Children of Captain Grant", "Jules Gabriel Verne", "DNERN-2341-241211", 1854));
        books.add(new Book("The Witcher", "Andjey Sapkovsky", "MEMORE-612-1424531", 1989));
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            boolean doBreak = false;
            printMenu();
            String str = scanner.nextLine();
            switch(str.trim()) {
                case "1":
                    books.add(addNewBook(scanner));
                    break;
                case "2":
                    System.out.println(books);
                    break;
                case "3":
                    List<Book> listOfBooks = searchBooks(scanner, books);
                    if (listOfBooks.isEmpty())
                    {
                        System.out.println("No books were found(");
                    }
                    else
                        System.out.println(listOfBooks);
                    break;
                case "4":
                    if (!deleteBook(scanner, books))
                    {
                         System.out.println("No book for deletion was found(");
                    }
                    break;
                default:
                    doBreak = true;
                    break;
            }
            if (doBreak) {
                break;
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("1 - add new book.");
        System.out.println("2 - print list of the books.");
        System.out.println("3 - search the books and display details of it");
        System.out.println("4 - delete particular book by ISBN");
    }

    public static Book addNewBook(Scanner in)
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
    public static List<Book> searchBooks(Scanner in, List<Book> books)
    {
        System.out.print("Please enter the name of the book: ");
        String search = in.nextLine();
        Stream<Book> sBooks = books.stream();
        return sBooks.filter(book -> book.getName().contains(search)).toList();
    }

    public static boolean deleteBook(Scanner in, List<Book> books)
    {
        System.out.println("Please enter ISBN of the book: ");
        String isbn = in.nextLine();
        Stream<Book> sBooks = books.stream();
        Optional<Book> forDelete = sBooks.filter(book -> book.getIsbn().equals(isbn)).findFirst();
        forDelete.ifPresent(books::remove);
        return forDelete.isPresent();
    }

}

