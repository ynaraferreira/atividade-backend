const form = document.getElementById('pessoaForm');
const pessoasList = document.getElementById('pessoasList');


async function listarPessoas() {
    const response = await fetch('http://localhost:8080/api/pessoas');
    const pessoas = await response.json();
    pessoasList.innerHTML = '';
    pessoas.forEach(pessoa => {
        const li = document.createElement('li');
        li.textContent = `${pessoa.nome} - CPF: ${pessoa.cpf} - Idade: ${pessoa.idade}`;
        pessoasList.appendChild(li);
    });
}


form.addEventListener('submit', async (event) => {
    event.preventDefault();
    const nome = document.getElementById('nome').value;
    const cpf = document.getElementById('cpf').value;
    const idade = document.getElementById('idade').value;

    const response = await fetch('http://localhost:8080/api/pessoas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ nome, cpf, idade }),
    });

    if (response.ok) {
        listarPessoas();
        form.reset();
    } else {
        alert('Erro ao adicionar pessoa');
    }
});


listarPessoas();
