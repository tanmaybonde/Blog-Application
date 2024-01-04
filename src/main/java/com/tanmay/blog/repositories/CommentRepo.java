package com.tanmay.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanmay.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer > {

}
