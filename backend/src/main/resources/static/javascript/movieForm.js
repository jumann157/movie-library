const blurOverlay = document.querySelector(".blur-background");
const movieFormOne = document.querySelector(".movie-form.first");
const movieFormTwo = document.querySelector(".movie-form.second");
const sideMenu = document.querySelector("#description-side-menu");
const movieCard = document.querySelectorAll(".movie-card");
const BASE_URL = 'http://localhost:8080';


// Event listener for the + button
const addButton = document.querySelector("#button-container button");
addButton.addEventListener('click',  e => {
    blurOverlay.style.display = "block";
    movieFormOne.style.display = "block";
    movieFormTwo.style.display = "none";
    addButton.style.display = "none";
    resultsContainer.style.display = "none";
    let inputForm = document.querySelectorAll(".movie-form input");
    inputForm.forEach(input => {
        input.value = "";
    });
    // testDisplay();
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
        closeForm();
    });
});

// Opens movie description sidebar
movieCard.forEach(card => {
    card.addEventListener('click', e => {
        sideMenu.style.display = "block";
    });
});

// event listen to close sideMenu
const sideMenuCloseIcon = document.querySelector("#description-side-menu .fa-circle-xmark");
sideMenuCloseIcon.addEventListener('click', e => {
    sideMenu.style.display = "none";
    
});

// closes movie form
function closeForm() {
    blurOverlay.style.display = "none";
    movieFormOne.style.display = "none";
    movieFormTwo.style.display = "none";
    addButton.style.display = "block";
}

// search button functionality
const searchForm = document.getElementById("search-form");
const searchInput = searchForm.querySelector("input");
const resultsContainer = document.getElementById("search-results");
// fetch results
searchForm.addEventListener('submit', async(e) => {
    e.preventDefault();
    
    const query = searchInput.value.trim();
    if(!query) return; // returns nothing if input is empty

    try {
        const response = await fetch(`${BASE_URL}/search?query=${query}`); //gets response object
        if(!response.ok) throw new Error('Failed!');
        const data = await response.json(); // turns it to json
        // console.log(data);

        displaySearchResults(data); //updates front-end
    } catch(error) {
        console.error('Search failed: ', error);
        resultsContainer.innerHTML = '<p>Search failed. Please try again.</p>'
    }

    searchInput.value = "";
});

// display results
function displaySearchResults(movieList) {
    resultsContainer.style.display = "block";
    const listul = resultsContainer.querySelector("ul");
    listul.innerHTML = "";
    for(let i = 0; i < movieList.results.length; i++) {
        const movie = movieList.results[i];
        const poster = movie.poster_path 
        ? `<img src="https://image.tmdb.org/t/p/original/${movie.poster_path}" alt="${movie.original_title}"></img>`
        : `<div style="height: 80px; width: 60px; border: 1px solid black"> <p style="margin: 0; font-size: 10px;">Poster not available</p> </div>`;
        listul.insertAdjacentHTML('beforeend', 
            // store movie data in data attributes
            `
            <li
            data-tmdb-id="${movie.id}"
            data-title="${movie.original_title}"
            data-overview="${movie.overview || ''}"
            data-poster-path="${movie.poster_path || ''}"
            data-release-date="${movie.release_date || ''}"
            class="movie-results"
            >
            ${poster}
            <p>${movie.original_title} (${movie.release_date})</p>
            </li>
            `
        );
    }
    resultsContainer.style.display = "block";
}

// user feedback on movie duplicates in library
function displayAlreadyExistsAlertInSearchResults(target) {
    const p = target.querySelector('p');
    p.insertAdjacentHTML('beforeend', '<div class="search-alert">Already exists in your library</div>');
    const alert = document.querySelector('.search-alert');
    // const intervalID = setInterval(() => {fadeOut(alert, intervalID)}, 1000);
}

function fadeOut(element, intervalID) {
    var elementOp = parseFloat(window.getComputedStyle(element).getPropertyValue("opacity"));
    console.log(elementOp);
    if(elementOp > 0) {
        element.style.opacity -= 0.1;
    } else {
        clearInterval(intervalID);
        element.remove();
    }
}

// event listener on target movie search results 
resultsContainer.addEventListener('click', event => {
const target = event.target.closest("li");
if(target) {
    console.log(target)
    sendMovieToBackend(target); // add it to database
}
});

// adds movie to library
function addMovieToLibrary(movie) {
    const moviePoster = movie.querySelector("img");
    moviePoster.className = "poster";
    const movieTitle = movie.querySelector("p");
    movieTitle.className = "caption";
    // get div container
    const libraryContainer = document.getElementById("library-container");
    // create div
    const movieCard = document.createElement("div");
    movieCard.className = "movie-card";
    movieCard.appendChild(moviePoster.cloneNode(true));
    // movieCard.appendChild(movieTitle.cloneNode(true));
    // add movie to libraryContainer
    libraryContainer.appendChild(movieCard);
    closeForm();
}

async function sendMovieToBackend(movieElement) {
    // Extract data from data attributes
    const movieData = {
        id: movieElement.dataset.tmdbId,
        original_title: movieElement.dataset.title,
        overview: movieElement.dataset.overview,
        poster_path: movieElement.dataset.posterPath,
        release_date: movieElement.dataset.releaseDate
    }

    try{
        const res = await fetch(`${BASE_URL}/add`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(movieData)
        });
        const text = await res.text();
    
        if(res.status == 500) {
            console.error(`${res.status}: ${text} , ${res.statusText}`);
            return;
        }
        if(res.status == 400) {
            displayAlreadyExistsAlertInSearchResults(movieElement);
            console.info(`${res.status}: ${text} , ${res.statusText}`);
            return;
        }
        addMovieToLibrary(movieElement);
    } catch(error) {
        console.error('Error adding movie to the library: ', error);
    }
}

function testDisplay() {
    const movieTest = [
        {name: "Batmaan: Movie 1", poster: "https://image.tmdb.org/t/p/original/cij4dd21v2Rk2YtUQbV5kW69WB2.jpg"},
        {name: "Batman: Movie 2", poster: "https://image.tmdb.org/t/p/original/cij4dd21v2Rk2YtUQbV5kW69WB2.jpg"}
    ];
    displaySearchResults(movieTest);
}
