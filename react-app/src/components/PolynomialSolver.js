import React, { useState } from "react";
import axios from "axios";
import { Line } from "react-chartjs-2";
import { Chart as ChartJS, LineElement, CategoryScale, LinearScale, PointElement } from "chart.js";
import { parse } from "mathjs";

ChartJS.register(LineElement, CategoryScale, LinearScale, PointElement);

const PolynomialSolver = ({ addHistory, addToTrash }) => {
  const [equation, setEquation] = useState("");
  const [roots, setRoots] = useState(null);
  const [factorization, setFactorization] = useState(null);
  const [error, setError] = useState("");
  const [chartData, setChartData] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setRoots(null);
    setFactorization(null);
    setChartData(null);

    try {
      const response = await axios.post("http://localhost:8084/api/polynomial/calculate", {
        polynomial: equation,
      });

      const newRoots = response.data.roots || [];
      const newFactorization = response.data.factorization || "N/A";

      setRoots(newRoots);
      setFactorization(newFactorization);

      // Parse the equation and generate data for the curve
      const parsedEquation = parse(equation);
      const compiledEquation = parsedEquation.compile();

      const xValues = Array.from({ length: 201 }, (_, i) => i - 100); // x from -100 to 100
      const yValues = xValues.map((x) => compiledEquation.evaluate({ x }));

      setChartData({
        labels: xValues,
        datasets: [
          {
            label: "Courbe du polynôme",
            data: yValues,
            borderColor: "rgba(75, 192, 192, 1)",
            backgroundColor: "rgba(75, 192, 192, 0.2)",
            fill: true,
          },
        ],
      });

      addHistory({
        equation,
        roots: newRoots,
        factorization: newFactorization,
      });
    } catch (err) {
      setError("Une erreur s'est produite lors du traitement de l'équation. Veuillez vérifier le format.");
    }
  };

  const handleDelete = () => {
    addToTrash({ equation, roots, factorization });
    setEquation("");
    setRoots(null);
    setFactorization(null);
    setChartData(null);
  };

  const styles = {
    container: {
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      minHeight: "100vh",
      background: "linear-gradient(145deg, #e6e9ef, #cbd3dc)",
      fontFamily: "'Poppins', sans-serif",
      padding: "20px",
    },
    card: {
      width: "500px",
      padding: "30px",
      background: "#e0e5ec",
      borderRadius: "20px",
      boxShadow: "10px 10px 30px #a3b1c6, -10px -10px 30px #ffffff",
      color: "#333",
      textAlign: "center",
    },
    title: {
      fontSize: "28px",
      fontWeight: "700",
      color: "#34495e",
      marginBottom: "20px",
    },
    form: {
      marginBottom: "20px",
    },
    inputGroup: {
      marginBottom: "15px",
    },
    label: {
      display: "block",
      marginBottom: "8px",
      fontSize: "18px",
      fontWeight: "600",
      color: "#555",
      textAlign: "left",
    },
    input: {
      width: "100%",
      padding: "15px",
      fontSize: "16px",
      borderRadius: "12px",
      border: "none",
      outline: "none",
      background: "#f0f3f7",
      boxShadow: "inset 4px 4px 8px #d1d9e6, inset -4px -4px 8px #ffffff",
      color: "#333",
      transition: "box-shadow 0.3s ease",
    },
    buttonGroup: {
      display: "flex",
      gap: "10px",
    },
    button: {
      flex: 1,
      padding: "12px 15px",
      fontSize: "16px",
      fontWeight: "600",
      borderRadius: "12px",
      border: "none",
      cursor: "pointer",
      background: "#e0e5ec",
      boxShadow: "4px 4px 8px #a3b1c6, -4px -4px 8px #ffffff",
      color: "#34495e",
      transition: "all 0.3s ease",
    },
    solveButton: {
      backgroundColor: "#2ecc71",
      color: "#ffffff",
    },
    deleteButton: {
      backgroundColor: "#e74c3c",
      color: "#ffffff",
    },
    error: {
      color: "#e74c3c",
      fontSize: "14px",
      marginTop: "10px",
      textAlign: "center",
    },
    results: {
      marginTop: "20px",
      textAlign: "left",
      color: "#34495e",
    },
    subtitle: {
      fontSize: "20px",
      fontWeight: "600",
      color: "#2c3e50",
      marginBottom: "10px",
    },
    chartContainer: {
      marginTop: "20px",
    },
  };

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <h2 style={styles.title}>Résolution de Polynômes</h2>
        <form onSubmit={handleSubmit} style={styles.form}>
          <div style={styles.inputGroup}>
            <label htmlFor="equation" style={styles.label}>
              Entrez une équation polynomiale :
            </label>
            <input
              type="text"
              id="equation"
              value={equation}
              onChange={(e) => setEquation(e.target.value)}
              placeholder="Exemple : x^2 - 3x + 2"
              style={styles.input}
            />
          </div>
          <div style={styles.buttonGroup}>
            <button type="submit" style={{ ...styles.button, ...styles.solveButton }}>
              Résoudre
            </button>
            <button type="button" onClick={handleDelete} style={{ ...styles.button, ...styles.deleteButton }}>
              Supprimer
            </button>
          </div>
        </form>
        {error && <p style={styles.error}>{error}</p>}
        {roots && (
          <div style={styles.results}>
            <h3 style={styles.subtitle}>Résultats :</h3>
            <p>
              <strong>Racines :</strong> {roots.length ? roots.join(", ") : "Aucune racine trouvée"}
            </p>
            <p>
              <strong>Factorisation :</strong> {factorization}
            </p>
          </div>
        )}
        {chartData && (
          <div style={styles.chartContainer}>
            <h3 style={styles.subtitle}>Courbe du polynôme :</h3>
            <Line data={chartData} />
          </div>
        )}
      </div>
    </div>
  );
};

export default PolynomialSolver;