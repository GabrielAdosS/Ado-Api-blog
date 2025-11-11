package com.blog.ado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.ado.entities.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{
}
