package booklibrary.model;

public abstract class Item {
    private String uniqueId;
    private String title;
    private boolean isBorrowed = false;

    Item(String uniqueId) {
        this.uniqueId = uniqueId;
    }
    Item(String title, String uniqueId) {
        this.title = title;
        this.uniqueId = uniqueId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String value)
    {
        title = value;
    }

    public String getUniqueId()
    {
        return uniqueId;
    }

    public void setUniqueId(String value)
    {
        uniqueId = value;
    }

    public boolean isBorrowed()
    {
        return isBorrowed;
    }

    protected void setBorrowed(boolean value)
    {
        isBorrowed = value;
    }

    abstract public void borrowItem();
    abstract public void returnItem();

}
