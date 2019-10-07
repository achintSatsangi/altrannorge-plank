import React, { Component } from "react";
import { Link } from "@reach/router";
import "./NavigationMenu.css";

const NavLink = props => (
  <Link
    {...props}
    getProps={({ isCurrent }) => {
      return {
        style: {
          color: isCurrent ? "#786022" : "#d4cfc6",
          borderBottom: isCurrent ? "5px solid #786022" : "inherit",
        }
      };
    }}
  />
);

export default class NavigationMenu extends Component {
  render() {
    return (
      <nav className="navbar navbar-light bg-light">
        <NavLink to="/" className="navbar-brand flex-fill">
          <i className="material-icons icon-l">timer</i>
        </NavLink>
        <NavLink to="graph" className="navbar-brand flex-fill">
          <i className="material-icons icon-l">emoji_events</i>
        </NavLink>
      </nav>
    );
  }
}