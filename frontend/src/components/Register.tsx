import {useState} from "react";


const Register = () => {

    const[email, setEmail] = useState("")
    const[password, setPassword] = useState("")
    const[role, setRole] = useState("")
    const[token, setToken] = useState("")

    const register = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/api/auth/login`, {
            method:"POST",
            body: JSON.stringify({
                "username":email,
                "password": password
            }),
            headers: {
                "Content-Type":"application/json"
            }
        })
            .then(response => {
                return response.text()
            })
            .then((responseBody: string) => {setToken(responseBody)})
            .then(() => localStorage.setItem("token", token))

    }

    return (
        <div>
            <input type={"text"} placeholder={"E-Mail"} value={email} onChange={e => setEmail(e.target.value)}/><br/>
            <input type={"text"} placeholder={"password"} value={password} onChange={e => setPassword(e.target.value)}/><br/>
            <input type={"text"} placeholder={"role"} value={role} onChange={e => setRole(e.target.value)}/><br/>
            <button onClick={register}>Register</button>
        </div>

    )

}

export default Register