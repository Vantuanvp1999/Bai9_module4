package com.example.baitap2.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotAvailableException.class)
    public String bookNotAvailable(BookNotAvailableException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
    @ExceptionHandler(InvalidBorrowCodeException.class)
    public String invalidBorrowCode(InvalidBorrowCodeException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        model.addAttribute("message", "Lỗi không xác đinh " +e.getMessage());
        return "error";
    }
}
