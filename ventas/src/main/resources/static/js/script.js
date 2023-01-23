const content = document.querySelector("body");
const hamburguer = content.querySelector(".icon");
const modo_tema = content.querySelector(".modo-tema")
const texto_tema = content.querySelector(".texto-tema");
const navbar = content.querySelector(".navbar");
const options = content.querySelector(".options");
const logout = document.querySelector(".logout");

logout.addEventListener("click", e => {
  document.formlogout.submit();
});

hamburguer.addEventListener('click', () => {
    content.classList.toggle('hamburguesa');
});

modo_tema.addEventListener('click', () => {
    content.classList.toggle('tema');
    if(content.classList.contains('tema')){
        texto_tema.innerHTML = "Ligth Mode";
        navbar.classList.remove("bg-light");
        navbar.classList.remove("navbar-light");
        navbar.classList.add("navbar-dark");
        navbar.classList.add("bg-dark");
        options.classList.add("text-white");
    }else{
        texto_tema.innerHTML = "Dark Mode";
        navbar.classList.add("bg-light");
        navbar.classList.add("navbar-light");
        navbar.classList.remove("navbar-dark");
        navbar.classList.remove("bg-dark");
        options.classList.remove("text-white");
    }
});

$(".sidebar ul li").on('click', function () {
    $(".sidebar ul li.active").removeClass('active');
    $(this).addClass('active');
  });
  $(".open-btn").on('click', function () {
    $(".sidebar").addClass('active');
  });
  $(".close-btn").on('click', function () {
    $(".sidebar").removeClass('active');
  });