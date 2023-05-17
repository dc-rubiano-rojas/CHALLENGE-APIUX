import { expect } from "vitest";
import { render, screen, fireEvent } from "@testing-library/react";
// import configureStore from "redux-mock-store";
import thunk from "redux-thunk";
import { applyMiddleware } from "redux";
import { configureStore } from "@reduxjs/toolkit";

import { prettyDOM } from "@testing-library/dom";
import { Provider } from "react-redux";
import store from "../redux/store";

import Home from "./Home";
import AddTask from "./AddTask";
import { TaskReducer } from "../redux/Tasks";

// // const mockStore = configureStore([]);
// const mockStore = configureStore(TaskReducer, applyMiddleware(thunk));

describe("Home", () => {
  // const mockTasks = [
  //   { id: 1, description: "Task 1", done: 0, date: new Date().getDate() },
  //   { id: 2, description: "Task 2", done: 0, date: new Date().getDate() },
  // ];
  // let store;

  // beforeEach(() => {
  //   store = mockStore({
  //     tasks: {
  //       tasks: [
  //         { id: 1, description: "Task 1", done: 0, date: new Date().getDate() },
  //         { id: 2, description: "Task 2", done: 0, date: new Date().getDate() },
  //       ],
  //     },
  //   });
  // });

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
    // const button = screen.getByText(/EDIT/i);
    // fireEvent.click(button);
    // expect(button).toBeDefined();
  });
});

// const linkElement =;
// console.log("---");
// console.log(
//   "component: ",
//   prettyDOM(component.container.querySelector("li"))
// );
// console.log("---");

// component.container.querySelector('li')

// const el = component.getByText('To Do List App')
// expect(el).toBeDefined()
// expect(screen.queryByText("content")).toBeDefined();
// expect(prettyDOM(component.container.querySelector('h1'))).toThrow('To Do List App');
// expect(component.container).toHaveTextContent('To Do List App')
