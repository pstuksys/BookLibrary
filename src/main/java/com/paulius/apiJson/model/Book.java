package com.paulius.apiJson.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class Book {

    private String name;
    private String author;
    private String category;
    private String language;
    private String PublicationDate;
    private String ISBN;
    private String GUID;


}
