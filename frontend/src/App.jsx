import{useState, useEffect} from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import HomePage from './homepage'
import Card from './Card'
import axios from 'axios'

function App() {

  const url = "http://localhost:8080/jobs/api";

  return (
    // <Card></Card>
    <HomePage></HomePage>
  )
}

export default App
