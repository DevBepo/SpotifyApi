document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('user-search-input');
    const searchResultsContainer = document.getElementById('search-results-container');

    searchInput.addEventListener('input', function () {
        const query = this.value.trim();

        if (query.length < 2) {
            searchResultsContainer.innerHTML = '';
            searchResultsContainer.style.display = 'none';
            return;
        }

        fetch(`/user/search?q=${query}`)
            .then(response => response.json())
            .then(data => {
                searchResultsContainer.innerHTML = '';
                if (data.length > 0) {
                    searchResultsContainer.style.display = 'block';
                    data.forEach(user => {
                        const userElement = document.createElement('div');
                        userElement.classList.add('search-result-item');
                        
                        const userImage = document.createElement('img');
                        userImage.src = user.imageUrl || 'default-profile.png'; 
                        userImage.alt = `Foto de ${user.displayName}`;
                        userImage.classList.add('search-result-image');

                        const userInfo = document.createElement('div');
                        userInfo.classList.add('search-result-info');
                        
                        const userName = document.createElement('p');
                        userName.textContent = user.displayName;
                        
                        userInfo.appendChild(userName);
                        userElement.appendChild(userImage);
                        userElement.appendChild(userInfo);

                        // Redireciona ao clicar no usuário para playlists.html com user_id como parâmetro
                        userElement.addEventListener('click', function() {
                            window.location.href = `playlists.html?user_id=${encodeURIComponent(user.spotifyUserId || user.id)}&userName=${encodeURIComponent(user.displayName)}`;
                        });

                        searchResultsContainer.appendChild(userElement);
                    });
                } else {
                    searchResultsContainer.style.display = 'none';
                }
            })
            .catch(error => {
                console.error('Erro ao buscar usuários:', error);
                searchResultsContainer.style.display = 'none';
            });
    });
});
