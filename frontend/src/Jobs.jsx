import { useState, useEffect, useRef } from 'react';
import Card from './Card'

function Jobs(data){
    console.log(data.data);
    return (
        <div id="card_list">
            {data.data.map((job) => (
                <Card job={job}></Card>
            ))}
        </div>
    )
}

export default Jobs