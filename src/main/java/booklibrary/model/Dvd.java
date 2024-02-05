package booklibrary.model;

import booklibrary.utils.UniqueId;

public class Dvd extends Item {
    private int duration = 0;

    public Dvd(String title, int duration) {
        super(title, UniqueId.generateUniqueId());
        this.duration = duration;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int value)
    {
        duration = value;
    }

    @Override
    public void borrowItem() {
        this.setBorrowed(true);
    }

    @Override
    public void returnItem() {
        this.setBorrowed(false);
    }

    @Override
    public String toString()
    {
        return "DVD {" +
                "\tId='" + this.getUniqueId() + "\'\n" +
                "\tTitle='" + this.getTitle() + "\'\n" +
                "\tDuration='" + this.getDuration() + "\'\n" +
                '}';
    }

}
