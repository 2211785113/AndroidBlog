// BookController.aidl
package com.example.ruru.sophiesblog.android.service.remote2.server;

////导入所需要使用的非默认支持数据类型的包
import com.example.ruru.sophiesblog.android.service.remote2.server.Book;

interface BookController {

    //获取书籍列表
    List<Book> getBookList();

    //添加书籍
    void addBookInOut(inout Book book);
}
