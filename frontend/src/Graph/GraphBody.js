import React, { Component } from "react";
import { Line } from "react-chartjs-2";
import "./GraphBody.scss";

export default class GraphBody extends Component {
  render() {
    const { graphData, message, showErrorMessage } = this.props;

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
      },
      tooltips: {
        mode: "nearest",
        intersect: false
      }
    };

    if (showErrorMessage) {
      return <div className="alert alert-danger">{message}</div>;
    } else {
      return (
        <div className="Linechart">
          <Line data={graphData} options={options} redraw={true} />
        </div>
      );
    }
  }
}
