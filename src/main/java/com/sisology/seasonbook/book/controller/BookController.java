package com.sisology.seasonbook.book.controller;

import com.sisology.seasonbook.book.common.Pagenation;
import com.sisology.seasonbook.book.common.PagingButton;
import com.sisology.seasonbook.book.dto.BookDTO;
import com.sisology.seasonbook.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 책 전체 조회
    @GetMapping("/list")
    public String findBookList(@PageableDefault Pageable pageable,
                               Model model) {

        Page<BookDTO> bookList = bookService.findBookList(pageable);
        PagingButton paging = Pagenation.getPagingButtonInfo(bookList);

        model.addAttribute("bookList", bookList);
        model.addAttribute("paging", paging);

        return "book/list";
    }

    // 책 상세보기
    @GetMapping("{bookCode}")
    public String findBookByCode(@PathVariable int bookCode,
                                 Model model) {

        BookDTO result = bookService.findBookByCode(bookCode);
        model.addAttribute("book", result);

        return "book/detail";
    }

    // 계절별 추천 도서 보기
    @GetMapping("/selectSeason")
    public void selectSeasonPage() {
    }

    @GetMapping("/seasonBook")
    public String findBySeasonCode(@RequestParam int seasonCode,
                                   Model model) {

        List<BookDTO> bookList = bookService.findBySeasonCode(seasonCode);
        model.addAttribute("bookList", bookList);

        return "book/seasonBook";
    }

    // 제목별 책 조회하기
    @GetMapping("/listByBookName")
    public List<BookDTO> findByBookName(Model model) {

        List<BookDTO> bookList = bookService.findBookByBookName();
        model.addAttribute("bookList", bookList);
        
        return bookService.findBookByBookName();
    }

    // 책 등록하기
    @GetMapping("/regist")
    public void registPage() {
    }

    @PostMapping("/regist")
    public String registNewBook(@ModelAttribute BookDTO bookDTO) {
        bookService.registBook(bookDTO);
        return "redirect:/book/list";
    }

    // 책 수정하기
    @GetMapping("/modify")
    public void modifyPage() {
    }

    @PostMapping("/modify")
    public String modifyBook(@ModelAttribute BookDTO bookDTO) {
        bookService.modifyBook(bookDTO);
        return "redirect:/book/" + bookDTO.getBookCode();
    }

    @GetMapping("/delete")
    public void deletePage() {}

    @PostMapping("/delete")
    public String deleteBook(@RequestParam Integer bookCode) {
        bookService.deleteBook(bookCode);
        return "redirect:/book/list";
    }
}
