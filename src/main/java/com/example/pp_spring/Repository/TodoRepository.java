package com.example.pp_spring.Repository;

import com.example.pp_spring.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByOrderByCreatedAtDesc();
    // Sort by createdAt in descending order
}
