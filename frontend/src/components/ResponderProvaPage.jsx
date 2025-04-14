// src/components/ResponderProvaPage.jsx
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import './ResponderProvaPage.css';

const ResponderProvaPage = () => {
  const { id } = useParams(); // ID da prova na URL
  const [prova, setProva] = useState(null);
  const [respostas, setRespostas] = useState({});

  useEffect(() => {
    const fetchProva = async () => {
      try {
        const response = await fetch(`/api/provas/${id}`);
        const data = await response.json();
        setProva(data);
      } catch (error) {
        console.error('Erro ao buscar prova:', error);
      }
    };

    fetchProva();
  }, [id]);

  const handleSelecionarAlternativa = (idQuestao, idAlternativa) => {
    setRespostas((prev) => ({
      ...prev,
      [idQuestao]: idAlternativa
    }));
  };

  const handleEnviarRespostas = async () => {
    try {
      for (const [idQuestao, idAlternativa] of Object.entries(respostas)) {
        await fetch('/api/respostas', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            alunoId: 1, // por enquanto fixo — vamos pegar do login depois
            provaId: parseInt(id),
            questaoId: parseInt(idQuestao),
            respostaMarcada: idAlternativa
          })
        });
      }
      alert('Respostas enviadas com sucesso!');
    } catch (error) {
      console.error('Erro ao enviar respostas:', error);
      alert('Erro ao enviar respostas.');
    }
  };

  if (!prova) return <p>Carregando prova...</p>;

  return (
    <div className="prova-container">
      <h1>{prova.titulo}</h1>

      {prova.questoes.map((questao) => (
        <div key={questao.id} className="questao-box">
          <h3>{questao.enunciado}</h3>
          <div className="alternativas">
            {questao.alternativas.map((alt) => (
              <label key={alt.id} className="alternativa-label">
                <input
                  type="radio"
                  name={`questao-${questao.id}`}
                  value={alt.id}
                  checked={respostas[questao.id] === alt.id}
                  onChange={() => handleSelecionarAlternativa(questao.id, alt.id)}
                />
                {alt.texto}
              </label>
            ))}
          </div>
        </div>
      ))}

      <button className="enviar-button" onClick={handleEnviarRespostas}>
        Enviar Respostas
      </button>
    </div>
  );
};

export default ResponderProvaPage;
