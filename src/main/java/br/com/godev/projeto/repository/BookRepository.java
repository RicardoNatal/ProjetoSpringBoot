package br.com.godev.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.godev.projeto.book.Book;

@Repository
public interface BookRepository extends JpaRepository <Book, Long> {



}
