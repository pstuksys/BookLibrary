package com.paulius.apiJson.controller;

import com.paulius.apiJson.model.Book;
import com.paulius.apiJson.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return service.createBook(book);
    }

    @GetMapping("/all")
    public HashMap<String, Book> getAllBooks() {
        return service.getAllBooks();
    }

    @DeleteMapping("/delete/{guid}")
    public String deleteBookByGuid(@PathVariable("guid") String guid){
      return  service.deleteBook(guid);
    }

    @GetMapping("/guid/{guid}")
    public Object findByGuid(@PathVariable("guid") String guid){
        return service.findByGuid(guid);
    }


    @GetMapping("/filter/{name}")
    public Stream<Book> filterByName(@PathVariable("name") String name){
        return service.filterByName(name);
    }
    @GetMapping("/filterauthor/{author}")
    public Stream<Book> filterByAuthor(@PathVariable("author") String author){
        return service.filterByAuthor(author);
    }
    @GetMapping("/filtercategory/{category}")
    public Stream<Book> filterByCategory(@PathVariable("category") String category){
        return service.filterByCategory(category);
    }
    @GetMapping("/filterlanguage/{language}")
    public Stream<Book> filterByLanguage(@PathVariable("language") String language){
        return service.filterByLanguage(language);
    }
    @GetMapping("/filterlanguage/{isbn}")
    public Stream<Book> filterByLISBN(@PathVariable("isbn") String isbn){
        return service.filterByISBN(isbn);
    }


//    @PostMapping("/take/{guid}/{userInformation}/{optional1 : (/optional1)?}/{optional2 : (/optional2)?}")
    @PostMapping("/take/{guid}/{userInformation}/{optional1}/{optional2}")
    public String takeBook(@PathVariable("guid") String guid,
                           @PathVariable("userInformation") String userInformation,
                           @PathVariable(value = "optional1", required = false) String optional1,
                           @PathVariable(value = "optional2",required = false) String optional2){
        return service.takeBookFromLibrary(guid,userInformation,optional1,optional2);
    }

    @GetMapping("/taken")
    public HashMap<String, Book[]> takenBooks() throws FileNotFoundException {
        return service.getAllTakenBooks();
    }

}
