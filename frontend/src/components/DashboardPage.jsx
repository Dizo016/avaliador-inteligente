import React, { useState } from 'react';
import CriarProvaIA from './CriarProvaIA';
import ListaProvas from './ListaProvas';
import { useNavigate } from 'react-router-dom';
import './Header.css';

const DashboardPage = () => {
  const [abaAtiva, setAbaAtiva] = useState('criar');
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('usuarioLogado');
    navigate('/login');
  };

  return (
    <div>
      <header className="header-container">
        <h2>Prova Fácil</h2>

        <div className="header-botoes">
          <button className="btn-criar" onClick={() => setAbaAtiva('criar')}>
            Criar Prova com IA
          </button>
          <button className="btn-listar" onClick={() => setAbaAtiva('listar')}>
            Mostrar Provas Já Criadas
          </button>
          <button className="btn-sair" onClick={handleLogout}>
            Sair
          </button>
        </div>
      </header>

      <div style={{ padding: '30px' }}>
        {abaAtiva === 'criar' && <CriarProvaIA />}
        {abaAtiva === 'listar' && <ListaProvas />}
      </div>
    </div>
  );
};

export default DashboardPage;
