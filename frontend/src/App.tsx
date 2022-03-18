import React from 'react';
import Header from "./components/Header";
import TodoList from "./components/TodoList";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import About from "./components/About";
import Login from "./components/Login";
import Register from "./components/Register";
import Logout from "./components/Logout";




function App() {

    return (
        <div className={"max-w-xl m-auto mt-20"}>
            <Header/>
            <Logout/>
            <BrowserRouter>
                <Routes>
                    <Route path={'/'} element={<TodoList/>}/>
                    <Route path={'about'} element={<About/>}/>
                    <Route path={'login'} element={<Login/>}/>
                    <Route path={'register'} element={<Register/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default App;
