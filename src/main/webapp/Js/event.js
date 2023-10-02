function Init() {
    document.querySelector("#addBtn").addEventListener("click", function () {
        document.querySelector(".Add-popup").style.display = "block";
        document.querySelector("#overlay").style.display = "block";
    });
    document.querySelector("#overlay").addEventListener("click", () => {
        document.querySelector(".Add-popup").style.display = "none";
        document.querySelector("#overlay").style.display = "none";
    })
    document.querySelector(".close-btn").addEventListener("click", () => {
        document.querySelector(".Add-popup").style.display = "none";
        document.querySelector("#overlay").style.display = "none";
    })

}

Init();

var url = location.href.split('/')[4];
switch (url) {
    case 'categories':
        document.querySelector(".menu li.active").classList.remove('active');
        document.querySelector("li.categories").classList.add('active');
        break;
    case 'products':
        document.querySelector("li.active").classList.remove('active');
        document.querySelector("li.products").classList.add('active');
        break;
    case 'users':
        document.querySelector("li.active").classList.remove('active');
        document.querySelector("li.users").classList.add('active');
        break;
}

  