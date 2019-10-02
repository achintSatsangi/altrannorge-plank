import React from "react";
import ReactDOM from "react-dom";
import Graph from "./Graph";

it("Renders correct default label", () => {
  const div = document.createElement("div");
  ReactDOM.render(<Graph />, div);
  expect(div.innerHTML).toBe("<div>Our awesome graph goes here :)</div>");
});
