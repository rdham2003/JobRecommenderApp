import { useState, useEffect } from "react";

function NavbarLoggedIn({logOut}){

    const [username, setUsername] = useState(localStorage.getItem("username") || "");

    useEffect(() => {
        const username = localStorage.getItem("username");
        setUsername(username);
    }, []);

    return(
        <header>
            <nav className="navbar">
                <h3 className="logo"><a href="/">Job Recommender App</a></h3>
                <ul className="nav_items">
                    <li><a href="/">Home</a></li>
                    <li><a href="/user/jobs">Your Saved Jobs</a></li>
                    <li id="welcome_box">Welcome {localStorage.getItem("username")}</li>
                    <li><a href="/" onClick={() => {logOut(); }}>Log Out</a></li>
                    <li><a href="https://tech-rdham.pro" target="_blank">Visit My Website</a></li>
                </ul>
            </nav>
        </header>
    )
}

export default NavbarLoggedIn