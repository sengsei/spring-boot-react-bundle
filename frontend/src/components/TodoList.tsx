import React, {useEffect, useState} from "react";
import {Todo} from "../model";
import "./TodoList.css"
import TodoForm from "./TodoForm";
import TodoItem from "./TodoItem";
import {useTranslation} from "react-i18next";

export default function TodoList() {

    const [todos, setTodos] = useState([] as Array<Todo>)
    const {t} = useTranslation();
    const[errorMessage, setErrorMessage] = useState('')
    const[deleteErrorMessage, setDeleteErrorMessage] = useState('')

    const fetchAll = () => {
        fetch(`${process.env.REACT_APP_DEV_URL}/todos`)
            .then(response =>
            {
                if(response.ok){
                    return  response.json()
                }
                throw new Error('Es sind keine Todos zum Anzeigen vorhanden!')

            })
            .then((todosFromBackend: Array<Todo>) => setTodos(todosFromBackend))
            .catch((e: Error) => setErrorMessage(e.message))
    }

    const deleteChecked = () => {
        fetch(`${process.env.REACT_APP_DEV_URL}/todos`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok){
                    return response.json()
                }
                throw new Error('Die zu löschende Nachricht existiert nicht!')
            } )
            .then((todosFromBackend: Array<Todo>) => setTodos(todosFromBackend))
            .catch(e => setDeleteErrorMessage(e.message))
    }

    useEffect(() => {
        setTimeout(() => setErrorMessage(''), 10000)
        fetchAll()
    }, []);

    return (
        <div className="todo-list">
            <div>
                <TodoForm onTodoCreation={setTodos} />
            </div>
            <div>
                {
                    deleteErrorMessage ? <h1>{deleteErrorMessage}</h1> : <button onClick={deleteChecked}>{t('delete-checked')}</button>
                }

            </div>
            <div>
                {
                    errorMessage ? <h1>{errorMessage}</h1>
                        :
                    todos.map((todo) => <div key={todo.id} data-testid={'add-task'}><TodoItem todo={todo} onTodoDeletion={fetchAll} onTodoChange={setTodos} /></div>)}
            </div>
        </div>
    )

}



