import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Demo from './Components/demo';
import Base from './Components/Base';
import Home from './Pages/Home';
import {BrowserRouter as Router,Routes,Route, BrowserRouter} from "react-router-dom";
import Login from './Pages/Login';
import Signup from './Pages/Signup';
import About from './Pages/About';
import Service from './Pages/Services';
function App() {
  return (
   
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="login" element={<Login/>}/>
        <Route path="Signup" element={<Signup/>}/>
        <Route path="About" element={<About/>}/>
        <Route path="services" element={<Service/>}/>
      </Routes>
      </BrowserRouter>
  );
}

export default App;
