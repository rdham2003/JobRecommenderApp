import{ useState, useEffect } from 'react'
import { Fragment } from 'react'
import SavedCard from "./SavedCard"

function Wishlist({username, email, joblist}){

    const [jobs, setJobs] = useState(JSON.parse(joblist))

    const handleLogIn3 = (jobs) => {
        setJobs(jobs);
        localStorage.setItem("joblist", JSON.stringify(jobs));
      }

    useEffect(() => {
        console.log(jobs);
    }, [jobs]);

    return (
        <Fragment>
            <h1><b>{username}'s jobs:</b></h1>
            <br />
            <div id="saved_cards">
                {jobs.map((job) => {
                    return <SavedCard job={job} onCallBack3={handleLogIn3}/>
                })}
            </div>
        </Fragment>
    )
}

export default Wishlist