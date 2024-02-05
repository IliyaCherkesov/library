package booklibrary.model;

import java.util.ArrayList;
import java.util.List;

public class Library implements IManageable {
    private List<Item> items = new ArrayList<Item>(0);
    private List<Patron> patrons = new ArrayList<Patron>(0);

    public Library() {
        this.items = new ArrayList<Item>(0);
    }

    public Patron signUpNewPatron(String name) {
        Patron patron = new Patron(name);
        this.patrons.add(patron);
        return patron;
    }

    public boolean borrowItem(String patronId, String itemId) {
        Patron patron = this.patrons.stream().filter(p -> p.ID().equals(patronId)).findFirst().orElse(null);
        Item item = this.items.stream().filter(i -> i.getUniqueId().equals(itemId)).findFirst().orElse(null);
        if (patron != null && item != null) {
            patron.borrow(item);
            return true;
        }
        return false;
    }

    public boolean returnItemBack(String patronId, String itemId) {
        Patron patron = this.patrons.stream().filter(p -> p.ID().equals(patronId)).findFirst().orElse(null);
        Item item = this.items.stream().filter(i -> i.getUniqueId().equals(itemId)).findFirst().orElse(null);
        if (patron != null && item != null) {
            patron.returnItem(item);
            return true;
        }
        return false;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public List<Book> getBooks() {
        return this.items.stream().filter(item -> item instanceof Book).map(item -> (Book) item).toList();
    }

    public List<Dvd> getDvds() {
        return this.items.stream().filter(item -> item instanceof Dvd).map(item -> (Dvd) item).toList();
    }

    @Override
    public void add(Item item) {
        this.items.add(item);
    }

    @Override
    public void remove(Item item) {
        this.items.remove(item);
    }

    @Override
    public List<Item> listAvailable() {
        return this.items.stream().filter(item -> !item.isBorrowed()).toList();
    }

    @Override
    public List<Item> listBorrowed() {
        return this.items.stream().filter(item -> item.isBorrowed()).toList();
    }

    @Override
    public String toString() {
        return "Library {" +
                "items=" + items.stream().map(item -> item.toString()+"\n").toList() +
                '}';
    }

}
