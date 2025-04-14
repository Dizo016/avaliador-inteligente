// src/components/ProvasPage.jsx
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './ProvasPage.css';

const ProvasPage = () => {
  const [provas, setProvas] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProvas = async () => {
      try {
        const response = await fetch('/api/provas');
        const data = await response.json();
        setProvas(data);
      } catch (error) {
        console.error('Erro ao buscar provas:', error);
      }
    };

    fetchProvas();
  }, []);

  return (
    <div className="provas-container">
      <h1>📋 Provas Disponíveis</h1>
      <div className="provas-list">
        {provas.map((prova) => (
          <div key={prova.id} className="prova-card">
            <h2>{prova.titulo}</h2>
            <p>{prova.descricao}</p>
            <button onClick={() => navigate(`/provas/${prova.id}`)}>Responder</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProvasPage;