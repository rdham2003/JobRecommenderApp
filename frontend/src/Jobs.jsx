import { useState, useEffect, useRef, Fragment } from 'react';
import axios from 'axios';
import Card from './Card'
import { useNavigate } from 'react-router-dom';

function Jobs({ data, isLogged, username }){
    const [jobArr, setJobArr] = useState(data);

    const navigate = useNavigate();

    const handleSave = async (e) => {
        e.preventDefault();
        console.log(isLogged);
        if (!isLogged){
            navigate("/login")
        }
        if (jobArr.length > 0) {
            // console.log("hi")
            const form = new FormData();
            form.append("username", username)
            const response = await axios.post("http://localhost:8080/users/save_job", form);
            console.log(response.data);
            setJobArr(jobArr.slice(1));
        }
    }

    const removeJob = () => {
        if (jobArr.length > 0) {
            console.log("Deleted Job");
            setJobArr(jobArr.slice(1));
        }
    };

    useEffect(() => {
        console.log(jobArr);
    }, [jobArr]);

    return (
        <Fragment>
            <div id="card_list">
                <form onSubmit={handleSave}>
                    <button className="btn btn-success" id="save">{isLogged ? "Save this job" : "Sign in to save"}</button>
                </form>
                <button className="btn btn-success" id="remove" onClick={removeJob}>Not interested!</button>
                
                {jobArr.length > 0 ? ( 
                    <Card job={jobArr[0]}/>
                    ) : 
                    <h1>No more jobs! Upload a different resume or try again soon.</h1>
                }
            </div>
        </Fragment>
    )
}

export default Jobs