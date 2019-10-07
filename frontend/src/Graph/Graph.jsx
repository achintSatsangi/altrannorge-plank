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
    await this.getDataAndPlotGraph("/plank/getAllDataForGraph");
  }

  async getDataAndPlotGraph(url) {
    await Axios.get(url)
      .then(res => this.success(res))
      .catch(err => this.error(err));
  }

  success(response) {
    this.setState({
      graphData: response.data,
      message: "",
      hideMessage: true
    });
  }

  error(error) {
    this.setState({
      graphData: {},
      message: "Oops!!! Something went wrong",
      hideMessage: false
    });
    console.log(error);
  }

  getData(duration) {
    console.log("duration : " + duration);
    var url = "/plank/getAllDataForGraph";
    if (duration === "MONTH") {
      url = "/plank/getDataForGraph/30";
    } else if (duration === "WEEK") {
      url = "/plank/getDataForGraph/7";
    }
    this.getDataAndPlotGraph(url);
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
        <nav className="navbar fixed-top navbar-dark bg-dark">
          <button
            id="allData"
            name="allData"
            className="btn btn-primary"
            onClick={() => this.getData("ALL")}
          >
            All
          </button>
          <button
            id="lastMonth"
            value="Month"
            className="btn btn-primary"
            onClick={() => this.getData("MONTH")}
          >
            Month
          </button>
          <button
            id="lastWeek"
            value="Week"
            className="btn btn-primary"
            onClick={() => this.getData("WEEK")}
          >
            Week
          </button>
        </nav>
        <div>Plank Progress</div>
        <div className="alert alert-danger" hidden={this.state.hideMessage}>
          {this.state.message}
        </div>
        <Line data={this.state.graphData} options={options} redraw={true} />
      </div>
    );
  }
}
