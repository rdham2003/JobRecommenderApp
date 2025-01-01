import{useState, useEffect} from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import Navbar from './navbar'
import HomePage from './homepage'
import Card from './Card'
import Jobs from './Jobs'


function App() {
  const [params, setParams] = useState({ jobType: "", country: "", location: "", distance: "" });

  const handleCallBack = (newParams) => {
    setParams(newParams); 
    console.log(params);
  };

  return (
    <div>
      <Navbar/>
      <HomePage onCallBack={handleCallBack} />
      <Jobs params={params} />
    </div>
  )
}

export default App
