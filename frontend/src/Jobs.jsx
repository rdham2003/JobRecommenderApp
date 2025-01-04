import { useState, useEffect, useRef, Fragment } from 'react';
import Card from './Card'

function Jobs(data){
    const [jobArr, setJobArr] = useState(data.data);

    const removeJob = () => {
        if (jobArr.length > 0) {
            setJobArr(jobArr.slice(1));
        }
    };

    useEffect(() => {
        console.log(jobArr);
    }, [jobArr]);

    return (
        <Fragment>
            <div id="card_list">
                <button className="btn btn-success" id="save" onClick={removeJob}>Save this Job</button>
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