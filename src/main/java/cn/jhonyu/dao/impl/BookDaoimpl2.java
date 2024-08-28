package cn.jhonyu.dao.impl;

import cn.jhonyu.dao.BookDao;
import cn.jhonyu.entity.Book;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDaoimpl2 implements BookDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll() {
        //lambda表达式
        String sql="select * from books";
        List<Book> books=jdbcTemplate.query(sql,(resultSet,num)->{
            Book book =new Book().setId(resultSet.getInt("id"))
                    .setTitle(resultSet.getString("title"))
                    .setPrice(resultSet.getDouble("price"))
                    .setQuantity(resultSet.getInt("quantity"));
            return book;
        });
        return books;
    }

    @Override
    public int addBook(Book book) {
        String sql="insert into books(title,price,quantity) values (?,?,?)";
        return jdbcTemplate.update(sql,book.getTitle(),book.getPrice(),book.getQuantity());

    }
}
