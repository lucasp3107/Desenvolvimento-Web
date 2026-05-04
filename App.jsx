import { useState } from 'react'
import './App.css'

function App() {
  // ─── Estado da lista de tarefas ───────────────────────────────────────────
  // Cada tarefa é um objeto: { id: number, text: string }
  const [tasks, setTasks] = useState([
    { id: Date.now(), text: 'Aprender Hooks do React' }
  ])

  // ─── Estado do input (componente controlado) ──────────────────────────────
  const [inputValue, setInputValue] = useState('')

  // ─── Adicionar tarefa ─────────────────────────────────────────────────────
  function handleAddTask(e) {
    e.preventDefault() // evita recarregar a página

    const trimmed = inputValue.trim()
    if (trimmed === '') return // ignora input vazio

    // Cria novo objeto de tarefa com ID único
    const newTask = { id: Date.now(), text: trimmed }

    // Atualiza o estado de forma IMUTÁVEL (cria novo array)
    setTasks([...tasks, newTask])

    // Limpa o campo de texto
    setInputValue('')
  }

  // ─── Remover tarefa ───────────────────────────────────────────────────────
  function handleRemoveTask(id) {
    // Cria novo array sem a tarefa de ID correspondente
    const newTasks = tasks.filter(task => task.id !== id)
    setTasks(newTasks)
  }

  // ─── Renderização ─────────────────────────────────────────────────────────
  return (
    <div className="app">
      <div className="card">

        {/* Cabeçalho */}
        <h1 className="title">
          <span className="title-icon">✦</span>
          Lista de Tarefas
        </h1>

        {/* Formulário de adição */}
        <form className="form" onSubmit={handleAddTask}>
          <input
            className="input"
            type="text"
            placeholder="Digite uma tarefa..."
            value={inputValue}                            // componente controlado
            onChange={e => setInputValue(e.target.value)} // atualiza estado
          />
          <button className="btn-add" type="submit">
            Adicionar
          </button>
        </form>

        {/* Contador de tarefas */}
        <p className="counter">
          {tasks.length === 0
            ? 'Nenhuma tarefa cadastrada'
            : `${tasks.length} tarefa${tasks.length > 1 ? 's' : ''}`}
        </p>

        {/* Lista renderizada com .map() */}
        <ul className="task-list">
          {tasks.map(task => (
            // key obrigatório: usa o id único de cada tarefa
            <li key={task.id} className="task-item">
              <span className="task-bullet">▸</span>
              <span className="task-text">{task.text}</span>

              {/* Botão Remover: passa o id para a função de remoção */}
              <button
                className="btn-remove"
                onClick={() => handleRemoveTask(task.id)}
                aria-label="Remover tarefa"
              >
                ✕
              </button>
            </li>
          ))}
        </ul>

        {/* Mensagem de lista vazia */}
        {tasks.length === 0 && (
          <p className="empty-msg">Adicione uma tarefa acima! 👆</p>
        )}

      </div>
    </div>
  )
}

export default App
