package com.blog.ado.dto;

import java.time.LocalDate;

public class PostId {
	
	private int id;
	
	private String titulo;
	
	private String autor;
	
	private String texto;
	
	private LocalDate dataPost;
	
	 public PostId() {
	}
	 
	public PostId(int id, String titulo, String autor, String texto, LocalDate dataPost) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.texto = texto;
		this.dataPost = dataPost;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public boolean isPublicado() {
		LocalDate atual = LocalDate.now();
		if (this.dataPost.isBefore(atual) || this.dataPost.isEqual(atual)) {
			return true;
		}
		return false;
	}
}
