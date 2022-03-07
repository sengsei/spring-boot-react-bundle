import React, {useState} from 'react';
import {State, Todo} from "../model";
import "./TodoItem.css"
import {useTranslation} from "react-i18next"

interface TodoItemProps {
    todo: Todo
    onTodoDeletion: () => void
    onTodoChange: (todos: Array<Todo>) => void
}

const TodoItem = (props: TodoItemProps ) => {
    const {t} = useTranslation()
    const[toggleErrorMessage, setToggleErrorMessage] = useState('')

    const deleteTodo = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todos/${props.todo.id}`, {
            method: 'DELETE'
        })
            .then(() => props.onTodoDeletion())
    }

    const toggle = () => {
        const newStatus = props.todo.state === State.Open ? State.Done : State.Open

        fetch(`${process.env.REACT_APP_BASE_URL}/todos/${props.todo.id}`, {
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
            .then(response => {
                if (response.ok){
                    return response.json()
                }
                throw Error('Da gibt es nichts zu Togglen!')

            })
            .then((todosFromBackend: Array<Todo>) => props.onTodoChange(todosFromBackend))
            .catch(e => setToggleErrorMessage(e.message))
    }

    return (
        <div >
            {toggleErrorMessage ? <h1>{toggleErrorMessage}</h1> : ''}
            <div data-testid={'toggleMessage'} className={props.todo.state === State.Done ? 'selected': ''}
                  onClick={toggle}>{props.todo.title} - {props.todo.text}</div>
            <button className={'bg-slate-200'} onClick={deleteTodo}>{t('delete')}</button>
        </div>
    )
}


export default TodoItem;