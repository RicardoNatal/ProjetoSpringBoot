package br.com.godev.projeto.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.godev.projeto.book.Book;

@Service
public class BookMapper {


    public BookDTO toDTO(Book book) {
        final BookDTO dto = new BookDTO();
        dto.id = book.getId();
        dto.title = book.getTitle();
        dto.nameAuthor = book.getNameAuthor();

        return dto;
    }

    public List<BookDTO> toDTO(List<Book> books) {
        final ArrayList<BookDTO> booksDto = new ArrayList<>();
        for (Book book : books) {
            booksDto.add(toDTO(book));
        }

        return booksDto;
    }

    public Book toEntity(BookDTO dto) {
        final Book book = new Book();
        book.setTitle(dto.title);
        book.setNameAutor(dto.nameAuthor);

        return book;
    }

    public Book toEntity(BookNewDTO dto) {
        final Book book = new Book();
        book.setTitle(dto.title);
        book.setNameAutor(dto.nameAuthor);

        return book;
    }

    public BookNewDTO toNewDTO(Book book) {
        final BookNewDTO dto = new BookNewDTO();
        dto.id = book.getId();
        dto.title = book.getTitle();
        dto.nameAuthor = book.getNameAuthor();

        return dto;
    }

}



