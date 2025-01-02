import{ useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import Navbar from './navbar'
import HomePage from './homepage'
import Card from './Card'
import Jobs from './Jobs'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';


function App() {
  const [params, setParams] = useState({ jobType: "", country: "", location: "", distance: "" });
  const [data, setData] = useState(null);

  const handleCallBack = (newParams) => {
    setParams(newParams); 
    // console.log(params);
  };

  const handleData = (newData) => {
    setData(newData);
  }

  useEffect(() => {
    console.log(data);
  }, [data]);

  return (
    <Router>
      <div>
        <Navbar/>
        <Routes>
          <Route path="/" element={<HomePage onCallBack={handleCallBack} onDataChange={handleData} />} />
          <Route path="/query/jobs" element={<Jobs data={data} />} />
        </Routes>
      </div>
    </Router>
  )
}

export default App
