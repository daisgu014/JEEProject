
    function Init() {
        document.getElementById("addBtn").addEventListener("click", function() {
            document.querySelector(".Add-popup").style.display="block";
            document.querySelector("#overlay").style.display="block";
        });
        document.querySelector("#overlay").addEventListener("click", ()=>{
            document.querySelector(".Add-popup").style.display="none";
            document.querySelector("#overlay").style.display="none";
        })
        document.querySelector(".close-btn").addEventListener("click", ()=>{
            document.querySelector(".Add-popup").style.display="none";
            document.querySelector("#overlay").style.display="none";
        })
        let avt = document.querySelector('.user--info')
        avt.addEventListener('click', function(){
            let modal = document.getElementById('user-modal');
            if( modal.style.top=='90px'){
                modal.style.top='-150px'
            }else{
                modal.style.top='90px'
            }

        })

        let checkbox = document.querySelector('.check_box');
        let checkbox_subs = document.querySelectorAll('.sub_checkbox');

        checkbox_subs.forEach(checkbox=>{
            checkbox.addEventListener('click', function (){
                for (let i = 0; i < checkbox_subs.length; i++) {
                    if (checkbox_subs[i].checked) {
                        document.getElementById('event').style.display = 'none';
                        document.getElementById('deleteBtn').style.display = 'block';
                        return;
                    }
                }
                document.getElementById('event').style.display = 'flex';
                document.getElementById('deleteBtn').style.display = 'none'

            })

        })


        checkbox.addEventListener('click',function(){
            if(this.checked){
                document.getElementById('event').style.display='none';
                document.getElementById('deleteBtn').style.display='block';
                checkbox_subs.forEach(checkbox =>{
                        checkbox.checked = true;
                    }
                )

            }else{
                document.getElementById('event').style.display='flex';
                document.getElementById('deleteBtn').style.display='none';
                checkbox_subs.forEach(checkbox =>{
                    checkbox.checked = false;
                })
            }
        })
    }

    Init();

// window.onload= ()=> {
//     var url = location.href.split('/')[4];
//     switch (url) {
//         case 'categories':
//             document.querySelector(".menu li.active").classList.remove('active');
//             document.querySelector("li.categories").classList.add('active');
//             break;
//         case 'products':
//             document.querySelector("li.active").classList.remove('active');
//             document.querySelector("li.products").classList.add('active');
//             break;
//         case 'users':
//             document.querySelector("li.active").classList.remove('active');
//             document.querySelector("li.users").classList.add('active');
//             break;
//     }
// }
//