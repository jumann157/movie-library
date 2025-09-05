const blurOverlay = document.querySelector(".blur-background");
const movieFormOne = document.querySelector(".movie-form.first");
const movieFormTwo = document.querySelector(".movie-form.second");
const sideMenu = document.querySelector(".description-side-menu");
const movieCard = document.querySelectorAll(".movie-card");
const BASE_URL = 'http://localhost:8080';

console.log(blurOverlay);


// Event listener for the + button
const addButton = document.querySelector("#button-container button");
addButton.addEventListener('click',  e => {
    blurOverlay.style.display = "block";
    movieFormOne.style.display = "block";
    movieFormTwo.style.display = "none";
    addButton.style.display = "none";
    let inputForm = document.querySelectorAll(".movie-form input");
    inputForm.forEach(input => {
        input.value = "";
    });
    testDisplay();
});

// Event listener to get to the second page of the form
const movieForm = document.getElementById("manual-movie-form");
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
        addButton.style.display = "block";
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

// search button functionality
const searchForm = document.querySelector(".movie-form.first search form");
const searchInput = searchForm.querySelector("input");
const resultsContainer = document.getElementById("search-results");
searchForm.addEventListener('submit', async(e) => {
    e.preventDefault();
    
    const query = searchInput.value.trim();
    if(!query) return; // returns nothing if input is empty

    try {
        const response = await fetch(`${BASE_URL}/search?query=${query}`); //gets response object
        if(!response.ok) throw new Error('Failed!');
        const data = await response.json(); // turns it to json
        console.log(data);

        displaySearchResults(data); //updates front-end
    } catch(error) {
        console.error('Search failed: ', error);
        resultsContainer.innerHTML = '<p>Search failed. Please try again.</p>'
    }

    searchInput.value = "";
});

function displaySearchResults(movieList) {
    const listul = resultsContainer.querySelector("ul");
    console.log(listul);
    console.log(resultsContainer);
    listul.innerHTML = "";
    for(let i = 0; i < movieList.length; i++) {
        listul.insertAdjacentHTML('beforeend', 
            `
            <li>
            <img src="https://image.tmdb.org/t/p/w185/${movieList[i].poster}" alt="${movieList[i].name}">
            <p>${movieList[i].name}</p>
            </li>
            `);
        // if(i % 7 == 0) {
        //     // stop until next page button is triggered
        // }
    }
}

function testDisplay() {
    const movieTest = [
        {name: "Batmaan: Movie 1", poster: "https://image.tmdb.org/t/p/w92/cij4dd21v2Rk2YtUQbV5kW69WB2.jpg"},
        {name: "Batman: Movie 2", poster: "https://image.tmdb.org/t/p/original/cij4dd21v2Rk2YtUQbV5kW69WB2.jpg"}
    ];
    displaySearchResults(movieTest);
}
