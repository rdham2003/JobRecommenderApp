import TinderCard from 'react-tinder-card'

function Card(job){
    // console.log(job);
    function date_posted(date){
        let newDate = "";
        // console.log(date.substring(5,7));
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
        <div className="job_card">
            <div className="center_container">
                <h2 className="job_title">{job.job.title}</h2>
                <p className="job_company">{job.job.company}</p>
                <p className="job_description">{job.job.description.substring(0, 200) + "..."}</p>
                <a href={job.job.job_url} target="_blank" className="job_link">More at: {job.job.job_url.substring(0,50)}</a>
            </div>
            <p className="job_salary">Avg. Salary: {salary}</p>
            <p className="job_date">{date_posted(job.job.job_posted)}</p>
            <p className="job_location">{job.job.location}</p>
        </div>
    )
}

export default Card