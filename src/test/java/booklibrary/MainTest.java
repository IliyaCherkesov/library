
package booklibrary;

import booklibrary.model.*;
import booklibrary.utils.ConsoleInputMock;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
    private List<Book> books = new ArrayList<Book>();

    @BeforeEach
    void initializeLibrary(){
        books.add(new Book("Harry Potter and The Room", "Joanne Rowling", "IDODNR-32423-3244", 1999));
        books.add(new Book("Children of Captain Grant", "Jules Gabriel Verne", "DNERN-2341-241211", 1854));
        books.add(new Book("The Witcher", "Andjey Sapkovsky", "MEMORE-612-1424531", 1989));
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
        assertEquals(result.getName(), "Arom");
        assertEquals(result.getAuthor(), "Forge");
        assertEquals(result.getIsbn(), "AAA-BBB");
        assertEquals(result.getPublicationYear(), 1994);

        books.add(result);
        assertEquals(books.size(), 4);
    }

    @Test
    void searchBookTest(){
        ConsoleInputMock consoleInputMock = new ConsoleInputMock();
        Scanner scanner = new Scanner(consoleInputMock);
        consoleInputMock.writeStringln("Harry");
        List<Book> result = Main.searchBooks(scanner, books);
        assertEquals(result.size(), 1);

        consoleInputMock.writeStringln("The");
        result = Main.searchBooks(scanner, books);
        assertEquals(result.size(), 2);

        consoleInputMock.writeStringln("Aurger");
        result = Main.searchBooks(scanner, books);
        assertEquals(result.size(), 0);
    }

    @Test
    void deleteBookTest(){
        ConsoleInputMock consoleInputMock = new ConsoleInputMock();
        Scanner scanner = new Scanner(consoleInputMock);
        consoleInputMock.writeStringln("IDODNR-32423-3244");
        boolean result = Main.deleteBook(scanner, books);
        assertEquals(result, true);
        assertEquals(books.size(), 2);

        consoleInputMock.writeStringln("MEMORE-612-1424531");
        result = Main.deleteBook(scanner, books);
        assertEquals(result, true);
        assertEquals(books.size(), 1);

        consoleInputMock.writeStringln("Aurger");
        result = Main.deleteBook(scanner, books);
        assertEquals(result, false);
        assertEquals(books.size(), 1);
    }
}
