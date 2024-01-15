import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
public class Main
{
    public static void main(String[] args)
    {
        ArrayList<String> LibraryBooks = new ArrayList<String>();
        LibraryBooks.add("Harry Potter");
        LibraryBooks.add("Children of Captain Grant");
        LibraryBooks.add("The Witcher");
        while (true)
        {
            Scanner in = new Scanner(System.in);
            System.out.println("If you want to add your book write 1. If you want to see all of the books print 2. If you want to see the book info print 3 and write it's name");
            String str = in.nextLine();
            if (str.equals("1"))
            {
                String str1 = in.nextLine();
                LibraryBooks.add(str1);
            }
            int ArraySize = LibraryBooks.toArray().length;
            if (str.equals("2"))
            {
                    System.out.println(LibraryBooks);
            }
                if (str.equals("3"))
                {
                    String str2 = in.nextLine();
                    for (int i = 0; i < ArraySize; i++)
                    {
                        if(LibraryBooks.get(i).equals(str2))
                        {
                            System.out.println("Book name: ");
                        }

                    }
                }

        }
    }
}
