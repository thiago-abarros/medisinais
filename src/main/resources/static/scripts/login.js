  document.getElementById('loginForm').addEventListener('submit', (event) => {
      event.preventDefault(); // Impede o envio do formulário padrão

      const formData = new FormData(event.target);

      axios.post('/login', formData)
        .then(function (response) {
          window.location.href = '/home';
        })
        .catch(function (error) {
          const errorContainer = document.getElementById('errorContainer');
          errorContainer.textContent = error.response.data;
          errorContainer.style.display = 'block';
        });
  });