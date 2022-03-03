import React, {useEffect, useState} from "react";
import {Todo} from "../model";
import "./TodoList.css"
import TodoForm from "./TodoForm";
import TodoItem from "./TodoItem";

export default function TodoList() {

    const [todos, setTodos] = useState([] as Array<Todo>)

    const fetchAll = () => {
        fetch(`${process.env.REACT_APP_DEV_URL}/todos`)
            .then(response => response.json())
            .then((todosFromBackend: Array<Todo>) => setTodos(todosFromBackend))
    }

    const deleteChecked = () => {
        fetch(`${process.env.REACT_APP_DEV_URL}/todos`, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then((todosFromBackend: Array<Todo>) => setTodos(todosFromBackend))
    }

    useEffect(() => {
        fetchAll()
    }, []);

    return (
        <div className="todo-list">
            <div>
                <TodoForm onTodoCreation={setTodos} />
            </div>
            <div>
                <button onClick={deleteChecked}>Delete all checked</button>
            </div>
            <ul>
                {todos.map(todo => <li key={todo.id}><TodoItem todo={todo} onTodoDeletion={fetchAll} onTodoChange={setTodos} /></li>)}
            </ul>
        </div>
    )

}



