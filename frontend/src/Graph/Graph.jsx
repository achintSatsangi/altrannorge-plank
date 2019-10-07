import React, { Component } from "react";
import { Line } from "react-chartjs-2";
import Axios from "axios";

import "./Graph.css";

const GraphBody = props => {
  const {graphData, message, showErrorMessage, options} = props;
  
  if (showErrorMessage) {
    return( <div className="alert alert-danger">{message}</div> );
  } else {
    return( <Line data={graphData} options={options} redraw={true}/> );
  }
};

export default class Graph extends Component {
  constructor(props) {
    super(props);
    this.state = {
      graphData: {},
      message: "",
      showErrorMessage: false
    };
  }

  async componentDidMount() {
    await Axios.get("/plank/getAllDataForGraph")
      .then(response =>
        this.setState({
          graphData: response.data,
          message: "",
          showErrorMessage: false
        })
      )
      .catch(error => {
        this.setState({
          graphData: {},
          message: "Oops!!! Something went wrong",
          showErrorMessage: true
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
              fontColor: "black"
            }
          }
        ],
        yAxes: [
          {
            display: false,
            ticks: {
              beginAtZero: false,
              fontColor: "black"
            }
          }
        ]
      },
      legend: {
        labels: {
          usePointStyle: true,
          boxWidth: 10
        }
      }
    };
    return (
      <div className="Linechart">
        <div>Plank Progress</div>
        <GraphBody {...this.state} options={options}/>
      </div>
    );
  }
}
