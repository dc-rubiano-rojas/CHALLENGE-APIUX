import { configureStore } from '@reduxjs/toolkit'

import TasksReducer from './Tasks'
import TaskReducer from './Task'

const store = configureStore({
    reducer: {
        tasks: TasksReducer,
        // editTask: TaskReducer
    },
})

export default store