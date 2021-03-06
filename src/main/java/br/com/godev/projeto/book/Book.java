package br.com.godev.projeto.book;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table
public class Book {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

	@ApiModelProperty(value = "Book title")
	@Column(nullable = false)
    private String title;

	@ApiModelProperty(value = "Author name")
	@Column(nullable = false)
    private String nameAuthor;

	public Book() {

	}

	public Book(String title, String nameAuthor) {
		this.nameAuthor = nameAuthor;
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

	public void setNameAutor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}

}
