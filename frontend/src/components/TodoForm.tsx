import {Todo} from "../model";
import {useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import LanguageSelection from "./LanguageSelection";
import {Link} from "react-router-dom";



interface TodoFormProps {
    onTodoCreation: (todos: Array<Todo>) => void
}

export default function TodoForm(props: TodoFormProps){

    const[title, setTitle] = useState(localStorage.getItem('title') ?? '')
    const[text, setText] = useState(localStorage.getItem('text') ?? '')
    const[addErrorMessage, setAddErrorMessage] = useState('');

    const{t} = useTranslation()

    useEffect(() => {
        setTimeout(() => setAddErrorMessage(''), 10000)
        localStorage.setItem('title', title)
        localStorage.setItem('text', text)
    } , [title, text]);

    const addTask = () => {
        setTitle('')
        setText('')
        fetch(`${process.env.REACT_APP_BASE_URL}/todos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                title: title,
                text: text
            })
        })
            .then(response => {
                if (response.ok){
                    return response.json()
                }
                throw Error('Eine Todo kann nicht hinzugefügt werden.')
            } )
            .then((todosFromBackend: Array<Todo>) => props.onTodoCreation(todosFromBackend))
            .catch(e => setAddErrorMessage(e.message))
    }

    return (
        <div className={'space-x-2 space-y-1'}>
            <LanguageSelection/>
            <div> <Link to={`About`}>About</Link></div>
            <input className={'border-2 rounded border-black'} type="text" placeholder={t('title')} value={title} onChange={ev => setTitle(ev.target.value)} />
            <input className={"border-2 rounded border-black"} type="text" placeholder={t('text')} value={text} onChange={ev => setText(ev.target.value)} />
            {addErrorMessage ? <h1>{addErrorMessage}</h1> : <button onClick={addTask} data-testid={'add-task-new'} className={"bg-slate-200"}>{t('send')}</button> }
        </div>
    )
}