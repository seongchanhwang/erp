import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { loginAuthAction } from "../../redux/actions/loginActions";

const LoginForm = () => {
  const [id, setId] = useState("");
  const [pw, setPw] = useState("");
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = {
      loginId: id,
      password: pw,
    };
    dispatch(loginAuthAction.getLoginAuth(data)).then(() => {
      navigate("/");
    });
  };
  return (
    <>
      <div className="txt-box">
        <h2>Eventually</h2>
        <p>
          A simple template for telling the world when you'll launch your next
          big thing. Brought to you by HTML5 UP.
        </p>
      </div>
      <form onSubmit={handleSubmit}>
        <div className="input-box">
          <div>
            <input
              name="loginId "
              type="text"
              placeholder="이메일을 입력해주세요"
              value={id}
              onChange={(e) => setId(e.target.value)}
            ></input>
          </div>
          <div>
            <input
              name="Password"
              type="password"
              placeholder="비밀번호를 입력해주세요"
              value={pw}
              onChange={(e) => setPw(e.target.value)}
            ></input>
          </div>
          <button className="btn-submit">Login</button>
        </div>
      </form>
    </>
  );
};

export default LoginForm;
