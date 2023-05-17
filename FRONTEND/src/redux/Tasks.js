import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
    loading: false,
    tasks: [],
    error: ''
}

export const TasksReducer = createSlice({
    name: 'Tasks',
    initialState,
    reducers: {
        Add: (state, action) => {
            const { date, description, done, id } = action.payload
            state.tasks.push({ date, description })
            // state.push(action.payload)
        },
        Remove: (state, action) => {
            return state.tasks.filter((task, idx) => task.id !== action.payload)
        },
        Edit: (state, action) => {
            const { id, date, description } = action.payload;
            const foundTask = state.find((task) => task.id === id);
            if (foundTask) {
                foundTask.date = date;
                foundTask.description = description;
            }
        },
    },
    extraReducers: (builder) => {
        builder.addCase(fetchTasks.pending, (state) => {
            state.loading = true
        }),
            builder.addCase(fetchTasks.fulfilled, (state, action) => {
                // console.log('action.payload: ', action.payload);
                state.loading = false
                state.tasks = action.payload
                state.error = ''
            }),
            builder.addCase(fetchTasks.rejected, (state, action) => {
                state.loading = false
                state.tasks = []
                state.error = action.error.message
            }),

            builder.addCase(deleteTask.pending, (state) => {
                state.loading = true
            }),
            builder.addCase(deleteTask.fulfilled, (state, { payload: { data: { data: id } } }) => {
                state.loading = false
                state.tasks = state.tasks.filter((task) => task.id != id)
                state.error = ''
            }),
            builder.addCase(deleteTask.rejected, (state, action) => {
                state.loading = false
                state.tasks = [...state.tasks]
                state.error = action.error.message
            }),

            builder.addCase(addTask.pending, (state) => {
                state.loading = true
            }),
            builder.addCase(addTask.fulfilled, (state, action) => {
                const { description, done, date, id } = action.payload.data.data
                state.loading = false
                state.tasks = [{ id, description, done, date }, ...state.tasks]
                state.error = ''
            }),
            builder.addCase(addTask.rejected, (state, action) => {
                state.loading = false
                state.tasks = [...state.tasks]
                state.error = action.error.message
            }),

            builder.addCase(updateTask.pending, (state) => {
                state.loading = true
            }),
            builder.addCase(updateTask.fulfilled, (state, action) => {
                const { description, done, date, id } = action.payload.data.data
                state.loading = false
                state.tasks = state.tasks.map((task) => {
                    if (task.id === id) {
                        return {
                            ...task,
                            done,
                            description
                        }
                    }
                    return task
                })
                state.error = ''
            }),
            builder.addCase(updateTask.rejected, (state, action) => {
                state.loading = false
                state.tasks = [...state.tasks]
                state.error = action.error.message
            })
    }
})

export const { Add, Remove, Edit, AddTasksList } = TasksReducer.actions

export default TasksReducer.reducer

export const fetchTasks = createAsyncThunk('tasks/fetchTasks', () => {
    return axios
        .get("http://localhost:8080/TODO/v1/tasks")
        .then(({ data: { data: data_1 } }) => {
            return data_1.reverse().map(task => {
                return task
            })
        })
})

export const deleteTask = createAsyncThunk('task/deleteTask', (id) => {
    return axios
        .delete(`http://localhost:8080/TODO/v1/delete/${id}`)
        .then((res) => {
            return res
        })
        .catch(error => console.log('error: ', error))
})

export const addTask = createAsyncThunk('task/addTask', ({ description, date, done }) => {
    console.log('addTask: ', { description, done, date });

    return axios
        .post(`http://localhost:8080/TODO/v1/task`, { description, date, done })
        .then((res) => {
            // console.log('res: ', res);

            return res
        })
        .catch(error => console.log('error: ', error))
})

export const updateTask = createAsyncThunk('task/updateTask', ({ id, done, description, date }) => {
    console.log('updateTask: ', { id, description, done, date });
    return axios
        .put(`http://localhost:8080/TODO/v1/update`, { id, description, done: Number(done) })
        .then((res) => {
            // console.log('res: ', res);
            return res
        })
        .catch(error => console.log('error: ', error))
})
