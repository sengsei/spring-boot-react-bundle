import React, {useState} from 'react';
import {State, Todo} from "../model";
import "./TodoItem.css"
import {useTranslation} from "react-i18next"

interface TodoItemProps {
    todo: Todo
    onTodoDeletion: () => void
    onTodoChange: (todos: Array<Todo>) => void
}

const TodoItem = (props: TodoItemProps) => {
    const {t} = useTranslation()
    const [toggleErrorMessage, setToggleErrorMessage] = useState('')
    const [titleToEdit, setTitleToEdit] = useState(props.todo.title)
    const [textToEdit, setTextToEdit] = useState(props.todo.text)
    const [editMode, setEditMode] = useState(false)

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
                if (response.ok) {
                    return response.json()
                }
                throw Error('Da gibt es nichts zu Togglen!')

            })
            .then((todosFromBackend: Array<Todo>) => props.onTodoChange(todosFromBackend))
            .catch(e => setToggleErrorMessage(e.message))
    }

    const editTodo = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todos/${props.todo.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: props.todo.id,
                title: titleToEdit,
                text: textToEdit,
                state: props.todo.state
            })
        })
            .then(response => response.json())
            .then((todosFromBackend: Array<Todo>) => {
                props.onTodoChange(todosFromBackend)
                setEditMode(false)
            })

    }

    return (
        <div>

            {
                editMode
                    ?
                    <div className="space-x-2 space-y-1">

                        <input className={'border-2 rounded border-black'} type="text"
                               value={titleToEdit}
                               onChange={ev => setTitleToEdit(ev.target.value)}
                           />
                        <input className={'border-2 rounded border-black '} type="text"
                               value={textToEdit}
                               onChange={ev => setTextToEdit(ev.target.value)}
                             />
                        <button className={'bg-slate-200 hover:bg-slate-400 rounded px-2'} onClick={editTodo}>{t('confirm')}</button>

                    </div>
                    :

                    <div className={'space-x-1 relative m-auto'}>
                        {toggleErrorMessage ? <h1>{toggleErrorMessage}</h1> : ''}
                        <div data-testid={'toggleMessage'} className={props.todo.state === State.Done ? 'selected' : ''}
                             onClick={toggle}>{props.todo.title} - {props.todo.text}</div>
                        <div className={"flex flex-row space-x-2"}>
                            <button className={'bg-slate-200 absolute top-0 right-20 rounded hover:bg-slate-400 px-2'} onClick={() => setEditMode(true)}>{t('edit')}</button>
                            <button className={'bg-slate-200 absolute bottom-0 right-0 rounded hover:bg-slate-400 px-2'} onClick={deleteTodo}>{t('delete')}</button>
                        </div>



                    </div>
            }
        </div>
    )
}


export default TodoItem;