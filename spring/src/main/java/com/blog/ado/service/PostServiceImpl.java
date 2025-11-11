package com.blog.ado.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.ado.dto.PostDto;
import com.blog.ado.entities.PostEntity;
import com.blog.ado.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository repo;

    private boolean calcularStatusPublicacao(LocalDate dataPost) {
        LocalDate hoje = LocalDate.now();
        return !dataPost.isAfter(hoje);
    }


	public PostDto toDto(PostEntity entity) {
		PostDto p = new PostDto();
        p.setId(entity.getId());
        p.setAutor(entity.getAutor());
		p.setTitulo(entity.getTitulo());
		p.setTexto(entity.getTexto());
		p.setDataPost(entity.getDataPost());
		p.setPublicado(entity.isPublicado());
        return p;
	}

    public PostEntity toEntity(PostDto dto) {
        PostEntity p = new PostEntity();
        p.setAutor(dto.getAutor());
        p.setTitulo(dto.getTitulo());
        p.setTexto(dto.getTexto());
        p.setDataPost(dto.getDataPost());
        p.setPublicado(calcularStatusPublicacao(dto.getDataPost()));

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
        PostEntity entitySalva = repo.save(entity);
        return toDto(entitySalva);
    }

	@Override
    public PostDto update(int id, PostDto dto) {
        Optional<PostEntity> entityBanco = repo.findById(id);
        if(entityBanco.isEmpty()) {
            throw new RuntimeException("Post não encontrado com id: " + id);
        }
        PostEntity entity = entityBanco.get();
        entity.setAutor(dto.getAutor());
        entity.setTitulo(dto.getTitulo());
        entity.setTexto(dto.getTexto());
        entity.setDataPost(dto.getDataPost());
        entity.setPublicado(calcularStatusPublicacao(dto.getDataPost()));

        PostEntity entitySalva = repo.save(entity);
        return toDto(entitySalva);
    }

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

	@Override
	public PostDto findById(int id) {
        Optional<PostEntity> entityBanco = repo.findById(id);
        if(entityBanco.isEmpty()) {
            throw new RuntimeException("Post não encontrado com id: " + id);
        }
        return toDto(entityBanco.get());
    }
}
