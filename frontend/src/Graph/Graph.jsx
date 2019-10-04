import React, { Component } from "react";
import { Line } from "react-chartjs-2";
import Axios from "axios";

import "./Graph.css";

export default class Graph extends Component {
  constructor(props) {
    super(props);
    this.state = {
      graphData: {},
      message: "",
      hideMessage: true
    };
  }

  async componentDidMount() {
    await Axios.get("/plank/getAllDataForGraph")
      .then(response =>
        this.setState({
          graphData: response.data,
          message: "",
          hideMessage: true
        })
      )
      .catch(error => {
        this.setState({
          graphData: {},
          message: "Oops!!! Something went wrong",
          hideMessage: false
        });
        console.log(error);
      });
  }

  render() {
    const options = {
      responsive: true,
      maintainAspectRatio: false,
      responsiveAnimationDuration: 500,
      aspectRatio: 1,
      scales: {
        xAxes: [
          {
            display: true,
            ticks: {
              beginAtZero: true,
              fontColor: "white"
            }
          }
        ],
        yAxes: [
          {
            display: false,
            ticks: {
              beginAtZero: false,
              fontColor: "white"
            }
          }
        ],
        legend: [
          {
            position: "bottom"
          }
        ]
      }
    };
    return (
      <div className="Linechart">
        <div>Plank Progress</div>
        <div className="alert alert-danger" hidden={this.state.hideMessage}>
          {this.state.message}
        </div>
        <Line data={this.state.graphData} options={options} redraw={true} />
      </div>
    );
  }
}
