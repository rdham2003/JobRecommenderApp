import{useState, useEffect} from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import HomePage from './homepage'
import Card from './Card'
import axios from 'axios'

function App() {

  const url = "http://localhost:8080/jobs/api";
  const [params, setParams] = useState({jobType: "", country: "", location: "", distance: ""})
  
  const updateParam = (K, V) => {
    setParams((prev) => ({
      ...prev,
      [K]: V,
    }));
  }

  console.log(params);

  document.getElementById("submit_button").onclick = function() {
    updateParam(jobType, document.getElementById("jobType").value);
    updateParam(country, document.getElementById("country").value);
    updateParam(location, document.getElementById("location").value);
    updateParam(distance, document.getElementById("distance").value);
  }

  return (
    // <Card></Card>
    <HomePage></HomePage>
  )
}

export default App
