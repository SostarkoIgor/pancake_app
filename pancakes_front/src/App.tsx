import { BrowserRouter, Routes, Route } from 'react-router-dom'
import './App.css'
import MainLayout from './layouts/MainLayout'
import ShowOrders from './pages/ShowOrders'
import ShowOrder from './pages/ShowOrder'
import CreateOrder from './pages/CreateOrder'

function App() {

  return (
    <>
        <BrowserRouter>
            <Routes>
              <Route element={<MainLayout />}>
                <Route path="/" element={<ShowOrders />} />
                <Route path="/create" element={<CreateOrder />} />
                <Route path="/order/:id" element={<ShowOrder />} />
              </Route>
            </Routes>
        </BrowserRouter>
    </>
  )
}

export default App
