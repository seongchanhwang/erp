import { createSlice } from "@reduxjs/toolkit";

let initialState = {
  auth: false,
};

const loginAuthSlice = createSlice({
  initialState,
  name: "loginAuth",
  reducers: {
    getLoginAuth(state, action) {
      state.auth = action.payload.auth;
    },
  },
});

export const loginAuthActions = loginAuthSlice.actions;
export default loginAuthSlice.reducer;
