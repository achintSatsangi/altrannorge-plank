import React, { Component } from "react";
import { Link } from "@reach/router";
import "./NavigationMenu.scss";

const NavLink = props => (
  <Link
    {...props}
    getProps={({ isCurrent }) => {
      return {
        style: {
          color: isCurrent ? "#ffffff" : "#eef0f2",
          backgroundColor: isCurrent ? "#3fc1c9" : "inherit"
        }
      };
    }}
  />
);

export default class NavigationMenu extends Component {
  render() {
    return (
      <nav className="navbar">
        <NavLink to="/" className="navbar-brand flex-fill">
          <i className="material-icons icon-l">timer</i>
        </NavLink>
        <NavLink to="graph" className="navbar-brand flex-fill">
          <i className="material-icons icon-l">emoji_events</i>
        </NavLink>
        <NavLink to="teamGraph" className="navbar-brand flex-fill">
          <i className="material-icons icon-l">group</i>
        </NavLink>
      </nav>
    );
  }
}
