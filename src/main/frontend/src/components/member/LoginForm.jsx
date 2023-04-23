import React, { useEffect } from "react";
import IntroWrap from "./IntroWrap";
import FormWrap from "./FormWrap";
import { useLocation, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { history } from "../../router/history";

const LoginForm = ({ page }) => {
  const { auth } = useSelector((state) => state.auth);
  const navigate = useNavigate();
  const { state } = useLocation();

  useEffect(() => {
    const listenBackEvent = () => {
      // 뒤로가기 할 때 수행할 동작을 적는다
      navigate("/");
    };

    const unlistenHistoryEvent = history.listen(({ action }) => {
      if (action === "POP") {
        listenBackEvent();
      }
    });

    return unlistenHistoryEvent;
  }, []);

  useEffect(() => {
    if (auth) {
      if (state) {
        navigate(state);
      } else {
        navigate("/");
      }
    }
  }, [auth]);
  return (
    <>
      <IntroWrap page={page} />
      <FormWrap page={page} />
    </>
  );
};

export default LoginForm;
