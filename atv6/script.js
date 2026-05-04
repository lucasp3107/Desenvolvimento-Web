const input = document.getElementById("taskInput");
const button = document.getElementById("addBtn");
const list = document.getElementById("taskList");

// Adicionar tarefa
button.addEventListener("click", () => {
    const texto = input.value.trim();

    if (texto === "") return;

    const li = document.createElement("li");

    // checkbox opcional
    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";

    const span = document.createElement("span");
    span.textContent = " " + texto;

    li.appendChild(checkbox);
    li.appendChild(span);

    list.appendChild(li);

    input.value = "";
});

// Delegação de eventos
list.addEventListener("click", (e) => {
    const li = e.target.closest("li");

    if (!li) return;

    if (e.target.type === "checkbox") {
        li.classList.toggle("completed");
        return;
    }

    li.remove();
});
