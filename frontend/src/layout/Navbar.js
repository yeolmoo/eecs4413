import React from 'react'

export default function Navbar() {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                    <a className="navbar-brand" to="/">EVShop</a>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav ms-auto">
                            <li className="nav-item">
                                <a className="nav-link" href="/Login">Login</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" to="/register">Register</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    )
}
