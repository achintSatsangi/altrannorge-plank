import React from "react";
import ReactDOM from "react-dom";
import Graph from "./Graph";
import "jest-canvas-mock";
import MockAxios from "axios";

afterEach(() => {
  MockAxios.reset();
});

it("Renders filter buttons with All button as active button and call axios get for graph data", async () => {
  const div = document.createElement("div");
  ReactDOM.render(<Graph />, div);
  
  const filterButtons= div.querySelector(".graph-filter");
  expect(filterButtons.innerHTML).toContain("All");
  expect(filterButtons.innerHTML).toContain("Month");
  expect(filterButtons.innerHTML).toContain("Week");

  let activeButton= filterButtons.querySelector(".btn-graph-filter.btn-active");
  expect(activeButton.innerHTML).toContain("All");

  expect(MockAxios.get).toHaveBeenCalledTimes(1);
});
