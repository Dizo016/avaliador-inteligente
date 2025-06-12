import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

export default function Header() {
  const navigate = useNavigate();
  const [nomeUsuario, setNomeUsuario] = useState('');

  useEffect(() => {
    const usuario = JSON.parse(localStorage.getItem("usuarioLogado"));
    if (usuario?.nome) {
      setNomeUsuario(usuario.nome);
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("usuarioLogado");
    navigate("/login");
  };

  return (
    <header className="w-full fixed top-0 left-0 z-50 bg-[#428F3882] text-white p-4 shadow-md flex justify-between items-center">
      <h1 className="text-xl font-semibold">Prova Fácil</h1>

      <div className="flex items-center space-x-4">
        <button
          onClick={() => navigate("/criar-prova")}
          className="bg-white text-[#428F38] font-semibold px-4 py-2 rounded-2xl border border-[#c8cccb] shadow hover:bg-gray-100 transition"
        >
          Criar prova com IA
        </button>

        <button
          onClick={() => navigate("/provas")}
          className="bg-white text-[#428F38] font-semibold px-4 py-2 rounded-2xl border border-[#c8cccb] shadow hover:bg-gray-100 transition"
        >
          Mostrar provas já criadas
        </button>

        <button
          onClick={handleLogout}
          className="bg-red-500 text-white font-semibold px-4 py-2 rounded-2xl border border-[#c8cccb] shadow hover:bg-red-600 transition"
        >
          Sair
        </button>

        {nomeUsuario && (
          <span className="ml-4 text-sm font-medium whitespace-nowrap">
            Bem-vindo – {nomeUsuario}
          </span>
        )}
      </div>
    </header>
  );
}
