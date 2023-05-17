import { expect } from "vitest";
import { render, screen, fireEvent } from "@testing-library/react";

import { Provider } from "react-redux";
import store from "../redux/store";

import Home from "./Home";

describe("Home", () => {

  test("should work as expected", () => {
    render(
      <Provider store={store}>
        <Home />
      </Provider>
    );
    const appTitle = screen.getByText("To Do List App");
    expect(appTitle).toBeDefined();
  });

  test("click show modal to add task", () => {
    render(
      <Provider store={store}>
        <Home />
      </Provider>
    );

    const button = screen.getByRole("button", { name: "+" });
    fireEvent.click(button);

    expect(screen.getByText(/Add New Task/i)).toBeDefined();
  });

  test("click hide modal ", () => {
    const component = render(
      <Provider store={store}>
        <Home />
      </Provider>
    );
    const button = screen.getByRole("button", { name: "+" });
    fireEvent.click(button);

    const buttonHide = screen.getByText("X");
    fireEvent.click(buttonHide);

    expect(buttonHide).toBeDefined();
    expect(screen.getByText(/manage your tasks/i)).toBeDefined();
  });

  test("click show edit modal ", () => {
    const component = render(
      <Provider store={store}>
        <Home />
      </Provider>
    );
  });
});
