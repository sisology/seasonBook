package com.sisology.seasonbook.book.repository;

import com.sisology.seasonbook.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    // 책 제목별로 조회
    @Query(
            value = "SELECT * FROM book ORDER BY book_name",
            nativeQuery = true
    )
    List<Book> findBookByBookName();

    // 전달받은 계절별로 조회
    List<Book> findBySeasonCodeOrderByBookCode(Integer seasonCode);

}
