package com.optimist.service;

import com.optimist.pojo.Books;

import java.util.List;

/**
 * @author 65667
 */
public interface BookService {

    //增加一本书
    int addBook(Books books);

    //删除一本书
    int deleteBookById(int id);

    //更新一本书
    int updateBook(Books books);

    //查询一本书
    Books queryBookById(int id);

    //查询全部的书
    List<Books> queryAllBook();

    //通过Name查询一本书
    Books queryBookByName(String bookName);
}
