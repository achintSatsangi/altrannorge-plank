import React from "react";
import ReactDOM from "react-dom";
import Graph from "./Graph";
import "jest-canvas-mock";
import MockAxios from "axios";

afterEach(() => {
  MockAxios.reset();
});

it("Renders correct default label and call axios get for graph data", async () => {
  const div = document.createElement("div");
  ReactDOM.render(<Graph />, div);
  expect(div.innerHTML).toContain("Plank Progress");
  expect(MockAxios.get).toHaveBeenCalledTimes(1);
});
