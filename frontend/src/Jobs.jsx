import {useState, useEffect, useRef} from 'react';
import axios from 'axios';

function Jobs(props){
    const url = "http://localhost:8080/jobs/api";
    const getData = async () => {
        try{
            const response = await axios.get(url);
            console.log(response.data);
        } catch (e) {
            console.log(e);
        }
    }

    getData();

    return (
        <h1>Hello World!</h1>
    )
}

export default Jobs