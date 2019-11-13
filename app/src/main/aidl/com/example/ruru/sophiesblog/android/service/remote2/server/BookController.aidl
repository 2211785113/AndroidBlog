// BookController.aidl
package com.example.ruru.sophiesblog.android.service.remote2.server;

import com.example.ruru.sophiesblog.android.service.remote2.server.Book;

interface BookController {

    //获取书籍列表
    List<Book> getBookList();

    //添加书籍
    void addBookInOut(inout Book book);
}
