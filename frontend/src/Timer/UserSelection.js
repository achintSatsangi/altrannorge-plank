import React, { Component } from "react";
import Axios from "axios";
import "./UserSelection.scss";

export default class UserSelection extends Component {
  constructor(props) {
    super(props);

    this.state = {
      users: []
    };
  }

  componentDidMount() {
    Axios.get("users")
      .then(response => {
        const users = Array.from(response.data);
        users.unshift("");
        this.setState({ users: users });
      })
      .catch(error => {
        console.log(error);
      });
  }

  render() {
    const options = this.state.users.map((user, index) => (
      <option key={index} value={user} disabled={!user}>
        {user || "SELECT USER"}
      </option>
    ));
    return (
      <select
        onChange={event => this.props.handleChange(event)}
        value={this.props.selectedUser}
        className="user-selection form-control"
      >
        {options}
      </select>
    );
  }
}
