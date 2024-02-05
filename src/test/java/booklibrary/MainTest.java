
package booklibrary;

import booklibrary.model.*;
import booklibrary.utils.ConsoleInputMock;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
    Book book1 = new Book("Harry The Potter", "J.K. Rowling", "IDODNR-32423-3244", 1997);
    Book book2 = new Book("The Witcher", "Andjey Sapkovsky", "MEMORE-612-1424531", 1989);
    Book book3 = new Book("Children of Captain Grant", "Jules Gabriel Verne", "DNERN-2341-241211", 1854);
    Dvd dvd1 = new Dvd("David Bowie", 600);
    Dvd dvd2 = new Dvd("Michael Jackson", 800);
    Library library;

    @BeforeEach
    void initLibrary() {

        library = new Library();
        library.add(book1);
        library.add(book2);
        library.add(book3);
        library.add(dvd1);
        library.add(dvd2);

        Main.library = library;
    }

    @Test
    void addNewBookTest() {
        ConsoleInputMock consoleInputMock = new ConsoleInputMock();
        Scanner scanner = new Scanner(consoleInputMock);
        consoleInputMock.writeStringln("Arom");
        consoleInputMock.writeStringln("Forge");
        consoleInputMock.writeStringln("AAA-BBB");
        consoleInputMock.writeStringln("1994");
        Book result = Main.addNewBook(scanner);
        assertEquals("Arom", result.getName());
        assertEquals("Forge", result.getAuthor());
        assertEquals("AAA-BBB", result.getIsbn());
        assertEquals(1994, result.getPublicationYear());
    }

    @Test
    void searchBookTest() {
        ConsoleInputMock consoleInputMock = new ConsoleInputMock();
        Scanner scanner = new Scanner(consoleInputMock);
        consoleInputMock.writeStringln("Harry");
        List<Book> result = Main.searchBooksBy(scanner, "name");
        assertEquals(1, result.size());

        consoleInputMock.writeStringln("The");
        result = Main.searchBooksBy(scanner, "name");
        assertEquals(2, result.size());

        consoleInputMock.writeStringln("Aurger");
        result = Main.searchBooksBy(scanner, "name");
        assertEquals(0,  result.size());
    }

    @Test
    void deleteBookTest() {
        ConsoleInputMock consoleInputMock = new ConsoleInputMock();
        Scanner scanner = new Scanner(consoleInputMock);
        consoleInputMock.writeStringln(book1.getUniqueId());
        boolean result = Main.removeItemById(scanner);
        assertEquals(result, true);

        consoleInputMock.writeStringln(book2.getUniqueId());
        result = Main.removeItemById(scanner);
        assertEquals(result, true);

        consoleInputMock.writeStringln("Aurger");
        result = Main.removeItemById(scanner);
        assertEquals(result, false);
    }

    @Test
    void signUpNewPatron() {
        ConsoleInputMock consoleInputMock = new ConsoleInputMock();
        Scanner scanner = new Scanner(consoleInputMock);
        consoleInputMock.writeStringln("John Doe");
        Patron result = Main.signUpNewPatron(scanner);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void borrowItemToPatron() {
        ConsoleInputMock consoleInputMock = new ConsoleInputMock();
        Scanner scanner = new Scanner(consoleInputMock);
        consoleInputMock.writeStringln("John Doe");
        Patron patron = Main.signUpNewPatron(scanner);

        consoleInputMock.writeStringln(patron.ID());
        consoleInputMock.writeStringln(book1.getUniqueId());
        boolean result = Main.borrowItemToPatron(scanner);
        int borrowedCount = library.listBorrowed().size();
        assertEquals(result, true);
        assertEquals(borrowedCount, 1);
    }

    @Test
    void returnItemFromPatron() {
        ConsoleInputMock consoleInputMock = new ConsoleInputMock();
        Scanner scanner = new Scanner(consoleInputMock);
        consoleInputMock.writeStringln("John Doe");
        Patron patron = Main.signUpNewPatron(scanner);

        consoleInputMock.writeStringln(patron.ID());
        consoleInputMock.writeStringln(book1.getUniqueId());
        boolean result = Main.borrowItemToPatron(scanner);
        int borrowedCount = library.listBorrowed().size();
        assertEquals(result, true);
        assertEquals(borrowedCount, 1);

        consoleInputMock.writeStringln(patron.ID());
        consoleInputMock.writeStringln(book1.getUniqueId());
        result = Main.returnItemFromPatron(scanner);
        int availableCount = library.listAvailable().size();
        assertEquals(result, true);
        assertEquals(availableCount, 5);
    }

}
