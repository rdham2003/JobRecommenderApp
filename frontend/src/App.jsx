import{ useState, useEffect } from 'react'
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
  const [username, setUsername] = useState(localStorage.getItem("username") || null);
  const [email, setEmail] = useState(localStorage.getItem("email") || null);
  const [params, setParams] = useState({ jobType: "", country: "", location: "", distance: "" });
  const [data, setData] = useState(null);
  const [isLogged, setIsLogged] = useState(localStorage.getItem("isLogged") === 'true');

  const handleCallBack = (newParams) => {
    setParams(newParams);
  };

  const handleData = (newData) => {
    setData(newData);
  }

  const handleLogIn = (newUser) => {
    setUsername(newUser);
    localStorage.setItem("username", newUser);
    setIsLogged(true); 
    localStorage.setItem("isLogged", 'true'); 
  }

  const handleLogIn2 = (newUser) => {
    setEmail(newUser);
    localStorage.setItem("email", newUser);
  }

  const logOut = () => {
    localStorage.setItem("username", null);
    localStorage.setItem("email", null);
    localStorage.setItem("isLogged", 'false');
    setIsLogged(false); 
    setUsername(null);  
  }

  return (
    <Router>
      <div>
        {isLogged ? <NavbarLoggedIn logOut={logOut} /> : <Navbar />}
        <Routes>
          <Route path="/" element={<HomePage onCallBack={handleCallBack} onDataChange={handleData} username={username} />} />
          <Route path="/signup" element={<SignUp onCallBack={handleLogIn} onCallBack2={handleLogIn2} />} />
          <Route path="/login" element={<LogIn onCallBack={handleLogIn} onCallBack2={handleLogIn2} />} />
          <Route path="/user/jobs" element={<Wishlist username={username} email={email} />} />
          <Route path="/query/jobs" element={<Jobs data={data} isLogged={isLogged} email={email} />} />
        </Routes>
      </div>
    </Router>
  );
}


export default App
