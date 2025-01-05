import { useState, useEffect, useRef, Fragment } from 'react';
import Card from './Card'

function Jobs(data){
    const [jobArr, setJobArr] = useState(data.data);

    const handleSave = async () => {
        if (jobArr.length > 0) {
            const form = new FormData()
            form.append('job', jobArr.append[0]);
            const response = await axios.post("http://localhost:8080/jobs/save", form, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            console.log(response);
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
                    <button className="btn btn-success" id="save">Save this Job</button>
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