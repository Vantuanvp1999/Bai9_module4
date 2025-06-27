package com.example.baitap2.controller;

import com.example.baitap2.model.Book;
import com.example.baitap2.service.BookService;
import com.example.baitap2.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    IBookService bookService;

    @GetMapping
    public String index(Model model) {
     model.addAttribute("books", bookService.findAll());
     return "books";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("book", new Book());
        return "create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/borrow")
    public String borrow(@PathVariable long id, Model model) {
        String code = bookService.borrowBook(id);
        model.addAttribute("code", code);
        return "borrow";
    }
    @GetMapping("/return")
    public String returnBook(Model model) {
        return "return";
    }
    @PostMapping("/return")
    public String returnBook(@RequestParam String code, Model model) {
        bookService.returnBook(code);
        model.addAttribute("message", "Trả sách thành công!");
        return "return";
    }
}
