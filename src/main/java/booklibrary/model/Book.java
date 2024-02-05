package booklibrary.model;

import booklibrary.utils.UniqueId;

public class Book extends Item {
    private String name;
    private String author;
    private String isbn;
    private int publicationYear;

    public Book(String name, String author, String isbn, int publicationYear)
    {
        super(UniqueId.generateUniqueId());
        this.setTitle(name);
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
        return "Book {" +
                "\tId='" + this.getUniqueId() + "\'\n" +
                "\tName='" + this.getName() + "\'\n" +
                "\tAuthor='" + this.getAuthor() + "\'\n" +
                "\tISBN='" + this.getIsbn() + "\'\n" +
                "\tPublication Year=" + this.getPublicationYear() + '\n' +
        '}';
    }

    @Override
    public void borrowItem() {
        this.setBorrowed(true);
    }

    @Override
    public void returnItem() {
        this.setBorrowed(false);
    }
}
