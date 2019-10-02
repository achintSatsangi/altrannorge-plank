import React from "react";
import ReactDOM from "react-dom";
import Graph from "./Graph";
import "jest-canvas-mock";

it("Renders correct default label", () => {
  const div = document.createElement("div");
  ReactDOM.render(<Graph />, div);
  expect(div.innerHTML).toContain("Plank Progress");
});
