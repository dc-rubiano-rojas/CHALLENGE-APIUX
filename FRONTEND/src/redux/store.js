import { configureStore } from '@reduxjs/toolkit'

import TasksReducer from './Tasks'

const store = configureStore({
    reducer: {
        tasks: TasksReducer,
    },
})

export default store