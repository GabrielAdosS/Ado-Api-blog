package com.blog.ado.service;

import java.util.List;

import com.blog.ado.dto.PostDto;
import com.blog.ado.dto.PostDtoId;

public interface PostService {
    List<PostDtoId> findAll();

    PostDto addNew(PostDto dto);

    PostDto update(int id, PostDto dto);

    void delete(int id);
    
    PostDtoId findById(int id);
}
