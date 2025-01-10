import{ useState, useEffect } from 'react'
import { Fragment } from 'react'
import SavedCard from "./SavedCard"

function Wishlist({username, email, joblist}){

    const [jobs, setJobs] = useState(JSON.parse(joblist))

    useEffect(() => {
        console.log(jobs);
    }, [jobs]);

    return (
        <Fragment>
            <h1><b>{username}'s jobs:</b></h1>
            <br />
            <div id="saved_cards">
                {jobs.map((job) => {
                    return <SavedCard job={job}/>
                })}
            </div>
        </Fragment>
    )
}

export default Wishlist