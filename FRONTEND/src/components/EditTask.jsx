import React, { useRef, useState } from "react";
import { useDispatch } from "react-redux";

import { updateTask } from "../redux/Tasks";

const EditTask = ({ setEditTaskModal, taskToEdit }) => {
  const descriptioneRef = useRef();
  const dispatch = useDispatch();

  const closeHandle = () => {
    setEditTaskModal(false);
  };

  const onSubmit = (e) => {
    e.preventDefault();

    const description = descriptioneRef.current.value;

    if (description === "") return;

    dispatch(
      updateTask({
        id: taskToEdit.id,
        date: taskToEdit.date,
        done: taskToEdit.done,
        description,
      })
    );

    setEditTaskModal(false);
  };

  return (
    <div className="w-full h-screen bg-black/30 absolute flex justify-center items-center">
      <div className="w-[400px] bg-white rounded">
        <div className="flex flex-col space-y-4 p-4">
          <div className="flex justify-between">
            <h1 className="text-xl font-semibold">Edit Task</h1>
            <button onClick={() => closeHandle()}>
              X
            </button>
          </div>

          <form onSubmit={onSubmit} className="">

            <input
              type="text"
              placeholder={taskToEdit.description}
              className="w-full outline-0 p-2 bg-gray-100 my-2"
              ref={descriptioneRef}
            />
            <button
              type="submit"
              className="w-28 p-2 rounded-md flex justify-center items-center space-x-1 bg-yellow-400"
            >
              <span>Edit</span>
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default EditTask;
