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
        todoRepo.addTodo(todoElement);
        TodoService todoService = new TodoService(todoRepo);

        List<TodoElement> expected = List.of(todoElement);

        assertEquals(expected, todoService.getTodoList());
    }

    @Test
    void addTodo() {
        TodoElement todoElement = new TodoElement();
        todoElement.setTitle("Sport");
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(todoElement);
        TodoService todoService = new TodoService(todoRepo);

        List<TodoElement> expected = List.of(todoElement);

        assertEquals(expected, todoService.getTodoList());
    }

    @Test
    void getTodoElementByTitle() {
        TodoElement todoElement = new TodoElement();
        todoElement.setTitle("Sport");
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(todoElement);
        TodoService todoService = new TodoService(todoRepo);
        String expected = "Sport";

        assertEquals(expected, todoService.getTodoElementByTitle("Sport").orElse(todoElement).getTitle());
    }

    @Test
    void getAllCompletedTodoElements() {
        TodoElement todoElement = new TodoElement();
        todoElement.setTitle("Sport");
        todoElement.setState(true);
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(todoElement);
        TodoService todoService = new TodoService(todoRepo);
        List<TodoElement> expected = List.of(todoElement);

        assertEquals(expected, todoService.getAllCompletedTodoElements());

    }

    @Test
    void getAllUncompletedTodoElements() {
        TodoElement todoElement = new TodoElement();
        todoElement.setTitle("Sport");
        TodoElement todoElement1 = new TodoElement();
        todoElement1.setTitle("Java");
        todoElement1.setState(true);
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(todoElement);
        todoRepo.addTodo(todoElement1);
        TodoService todoService = new TodoService(todoRepo);
        List<TodoElement> expected = List.of(todoElement);

        assertEquals(expected, todoService.getAllUncompletedTodoElements());
    }

    @Test
    void shouldSetStateDone(){
        TodoElement todoElement = new TodoElement();
        todoElement.setTitle("Sport");
        TodoRepo todoRepo = new TodoRepo();
        todoRepo.addTodo(todoElement);
        TodoService todoService = new TodoService(todoRepo);

        todoService.setState(todoElement.getId());

        assertTrue(todoService.getTodoElementByTitle("Sport").orElse(todoElement).isState());



    }
}