  document.getElementById('loginForm').addEventListener('submit', (event) => {
      event.preventDefault();

      const formData = new FormData(event.target);

      axios.post('/login', formData)
        .then(function (response) {
          window.location.href = '/';
        })
        .catch(function (error) {
          const msgErro = document.getElementById('msgErro');
          msgErro.textContent = error.response.data;
          msgErro.style.display = 'block';
        });
  });