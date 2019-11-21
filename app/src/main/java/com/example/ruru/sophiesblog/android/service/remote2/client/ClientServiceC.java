package com.example.ruru.sophiesblog.android.service.remote2.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.android.service.remote2.server.Book;
import com.example.ruru.sophiesblog.android.service.remote2.server.BookController;
import com.example.ruru.sophiesblog.android.service.remote2.server.ServiceC;

import java.util.List;

public class ClientServiceC extends AppCompatActivity implements View.OnClickListener {

    private BookController bookController;

    private boolean connected;

    private List<Book> bookList;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(getClass().getName(), "onServiceConnected: ");

            //获取bookController对象
            bookController = BookController.Stub.asInterface(service);
            connected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_service_c);

        findViewById(R.id.getBookList).setOnClickListener(this);
        findViewById(R.id.addBook).setOnClickListener(this);

        bindService();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getBookList:
                if (connected) {
                    try {
                        bookList = bookController.getBookList();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    log();
                }
                break;
            case R.id.addBook:
                if (connected) {
                    Book book = new Book("这是一本新书 InOut");
                    try {
                        bookController.addBookInOut(book);
                        Log.d(getClass().getName(), "向服务器以InOut方式添加了一本新书");
                        Log.d(getClass().getName(), "新书名：" + book.getName());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connected) {
            unbindService(serviceConnection);
        }
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.aaa.servicec.action");
        intent.setPackage("com.example.ruru.sophiesblog.android.service.remote2.server");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void log() {
        for (Book book : bookList) {
            Log.d(getClass().getName(), book.toString());
        }
    }
}
