import React, { Component } from "react";

const axios = require("axios");

export default class UserSelection extends Component {
    constructor(props) {
      super(props);
  
      this.state = {
        users: []
      };
    }
  
    componentDidMount() {
      axios
        .get("users")
        .then(response => {
          this.setState({ users: response.data });
        })
        .catch(error => {
          console.log(error);
        });
    }
  
    render() {
      const options = this.state.users.map(user => (
        <option key={user}>{user}</option>
      ));
      return <select className="form-control">{options}</select>;
    }
  }