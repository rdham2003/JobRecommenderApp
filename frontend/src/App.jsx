import{ useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import Navbar from './navbar'
import NavbarLoggedIn from './NavbarLoggedIn'
import HomePage from './homepage'
import Jobs from './Jobs'
import axios from 'axios'
import SignUp from './SignUp'
import LogIn from './LogIn'
import Wishlist from './Wishlist'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';


function App() {
  const [username, setUsername] = useState(null)
  const [params, setParams] = useState({ jobType: "", country: "", location: "", distance: "" });
  const [data, setData] = useState(null);
  const [isLogged, setIsLogged] = useState(false)

  const isLoggedIn = async () => {
    const response = await axios.get("http://localhost:8080");
    setIsLogged(response.data);
  }

  const handleCallBack = (newParams) => {
    setParams(newParams); 
    // console.log(params);
  };

  const handleData = (newData) => {
    setData(newData);
  }

  const handleLogIn = (newUser) => {
    setUsername(newUser);
  }

  useEffect(() => {
    console.log(username);
  },[username])

  // useEffect(() => {
  //   console.log(data);
  // }, [data]);

  useEffect(() => {
    const interval = setInterval(() => {
      isLoggedIn();
    }, 10)
    return () => clearInterval(interval);
  }, [])

  useEffect(() => {
    console.log(isLogged);
  }, [isLogged])
  

  return (
    <Router>
      <div>
        {isLogged ? <NavbarLoggedIn/> : <Navbar/>}
        <Routes>
          <Route path="/" element={<HomePage onCallBack={handleCallBack} onDataChange={handleData} />} />
          <Route path="/signup" element={<SignUp onCallBack={handleLogIn}/>}/>
          <Route path="/login" element={<LogIn onCallBack={handleLogIn}/>}/>
          <Route path="/user/wishlist" element={<Wishlist/>}/>
          <Route path="/query/jobs" element={<Jobs data={data} isLogged = {isLogged} username={username}/>} />
        </Routes>
      </div>
    </Router>
  )
}

export default App
