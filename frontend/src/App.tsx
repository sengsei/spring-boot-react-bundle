import React from 'react';
import Header from "./components/Header";
import TodoList from "./components/TodoList";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import About from "./components/About";




function App() {

    return (
        <div>
            <Header/>
            <BrowserRouter>
                <Routes>
                    <Route path={'/'} element={<TodoList/>}/>
                    <Route path={'about'} element={<About/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default App;
