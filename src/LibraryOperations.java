import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryOperations
{
    static {
        String create=" CREATE TABLE IF NOT EXISTS books(id INT PRIMARY KEY, title VARCHAR(100), author VARCHAR(100), price DECIMAL(10,2))";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement())
        {
            st.executeUpdate(create);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //ADD BOOK
    public String addBook(Book book)
    {
        String sql = "INSERT INTO books(id, title, author, price) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql);)
        {
            st.setInt(1, book.getId());
            st.setString(2, book.getTitle());
            st.setString(3, book.getAuthor());
            st.setDouble(4, book.getPrice());
            st.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return "\t✅Book Add Sucessfully";
    }
    //GET ALL BOOKS
    public List<Book> getAllBooks()
    {
        List<Book> books=new ArrayList<>();
        String sql="SELECT * FROM books";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs=st.executeQuery();)
        {
            while (rs.next())
            {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price")
                ));
            }

            if (books.isEmpty()) {
                System.out.println("\t⚠️ No books found in the database.");
            }
            else {
                System.out.println("\t------ All Books ------");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return books;
    }

    //DELECT BOOK
    public void deleteBook(int id)
    {
        String sql="DELETE FROM books WHERE id=?";
        try(Connection con=DatabaseConnection.getConnection();
            PreparedStatement st= con.prepareStatement(sql);)
        {
            st.setInt(1, id);
            int row=st.executeUpdate();
            if (row>0)
            {
                System.out.println("\t✅Book deleted Sucessfully");
            }
            else
            {
                System.out.println("\t⚠️No found with this ID"+id);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
