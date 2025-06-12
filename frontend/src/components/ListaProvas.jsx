// src/components/ListaProvas.jsx
import React, { useEffect, useState } from 'react';
import './ListaProvas.css';

const ListaProvas = () => {
  const [provas, setProvas] = useState([]);
  const [mensagem, setMensagem] = useState('');

  useEffect(() => {
    const carregarProvas = async () => {
      try {
        const response = await fetch('/api/provas');
        if (!response.ok) throw new Error('Erro ao buscar provas');
        const data = await response.json();
        setProvas(data);
      } catch (err) {
        setMensagem('Erro ao carregar provas.');
      }
    };
    carregarProvas();
  }, []);

  const visualizarPdf = async (provaId, tipo) => {
    try {
      const endpoint = tipo === 'gabarito'
        ? `/api/provas/${provaId}/gabarito/pdf`
        : `/api/provas/${provaId}/pdf`;

      const response = await fetch(endpoint);
      if (!response.ok) throw new Error('Erro ao buscar PDF');

      const blob = await response.blob();
      const url = URL.createObjectURL(blob);
      window.open(url, '_blank');
    } catch (error) {
      setMensagem(`Erro ao carregar PDF do ${tipo}`);
    }
  };

  return (
    <div className="provas-container">
      <h3>📚 Provas Já Criadas</h3>
      {mensagem && <p className="mensagem-erro">{mensagem}</p>}

      {provas.length === 0 ? (
        <p className="mensagem-vazia">Nenhuma prova cadastrada ainda.</p>
      ) : (
        <ul className="lista-provas">
          {provas.map((prova) => (
            <li key={prova.id} className="prova-item">
              <strong className="prova-titulo">{prova.titulo}</strong>
              <p className="prova-criador">
                Criador: {prova.criador ? `${prova.criador.nome}` : 'Desconhecido'}
              </p>
              <div className="botoes-prova">
                <button onClick={() => visualizarPdf(prova.id, 'prova')}>
                  Visualizar Prova
                </button>
                <button onClick={() => visualizarPdf(prova.id, 'gabarito')}>
                  Visualizar Gabarito
                </button>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default ListaProvas;
