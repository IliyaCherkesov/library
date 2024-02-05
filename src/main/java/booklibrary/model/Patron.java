package booklibrary.model;

import java.util.ArrayList;
import java.util.List;

import booklibrary.utils.UniqueId;

public class Patron {
    private String name;
    private String id;
    private List<Item> borrowedItems = new ArrayList<Item>(0);

    Patron(String name) {
        this.id = UniqueId.generateUniqueId();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String ID() {
        return id;
    }

    public void borrow(Item item) {
        item.borrowItem();
        borrowedItems.add(item);
    }

    public void returnItem(Item item) {
        item.returnItem();
        borrowedItems.remove(item);
    }
}
