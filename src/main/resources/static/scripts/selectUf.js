axios.get("https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome")
  .then(function (response) {
    let select = document.getElementById("uf");
    response.data.forEach(d => {
      const option = document.createElement('option');

      option.setAttribute('value', d.sigla);
      option.textContent = d.sigla;
      option.value = d.sigla;

      select.appendChild(option);
    });
  })
  .catch(function (error) {
    console.log('Axios Error :-S', error);
  });