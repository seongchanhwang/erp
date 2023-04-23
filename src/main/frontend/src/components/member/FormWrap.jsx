import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { Link, useNavigate, useParams } from "react-router-dom";
import { loginAuthAction } from "../../redux/actions/loginActions";

const FormWrap = () => {
  const { page } = useParams();
  const [id, setId] = useState("");
  const [pw, setPw] = useState("");
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const handleSubmit = (e) => {
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
      <article className="form-wrap">
        <div>
          <div className="txt-box">
            {page === "login" && (
              <>
                <h2>Welcome back!!</h2>
                <p>Start your project</p>
              </>
            )}
            {page === "join" && (
              <>
                <h2>Hello, Friend!</h2>
                <p>do you have our account?</p>
              </>
            )}
          </div>
          <div className="input-box">
            <form onSubmit={handleSubmit}>
              {page === "login" && (
                <ul>
                  <li>
                    <input
                      type="text"
                      placeholder="Email"
                      value={id}
                      onChange={(e) => setId(e.target.value)}
                    />
                  </li>
                  <li>
                    <input
                      type="password"
                      placeholder="Password "
                      value={pw}
                      onChange={(e) => setPw(e.target.value)}
                    />
                    <span className="icon material-icons-outlined">
                      visibility
                    </span>
                  </li>
                </ul>
              )}
              {page === "join" && (
                <ul>
                  <li>
                    <input type="text" placeholder="Your name" />
                  </li>
                  <li>
                    <input type="email" placeholder="Email" />
                  </li>
                  <li>
                    <input type="password" placeholder="Password" />
                  </li>
                  <li>
                    <input type="password" placeholder="Confirm Password" />
                  </li>
                </ul>
              )}
              <div className="remember flex-box justify-bet">
                <div className="ckeckbox-wrap">
                  <input type="checkbox" id="rem" className="hidden" />
                  <label htmlFor="rem" className="flex-box align-cnt">
                    <div className="checkbox_item">
                      <span className="material-icons-outlined">done</span>
                    </div>
                    <p>Remember</p>
                  </label>
                </div>
                <div className="find-link">
                  <Link to={"/member/idfind"}>forgot your Email</Link>
                  <Link to={"/member/pwfind"}>Password?</Link>
                </div>
              </div>
              <div className="link-btn">
                {page === "login" && <button type="submit">SIGN IN</button>}
                {page === "join" && (
                  <button type="submit" className="sign-in">
                    SIGN UP
                  </button>
                )}
              </div>
            </form>
          </div>
        </div>
      </article>
    </>
  );
};

export default FormWrap;
