import { createSlice } from "@reduxjs/toolkit";

export const TaskReducer = createSlice({
    name: 'Task',
    initialState: [],
    reducers: {
        Add: (state, action) => {
            const { title, description } = action.payload
            state.push({title, description })
            // state.push(action.payload)
        },
        Remove: (state, action) => {
            return state.filter((addTask, idx) => idx !== action.payload)
        },
    }
})

export const { Add, Remove } = TaskReducer.actions

export default TaskReducer.reducer