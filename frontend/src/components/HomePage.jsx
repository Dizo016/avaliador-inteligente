// src/components/HomePage.jsx
import React from 'react';
import { useNavigate } from 'react-router-dom'; // import

import './HomePage.css';

const HomePage = () => {
  const navigate = useNavigate(); // hook do router

  const handleStart = () => {
    navigate('/login');
  };

  return (
    <div className="text-container">
      <h1 className="title">
        AVALIADOR INTELIGENTE
        <img src="/avaliador-inteligente.svg" alt="Ícone de livro" className="icon" />
      </h1>

      <p className="description">
        Uma plataforma educacional inovadora que utiliza <strong>inteligência artificial</strong> para fornecer feedback automatizado em questões de múltipla escolha.
      </p>

      <button className="start-btn" onClick={handleStart}>Acessar Plataforma</button>
      <p className="read-more">Leia mais...</p>
    </div>
  );
};

export default HomePage;