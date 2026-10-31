const jokeBox = document.getElementById("jokeBox");
const newJokeBtn = document.getElementById("newJokeBtn");
const copyBtn = document.getElementById("copyBtn");

async function fetchJoke() {
  jokeBox.innerHTML = "<p>Loading a joke...</p>";

  try {
    const response = await fetch("https://v2.jokeapi.dev/joke/Any?type=single");
    const data = await response.json();

    if (data.error) {
      throw new Error("Could not fetch a joke");
    }

    jokeBox.innerHTML = `<p>${data.joke}</p>`;
  } catch (error) {
    jokeBox.innerHTML = "<p>Oops! Could not load a joke. Try again.</p>";
    console.error(error);
  }
}

newJokeBtn.addEventListener("click", fetchJoke);

copyBtn.addEventListener("click", async () => {
  const jokeText = jokeBox.textContent.trim();
  if (!jokeText) return;

  try {
    await navigator.clipboard.writeText(jokeText);
    copyBtn.textContent = "Copied!";
    setTimeout(() => {
      copyBtn.textContent = "Copy";
    }, 1200);
  } catch (error) {
    console.error("Copy failed:", error);
  }
});

fetchJoke();
