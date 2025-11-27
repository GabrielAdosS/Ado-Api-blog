package com.blog.ado.service;

import java.util.List;

import com.blog.ado.dto.Post;
import com.blog.ado.dto.PostId;

public interface ServicePostagem {
    List<PostId> findAll();

    Post addNew(Post dto);

    Post update(int id, Post dto);

    void delete(int id);
    
    PostId findById(int id);
}
