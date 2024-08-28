package cn.jhonyu;

import cn.jhonyu.dao.BookDao;
import cn.jhonyu.dao.impl.BookDaoimpl;
import cn.jhonyu.entity.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.List;

public class MyApp {
    public static void main(String[] args) throws Exception{
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//        DataSource dataSource =context.getBean("dataSource", DataSource.class);
//        System.out.println(dataSource.getConnection());
//从IOC容器中获取对象
        BookDao bookDao =context.getBean("bookDao2", BookDao.class);
//        int m=bookDao.addBook(new Book().setTitle("Java123").setPrice(100).setQuantity(100));
//        System.out.println(m);
        System.out.println(bookDao.findAll());
    }
}
