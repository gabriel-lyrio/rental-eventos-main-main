const parametros = new URLSearchParams(window.location.search);

const cadastro = parametros.get("cadastro");
const login = parametros.get("login");

if (cadastro === "sucesso") {
    alert("Cadastro feito com sucesso!");
}

if (login === "sucesso") {
    alert("Login realizado com sucesso!");
}

if (login === "erro") {
    alert("Email ou senha incorretos!");
}