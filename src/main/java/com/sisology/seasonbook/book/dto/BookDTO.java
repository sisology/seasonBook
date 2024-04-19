package com.sisology.seasonbook.book.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookDTO {

    private int bookCode;
    private int seasonCode;
    private String bookName;
    private String author;
    private String bookCategory;

}
