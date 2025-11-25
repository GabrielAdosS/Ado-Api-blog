package com.blog.ado.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class PostDto {

    @NotBlank(message = "Titulo não pode ser nulo/vazio")
	private String titulo;
	
	@NotBlank(message = "Autor não pode ser nulo/vazio")
	private String autor;
	
	@NotBlank(message = "Autor não pode ser nulo/vazio")
	@Size(min = 10)
	private String texto;

	private LocalDate dataPost;

	public PostDto() {
	}

    public PostDto(String titulo, String autor, String texto, LocalDate dataPost, boolean publicado) {
		this.titulo = titulo;
		this.autor = autor;
		this.texto = texto;
		this.dataPost = dataPost;
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

    public LocalDate getDataPost() {
		return dataPost;
	}

	public void setDataPost(LocalDate dataPost) {
		this.dataPost = dataPost;
	}
}
