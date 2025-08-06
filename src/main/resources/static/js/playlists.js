const playlistContainer = document.getElementById('playlist-container');
const tracksContainer = document.getElementById('tracks-container');
const loadMoreBtn = document.getElementById('load-more-btn');
const backButton = document.getElementById('back-to-playlists-btn');
const loadingIndicator = document.getElementById('loading-indicator');
const errorMessage = document.getElementById('error-message');

const API_BASE_URL = '/playlists';
let nextUrl = API_BASE_URL;

/**
 * Converte milissegundos para o formato MM:SS.
 * @param {number} ms - Duração em milissegundos.
 * @returns {string} A duração formatada.
 */
function formatDuration(ms) {
    const minutes = Math.floor(ms / 60000);
    const seconds = ((ms % 60000) / 1000).toFixed(0);
    return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
}

/**
 * Cria o HTML para uma única música.
 * @param {object} trackItem - O item da música vindo da API.
 * @param {number} index - O número da faixa na lista.
 * @returns {HTMLElement | null} O elemento da linha da música.
 */
function createTrackRow(trackItem, index) {
    const track = trackItem.track;
    if (!track) return null; // Pula faixas que não estão disponíveis

    const row = document.createElement('div');
    row.className = 'track-row';

    const trackNumber = document.createElement('span');
    trackNumber.className = 'track-number';
    trackNumber.textContent = index;

    const trackImage = document.createElement('img');
    trackImage.className = 'track-image';
    trackImage.src = track.album.images?.[2]?.url || 'https://i.scdn.co/image/ab67616d00001e0212166260750943ad9dcdd563';

    const trackInfo = document.createElement('div');
    trackInfo.className = 'track-info';
    const trackName = document.createElement('p');
    trackName.className = 'track-name';
    trackName.textContent = track.name;
    const trackArtists = document.createElement('p');
    trackArtists.className = 'track-artists';
    trackArtists.textContent = track.artists.map(artist => artist.name).join(', ');
    trackInfo.appendChild(trackName);
    trackInfo.appendChild(trackArtists);

    const trackDuration = document.createElement('span');
    trackDuration.className = 'track-duration';
    trackDuration.textContent = formatDuration(track.duration_ms);

    row.appendChild(trackNumber);
    row.appendChild(trackImage);
    row.appendChild(trackInfo);
    row.appendChild(trackDuration);

    return row;
}

/**
 * Busca as músicas de uma playlist e as exibe na tela.
 * @param {string} tracksUrl - A URL para buscar as músicas da playlist.
 */
async function fetchAndDisplayTracks(tracksUrl) {
    playlistContainer.classList.add('hidden');
    loadMoreBtn.classList.add('hidden');
    loadingIndicator.classList.remove('hidden');
    errorMessage.classList.add('hidden');
    tracksContainer.innerHTML = ''; // Limpa músicas anteriores

    try {
        const response = await fetch(tracksUrl);
        if (response.status === 401) {
            window.location.href = '/login.html';
            return;
        }
        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.statusText}`);
        }
        const data = await response.json();

        // Adiciona um cabeçalho
        const header = document.createElement('div');
        header.className = 'track-row header';
        header.innerHTML = `<span class="track-number">#</span><p class="track-info">Título</p><span class="track-duration">🕒</span>`;
        tracksContainer.appendChild(header);

        data.items.forEach((item, index) => {
            const trackElement = createTrackRow(item, index + 1);
            if (trackElement) tracksContainer.appendChild(trackElement);
        });

    } catch (error) {
        console.error('Erro ao buscar músicas da playlist:', error);
        errorMessage.classList.remove('hidden');
    } finally {
        loadingIndicator.classList.add('hidden');
        tracksContainer.classList.remove('hidden');
        backButton.classList.remove('hidden');
    }
}

/**
 * Cria o elemento HTML para um card de playlist.
 * @param {object} playlist - O objeto da playlist vindo da API.
 * @returns {HTMLElement} O elemento do card pronto.
 */
function createPlaylistCard(playlist) {
    const card = document.createElement('div');
    card.className = 'playlist-card';

    // Adiciona o evento de clique para buscar as músicas
    card.addEventListener('click', () => {
        window.location.href = `/track.html?playlistId=${playlist.id}`;
    });

    const image = document.createElement('img');
    image.src = playlist.images?.[0]?.url || 'https://i.scdn.co/image/ab67616d00001e0212166260750943ad9dcdd563';
    image.alt = playlist.name;

    const title = document.createElement('h3');
    title.textContent = playlist.name;

    const details = document.createElement('p');
    details.textContent = `${playlist.tracks.total} músicas • Por ${playlist.owner.display_name}`;

    card.appendChild(image);
    card.appendChild(title);
    card.appendChild(details);

    return card;
}

