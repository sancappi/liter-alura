document.addEventListener("DOMContentLoaded", function() {
    function buscarLivroPorTitulo() {
        document.querySelector("#button_livro_titulo").addEventListener("click", function(event) {
            event.preventDefault();

            let tituloLivro = document.getElementById("input_livro_titulo").value;

            if (tituloLivro.trim() === "") {
                let retornar = document.getElementById("retorno");
                retornar.innerText = "Por favor, digite o título de um livro. ";
                retornar.style.color="red";
                return;
            }

            let livro = {
                titulo: tituloLivro
            };

            fetch("/livros/buscar_livro", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(livro)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Erro ao buscar livro.");
                }
                return response.json();
            })
            .then( data_book => {
                document.getElementById("titulo").textContent = "Título: " + data_book.titulo;
                const autoresNomes = data_book.autores.join(", ")
                document.getElementById("autores").textContent = "Autores: " + autoresNomes;
                const idiomas = data_book.idiomas.join(", ");
                document.getElementById("idiomas").textContent = "Idiomas: " + idiomas;
                document.getElementById("downloads").textContent = "Número de downloads: " + data_book.numeroDownloads;
            })
            .catch(error => {
                console.error("Erro:", error);
            });
        });
    }

    function buscarTodosLivros() {
        document.getElementById("buscar_todos_livros").addEventListener("click", () => {
            fetch("/livros/todos")
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Erro ao buscar todos os livros.");
                    }
                    return response.json();
                })
                .then(data_book => {
                    const livrosContainer = document.getElementById("livros_todos");
                    livrosContainer.innerHTML = "";

                    data_book.forEach(livro => {
                        const livroElemento = document.createElement("div");
                        const autoresNomes = livro.autores.join(", ");
                        const idiomas = livro.idiomas.join(", ");

                        livroElemento.innerHTML = `
                            <h3>Título: ${livro.titulo}</h3>
                            <p>Autores: ${autoresNomes}</p>
                            <p>Idiomas: ${idiomas}</p>
                            <p>Número de downloads: ${livro.numeroDownloads}</p>
                        `;
                        livrosContainer.appendChild(livroElemento);
                    });
                })
                .catch(error => {
                    console.error("Erro:", error);
                });
        });
    }

    function buscarTodosAutores() {
        document.getElementById("buscar_todos_autores").addEventListener("click", () => {
            fetch("/autores/todos")
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Erro ao buscar todos os autores.");
                    }
                    return response.json();
                })
                .then(data_author => {
                    const autoresContainer = document.getElementById("autores_todos");
                    autoresContainer.innerHTML = "";

                    data_author.forEach(autor => {
                        const autorElemento = document.createElement("div");
                        autorElemento.innerHTML = `
                            <h3>Nome: ${autor.nome}</h3>
                            <p>Ano nascimento: ${autor.anoNascimento}</p>
                            <p>Ano falecimento: ${autor.anoMorte !== null ? autor.anoMorte : "Vivo"}</p>
                        `;
                        autoresContainer.appendChild(autorElemento);
                    });
                })
                .catch(error => {
                    console.error("Erro:", error);
                });
        });
    }

    function buscarAutoresPorAno() {
        document.querySelector("#button_autor_ano").addEventListener("click", function(event) {
            event.preventDefault();

            let autorAno = document.getElementById("input_autor_ano").value;

            if (autorAno.trim() === "") {
                let retornar = document.getElementById("autores_ano");
                retornar.innerText = "Por favor, digite um ano. ";
                retornar.style.color="red";
                return;
            }

            let autorAnoF = parseInt(autorAno);

            let dadosAutor = {
                ano: autorAnoF
            };

            fetch("/autores/buscar_autor", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(dadosAutor)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Erro ao buscar autor.");
                }
                return response.json();
            })
            .then(data_author => {
                const retornarVivos = document.querySelector("#autores_ano");
                retornarVivos.innerHTML = "";

                data_author.forEach(autor => {
                    const autorItem = document.createElement("li");
                    autorItem.innerHTML = `
                        <p>Nome: ${autor.nome} (${autor.anoNascimento} - ${autor.anoMorte !== null ? autor.anoMorte : 'Vivo'})</p>
                    `;
                    retornarVivos.appendChild(autorItem);
                });
            })
            .catch(error => {
                console.error("Erro:", error);
            });
        });
    }

    function livroPorIdioma() {
        console.log("funcao chamada")
        let idiomaEscolhido;

        let radioButtons = document.querySelectorAll('.buscar_livro_idioma button[type="radio"]');
        radioButtons.forEach(function(radioButton) {
            radioButton.addEventListener("click", function() {
                idiomaEscolhido = this.value;

                let livroIdioma = {
                    idioma: idiomaEscolhido
                };

                fetch("/livros/livros_por_idioma", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(livroIdioma)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Erro ao buscar livro.");
                    }
                    return response.json();
                })
                .then(data_book => {
                    if (data_book.length === 0) {
                        let retorno = document.getElementById("livros");
                        retorno.innerText = "Não existem livros cadastrados com o idioma selecionado.";
                        retorno.style.color = "red";
                    } else {
                        const livros = document.querySelector("#livros");
                        livros.innerHTML = "";

                        data_book.forEach(livro => {
                            const livrosRetornados = document.createElement("li");
                            livrosRetornados.innerHTML = `
                                <h3>${livro}</h3>
                            `;
                            livros.appendChild(livrosRetornados);
                        });
                    }
                })
                .catch(error => {
                    console.error("Erro:", error);
                });
            });
        });
    };

    buscarLivroPorTitulo();
    buscarTodosLivros();
    buscarTodosAutores();
    buscarAutoresPorAno();
    livroPorIdioma();
});