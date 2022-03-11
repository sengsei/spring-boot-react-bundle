package de.neuefische.repository;

import de.neuefische.TodoElement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TodoRepo extends MongoRepository<TodoElement, String> {


    /*
    private final Map<String, TodoElement> todoList = new HashMap<>();

    public Collection<TodoElement> getTodoList(){
        return todoList.values();
    }

    public TodoElement getTodoElementByID(String id){
        return todoList.get(id);
    }

    public void save(TodoElement todoElement) {
        todoList.put(todoElement.getId(), todoElement);
    }

    public void delete(String id){
        todoList.remove(id);
    }
    */

}
