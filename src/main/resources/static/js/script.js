document.addEventListener("DOMContentLoaded", function() {
	var spinner = document.getElementById("loadingSpinner");
	spinner.style.display = "block";
});

window.addEventListener("load", function() {
	var spinner = document.getElementById("loadingSpinner");
	spinner.style.display = "none";
});