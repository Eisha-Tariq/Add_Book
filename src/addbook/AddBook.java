/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package addbook;

/**
 *
 * @author Eisha Tariq
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddBook {
    public static void main(String[] args) {
        BookDao bookDao = new BookDaoImpl();

        // Create a JFrame to hold the components
        JFrame frame = new JFrame("Enter Book Details");

        // Create a JPanel with GridBagLayout to hold the input fields and button
        JPanel panel = new JPanel(new GridBagLayout());

        // Create labels and text fields for Book Name, Genre, and Price
        JLabel bookNameLabel = new JLabel("Book Name:");
        JTextField bookNameField = new JTextField(10);

        JLabel genreLabel = new JLabel("Genre:");
        JTextField genreField = new JTextField(10);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(10);

        // Create a button to add the book
        JButton addButton = new JButton("Submit");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the text fields
                String bookName = bookNameField.getText();
                String genre = genreField.getText();
                double price = Double.parseDouble(priceField.getText());

                // Create a new book instance
                Book newBook = new Book(bookName, genre, price);

                // Add the book using the BookDao
                bookDao.addBook(newBook);

                // Display a message dialog
                JOptionPane.showMessageDialog(frame, "Book added successfully!");
            }
        });

        // Create GridBagConstraints for layout control
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Add the components to the panel
        panel.add(bookNameLabel, constraints);
        constraints.gridy = 1;
        panel.add(genreLabel, constraints);
        constraints.gridy = 2;
        panel.add(priceLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(bookNameField, constraints);
        constraints.gridy = 1;
        panel.add(genreField, constraints);
        constraints.gridy = 2;
        panel.add(priceField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(addButton, constraints);

        // Add the panel to the JFrame
        frame.getContentPane().add(panel);

        // Set the JFrame size and make it visible
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


interface BookDao {
    void addBook(Book book);
    Book getBookById(int bookId);
}

class BookDaoImpl implements BookDao {
    private List<Book> bookList;

    public BookDaoImpl() {
        // Initialize the bookList
        bookList = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        // Add the book to the bookList
        bookList.add(book);
    }

    @Override
    public Book getBookById(int bookId) {
        // Find and return the book with the specified bookId
        for (Book book : bookList) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null; // Book not found
    }
}

class Book {
    private static int nextBookId = 1;
    private int bookId;
    private String bookName;
    private String genre;
    private double price;

    public Book(String bookName, String genre, double price) {
        this.bookId = nextBookId++;
        this.bookName = bookName;
        this.genre = genre;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }
}
