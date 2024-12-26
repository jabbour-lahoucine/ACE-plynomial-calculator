import React from "react";

const Sidebar = ({ history = [], trash = [], onRestore }) => {
  return (
    <div style={styles.sidebar}>
      <h3 style={styles.heading}>Historique</h3>
      <ul style={styles.list}>
        {history && history.length > 0 ? (
          history.map((item, index) => (
            <li key={index} style={styles.item}>
              {item.equation} <br />
              <span style={styles.smallText}>Racines: {item.roots.join(", ")}</span>
            </li>
          ))
        ) : (
          <li style={styles.item}>Aucun historique disponible.</li>
        )}
      </ul>
      <h3 style={styles.heading}>Corbeille</h3>
      <ul style={styles.list}>
        {trash && trash.length > 0 ? (
          trash.map((item, index) => (
            <li key={index} style={styles.item}>
              {item.equation} <br />
              <button onClick={() => onRestore(index)} style={styles.restoreButton}>
                Restaurer
              </button>
            </li>
          ))
        ) : (
          <li style={styles.item}>Aucune donnée dans la corbeille.</li>
        )}
      </ul>
    </div>
  );
};

const styles = {
  sidebar: {
    width: "280px",
    backgroundColor: "#263238", // Couleur de fond plus sombre pour un aspect moderne
    padding: "30px 20px",
    borderRight: "1px solid #37474f", // Limite subtile pour un effet plus net
    height: "100vh",
    overflowY: "auto",
    color: "#eceff1", // Texte clair pour contraster avec le fond sombre
    fontFamily: "'Poppins', sans-serif", // Police moderne et élégante
    boxShadow: "4px 0px 20px rgba(0, 0, 0, 0.2)",
    transition: "width 0.3s ease-in-out",
    position: "relative",
  },
  heading: {
    marginBottom: "20px",
    color: "#f0f4c3", // Couleur claire et élégante pour le titre
    fontSize: "24px",
    fontWeight: "700", // Plus de poids pour le titre
    letterSpacing: "0.5px",
    textTransform: "uppercase",
    borderBottom: "3px solid #ffeb3b", // Ligne sous le titre pour un effet de focus
    paddingBottom: "10px",
    fontFamily: "'Poppins', sans-serif",
  },
  list: {
    listStyleType: "none",
    padding: 0,
    margin: 0,
  },
  item: {
    marginBottom: "20px",
    padding: "15px",
    backgroundColor: "#37474f", // Fond de l'élément plus foncé
    borderRadius: "10px",
    boxShadow: "0 6px 12px rgba(0, 0, 0, 0.1)",
    fontSize: "16px",
    lineHeight: "1.8",
    color: "#eceff1",
    transition: "transform 0.3s ease, box-shadow 0.3s ease",
    cursor: "pointer",
  },
  itemHover: {
    transform: "translateX(10px)", // Déplacement à gauche au survol pour un effet de profondeur
    boxShadow: "0px 12px 20px rgba(0, 0, 0, 0.2)",
  },
  smallText: {
    fontSize: "14px",
    color: "#b0bec5", // Couleur subtile pour le texte secondaire
    fontStyle: "italic",
  },
  restoreButton: {
    marginTop: "12px",
    padding: "10px 20px",
    backgroundColor: "#ffeb3b", // Jaune lumineux pour attirer l'attention
    color: "#263238",
    border: "none",
    borderRadius: "8px",
    cursor: "pointer",
    fontWeight: "600",
    fontFamily: "'Poppins', sans-serif",
    transition: "background-color 0.3s ease, transform 0.3s ease",
    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
  },
  restoreButtonHover: {
    backgroundColor: "#c8e6c9", // Couleur verte claire au survol
    transform: "scale(1.05)", // Zoom au survol pour un effet interactif
  },
};

export default Sidebar;
