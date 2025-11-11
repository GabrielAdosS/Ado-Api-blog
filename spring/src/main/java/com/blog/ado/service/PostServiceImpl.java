package com.blog.ado.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.ado.dto.PostDto;
import com.blog.ado.entities.PostEntity;
import com.blog.ado.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository repo;

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
	public List<PostDto> findAll() {
		List<PostEntity> listEntity = repo.findAll();
		List<PostDto> listDto = new ArrayList<PostDto>();
		for(PostEntity entity : listEntity) {
			listDto.add(toDto(entity));
		}
		return listDto;
	}

	@Override
	public PostDto addNew(PostDto dto) {
		PostEntity entity = toEntity(dto);
		repo.save(entity);
		return dto;
	}

	@Override
	public PostDto update(int id, PostDto dto) {
		Optional<PostEntity> entityBanco = repo.findById(id);
		if(entityBanco == null) {
			//tratar erro
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
	public PostDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
