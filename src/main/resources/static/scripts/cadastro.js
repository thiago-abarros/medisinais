    document.getElementById('cadastroForm').addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);

    axios.post('/cadastro', formData)
      .then(function (response) {
        window.location.href = '/';
      })
      .catch(function (error) {
        var erros = error.response.data;

        var tipos = Object.keys(erros);

        tipos.forEach(tipo => {
            var elemento = document.getElementById('erro' + tipo);
            console.log(elemento);
            elemento.textContent = erros[tipo];
            elemento.style.display = 'block';
        });

//        const msgErro = document.getElementById('erro' + );
//        msgErro.textContent = error.response.data;
//        console.log(error.response.data);
//        msgErro.style.display = 'block';
      });
});