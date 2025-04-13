import React from 'react';
import './HomePage.css';

const HomePage = () => {
  return (
    <div className="text-container">
      <h1 className="title">
        AVALIADOR INTELIGENTE
        <img src="/avaliador-inteligente.svg" alt="Ícone de livro" className="icon" />
      </h1>

      <p className="description">
        Uma plataforma educacional inovadora que utiliza <strong>inteligência artificial</strong> para fornecer feedback automatizado em questões de múltipla escolha.
      </p>

      <button className="start-btn">Acessar Plataforma</button>
      <p className="read-more">Leia mais...</p>
    </div>
  );
};

export default HomePage;
