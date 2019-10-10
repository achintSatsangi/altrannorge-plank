import React, { Component } from "react";
import "chartjs-plugin-colorschemes";
import Axios from "axios";

import "./Graph.scss";
import GraphBody from "./GraphBody";


export default class Graph extends Component {
  constructor(props) {
    super(props);
    this.state = {
      graphData: {},
      message: "",
      showErrorMessage: false,
      filter: "ALL"
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
      showErrorMessage: false
    });
  }

  error(error) {
    this.setState({
      graphData: {},
      message: "Oops!!! Something went wrong",
      showErrorMessage: true
    });
    console.log(error);
  }

  getData(duration) {
    var url = "/plank/getAllDataForGraph";
    this.setState({ filter: "ALL" });
    if (duration === "MONTH") {
      url = "/plank/getDataForGraph/30";
      this.setState({ filter: "MONTH" });
    } else if (duration === "WEEK") {
      url = "/plank/getDataForGraph/7";
      this.setState({ filter: "WEEK" });
    }
    this.getDataAndPlotGraph(url);
  }

  render() {
    return (
      <>
        <div className="graph-filter">
          <button
            type="button"
            id="allData"
            name="allData"
            className={ `btn btn-graph-filter ${this.state.filter === "ALL" ? 'btn-active' : ''}` }
            onClick={() => this.getData("ALL")}
          >
            All
          </button>
          <button
            type="button"
            id="lastMonth"
            value="Month"
            className={ `btn btn-graph-filter ${this.state.filter === "MONTH" ? 'btn-active' : ''}` }
            onClick={() => this.getData("MONTH")}
          >
            Month
          </button>
          <button
            type="button"
            id="lastWeek"
            value="Week"
            className={ `btn btn-graph-filter ${this.state.filter === "WEEK" ? 'btn-active' : ''}` }
            onClick={() => this.getData("WEEK")}
          >
            Week
          </button>
        </div>

        <GraphBody {...this.state} />
      </>
    );
  }
}
