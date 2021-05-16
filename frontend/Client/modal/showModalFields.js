
const showModalFields = document.getElementById('create-fields');
const closeModal = document.getElementById('close');
const submitDate = document.getElementById('submit-date');
showModalFields.addEventListener('click', () => {
    document.getElementById("modal-fields").classList.toggle("show-modal-fields");
    document.getElementById("main-block").classList.toggle("hiddle");
})
closeModal.addEventListener('click', ()=>{
    document.getElementById("modal-fields").classList.remove("show-modal-fields");
    document.getElementById("main-block").classList.remove("hiddle");
})
submitDate.addEventListener('click', ()=>{
    document.getElementById("modal-fields").classList.remove("show-modal-fields");
    document.getElementById("main-block").classList.remove("hiddle");
})