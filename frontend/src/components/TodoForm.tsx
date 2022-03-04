import {Todo} from "../model";
import {useEffect, useState} from "react";
import "./TodoForm.css"
import {useTranslation} from "react-i18next";
import LanguageSelection from "./LanguageSelection";


interface TodoFormProps {
    onTodoCreation: (todos: Array<Todo>) => void
}

export default function TodoForm(props: TodoFormProps){

    const[title, setTitle] = useState(localStorage.getItem('title') ?? '')
    const[text, setText] = useState(localStorage.getItem('text') ?? '')

    const{t} = useTranslation()

    useEffect(() => {
        localStorage.setItem('title', title)
        localStorage.setItem('text', text)
    } , [title, text]);

    const addTask = () => {
        setTitle('')
        setText('')
        fetch(`${process.env.REACT_APP_DEV_URL}/todos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                title: title,
                text: text
            })
        })
            .then(response => response.json())
            .then((todosFromBackend: Array<Todo>) => props.onTodoCreation(todosFromBackend))

    }

    return (
        <div>
            <LanguageSelection/>
            <input type="text" placeholder={t('title')} value={title} onChange={ev => setTitle(ev.target.value)} />
            <input className={"text-field"} type="text" placeholder={t('text')} value={text} onChange={ev => setText(ev.target.value)} />
            <button onClick={addTask} className={"send-button"}>{t('send')}</button>
        </div>
    )
}