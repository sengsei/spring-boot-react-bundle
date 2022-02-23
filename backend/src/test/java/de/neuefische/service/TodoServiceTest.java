package de.neuefische.service;

import de.neuefische.TodoElement;
import de.neuefische.repository.TodoRepo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoServiceTest {

    @Test
    void getTodoList() {
        TodoElement todoElement = new TodoElement();
        todoElement.setTitle("Sport");
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.save(todoElement);
        TodoService todoService = new TodoService(todoRepo);

        List<TodoElement> expected = List.of(todoElement);

        assertEquals(expected, todoService.getTodoList());
    }

    @Test
    void addTodo() {
        TodoElement todoElement = new TodoElement();
        todoElement.setTitle("Sport");
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.save(todoElement);
        TodoService todoService = new TodoService(todoRepo);

        List<TodoElement> expected = List.of(todoElement);

        assertEquals(expected, todoService.getTodoList());
    }

}