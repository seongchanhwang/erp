import axios from "axios";
import { useEffect, useState } from "react";

function App() {
  const [message, setMessage] = useState("");
  useEffect(() => {
    axios
      .get("/api/hello")
      .then((response) => setMessage(response.data))
      .catch((error) => console.log(error));
  }, []);
  return <div>123 {message}</div>;
}

export default App;
