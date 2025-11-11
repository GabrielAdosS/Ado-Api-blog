package com.blog.ado.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blog.ado.dto.PostDto;
import com.blog.ado.service.PostService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("post")
public class PostRestController {

    @Autowired
    private PostService service;

    @GetMapping("/todos")
    public List<PostDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<PostDto> findById(@RequestParam int id) {
        PostDto post = service.findById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
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

    @PutMapping("/atualizar")
    public ResponseEntity<PostDto> update(@RequestParam int id, @RequestBody @Valid PostDto dto) {
        PostDto post = service.findById(id);
        if(post == null) {
            //tratar
        }
        post = service.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/apagar")
    public ResponseEntity<PostDto> delete(@RequestParam int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}