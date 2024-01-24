const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const loginPopup = document.querySelector('.login-btn');
const registerPopup = document.querySelector('.register-btn');
const closeIcon = document.querySelector('.icon-close');

function toggleActiveClass() {
    if (wrapper.classList.contains('active')) {
        wrapper.classList.remove('active');
    }
}

loginPopup.addEventListener('click', ()=> {
    wrapper.id = '';
    wrapper.classList.add('active-login');
})

registerLink.addEventListener('click', ()=> {
    wrapper.id = '';
    wrapper.classList.add('active');
})

loginLink.addEventListener('click', ()=> {
    wrapper.id = '';
    wrapper.classList.remove('active');
})

registerPopup.addEventListener('click', ()=> {
    wrapper.id = '';
    wrapper.classList.add('active-login');
    wrapper.classList.add('active');
})

closeIcon.addEventListener('click', ()=> {
    wrapper.id = 'close';
    toggleActiveClass();
})