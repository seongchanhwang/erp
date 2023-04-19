import axios from "axios";
import { useEffect, useState } from "react";

function App() {
  // const [message, setMessage] = useState("");
  useEffect(() => {
    axios
      .get("/login", {
        loginId: "admin",
        password: "admin!2",
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => console.log(error));
  }, []);
  return (
    <>
      <div>123 </div>
      <div>456</div>
      <div>456</div>
      <div>456</div>
      <div>456</div>
      <div>456</div>
      <div>456</div>
    </>
  );
}

export default App;
