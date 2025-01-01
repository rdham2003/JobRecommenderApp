import TinderCard from 'react-tinder-card'

function Card(){
    return(
        <div className="job_card">
            <div className="center_container">
                <h2 className="job_title">Job Title</h2>
                <p className="job_company">Company Name</p>
                <p className="job_description">Job Description goes here...</p>
                <a href="#" className="job_link">Apply Now</a>
            </div>
            <p className="job_salary">$80,000-100,000</p>
            <p className="job_date">Posted: Dec 30, 2024</p>
            <p className="job_location">Remote</p>
        </div>
    )
}

export default Card