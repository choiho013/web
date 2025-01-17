/**
 * 
 */

document.addEventListener('DOMContentLoaded', ()=> {
    const images = document.querySelectorAll('img.img');
    const modal_img = document.querySelector('.modalcontent');
    const span = document.querySelector('.close');
    const modal = document.querySelector('.modal');
    

    images.forEach((img) => {
        img.addEventListener('click', () => {
            const relativeSrc = img.getAttribute('src');
            modalDisplay("block");
            modal_img.src = relativeSrc;
        });
    });
    span.addEventListener('click', () => {
        modalDisplay("none");
    });
    modal.addEventListener('click', () => {
        modalDisplay("none");
    });
    function modalDisplay(text){
    modal.style.display = text;
    }
});