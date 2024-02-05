package booklibrary;

import booklibrary.model.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {

    private List<Book> books = new ArrayList<Book>();

    @BeforeEach
    void initializeLibrary(){
        books.add(new Book("Harry Potter", "Joanne Rowling", "IDODNR-32423-3244", 1999));
        books.add(new Book("Children of Captain Grant", "Jules Gabriel Verne", "DNERN-2341-241211", 1854));
        books.add(new Book("The Witcher", "Andjey Sapkovsky", "MEMORE-612-1424531", 1989));
    }

    @Test
    void addNewBookTest() {
        InputStream input = new ByteArrayInputStream("Arom\nForge\nAAA-BBB\n1993".getBytes());
        Scanner scanner = new Scanner(input);
        Book result = Main.addNewBook(scanner);
        assertEquals(result.getName(), "Arom");
        assertEquals(result.getAuthor(), "Forge");
        assertEquals(result.getIsbn(), "AAA-BBB");
        assertEquals(result.getPublicationYear(), 1993);

    }

    @Test
    void searchBookTest(){
        InputStream input = new ByteArrayInputStream("Harry".getBytes());
        Scanner scanner = new Scanner(input);
        String expectedBook = Main.searchBooks(scanner, books).toString();
        String book = "[" + books.get(0).toString() + "]";
        assertEquals(expectedBook, book);

    }

    @Test
    void deleteBookTest(){
        InputStream input = new ByteArrayInputStream("IDODNR-32423-3244".getBytes());
        Scanner scanner = new Scanner(input);
        Book book = books.get(0);
        Boolean deletionAcception = Main.deleteBook(scanner, books);
        if (!books.contains(book))
        {
            assertEquals(true, deletionAcception);
        }
    }
}
