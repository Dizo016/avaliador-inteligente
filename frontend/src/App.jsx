import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage';
import LoginPage from './components/LoginPage';
import CadastroPage from './components/CadastroPage';
import DashboardPage from './components/DashboardPage';
  
const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />}/>
        <Route path="/cadastro" element={<CadastroPage />}/>
        <Route path="/dashboard" element={<DashboardPage />} />
      </Routes>
    </Router>
  );
}
export default App;
