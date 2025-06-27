package com.example.baitap2.service;

import com.example.baitap2.exception.BookNotAvailableException;
import com.example.baitap2.exception.InvalidBorrowCodeException;
import com.example.baitap2.model.Book;
import com.example.baitap2.model.BorrowCode;
import com.example.baitap2.repository.BookRepository;
import com.example.baitap2.repository.BorrowCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowCodeRepository borrowCodeRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public String borrowBook(long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent()) {
            throw new BookNotAvailableException("Không tìm thấy sách có id: " + bookId);
        }
        Book book = bookOptional.get();
        if (book.getQuantity()<=0){
            throw new BookNotAvailableException("Sách "+book.getTitle()+" đã hết!");
        }
        book.setQuantity(book.getQuantity()-1);
        bookRepository.save(book);
        String code;
        do {
            code = String.format("%05d", new Random().nextInt(100000));
        }while (borrowCodeRepository.existsById(code));
        BorrowCode borrowCode = new BorrowCode();
        borrowCode.setBook(book);
        borrowCode.setCode(code);
        borrowCodeRepository.save(borrowCode);
        return code;
    }

    @Override
    public void returnBook(String borrowCode) {
        BorrowCode code = borrowCodeRepository.findById(borrowCode)
                .orElseThrow(() -> new InvalidBorrowCodeException("Mã mượn "+borrowCode+" không hợp lệ!"));
    Book book = code.getBook();
    book.setQuantity(book.getQuantity()+1);
    bookRepository.save(book);
    borrowCodeRepository.delete(code);

    }

    @Override
    public void saveBook(Book book) {
    bookRepository.save(book);
    }
}
