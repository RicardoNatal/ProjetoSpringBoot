package br.com.godev.projeto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.godev.projeto.book.Book;
import br.com.godev.projeto.dto.BookDTO;
import br.com.godev.projeto.dto.BookMapper;
import br.com.godev.projeto.dto.BookNewDTO;
import br.com.godev.projeto.repository.BookRepository;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<BookNewDTO> addBook(@RequestBody BookNewDTO bookDTO) {
        final Book bookEntity = bookMapper.toEntity(bookDTO);
        final Book bookSaved = bookRepository.save(bookEntity);
        BookNewDTO dto = bookMapper.toNewDTO(bookSaved);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> all(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "nameAuthor", required = false) String nameAuthor) {
        List<Book> books = bookRepository.findAll();

        if (title == null && nameAuthor == null) {
            return ResponseEntity.ok().body(bookMapper.toDTO(books));
        } else if (title != null && nameAuthor != null) {
            List<Book> listNameAuthor = new ArrayList<>();
            for (Book book : books) {

                if (book.getTitle().toLowerCase().contains(title.toLowerCase()) && book.getNameAuthor().toLowerCase().equals(nameAuthor.toLowerCase())) {
                    listNameAuthor.add(book);
                }
            }
            return new ResponseEntity<>(bookMapper.toDTO(listNameAuthor), HttpStatus.OK);

        } else if (title != null && nameAuthor == null) {
            List<Book> BooksSameTitle = new ArrayList<>();
            for (int i = 0; i < books.size(); i++) {

                if (books.get(i).getTitle().toLowerCase().contains(title.toLowerCase())) {
                    BooksSameTitle.add(books.get(i));
                }

            }
            return ResponseEntity.ok().body(bookMapper.toDTO(BooksSameTitle));
        } else if (nameAuthor != null && title == null) {
            List<Book> booksSameAuthor = new ArrayList<>();
            for (int i = 0; i < books.size(); i++) {

                if (books.get(i).getNameAuthor().toLowerCase().contains(nameAuthor.toLowerCase())) {
                    booksSameAuthor.add(books.get(i));
                }

            }
            return new ResponseEntity<>(bookMapper.toDTO(booksSameAuthor), HttpStatus.OK);
        }
        return new ResponseEntity<>(bookMapper.toDTO(books), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Book> changeBook(@RequestBody Book book, @PathVariable Long id) {
        Book newBook = bookRepository.getById(id);
        newBook.setTitle(book.getTitle());
        newBook.setNameAutor(book.getNameAuthor());
        Book bookSave = bookRepository.save(newBook);
        return ResponseEntity.ok().body(bookSave);

    }

    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> getById(@PathVariable(value = "id") Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
