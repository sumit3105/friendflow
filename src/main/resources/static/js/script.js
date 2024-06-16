document.addEventListener("DOMContentLoaded", function() {
	var spinner = document.getElementById("loadingSpinner");
	spinner.style.display = "block";
});

window.addEventListener("load", function() {
	var spinner = document.getElementById("loadingSpinner");
	spinner.style.display = "none";
});

// Get the modal
var modal = document.getElementById('comment');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}