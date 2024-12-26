import React, { useState } from "react";
import PolynomialSolver from "./components/PolynomialSolver";
import Sidebar from "./components/Sidebar";

function App() {
  const [history, setHistory] = useState([]);
  const [trash, setTrash] = useState([]);

  const addHistory = (item) => {
    setHistory([...history, item]);
  };

  const addToTrash = (item) => {
    setTrash([...trash, item]);
  };

  const restoreFromTrash = (index) => {
    const restoredItem = trash[index];
    setTrash(trash.filter((_, i) => i !== index));
    setHistory([...history, restoredItem]);
  };

  return (
    <div style={{ display: "flex", minHeight: "100vh", fontFamily: "Arial, sans-serif" }}>
      <Sidebar history={history} trash={trash} onRestore={restoreFromTrash} />
      <PolynomialSolver addHistory={addHistory} addToTrash={addToTrash} />
    </div>
  );
}

export default App;
