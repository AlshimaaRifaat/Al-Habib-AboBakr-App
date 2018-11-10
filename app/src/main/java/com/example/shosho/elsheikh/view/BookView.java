package com.example.shosho.elsheikh.view;

import com.example.shosho.elsheikh.model.BookData;

import java.util.List;

public interface BookView {
    void showBooksData(List<BookData> booksData);
    void Error();
}
