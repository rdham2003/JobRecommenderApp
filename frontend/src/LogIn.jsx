import { useNavigate } from 'react-router-dom';
import { Fragment } from 'react'
import { useState } from 'react';

function LogIn(){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleSubmission = async(e) => {
        e.preventDefault();
    }

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    }

    const handlePassChange = (e) => {
        setPassword(e.target.value);
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
                        <input type="email" value={password} name="password" onChange={handlePassChange} class="form-control" id="exampleFormControlInput1" placeholder="name@example.com" />
                    </div>
                    <div id="submit_container">
                        <button id="submit_button" className="btn btn-success" type='submit'>Log In</button>
                    </div>
                    <div id="submit_container">
                        <p>Don't have an account?</p>
                        <button id="submit_button" className="btn btn-success" type='submit' onClick={() => navigate("/signup")}>Sign Up</button>
                    </div>
                </div>
            </form>
        </Fragment>
    )
}

export default LogIn