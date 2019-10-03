import React, { Component } from "react";
import { Line } from "react-chartjs-2";
import "./Graph.css";

export default class Graph extends Component {
  render() {
    var data = {
      labels: [
        "20-Sep-2019",
        "21-Sep-2019",
        "22-Sep-2019",
        "23-Sep-2019",
        "24-Sep-2019",
        "25-Sep-2019",
        "26-Sep-2019",
        "27-Sep-2019",
        "28-Sep-2019",
        "29-Sep-2019",
        "30-Sep-2019",
        "01-Oct-2019"
      ],
      datasets: [
        {
          label: "RUBEN",
          fill: false,
          lineTension: 0.1,
          borderColor: "#9CF196",
          pointHoverBackgroundColor: "#9CF196",
          pointBorderWidth: 1,
          // notice the gap in the data and the spanGaps: true
          data: [45, 52, 66, 12, 56, 89, 90, null, 425, 200, 100, 90],
          spanGaps: false
        },
        {
          label: "ACHINT",
          fill: false,
          lineTension: 0.1,
          borderColor: "#ECEBA7",
          pointHoverBackgroundColor: "#ECEBA7",
          pointBorderWidth: 1,
          // notice the gap in the data and the spanGaps: true
          data: [65, 59, 80, 81, 56, 55, 40, null, 60, 55, 30, 78],
          spanGaps: false
        },
        {
          label: "MELISSA",
          fill: false,
          lineTension: 0.1,
          borderColor: "#EDAAAA",
          pointHoverBackgroundColor: "#EDAAAA",
          pointBorderWidth: 1,
          // notice the gap in the data and the spanGaps: true
          data: [50, 80, 70, 56, 34, null, 23, 34, 98, 120, 90, 40],
          spanGaps: false
        },
        {
          label: "SAINYAM",
          fill: false,
          lineTension: 0.1,
          borderColor: "#b1e8ed",
          pointHoverBackgroundColor: "#b1e8ed",
          pointBorderWidth: 1,
          // notice the gap in the data and the spanGaps: true
          data: [40, 90, 30, 500, 500, null, null, , 42, 200, 75, 80],
          spanGaps: false
        },
        {
          label: "PK",
          fill: false,
          lineTension: 0.1,
          borderColor: "#01D28E",
          pointHoverBackgroundColor: "#01D28E",
          pointBorderWidth: 1,
          // notice the gap in the data and the spanGaps: true
          data: [20, 80, 40, 116, 54, , , 84, 80, 100, 10, 80],
          spanGaps: false
        },
        {
          label: "CAMILLA",
          fill: false,
          lineTension: 0.1,
          borderColor: "#FE59D7",
          pointHoverBackgroundColor: "#FE59D7",
          pointBorderWidth: 1,
          // notice the gap in the data and the spanGaps: true
          data: [90, 40, 20, 216, 74, , , 12, 90, 50, 100, 300],
          spanGaps: false
        },
        {
          label: "HENRIK",
          fill: false,
          lineTension: 0.1,
          borderColor: "#eccd8f",
          pointHoverBackgroundColor: "#eccd8f",
          pointBorderWidth: 1,
          // notice the gap in the data and the spanGaps: true
          data: [57, 27, 165, 262, 154, , , 184, 50, 160, 18, 450],
          spanGaps: false
        },
        {
          label: "OLE",
          fill: false,
          lineTension: 0.1,
          borderColor: "#DA9833",
          pointHoverBackgroundColor: "#DA9833",
          pointBorderWidth: 1,
          // notice the gap in the data and the spanGaps: true
          data: [12, 67, 65, 162, 354, , 34, 84, 30, 123, 117, 90],
          spanGaps: false
        }
      ]
    };
    const options = {
      responsive: true,
      maintainAspectRatio: false,
      responsiveAnimationDuration: 1000,
      aspectRatio: 1,
      scales: {
        xAxes: [
          {
            display: false,
            ticks: {
              beginAtZero: true,
              fontColor: "white"
            }
          }
        ],
        yAxes: [
          {
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
        <div>Plank Progress (Mock data)</div>
        <Line data={data} options={options} />
      </div>
    );
  }
}
