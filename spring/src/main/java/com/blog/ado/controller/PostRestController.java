package com.blog.ado.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blog.ado.dto.PostDto;
import com.blog.ado.dto.PostDtoId;
import com.blog.ado.service.PostService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("post")
public class PostRestController {
	
	@Autowired
	private PostService service;
	
	@GetMapping("/todos")
	public List<PostDtoId> findAll() {
		return service.findAll();
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<PostDto> addNew(@RequestBody @Valid PostDto dto) {
		PostDto post = service.addNew(dto);
		 URI location = ServletUriComponentsBuilder
		            .fromCurrentRequestUri()
		            .path("/{titulo}")
		            .buildAndExpand(post.getTitulo())
		            .toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<PostDto> update(@PathVariable int id,  @RequestBody @Valid PostDto dto) {
		service.update(id, dto);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/apagar/{id}")
	public ResponseEntity<PostDto> delete(@PathVariable int id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
