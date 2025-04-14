import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage';
import LoginPage from './components/LoginPage';
import CadastroPage from './components/CadastroPage';
import DashboardPage from './components/DashboardPage';
import ProvasPage from './components/ProvasPage';
import ResponderProvaPage from './components/ResponderProvaPage';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />}/>
        <Route path="/cadastro" element={<CadastroPage />}/>
        <Route path="/dashboard" element={<DashboardPage />} />
        <Route path="/provas" element={<ProvasPage />} />
        <Route path="/provas/:id" element={<ResponderProvaPage />} />
      </Routes>
    </Router>
  );
}
export default App;
