import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import Login from './components/login/Login'
import MainPage from './components/mainPage/MainPage'

function App() {
  

  return (
    <>
    <BrowserRouter>

    <Routes>
      <Route path="/" element={<Login/>}/>
      <Route path="/FrontEnd/react/DuckyTracker/src/components/mainPage/MainPage.tsx" element={<MainPage/>}/>


    </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
