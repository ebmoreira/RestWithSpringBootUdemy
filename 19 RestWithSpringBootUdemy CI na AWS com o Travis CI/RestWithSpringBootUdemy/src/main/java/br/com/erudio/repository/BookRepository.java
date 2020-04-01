package br.com.erudio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.erudio.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	@Query("SELECT p FROM Book p WHERE p.title LIKE LOWER(CONCAT ('%', :title, '%'))")
	Page<Book> findBookByName(@Param("title") String title, Pageable pageable);
}
