import TinderCard from 'react-tinder-card'

function Card(){
    return(
        <div class="job_card">
            <div class="center_container">
                <h2 class="job_title">Job Title</h2>
                <p class="job_company">Company Name</p>
                <p class="job_description">Job Description goes here...</p>
                <a href="#" class="job_link">Apply Now</a>
            </div>
            <p class="job_salary">$80,000-100,000</p>
            <p class="job_date">Posted: Dec 30, 2024</p>
            <p class="job_location">Remote</p>
        </div>
    )
}

export default Card