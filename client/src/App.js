import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Demo from './Components/demo';
import Base from './Components/Base';
import Home from './Pages/Home';
import { BrowserRouter as Router, Routes, Route, BrowserRouter } from "react-router-dom";
import Login from './Pages/Login';
import Signup from './Pages/Signup';
import About from './Pages/About';
import Service from './Pages/Services';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';
import UserDashboard from './Pages/User-routes/UserDashboard';
import Privateroutes from './Components/Privateroutes';
import Profileinfo from './Pages/User-routes/Profileinfo';
function App() {
  return (

    <BrowserRouter>
      <ToastContainer position='top-center' />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="login" element={<Login />} />
        <Route path="Signup" element={<Signup />} />
        <Route path="About" element={<About />} />
        <Route path="services" element={<Service />} />
{/* here nested links are their for nested you have to use Outlet in it*/}
        <Route path="user" element={<Privateroutes />}>
          <Route path="dashboard" element={<UserDashboard />} />
          <Route path="profile-info" element={<Profileinfo/>}/>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
