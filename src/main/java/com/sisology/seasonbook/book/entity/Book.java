package com.sisology.seasonbook.book.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookCode;
    private int seasonCode;
    private String bookName;
    private String author;
    private String bookCategory;

    public void modifyBookName(String bookName) {
        this.bookName = bookName;
    }

    public void modifyAuthor(String author) {
        this.author = author;
    }

    public void modifyBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public void modifySeasonCode(int seasonCode) {
        this.seasonCode = seasonCode;
    }
}
