import React from 'react';
import {State, Todo} from "../model";
import "./TodoItem.css"

interface TodoItemProps {
    todo: Todo
    onTodoDeletion: () => void
    onTodoChange: (todos: Array<Todo>) => void
}

const TodoItem = (props: TodoItemProps ) => {

    const deleteTodo = () => {
        fetch(`${process.env.REACT_APP_DEV_URL}/todos/${props.todo.id}`, {
            method: 'DELETE'
        })
            .then(() => props.onTodoDeletion())
    }

    const toggle = () => {
        const newStatus = props.todo.state === State.Open ? State.Done : State.Open

        fetch(`${process.env.REACT_APP_DEV_URL}/todos/${props.todo.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: props.todo.id,
                title: props.todo.title,
                text: props.todo.text,
                state: newStatus
            })
        })
            .then(response => response.json())
            .then((todosFromBackend: Array<Todo>) => props.onTodoChange(todosFromBackend))
    }

    return (
        <div >
            <div className={props.todo.state === State.Done ? 'selected': ''} onClick={toggle}>{props.todo.title} - {props.todo.text}</div> <button onClick={deleteTodo}>Delete</button>
        </div>
    )
}


export default TodoItem;