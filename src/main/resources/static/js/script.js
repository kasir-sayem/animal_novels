// Custom JavaScript for the animal characters website

// Wait for the DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    // Initialize Bootstrap tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
    
    // Add event listener for the search functionality
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('keyup', function() {
            const searchValue = this.value.toLowerCase();
            const cards = document.querySelectorAll('.card');
            
            cards.forEach(function(card) {
                const title = card.querySelector('.card-title').textContent.toLowerCase();
                const subtitle = card.querySelector('.card-subtitle') ? 
                    card.querySelector('.card-subtitle').textContent.toLowerCase() : '';
                
                if (title.includes(searchValue) || subtitle.includes(searchValue)) {
                    card.style.display = '';
                } else {
                    card.style.display = 'none';
                }
            });
        });
    }
    
    // Add animation for messages
    const messageAlerts = document.querySelectorAll('.alert');
    messageAlerts.forEach(function(alert) {
        setTimeout(function() {
            if (alert) {
                const bsAlert = new bootstrap.Alert(alert);
                bsAlert.close();
            }
        }, 5000);
    });
});