/**
 * Busca os dados da API e renderiza os cards de playlist.
 * @param {string} url - A URL do endpoint a ser chamado.
 */
async function fetchPlaylists(url) {
    if (!url) return;

    loadingIndicator.classList.remove('hidden');
    errorMessage.classList.add('hidden');
    loadMoreBtn.classList.add('hidden');

    try {
        const response = await fetch(url);
        if (response.status === 401) {
            window.location.href = '/login.html';
            return;
        }
        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.statusText}`);
        }
        const data = await response.json();

        data.items.forEach(playlist => {
            const cardElement = createPlaylistCard(playlist);
            playlistContainer.appendChild(cardElement);
        });

        nextUrl = data.next;

        if (!nextUrl) {
            loadMoreBtn.classList.add('hidden');
        } else {
            loadMoreBtn.classList.remove('hidden');
        }

    } catch (error) {
        console.error('Erro ao buscar playlists:', error);
        errorMessage.classList.remove('hidden');
    } finally {
        loadingIndicator.classList.add('hidden');
    }
}

// Event listener para o botão "Voltar"
backButton.addEventListener('click', () => {
    tracksContainer.classList.add('hidden');
    backButton.classList.add('hidden');
    playlistContainer.classList.remove('hidden');
    if (nextUrl) {
        loadMoreBtn.classList.remove('hidden');
    }
});

// Event listener para o botão "Carregar Mais"
loadMoreBtn.addEventListener('click', () => fetchPlaylists(nextUrl));

/**
 * Helper para obter parâmetros da URL
 * @param {string} param - Nome do parâmetro
 * @returns {string | null} Valor do parâmetro ou null
 */
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

// Ajuste do carregamento inicial
document.addEventListener('DOMContentLoaded', () => {
    const userId = getQueryParam('user');
    const userName = getQueryParam('userName') || userId; // Fallback para userId se userName não estiver disponível
    let initialUrl;

    // Atualizar o título da página
    const headerTitle = document.querySelector('.main-header h1');
    if (userId && headerTitle) {
        headerTitle.textContent = userName ? `Playlists de ${userName}` : `Playlists do usuário ${userId}`;
    }

    if (userId) {
        initialUrl = `/users/${encodeURIComponent(userId)}/playlists`;
    } else {
        initialUrl = API_BASE_URL;
    }
    fetchPlaylists(initialUrl);
});

document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById('user-search-input');
    const resultsContainer = document.getElementById('search-results-container');

    let searchTimeout;

    searchInput.addEventListener('input', () => {
        // Cancela a busca anterior para não sobrecarregar o servidor
        clearTimeout(searchTimeout);

        const query = searchInput.value.trim();

        if (query.length < 2) {
            resultsContainer.innerHTML = '';
            resultsContainer.style.display = 'none';
            return;
        }

        searchTimeout = setTimeout(async () => {
            try {
                const response = await fetch(`/api/users/search?q=${query}`);
                if (!response.ok) return;

                const users = await response.json();

                resultsContainer.innerHTML = ''; // Limpa resultados antigos

                if (users.length === 0) {
                    resultsContainer.innerHTML = '<p style="padding: 10px; text-align: center; color: #b3b3b3;">Nenhum usuário encontrado.</p>';
                } else {
                    users.forEach(user => {
                        const userElement = document.createElement('a');
                        // Passo 3: O link que leva para as playlists do usuário
                        userElement.href = `/playlists.html?user=${user.spotifyUserId}&userName=${encodeURIComponent(user.displayName)}`;
                        userElement.className = 'search-result-item';
                        userElement.innerHTML = `
                            <img src="${user.imageUrl}" alt="${user.displayName}">
                            <span>${user.displayName}</span>
                        `;
                        resultsContainer.appendChild(userElement);
                    });
                }
                resultsContainer.style.display = 'block';

            } catch (error) {
                console.error("Erro ao buscar usuários:", error);
            }
        }, 300);
    });

    // Esconde os resultados se clicar fora
    document.addEventListener('click', (event) => {
        if (!searchInput.contains(event.target)) {
            resultsContainer.style.display = 'none';
        }
    });
});