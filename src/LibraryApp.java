import java.util.List;
import java.util.Scanner;


public class LibraryApp {
    public static void main(String[] args)
    {
        LibraryOperations lo=new LibraryOperations();
        Scanner sc=new Scanner(System.in);

        while (true)
        {
            System.out.println("\n\t------ Library Management System ------");
            System.out.println("\t1.Add Book ");
            System.out.println("\t2.View All Books");
            System.out.println("\t3.Delete Book");
            System.out.println("\t4.Exit");
            System.out.println("Enter Choice");
            int choice=sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.println("Enter Book ID");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Book Title");
                    String title=sc.nextLine();
                    System.out.println("Enter Book Author Name");
                    String author=sc.nextLine();
                    System.out.println("Enter Book Price");
                    double price=sc.nextDouble();

                    System.out.println(lo.addBook(new Book(id,title,author,price)));
                    break;

                case 2:
                    List<Book> books=lo.getAllBooks();
                    books.forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Enter Book ID to Delete");
                    int Id=sc.nextInt();
                    lo.deleteBook(Id);
                    break;

                case 4:
                    System.out.println("\tExiting....");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("\tInvalid Choice ");


            }
        }
    }
}

