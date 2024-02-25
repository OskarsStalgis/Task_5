package com.example.pp_spring.Service;

import com.example.pp_spring.Model.Todo;
import com.example.pp_spring.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {


    private final TodoRepository todoRepository;


    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // returns all data from table
    public List<Todo> findAll() {
        return todoRepository.findAllByOrderByCreatedAtDesc(); // Use the sorted method
    }

    // save todos
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    // find todos by id
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    // delete todos by id
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    // update todos by id
    public Todo update(Long id, String title, boolean completed) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid todo id: " + id));

        todo.setTitle(title);
        todo.setCompleted(completed);

        return todoRepository.save(todo);
    }

    //set todos as completed if the task is completed or set as not completed if completed
    public Todo complete(Long id, boolean completed) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid todo id: " + id));
            boolean status = todo.isCompleted();

            System.out.println(status);

            if(status) {
                todo.setCompleted(false);
            }else{
                todo.setCompleted(true);
            }

            return todoRepository.save(todo);

    }


}
