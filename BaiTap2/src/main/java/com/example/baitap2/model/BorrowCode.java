package com.example.baitap2.model;

import javax.persistence.*;

@Entity
@Table(name = "borrow_code")
public class BorrowCode {
    @Id
    private String code;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public BorrowCode() {
    }

    public BorrowCode(String code, Book book) {
        this.code = code;
        this.book = book;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
