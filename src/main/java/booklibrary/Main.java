package booklibrary;

import booklibrary.model.Book;
import booklibrary.model.Dvd;
import booklibrary.model.Item;
import booklibrary.model.Library;
import booklibrary.model.Patron;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main
{
    public static Library library = new Library();

    private static void initLibrary() {
        library.add(new Book("Harry Potter", "Joanne Rowling", "IDODNR-32423-3244", 1999));
        library.add(new Book("Children of Captain Grant", "Jules Gabriel Verne", "DNERN-2341-241211", 1854));
        library.add(new Book("The Witcher", "Andjey Sapkovsky", "MEMORE-612-1424531", 1989));
        library.add(new Dvd("David Bowie", 600));
        library.add(new Dvd("Michael Jackson", 800));
    }

    public static void main(String[] args)
    {
        initLibrary();
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            boolean doBreak = false;
            printMenu();
            String str = scanner.nextLine();
            switch(str.trim()) {
                case "1":
                    library.add(addNewItem(scanner));
                    break;
                case "2":
                    boolean removeSuccess = removeItemById(scanner);
                    if (!removeSuccess)
                    {
                        System.out.println("No item for deletion was found(");
                    } else {
                        System.out.println("Item was successfully deleted!");
                    }
                    break;
                case "3":
                    Patron patron = signUpNewPatron(scanner);
                    if (patron != null) {
                        System.out.println("Patron was successfully signed up! ID: " + patron.ID());
                    }
                    break;
                case "4":
                    boolean borrowSuccess = borrowItemToPatron(scanner);
                    if (!borrowSuccess)
                    {
                        System.out.println("No item for borrowing was found(");
                    } else {
                        System.out.println("Item was successfully borrowed!");
                    }
                    break;
                case "5":
                    boolean returnSuccess = returnItemFromPatron(scanner);
                    if (!returnSuccess)
                    {
                        System.out.println("No item for returning was found(");
                    } else {
                        System.out.println("Item was successfully returned!");
                    }
                    break;
                case "6":
                    List<Item> availableItems = library.listAvailable();
                    System.out.println("Available items: ");
                    if (availableItems.size() == 0) {
                        System.out.println("No available items");
                    } else {
                        availableItems.forEach(item -> System.out.print(item.toString() + ", \n"));
                    }
                    break;
                case "7":
                    List<Item> borrowedItems = library.listBorrowed();
                    System.out.println("Borrowed items: ");
                    if (borrowedItems.size() == 0) {
                        System.out.println("No borrowed items");
                    } else {
                        borrowedItems.forEach(item -> System.out.print(item.toString() + ", \n"));
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
        System.out.println("1 - add new library item.");
        System.out.println("2 - remove library item.");
        System.out.println("3 - sign up the patron.");
        System.out.println("4 - borrow library item to patron.");
        System.out.println("5 - take library item from patron back.");
        System.out.println("6 - show abailable library items.");
        System.out.println("7 - show borrowed library items.");
    }

    public static Patron signUpNewPatron(Scanner scanner) {
        System.out.println("Enter patron name:");
        String patronName = scanner.nextLine();
        return library.signUpNewPatron(patronName);
    }

    public static Book addNewBook(Scanner scanner)
    {
        System.out.println("Enter book name:");
        String bookName = scanner.nextLine();
        System.out.println("Enter author name:");
        String authorName = scanner.nextLine();
        System.out.println("Enter ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Enter publication year:");
        int publicationYear = scanner.nextInt();
        return new Book(bookName, authorName, isbn, publicationYear);
    }

    public static Dvd addNewDvd(Scanner scanner) {
        System.out.println("Enter DVD name:");
        String dvdName = scanner.nextLine();
        System.out.println("Enter duration:");
        int duration = scanner.nextInt();
        return new Dvd(dvdName, duration);
    }

    public static Item addNewItem(Scanner scanner)
    {
        System.out.println("Enter type of item (book/dvd):");
        System.out.println("1 - book");
        System.out.println("2 - dvd");
        String type = scanner.nextLine();
        switch(type) {
            case "1":
                return addNewBook(scanner);
            case "2":
                return addNewDvd(scanner);
            default:
                return null;
        }
    }

    public static List<Book> searchBooksBy(Scanner scanner, String type)
    {
        System.out.print("Please enter the name of the book: ");
        String search = scanner.nextLine();
        List<Book> books = library.getBooks();
        Stream<Book> sBooks = books.stream();
        Function<Book, Boolean> criteria = (Book book) -> {
            switch(type) {
                case "name":
                    return book.getName().contains(search);
                case "author":
                    return book.getAuthor().contains(search);
                case "isbn":
                    return book.getIsbn().contains(search);
                case "publication year":
                    return String.valueOf(book.getPublicationYear()).contains(search);
                default:
                    return false;
            }
        };
        return sBooks.filter(book -> criteria.apply(book)).toList();
    }

    public static boolean removeItemById(Scanner scanner)
    {
        System.out.println("Please enter unique item ID: ");
        String itemId = scanner.nextLine();
        Stream<Item> items = library.getItems().stream();
        Item forDelete = items.filter(item -> item.getUniqueId().equals(itemId)).findFirst().orElse(null);
        if (forDelete == null) {
            return false;
        }
        library.remove(forDelete);
        return true;
    }

    public static boolean borrowItemToPatron(Scanner scanner)
    {
        System.out.println("Please enter patron ID: ");
        String patronId = scanner.nextLine();
        System.out.println("Please enter item ID: ");
        String itemId = scanner.nextLine();
        return library.borrowItem(patronId, itemId);
    }

    public static boolean returnItemFromPatron(Scanner scanner)
    {
        System.out.println("Enter patron ID:");
        String patronId = scanner.nextLine();
        System.out.println("Enter item ID:");
        String itemId = scanner.nextLine();
        return library.returnItemBack(patronId, itemId);
    }

}

