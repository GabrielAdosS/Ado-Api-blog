package com.blog.ado.service;

import java.util.List;

import com.blog.ado.dto.PostDto;

public interface PostService {
    List<PostDto> findAll();

    PostDto addNew(PostDto dto);

    PostDto update(int id, PostDto dto);

    void delete(int id);
    
    PostDto findById(int id);
}
