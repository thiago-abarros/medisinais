const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
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
})

loginLink.addEventListener('click', ()=> {
    wrapper.id = '';
    wrapper.classList.remove('active');
})

closeIcon.addEventListener('click', ()=> {
    wrapper.id = 'close';
    wrapper.classList.remove('open');
    toggleActiveClass();
})