document.getElementById('cadastroForm').addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);

    axios.post('/cadastro', formData).then(function (response) {
        window.location.href = '/';
    }).catch(function (error) {
        var errorElements = document.querySelectorAll('[id^="msgErro"]');

        errorElements.forEach(element => {
            element.textContent = '';
            element.style.display = 'none';
        });

        var erros = error.response.data;
        var tipos = Object.keys(erros);

        tipos.forEach(tipo => {
            var elemento = document.getElementById('msgErro' + tipo);
            elemento.textContent = erros[tipo];
            elemento.style.display = 'block';
        });
    });
});