import React, {useEffect, useState} from "react";
import {Todo} from "../model";
import "./TodoList.css"
import TodoForm from "./TodoForm";
import TodoItem from "./TodoItem";

export default function TodoList() {

    const [todos, setTodos] = useState([] as Array<Todo>)

    const fetchAll = () => {
        fetch('http://localhost:8080/todos')
            .then(response => response.json())
            .then((todosFromBackend: Array<Todo>) => setTodos(todosFromBackend))
    }

    useEffect(() => {
        fetchAll()
    }, []);

    return (
        <div>
           <TodoForm onTodoCreation={setTodos}/>
            {todos.map(todo => <TodoItem key= {todo.id} todo={todo} onTodoDeletion={fetchAll} onTodoChange={setTodos} />)}
        </div>

    )

}



