package com.blog.ado.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostDto {

    private int id;

    @NotBlank(message = "Titulo não pode ser nulo/vazio")
	private String titulo;
	
	@NotBlank(message = "Autor não pode ser nulo/vazio")
	private String autor;
	
	@NotBlank(message = "Autor não pode ser nulo/vazio")
	@Size(min = 10)
	private String texto;

    @FutureOrPresent(message = "A data deve ser hoje ou no futuro!")
	private LocalDate dataPost;
	private boolean publicado;

	public PostDto() {
	}

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public PostDto(String titulo, String autor, String texto, LocalDate dataPost, boolean publicado) {
		this.titulo = titulo;
		this.autor = autor;
		this.texto = texto;
		this.dataPost = dataPost;
	    this.publicado = publicado;
    }

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataPost() {
		return dataPost;
	}

	public void setDataPost(LocalDate dataPost) {
		this.dataPost = dataPost;
	}
}
