package BookLibrary.Model;

public class Book {
    private String name;
    private String author;
    private String isbn;
    private int publicationYear;

    public Book(String name, String author, String isbn, int publicationYear)
    {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String value)
    {
        name = value;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String value)
    {
        author = value;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String value)
    {
        isbn = value;
    }

    public int getPublicationYear()
    {
        return publicationYear;
    }

    public void setPublicationYear(int value)
    {
        this.publicationYear = value;
    }

    @Override
    public String toString()
    {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
