function Navbar(){
    return(
        <header>
            <nav className="navbar">
                <h3 className="logo"><a href="/">SkillTap</a></h3>
                <ul className="nav_items">
                    <li><a href="/">Home</a></li>
                    <li><a href="/signup">Sign Up</a></li>
                    <li><a href="/login">Login</a></li>
                    <li><a href="https://tech-rdham.pro" target="_blank">Visit My Website</a></li>
                </ul>
            </nav>
        </header>
    )
}

export default Navbar