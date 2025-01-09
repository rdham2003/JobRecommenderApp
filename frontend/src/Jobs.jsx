import { useState, useEffect, useRef, Fragment } from 'react';
import axios from 'axios';
import Card from './Card'
import { useNavigate } from 'react-router-dom';

function Jobs({ data, isLogged, email }){
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
            form.append("email", email)
            form.append("jobId", jobArr[0].jobId);
            form.append("title", jobArr[0].title);
            form.append("company", jobArr[0].company);
            form.append("description", jobArr[0].description);
            form.append("job_url", jobArr[0].job_url);
            form.append("job_posted", jobArr[0].job_posted);
            form.append("location", jobArr[0].location);
            form.append("min_salary", jobArr[0].min_salary);
            form.append("max_salary", jobArr[0].max_salary);
            for (const [key, value] of form.entries()) {
                console.log(`${key}: ${value}`);
            }
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