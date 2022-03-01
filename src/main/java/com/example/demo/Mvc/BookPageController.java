package com.example.demo.Mvc;

import com.example.demo.payload.BookDto;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookPageController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    BookService bookService;
    @GetMapping
    public String BookPage(Model model){
        model.addAttribute("newBook",new BookDto());
        model.addAttribute("books",bookRepository.findAll());
        model.addAttribute("categoryList",categoryRepository.findAll());
        model.addAttribute("libraryList",libraryRepository.findAll());
        return "BookPage";
    }
    @PostMapping("/add")
    public String addBooks(@ModelAttribute BookDto bookDto){

        bookService.add(bookDto);
        return "redirect:/book";
    }
    @PostMapping("/delete/{id}")
    public  String deleteBook(@PathVariable Integer id){
        bookService.delete(id);
        return "redirect:/book";
    }
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id,Model model){
        model.addAttribute("editBook",bookRepository.findById(id).get());
        model.addAttribute("libraryList",libraryRepository.findAll());
        model.addAttribute("categoryList",categoryRepository.findAll());
        return "bookEdit";
    }
    @PostMapping("/edit/{id}")
    public String saveEditedPage(@PathVariable Integer id,@ModelAttribute BookDto bookDto){
        bookService.edit(id,bookDto);
        return "redirect:/book";
    }
}
