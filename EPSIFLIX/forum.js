document.addEventListener('DOMContentLoaded', function() {
    const messageForm = document.getElementById('messageForm');
    const messageInput = document.getElementById('messageInput');
    const messagesDiv = document.getElementById('messages');

    messageForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const messageText = messageInput.value.trim();
        if (messageText !== '') {
            const messageElement = document.createElement('div');
            messageElement.textContent = messageText;
            messagesDiv.appendChild(messageElement);
            messageInput.value = '';
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const messageForm = document.getElementById('messageForm');
    const messageInput = document.getElementById('messageInput');
    const messagesDiv = document.getElementById('messages');
    const tabs = document.getElementsByClassName('tablinks');
    const tabContents = document.getElementsByClassName('tabcontent');

    // Fonction pour ouvrir l'onglet sélectionné
    function openTab(event, tabName) {
        // Cacher tous les contenus d'onglet
        for (let i = 0; i < tabContents.length; i++) {
            tabContents[i].style.display = 'none';
        }

        // Supprimer la classe active de tous les boutons d'onglet
        for (let i = 0; i < tabs.length; i++) {
            tabs[i].classList.remove('active');
        }

        // Afficher le contenu de l'onglet sélectionné
        document.getElementById(tabName).style.display = 'block';

        // Ajouter la classe active au bouton d'onglet sélectionné
        event.currentTarget.classList.add('active');
    }

    // Ajouter un écouteur d'événements à chaque bouton d'onglet
    for (let i = 0; i < tabs.length; i++) {
        tabs[i].addEventListener('click', function(event) {
            openTab(event, tabContents[i].id);
        });
    }
});
