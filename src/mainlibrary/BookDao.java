package mainlibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

    
public class BookDao {



    public static int save(String callno, String name, String author, String publisher, int quantity) {
        int status = 0;
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO books(callno, name, author, publisher, quantity) VALUES (?, ?, ?, ?, ?)")) {

            // Input validation (example: basic null check)
            if (callno == null || name == null || author == null || publisher == null || quantity < 0) {
                throw new IllegalArgumentException("Invalid input parameters");
            }

            ps.setString(1, callno);
            ps.setString(2, name);
            ps.setString(3, author);
            ps.setString(4, publisher);
            ps.setInt(5, quantity);
            status = ps.executeUpdate();

        } catch (Exception e) {
  
        }
        return status;
    }

    public static boolean PublisherValidate(String publisher) {
        boolean status = false;
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Publisher WHERE PublisherName = ?")) {

            ps.setString(1, publisher);
            try (ResultSet rs = ps.executeQuery()) {
                status = rs.next();
            }

        } catch (Exception e) {
           
        }
        return status;
    }

    public static int AddPublisher(String publisher) {
        int status = 0;
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Publisher(PublisherName) VALUES (?)")) {

            // Input validation (example: basic null check)
            if (publisher == null || publisher.isEmpty()) {
                throw new IllegalArgumentException("Publisher name cannot be null or empty");
            }

            ps.setString(1, publisher);
            status = ps.executeUpdate();

        } catch (Exception e) {
           // logger.error("Error adding publisher: ", e);
        }
        return status;
    }

    public static int SaveBook(String bookName, String author, String publisher, String genre, String shelf, String rose) {
        int status = 0;
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO books(BookName, Author, Publisher, Genre, Shelf, Rose) VALUES (?, ?, ?, ?, ?, ?)")) {

            // Input validation (example: basic null check)
            if (bookName == null || author == null || publisher == null || genre == null || shelf == null || rose == null) {
                throw new IllegalArgumentException("Invalid input parameters");
            }

            ps.setString(1, bookName);
            ps.setString(2, author);
            ps.setString(3, publisher);
            ps.setString(4, genre);
            ps.setString(5, shelf);
            ps.setString(6, rose);

            status = ps.executeUpdate();

        } catch (Exception e) {
//            logger.error("Error saving book: ", e);
        }
        return status;
    }

    public static int Delete(int bookID) {
        int status = 0;
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM Books WHERE BookID = ?")) {

            // Input validation
            if (bookID <= 0) {
                throw new IllegalArgumentException("Invalid book ID");
            }

            ps.setInt(1, bookID);
            status = ps.executeUpdate();

        } catch (Exception e) {
//            logger.error("Error deleting book: ", e);
        }
        return status;
    }
}
