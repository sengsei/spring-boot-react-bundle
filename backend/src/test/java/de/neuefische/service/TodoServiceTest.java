package de.neuefische.service;

import de.neuefische.TodoElement;
import de.neuefische.TodoState;
import de.neuefische.repository.TodoRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TodoServiceTest {

    @Test
    void shouldAddNewTodo() {
        TodoElement todo1 = new TodoElement();
        todo1.setTitle("Sport");
        todo1.setState(TodoState.Open);

        TodoRepo repo = Mockito.mock(TodoRepo.class);
        TodoService todoService = new TodoService(repo);

        todoService.addTodo(todo1);

        Mockito.verify(repo).save(todo1);
    }

    @Test
    void shouldRetrieveAllTodos() {
        TodoElement todo1 = new TodoElement();
        todo1.setTitle("Sport");
        todo1.setState(TodoState.Open);

        TodoElement todo2 = new TodoElement();
        todo2.setTitle("Java");
        todo2.setState(TodoState.Open);

        List<TodoElement> todoList = List.of(todo1, todo2);
        TodoRepo repo = Mockito.mock(TodoRepo.class);
        Mockito.when(repo.getTodoList()).thenReturn(todoList);

        TodoService todoService = new TodoService(repo);

        Collection<TodoElement> actual = todoService.getTodoList();

        assertThat(actual).isEqualTo(todoList);
    }

    @Test
    void shouldRetrieveOneTodo() {
        TodoElement todo1 = new TodoElement();
        todo1.setTitle("Sport");
        todo1.setState(TodoState.Open);

        TodoRepo repo = Mockito.mock(TodoRepo.class);
        Mockito.when(repo.getTodoElementByID(todo1.getId())).thenReturn(todo1);

        TodoService todoService = new TodoService(repo);

        TodoElement actual = todoService.getTodoElementById(todo1.getId());

        assertThat(actual).isEqualTo(todo1);
    }

    @Test
    void shouldDeleteTodo() {
        String id = "777";

        TodoRepo repo = Mockito.mock(TodoRepo.class);
        TodoService todoService = new TodoService(repo);

        todoService.deleteTodo(id);

        Mockito.verify(repo).delete(id);
    }

    @Test
    void shouldChangeTodo() {
        TodoElement todo1 = new TodoElement();
        todo1.setId("777");
        todo1.setTitle("Sport");
        todo1.setState(TodoState.Open);

        TodoElement savedTodo = new TodoElement();
        savedTodo.setId("777");
        savedTodo.setTitle("Sport");
        savedTodo.setState(TodoState.Done);

        TodoRepo repo = Mockito.mock(TodoRepo.class);
        Mockito.when(repo.getTodoElementByID("777")).thenReturn(todo1);

        TodoService todoService = new TodoService(repo);

        todoService.changeTodo("777", savedTodo);

        Mockito.verify(repo).save(savedTodo);
    }



}