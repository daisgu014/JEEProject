const InitProduct = ()=>{
    let filterBtn  = document.querySelector('.filterBtn');
    let filterModal = document.querySelector('.filter-modal')
    filterBtn.addEventListener('click', ()=>{
        if(filterModal.style.display!='flex'){
            filterModal.style.display = 'flex';
        }else{
            filterModal.style.display = 'none';
        }




    })
}
InitProduct()