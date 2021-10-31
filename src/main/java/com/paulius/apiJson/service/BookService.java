package com.paulius.apiJson.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.paulius.apiJson.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookService {

    private static Map<String, Book> books = new HashMap<>();
    private static Map<String, Book[]> takenBooks = new HashMap<String, Book[]>();
    private static long index = 1l;

    public static Book createBook(Book book) {
        books.put(book.getGUID(), book);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter("src/main/java/com/paulius/apiJson/bookLibrary/books.json")) {
            gson.toJson(books, fileWriter);
            System.out.println("Adding book to the library");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;
    }

    public static HashMap<String, Book> getAllBooks() {
        Gson gson = new Gson();
        String json = gson.toJson(books);
        Type type = new TypeToken<HashMap<String, Book>>() {
        }.getType();
        HashMap<String, Book> copy = gson.fromJson(json, type);
        return copy;
    }

    public String deleteBook(String guid) {
        if (books.containsKey(guid)) {
            books.remove(guid);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter fileWriter = new FileWriter("src/main/java/com/paulius/apiJson/bookLibrary/books.json")) {
                gson.toJson(books, fileWriter);
                System.out.println("Library Updated");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Deleted book from the library";
        } else {
            return "Book with Guid value: " + guid + " Not found.";
        }
    }


    public Object findByGuid(String guid) {
        if (books.containsKey(guid)) {
            return books.get(guid);
        }
        return "Not found";
    }


    public Stream<Book> filterByName(String name) {
        return books.values().stream().filter(x -> x.getName().equals(name));
    }

    public Stream<Book> filterByAuthor(String author) {
        return books.values().stream().filter(x -> x.getAuthor().equals(author));
    }

    public Stream<Book> filterByCategory(String category) {
        return books.values().stream().filter(x -> x.getCategory().equals(category));
    }

    public Stream<Book> filterByLanguage(String language) {
        return books.values().stream().filter(x -> x.getLanguage().equals(language));
    }

    public Stream<Book> filterByISBN(String isbn) {
        return books.values().stream().filter(x -> x.getISBN().equals(isbn));
    }

    public String takeBookFromLibrary(String guid,String userInformation, String optional1,String optional2) {
        Book[] myArray = new Book[3];
        if (books.containsKey(guid)){
            myArray[0] = books.get(guid);
            System.out.println("Book has been taken");
        }
        if (books.containsKey(optional1)){myArray[1] = books.get(optional1);}
        if (books.containsKey(optional2)){myArray[2] = books.get(optional2);}
        takenBooks.put(userInformation,myArray);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter("src/main/java/com/paulius/apiJson/bookLibrary/takenBooks.json")) {
            gson.toJson(takenBooks, fileWriter);
            System.out.println("Book has been taken from the library");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Book: " + books.get(guid) + " is now taken";
    }

    public HashMap<String, Book[]> getAllTakenBooks() throws FileNotFoundException {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Book[]>>() {
        }.getType();
        HashMap<String, Book[]> getTaken = gson.fromJson(
                new FileReader("src/main/java/com/paulius/apiJson/bookLibrary/takenBooks.json"), type);
        return getTaken;
    }
}
