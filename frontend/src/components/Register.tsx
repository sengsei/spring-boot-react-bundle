import {useEffect, useState} from "react";
import {useNavigate} from "react-router";


const Register = () => {

    const[email, setEmail] = useState("")
    const[password, setPassword] = useState("")
    const[vPassword, setVPassword] = useState("")
    const[errorMessage, setErrorMessage] = useState("")
    const navigate = useNavigate()

    useEffect(() => {
        const timeoutId = setTimeout(() => setErrorMessage(''), 15000);
        return () => clearTimeout(timeoutId);
    }, [errorMessage]);

    const register = () => {
        if (!password || !vPassword || !email){
            setErrorMessage('some fields are empty')
        }
        if (password !== vPassword){
            setErrorMessage('password is not the same')
        }
        fetch(`${process.env.REACT_APP_BASE_URL}/api/users`, {
            method:"POST",
            body: JSON.stringify({
                "email":email,
                "password": password,
                "passwordAgain": vPassword
            }),
            headers: {
                "Content-Type":"application/json"
            }
        })
            .then(response => {
                if (response.status === 201){
                    return response.json()
                }
                throw new Error()
            })
            .then(() => navigate("/login"))

    }

    return (
        <div>
            <input type={"text"} placeholder={"E-Mail"} value={email} onChange={e => setEmail(e.target.value)}/><br/>
            <input type={"password"} placeholder={"password"} value={password} onChange={e => setPassword(e.target.value)}/><br/>
            <input type={"password"} placeholder={"reply password"} value={vPassword} onChange={e => setVPassword(e.target.value)}/><br/>
            <button onClick={register}>Register</button>
        </div>

    )

}

export default Register