package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.entity.Library;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.BookDto;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LibraryRepository libraryRepository;
    public ApiResponse add(BookDto bookDto){
        Optional<Category> categoryOptional=categoryRepository.findById(bookDto.getCategoryId());
        Optional<Library> libraryOptional=libraryRepository.findById(bookDto.getLibraryId());
        Book book=new Book();
        book.setName(bookDto.getName());
        book.setCategory(categoryOptional.get());
        book.setLibrary(libraryOptional.get());
        Book save = bookRepository.save(book);
        return new ApiResponse("added",true,save);

    }
    public ApiResponse delete(Integer id){
        if(!bookRepository.existsById(id)) return new ApiResponse("error",false);
        bookRepository.deleteById(id);
        return new ApiResponse("success",true);
    }
    public ApiResponse edit(Integer id, BookDto bookDto){
        Optional<Book> byId = bookRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("xatolik", false);
        }
        Book book=byId.get();
        book.setName(bookDto.getName());
        Optional<Category> byId1 = categoryRepository.findById(bookDto.getCategoryId());
        book.setCategory(byId1.get());
        Optional<Library> byId2 = libraryRepository.findById(bookDto.getLibraryId());
        book.setLibrary(byId2.get());
        Book save=bookRepository.save(book);
        return new ApiResponse("edited",true,save);
    }
}
