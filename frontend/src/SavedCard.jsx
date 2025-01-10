import { Fragment } from "react";
import axios from "axios";

function SavedCard({job, onCallBack3}){
    function date_posted(date){
        let newDate = "";
        switch(date.substring(5,7)){
            case "01": newDate+="January "; break;
            case "02": newDate+="February "; break;
            case "03": newDate+="March "; break;
            case "04": newDate+="April "; break;
            case "05": newDate+="May "; break;
            case "06": newDate+="June "; break;
            case "07": newDate+="July "; break;
            case "08": newDate+="August "; break;
            case "09": newDate+="September "; break;
            case "10": newDate+="October "; break;
            case "11": newDate+="November "; break;
            case "12": newDate+="December "; break;
        }
        newDate+=date.substring(8,10) + ", " + date.substring(0,4);
        return newDate;
    }
    const salary = job.min_salary == job.max_salary  ? `$${(job.min_salary).toFixed(2)}` : `$${(job.min_salary).toFixed(2)}-${(job.max_salary).toFixed(2)}`

    const delete_job = async () => {
        const form = new FormData();
        form.append("email", localStorage.getItem("email"));
        form.append("jobId", job.jobId);
        const response = await axios.post("http://localhost:8080/users/delete_job", form);
        console.log(response.data.jobs);
        onCallBack3(response.data.jobs);
    }

    return(
        <Fragment>
            <div className="saved_card">
                <div className="center_container">
                    <h2 className="new_job_title">{job.title}</h2>
                    <p className="new_job_company">{job.company}</p>
                    <p className="new_job_description">{job.description.substring(0, 200) + "..."}</p>
                    <a href={job.job_url} target="_blank" className="new_job_link">More at: {job.job_url.substring(0,50)}</a>
                </div>
                <p className="new_job_salary">Avg. Salary: {salary}</p>
                <p className="new_job_date">{date_posted(job.job_posted)}</p>
                <p className="new_job_location">{job.location}</p>
                <button className="btn btn-success" id="delete_job" onClick={delete_job}>Delete Job</button>
            </div>
        </Fragment>
    )
}

export default SavedCard