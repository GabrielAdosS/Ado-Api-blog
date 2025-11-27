package com.blog.ado.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.ado.dto.Post;
import com.blog.ado.dto.PostId;
import com.blog.ado.entities.PostEntity;
import com.blog.ado.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiceImplement implements ServicePostagem {
	
	@Autowired
	private PostRepository repo;

	public PostId toDtoId(PostEntity entity) {
		PostId p = new PostId();
		p.setId(entity.getId());
		p.setAutor(entity.getAutor());
		p.setTitulo(entity.getTitulo());
		p.setTexto(entity.getTexto());
		p.setDataPost(entity.getDataPost());
		return p;
	}
	public Post toDto(PostEntity entity) {
		Post p = new Post();
        p.setAutor(entity.getAutor());
		p.setTitulo(entity.getTitulo());
		p.setTexto(entity.getTexto());
		p.setDataPost(entity.getDataPost());
        return p;
	}

    public PostEntity toEntity(Post dto) {
        PostEntity p = new PostEntity();
        p.setAutor(dto.getAutor());
        p.setTitulo(dto.getTitulo());
        p.setTexto(dto.getTexto());
        p.setDataPost(dto.getDataPost());
        
        return p;
    }

	@Override
	public List<PostId> findAll() {
		List<PostEntity> listEntity = repo.findAll();
		List<PostId> listDto = new ArrayList<PostId>();
		for(PostEntity entity : listEntity) {
			listDto.add(toDtoId(entity));
		}
		return listDto;
	}

	@Override
    public Post addNew(Post dto) {
        PostEntity entity = toEntity(dto);
        PostEntity entitySalva = repo.save(entity);
        return toDto(entitySalva);
    }

	@Override
	public Post update(int id, Post dto) {
		Optional<PostEntity> entityBanco = repo.findById(id);
		if(entityBanco == null) {
			throw new EntityNotFoundException("Post de id %i não encontrado!".formatted(id));
		}
		PostEntity entity = entityBanco.get();
		entity.setAutor(dto.getAutor());
		entity.setTitulo(dto.getTitulo());
		entity.setTexto(dto.getTexto());
		entity.setDataPost(dto.getDataPost());
		repo.save(entity);
		return dto;
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}
	@Override
	public PostId findById(int id) {
		Optional<PostEntity> entity = repo.findById(id);
		if(entity == null) {
			throw new EntityNotFoundException("Erro ao buscar por id " + id);
		}
		PostId dto = toDtoId(entity.get());
		return dto;
	}
}
