const enBtn = document.getElementById("en");
const daBtn = document.getElementById("da");

enBtn.addEventListener("click", () => {
    fetch(`/lang?lang=en`)
        .then(() => window.location.reload());
});

daBtn.addEventListener("click", () => {
    fetch(`/lang?lang=da`)
        .then(() => window.location.reload());
});