let avt = document.querySelector('.user--info')
avt.addEventListener('click', function(){
    let modal = document.getElementById('user-modal');
    if( modal.style.top=='90px'){
        modal.style.top='-150px'
    }else{
        modal.style.top='90px'
    }

})