package com.example.pp_spring.Controller;

import com.example.pp_spring.Model.Todo;
import com.example.pp_spring.Repository.TodoRepository;
import com.example.pp_spring.Service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {

    private final TodoService todoService;


    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String listTodos(Model model) {
        model.addAttribute("todos", todoService.findAll()); // Changed to "todos" for list

        if(!model.containsAttribute("todo")) {
            model.addAttribute("todo", new Todo()); // "todo" for the form
        }

        return "index";
    }


    @PostMapping("/add")
    public String addTodo(@Valid Todo todo, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("todos", todoService.findAll());
            return "index";
        }
        todoService.save(todo);
        return "redirect:/";
    }


    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id, @RequestParam String title, @RequestParam(defaultValue = "false") boolean completed) {

        todoService.update(id, title, completed);

        return "redirect:/";
    }

    //Added a seperate controller for completeng a task
    @PostMapping("/complete/{id}")
    public String updateTodo(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean completed) {
        todoService.complete(id, completed);

        return "redirect:/";
    }


    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteById(id);

        return "redirect:/";
    }



}
