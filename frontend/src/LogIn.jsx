import { useNavigate } from 'react-router-dom';
import { Fragment } from 'react'
import { useState } from 'react';
import axios from 'axios';

function LogIn({onCallBack}){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [pass, setPass] = useState(true);

    const navigate = useNavigate();

    const handleSubmission = async(e) => {
        e.preventDefault();
        const form = new FormData();
        form.append("email", email);
        form.append("password", password);

        const response = await axios.post("http://localhost:8080/users/login", form);

        console.log(response.data);

        if (response.data == ""){
            document.getElementById("login_error").style.visibility = "visible";
            document.getElementById("login_container").style.height = "600px";
        }
        else{
            onCallBack(response.data.username);
            navigate("/");
        }
    }

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    }

    const handlePassChange = (e) => {
        setPassword(e.target.value);
    }
    
    const showPassword = () => {
        if (document.getElementById("pass").type == "password"){ 
            document.getElementById("pass").type = "text"
            setPass(false)
        }
        else{
             document.getElementById("pass").type = "password"
             setPass(true)
        }
        console.log(document.getElementById("pass").type == "password")
    }

    return (
        <Fragment>
            <br/>
            <br />
            <form onSubmit={handleSubmission}>
                <div className="form_container" id="login_container">
                    <h2 id="welcome">Welcome Back!</h2>
                    <div class="mb-3">
                        <br />
                        <label for="exampleFormControlInput1" class="form-label">Email address</label>
                        <input type="email" value={email} name="email" onChange={handleEmailChange} class="form-control" id="exampleFormControlInput1" placeholder="name@example.com" />
                    </div>
                    <div class="mb-3">
                        <br />
                        <label for="exampleFormControlInput1" class="form-label">Password</label>
                        <input type="password" value={password} name="password" onChange={handlePassChange} class="form-control" id="pass"/>
                    </div>
                    <div id="submit_container">
                    <button id="submit_button" className="btn btn-secondary" type='button' onClick={showPassword}>
                    {pass ?
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye-slash" viewBox="0 0 16 16">
                        <path d="M13.359 11.238C15.06 9.72 16 8 16 8s-3-5.5-8-5.5a7 7 0 0 0-2.79.588l.77.771A6 6 0 0 1 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13 13 0 0 1 14.828 8q-.086.13-.195.288c-.335.48-.83 1.12-1.465 1.755q-.247.248-.517.486z"/>
                        <path d="M11.297 9.176a3.5 3.5 0 0 0-4.474-4.474l.823.823a2.5 2.5 0 0 1 2.829 2.829zm-2.943 1.299.822.822a3.5 3.5 0 0 1-4.474-4.474l.823.823a2.5 2.5 0 0 0 2.829 2.829"/>
                        <path d="M3.35 5.47q-.27.24-.518.487A13 13 0 0 0 1.172 8l.195.288c.335.48.83 1.12 1.465 1.755C4.121 11.332 5.881 12.5 8 12.5c.716 0 1.39-.133 2.02-.36l.77.772A7 7 0 0 1 8 13.5C3 13.5 0 8 0 8s.939-1.721 2.641-3.238l.708.709zm10.296 8.884-12-12 .708-.708 12 12z"/>
                    </svg> :
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye-fill" viewBox="0 0 16 16">
                        <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0"/>
                        <path d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8m8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7"/>
                    </svg>
                    }
                    </button>
                        <button id="submit_button" className="btn btn-success" type='submit'>Log In</button>
                    </div>
                    <div id="submit_container">
                        <p>Don't have an account?</p>
                        <button id="submit_button" className="btn btn-success" type='button' onClick={() => navigate("/signup")}>Sign Up</button>
                    </div>
                    <div id="login_error">
                        <h1>Error: Email or Password incorrect!</h1>
                    </div>
                </div>
            </form>
        </Fragment>
    )
}

export default LogIn