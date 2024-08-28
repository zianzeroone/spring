package cn.jhonyu.dao.impl;

import cn.jhonyu.dao.BookDao;
import cn.jhonyu.entity.Book;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoimpl implements BookDao {
    private DataSource dataSource=null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Book> findAll() {
        String sql = "select * from books";
        List<Book> books = new ArrayList<>();
        Connection connection =null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection=dataSource.getConnection();
            statement=connection.prepareStatement(sql);
            resultSet =statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book().setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setPrice(resultSet.getDouble("price"))
                        .setQuantity(resultSet.getInt("quantity"));
                books.add(book);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
    @Override
    public int addBook(Book book) {
        String sql = "insert into books(title,price,quantity) values (?,?,?)";
        Connection connection =null;
        PreparedStatement statement = null;
        try {
            connection=dataSource.getConnection();
            connection.setAutoCommit(true);
            statement=connection.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            statement.setDouble(2,book.getPrice());
            statement.setInt(3,book.getQuantity());
            return statement.executeUpdate();
//            connection.setAutoComait(true);
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, book.getTitle());
//            ps.setDouble(2, book.getPrice());
//            ps.setInt(3, book.getQuantity());
//            return ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
