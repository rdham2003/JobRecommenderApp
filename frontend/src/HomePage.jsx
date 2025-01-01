import{useState, useEffect, useRef} from 'react'
import Navbar from './navbar'

function HomePage(){
    let [params, setParams] = useState({jobType: "", country: "", location: "", distance: ""})
    const jobTypeRef = useRef(null);
    const locationRef = useRef(null);
    const countryRef = useRef(null);
    const distanceRef = useRef(null);
    
    const updateParam = (K, V) => {
        setParams((prev) => ({
        ...prev,
        [K]: V,
        }));
    }

    const inputChange = (e) => {
        updateParam(e.target.id, e.target.value);
    //     switch(e.target.id){
    //         case "jobType":
    //             console.log(jobTypeRef.current?.value);
    //             console.log(params.jobType);
    //         case "location":
    //             console.log(locationRef.current?.value);
    //             console.log(params.location);
    //         case "country":
    //             console.log(countryRef.current?.value);
    //             console.log(params.country);
    //         case "distance":
    //             console.log(distanceRef.current?.value);
    //             console.log(params.distance);
    // }
    };

    function printProps(){
        console.log(params)
    }
    
    return(
        <form action="http://localhost:8080/jobs/api" method="POST" encType='multipart/form-data'>
            <Navbar/>
            <br />
            <h1 id="title">Find your dream job even faster!</h1>
            <br />
            <div className="form_container">
                <label>What type of Job are you looking for?</label>
                <select name="jobType" id="jobType" ref={jobTypeRef} value={params.jobType} onChange={inputChange} required>
                    <option value=""></option>
                    <option value="job">Job</option>
                    <option value="internship">Internship/Co-op</option>
                </select>
                <br />
                <br />
                <h4>Location</h4>
                <label htmlFor="">Zip Code</label>
                <input type="text" name="location" id="location" ref={locationRef} value={params.location} onChange={inputChange} placeholder='e.g: 90210' required/>
                <label htmlFor="">Country</label>
                <select name="country" id="country" ref={countryRef} value={params.country} onChange={inputChange} required>
                    <option value=""></option>
                    <option value="us">us</option>
                    <option value="gb">gb</option>
                    <option value="at">at</option>
                    <option value="au">au</option>
                    <option value="be">be</option>
                    <option value="br">br</option>
                    <option value="ca">ca</option>
                    <option value="ch">ch</option>
                    <option value="de">de</option>
                    <option value="es">es</option>
                    <option value="fr">fr</option>
                    <option value="in">in</option>
                    <option value="it">it</option>
                    <option value="mx">mx</option>
                    <option value="nl">nl</option>
                    <option value="nz">nz</option>
                    <option value="pl">pl</option>
                    <option value="sg">sg</option>      
                    <option value="za">za</option>
                </select>
                <br />
                <label htmlFor="">Range (in km)</label>
                <br />
                <input type="number" name="distance" id="distance" ref={distanceRef} value={params.distance} onChange={inputChange} min="5" max="1000" placeholder='e.g. 200'/>
                <br />
                <br />
                <label>Upload Your Resume</label>
                <br />
                <input className="btn btn-primary" type="file" name="pdf" accept=".pdf" required/>
                <br />
                <div id="submit_container">
                    <button id="submit_button" onClick={printProps} className="btn btn-success" type='submit'>Find me Jobs</button>
                </div>
            </div>
        </form>
    )
}

export default HomePage