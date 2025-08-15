const blurOverlay = document.querySelector(".blur-background");
const movieFormOne = document.querySelector(".movie-form.first");
const movieFormTwo = document.querySelector(".movie-form.second");
const sideMenu = document.querySelector(".description-side-menu");
const movieCard = document.querySelectorAll(".movie-card");

console.log(blurOverlay);


// Event listener for the + button
const addButton = document.querySelector("#button-container button");
addButton.addEventListener('click',  e => {
    blurOverlay.style.display = "block";
    movieFormOne.style.display = "block";
    movieFormTwo.style.display = "none";
    let inputForm = document.querySelectorAll(".movie-form input");
    inputForm.forEach(input => {
        input.value = "";
    });
});

// Event listener to get to the second page of the form
const movieForm = document.querySelector(".manual-movie-form");
movieForm.addEventListener('submit', e => {
    e.preventDefault();
    movieFormOne.style.display = "none";
    movieFormTwo.style.display = "block";
    const circle = document.querySelector(".movie-form.second .progress-circle.second");
    circle.style.backgroundColor = "#20285b";
});

// Event listener to close the form
const formCloseIcon = document.querySelectorAll(".movie-form .fa-circle-xmark");
formCloseIcon.forEach(icon => {
    icon.addEventListener('click', e => {
        blurOverlay.style.display = "none";
        movieFormOne.style.display = "none";
        movieFormTwo.style.display = "none";
    });
});

// Opens movie description sidebar
movieCard.forEach(card => {
    card.addEventListener('click', e => {
        sideMenu.style.display = "block";
    });
});

const sideMenuCloseIcon = document.querySelector(".description-side-menu .fa-circle-xmark");
sideMenuCloseIcon.addEventListener('click', e => {
    sideMenu.style.display = "none";
});

// fills star rating
