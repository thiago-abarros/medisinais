const wrapper = document.querySelector('.wrapper');
const loginPopup = document.querySelector('.login-btn');
const closeIcon = document.querySelector('.icon-close');

function toggleActiveClass() {
    if (wrapper.classList.contains('active')) {
        wrapper.classList.remove('active');
    }
}

loginPopup.addEventListener('click', ()=> {
    wrapper.id = '';
    wrapper.classList.add('active-login');
    wrapper.classList.add('open');
});


closeIcon.addEventListener('click', ()=> {
    wrapper.id = 'close';
    wrapper.classList.remove('open');
    toggleActiveClass();
});

document.getElementById('loginForm').addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);

    axios.post('/login', formData).then(function (response) {
        window.location.href = '/';
    }).catch(function (error) {
        const msgErro = document.getElementById('msgErro');
        msgErro.textContent = error.response.data;
        msgErro.style.display = 'block';
    });
});