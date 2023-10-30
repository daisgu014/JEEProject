let avt = document.querySelector('.user--info')
avt.addEventListener('click', function(){
    let modal = document.getElementById('user-modal');
    if( modal.style.top=='90px'){
        modal.style.top='-150px'
    }else{
        modal.style.top='90px'
    }

})
let filterBtn  = document.querySelector('.filterBtn');
let filterModal = document.querySelector('.filter-modal')
filterBtn.addEventListener('click', ()=>{
    if(filterModal.style.display!='flex'){
        filterModal.style.display = 'flex';
    }else{
        filterModal.style.display = 'none';
    }
})