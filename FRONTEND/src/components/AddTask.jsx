import React, { useRef, useState } from "react";

//redux
import { useDispatch } from "react-redux";
import { Add, addTask, fetchTasks } from "../redux/Tasks";

const AddTask = ({ setAddTaskModal }) => {

  const descriptioneRef = useRef();
  const doneRef = useRef();

  const dispatch = useDispatch();

  const closeHandle = () => {
    setAddTaskModal(false);
  };

  const onSubmit = (e) => {
    e.preventDefault();

    const description = descriptioneRef.current.value;
    let done = doneRef.current.checked;
    const date = new Date().getDate();

    done = done? 1: 0
    if (description === "") return;
    dispatch(addTask({
      description,
      date,
      done
    }));

    setAddTaskModal(false);
  };

  return (
    <div className="w-full h-screen bg-black/30 absolute flex justify-center items-center">
      <div className="w-[400px] bg-white rounded">
        <div className="flex flex-col space-y-4 p-4">
          <div className="flex justify-between">
            <h1 className="text-xl font-semibold">Add New Task</h1>
            <button onClick={() => closeHandle()}>
              X
            </button>
          </div>

          <form onSubmit={onSubmit} className="">

            <input
              type="checkbox"
              name=""
              id="complete"
              className="peer/complete"
              ref={doneRef}
            />
            <label className="peer-checked/complete:line-through ml-2">
              DONE
            </label>

            <input
              type="text"
              placeholder="Description"
              className="w-full outline-0 p-2 bg-gray-100 my-2"
              ref={descriptioneRef}
            />
            <button
              type="submit"
              className="w-28 p-2 rounded-md flex justify-center items-center space-x-1 bg-yellow-400"
            >
              <span>Add</span>
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddTask;
