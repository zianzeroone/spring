package cn.jhonyu.dao;

import cn.jhonyu.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAll();
    int addBook(Book book);

}
