import React, { useState } from "react";
import IntroWrap from "./IntroWrap";
import FormWrap from "./FormWrap";

const LoginForm = ({ page }) => {
  return (
    <>
      <IntroWrap page={page} />
      <FormWrap page={page} />
    </>
  );
};

export default LoginForm;
