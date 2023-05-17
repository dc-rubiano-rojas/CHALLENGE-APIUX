import { useState, useEffect, useRef } from "react";

//add task modal
import AddTask from "./AddTask";
import EditTask from "./EditTask";

//redux
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { fetchTasks, deleteTask, updateTask } from "../redux/Tasks";

function Home() {
  const [addTaskModal, setAddTaskModal] = useState(false);
  const [addEditModal, setEditTaskModal] = useState(false);
  const [checked, setChecked] = useState(false);
  const [taskToEdit, setTaskToEdit] = useState({});

  const tasks = useSelector((state) => state.tasks.tasks);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchTasks());
  }, []);

  const removeHandle = (id) => {
    dispatch(deleteTask(id));
  };

  const editHandle = (task) => {
    setTaskToEdit(task);
    setEditTaskModal(true);
  };

  const checkHandle = (value, task) => {
    dispatch(updateTask({
      ...task,
      done: value
    }));
  };

  return (
    <div className="App w-full h-screen flex justify-center items-center">
      <div className="w-4/5 md:w-1/2 lg:w-4/12 2xl:w-1/5 m-auto h-1/2 overflow-auto bg-white rounded-md drop-shadow-xl border relative">
        <div className="w-full sticky top-0 bg-[#161616] text-gray-200 p-4 flex justify-between items-center">
          <div>
            <h1 className="text-xl font-semibold">To Do List App</h1>
            <p className="text-xs">manage your tasks</p>
          </div>
          <div className="w-8 h-8 bg-white text-black rounded-full flex justify-center items-center">
            <button onClick={() => setAddTaskModal(true)}>
              +
           
            </button>
          </div>
        </div>

        <div className="p-5 text-black">
          <ul className="space-y-4">
            {tasks &&
              tasks.map((task, idx) => {
                const check = checked === (task.done === 1 ? true : false);
                return (
                  <div key={idx} className="bg-gray-100 flex justify-between">
                    <li className="flex space-x-4 p-4">
                      <input
                        type="checkbox"
                        name=""
                        id="complete"
                        className="peer/complete"
                        checked={!check}
                        onChange={(e) => checkHandle(e.target.checked, task)}
                      />
                      <label className="peer-checked/complete:line-through">
                        {task.description}
                      </label>
                    </li>
                    <button
                      onClick={() => {
                        editHandle(task);
                      }}
                      className="ml-12"
                    >
                      EDIT
                    </button>

                    <button
                      onClick={() => {
                        removeHandle(task.id);
                      }}
                      className="mr-3"
                    >
                    </button>
                  </div>
                );
              })}
          </ul>
        </div>
      </div>
      {addTaskModal && <AddTask setAddTaskModal={setAddTaskModal} />}
      {addEditModal && <EditTask setEditTaskModal={setEditTaskModal} taskToEdit={taskToEdit} />}
    </div>
  );
}

export default Home;
