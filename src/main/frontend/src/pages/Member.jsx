import React, { useEffect, useState } from "react";
import styles from "../css/member.scss";
import Background from "../components/member/Background";
import LoginForm from "../components/member/LoginForm";
import { useSelector } from "react-redux";
import JoinForm from "../components/member/JoinForm";

import { useLocation } from "react-router-dom";

const Member = () => {
  const [lastPath, setLastPath] = useState("");
  const location = useLocation();
  const locationPath = location.pathname;
  const locationPathArr = locationPath.split("/");
  useEffect(() => {
    setLastPath(locationPathArr[locationPathArr.length - 1]);
  }, []);
  return (
    <>
      <div id="member-wrap" className="flex-box justify-cnt dir-col">
        <section>
          <div className="content flex-box ">
            {lastPath === "login" && <LoginForm page={lastPath} />}
            {lastPath === "join" && <JoinForm page={lastPath} />}
          </div>
        </section>
      </div>
    </>
  );
};

export default Member;
