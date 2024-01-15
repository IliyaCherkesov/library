import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
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
            String str = in.nextLine();
            if (str.equals("1"))
            {
                System.out.println("Enter book name:");
                String bookName = in.nextLine();
                System.out.println("Enter author name:");
                String authorName = in.nextLine();
                System.out.println("Enter ISBN:");
                String isbn = in.nextLine();
                System.out.println("Enter publication year:");
                int publicationYear = in.nextInt();
                books.add(new Book(bookName, authorName, isbn, publicationYear));
            }
            int ArraySize = books.toArray().length;
            if (str.equals("2"))
            {
                System.out.println(books);
            }

            if (str.equals("3"))
            {
                String str2 = in.nextLine();
                for (int i = 0; i < ArraySize; i++)
                {
                    Book book = books.get(i);
                    if(book.getName().contains(str2))
                    {
                        System.out.println("Book name: " + book.getName());
                    }

                }
            }

            if (str.equals("4")) {
                break;
            }
        }
        in.close();
    }
}
