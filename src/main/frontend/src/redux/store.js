import React from "react";
import { configureStore } from "@reduxjs/toolkit";
import loginAuthReducer from "./reducers/loginAuthReducer";

const store = configureStore({
  reducer: {
    auth: loginAuthReducer,
  },
});

export default store;
