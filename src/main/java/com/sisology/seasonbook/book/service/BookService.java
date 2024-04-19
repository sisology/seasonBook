package com.sisology.seasonbook.book.service;

import com.sisology.seasonbook.book.dto.BookDTO;
import com.sisology.seasonbook.book.entity.Book;
import com.sisology.seasonbook.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    // findAll, pageable
    public Page<BookDTO> findBookList(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("bookCode").descending()
        );

        Page<Book> menuList = bookRepository.findAll(pageable);
        return menuList.map(menu -> modelMapper.map(menu, BookDTO.class));
    }

    // findById
    public BookDTO findBookByCode(int bookCode) {
        Book foundBook = bookRepository.findById(bookCode).orElseThrow(IllegalAccessError::new);
        return modelMapper.map(foundBook, BookDTO.class);
    }

    public List<BookDTO> findBySeasonCode(Integer seasonCode) {
        List<Book> bookList = bookRepository.findBySeasonCodeOrderByBookCode(seasonCode);
        return bookList.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .toList();
    }

    // Native
    public List<BookDTO> findBookByBookName() {
        List<Book> bookList = bookRepository.findBookByBookName();
        return bookList.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    // save
    @Transactional
    public void registBook(BookDTO bookDTO) {
        bookRepository.save(modelMapper.map(bookDTO, Book.class));
    }

    // modify
    @Transactional
    public void modifyBook(BookDTO bookDTO) {
        Book foundBook = bookRepository.findById(bookDTO.getBookCode())
                .orElseThrow(IllegalAccessError::new);

        foundBook.modifyBookName(bookDTO.getBookName());
        foundBook.modifyAuthor(bookDTO.getAuthor());
        foundBook.modifyBookCategory(bookDTO.getBookCategory());
        foundBook.modifySeasonCode(bookDTO.getSeasonCode());
    }

    // delete
    public void deleteBook(Integer bookCode) {
        bookRepository.deleteById(bookCode);
    }

}
