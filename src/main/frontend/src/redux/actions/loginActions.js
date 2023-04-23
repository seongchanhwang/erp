import axios from "axios";
import { loginAuthActions } from "../reducers/loginAuthReducer";

function getLoginAuth(data) {
  return async (dispatch) => {
    try {
      const response = await axios.post("/api/login", JSON.stringify(data), {
        withCredentials: false,
        headers: {
          "Content-Type": "application/json",
        },
      });

      console.log(response);
      if (response.status === 200) {
        dispatch(
          loginAuthActions.getLoginAuth({
            auth: true,
          })
        );
      }
    } catch (error) {
      alert("계정을 확인해주세요");
      console.log("에러");
      console.log(error);
    }
  };
}

export const loginAuthAction = {
  getLoginAuth,
};
