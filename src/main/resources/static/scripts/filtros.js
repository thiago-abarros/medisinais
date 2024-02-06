function pegarQueryParam(parametro) {
    var urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(parametro);
}

function marcarCheckbox(valores, idDiv) {
    var checkboxes = document.querySelectorAll("#" + idDiv + " input[type='checkbox']");

    checkboxes.forEach(function(checkbox) {
        if(valores == checkbox.value) {
            checkbox.checked = true;

            if(idDiv == "especialidade") {
                var especialidade = document.getElementById(valores)
                var titulo = document.getElementById("titulo")
                titulo.innerHTML = especialidade.textContent + "s " + "encontrados:"
            }
        }
    });
}

var cidade = pegarQueryParam('cidade');
var plano = pegarQueryParam('planoSaude');
var especialidade = pegarQueryParam('especialidade');

if(cidade != null) {
    document.getElementById('cidade').value = cidade;
}

marcarCheckbox(plano, "planosSaude");
marcarCheckbox(especialidade, "especialidade");