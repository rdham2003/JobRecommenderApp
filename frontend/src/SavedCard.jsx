import { Fragment } from "react";

function SavedCard(job){
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

    const salary = job.job.min_salary == job.job.max_salary ? `$${(job.job.min_salary).toFixed(2)}` : `$${(job.job.min_salary).toFixed(2)}-${(job.job.max_salary).toFixed(2)}`

    return(
        <Fragment>
            <div className="saved_card">
                <div className="center_container">
                    <h2 className="new_job_title">{job.job.title}</h2>
                    <p className="new_job_company">{job.job.company}</p>
                    <p className="new_job_description">{job.job.description.substring(0, 200) + "..."}</p>
                    <a href={job.job.job_url} target="_blank" className="new_job_link">More at: {job.job.job_url.substring(0,50)}</a>
                </div>
                <p className="new_job_salary">Avg. Salary: {salary}</p>
                <p className="new_job_date">{date_posted(job.job.job_posted)}</p>
                <p className="new_job_location">{job.job.location}</p>
                <button className="btn btn-success" id="delete_job">Delete Job</button>
            </div>
        </Fragment>
    )
}

export default SavedCard