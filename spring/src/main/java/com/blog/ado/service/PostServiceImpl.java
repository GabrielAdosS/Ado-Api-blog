package com.blog.ado.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.ado.dto.PostDto;
import com.blog.ado.dto.PostDtoId;
import com.blog.ado.entities.PostEntity;
import com.blog.ado.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository repo;

	public PostDtoId toDtoId(PostEntity entity) {
		PostDtoId p = new PostDtoId();
		p.setId(entity.getId());
		p.setAutor(entity.getAutor());
		p.setTitulo(entity.getTitulo());
		p.setTexto(entity.getTexto());
		p.setDataPost(entity.getDataPost());
		return p;
	}
	public PostDto toDto(PostEntity entity) {
		PostDto p = new PostDto();
        p.setAutor(entity.getAutor());
		p.setTitulo(entity.getTitulo());
		p.setTexto(entity.getTexto());
		p.setDataPost(entity.getDataPost());
        return p;
	}

    public PostEntity toEntity(PostDto dto) {
        PostEntity p = new PostEntity();
        p.setAutor(dto.getAutor());
        p.setTitulo(dto.getTitulo());
        p.setTexto(dto.getTexto());
        p.setDataPost(dto.getDataPost());

        return p;
    }

	@Override
	public List<PostDtoId> findAll() {
		List<PostEntity> listEntity = repo.findAll();
		List<PostDtoId> listDto = new ArrayList<PostDtoId>();
		for(PostEntity entity : listEntity) {
			listDto.add(toDtoId(entity));
		}
		return listDto;
	}

	@Override
    public PostDto addNew(PostDto dto) {
        PostEntity entity = toEntity(dto);
        PostEntity entitySalva = repo.save(entity);
        return toDto(entitySalva);
    }

	@Override
	public PostDto update(int id, PostDto dto) {
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
	public PostDtoId findById(int id) {
		return null;
	}
}
