import React, { Component } from 'react'
import { Link } from "@reach/router";
import "./NavigationMenu.css";

export default class NavigationMenu extends Component {
    render() {
        return (
            <nav className="navbar fixed-bottom navbar-dark bg-dark">
                <Link to="/" className="navbar-brand flex-fill">
                    <i className="material-icons icon-l">timer</i>
                </Link>
                <Link to="graph" className="navbar-brand flex-fill">
                    <i className="material-icons icon-l">emoji_events</i>
                </Link>
            </nav>
        );
    }
}