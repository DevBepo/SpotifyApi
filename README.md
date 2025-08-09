# FriendPlaylist 🎵

Uma aplicação web para visualizar, avaliar e comentar playlists do Spotify com seus amigos.

## 🚀 Funcionalidades

- **Autenticação OAuth2** com Spotify
- **Visualização de Playlists** - Veja suas playlists e de outros usuários
- **Sistema de Avaliações** - Avalie playlists de 1 a 5 estrelas
- **Sistema de Comentários** - Comente e interaja com as playlists
- **Administração** - Sistema de moderação de comentários
- **API REST** - Endpoints documentados com Swagger

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21**
- **Spring Boot 3.3.1**
- **Spring Security** (OAuth2)
- **Spring Data JPA**
- **PostgreSQL**
- **OpenFeign** (integração com Spotify API)
- **SpringDoc OpenAPI** (Swagger)

### Frontend
- **HTML5, CSS3, JavaScript**
- **Interface responsiva**

### DevOps
- **Docker & Docker Compose**
- **Deploy no Render**

## 📚 Documentação da API

A documentação completa da API está disponível através do Swagger UI:

### Desenvolvimento
```
http://localhost:8081/swagger-ui.html
```

### Produção
```
https://friendplaylist-app.onrender.com/swagger-ui.html
```

### Endpoints da API JSON
```
http://localhost:8081/v3/api-docs
```

## 🏃‍♂️ Como Executar

### Pré-requisitos
- Java 21
- Maven 3.6+
- PostgreSQL
- Conta de desenvolvedor no Spotify

### Configuração do Spotify
1. Acesse o [Spotify Developer Dashboard](https://developer.spotify.com/dashboard)
2. Crie uma nova aplicação
3. Configure as URLs de redirecionamento:
   - Desenvolvimento: `http://localhost:8081/login/oauth2/code/spotify`
   - Produção: `https://seu-dominio.com/login/oauth2/code/spotify`

### Variáveis de Ambiente
```bash
export SPOTIFY_CLIENT_ID=seu_client_id
export SPOTIFY_CLIENT_SECRET=seu_client_secret
```

### Executando com Docker
```bash
docker-compose up -d
```

### Executando localmente
```bash
mvn spring-boot:run
```

## 📖 Estrutura da API

### Endpoints Principais

#### 🎵 Playlists
- `GET /playlists` - Listar playlists do usuário atual
- `GET /users/{user_id}/playlists` - Listar playlists de um usuário
- `GET /api/playlists/{playlist_id}/tracks` - Obter faixas de uma playlist

#### 💬 Comentários
- `GET /api/playlists/{playlistId}/comments` - Listar comentários
- `POST /api/playlists/{playlistId}/comments` - Adicionar comentário
- `PUT /api/playlists/{playlistId}/comments/{commentId}` - Editar comentário
- `DELETE /api/playlists/{playlistId}/comments/{commentId}` - Deletar comentário

#### ⭐ Avaliações
- `POST /api/playlists/{playlistId}/rate` - Avaliar playlist
- `GET /api/playlists/{playlistId}/rate` - Obter avaliação do usuário
- `GET /api/playlists/{playlistId}/average` - Obter média de avaliações

#### 👤 Usuários
- `GET /user/me` - Dados do usuário atual
- `GET /user/auth-status` - Status de autenticação
- `GET /user/search` - Buscar usuários
- `GET /user/id` - ID do usuário atual

## 🔐 Autenticação

A aplicação utiliza OAuth2 com Spotify. Todos os endpoints da API (exceto alguns públicos) requerem autenticação.

### Fluxo de Autenticação
1. Usuário clica em "Login com Spotify"
2. Redirecionamento para autorização do Spotify
3. Callback com código de autorização
4. Troca do código por token de acesso
5. Acesso aos recursos da API

## 👨‍💼 Sistema de Administração

O sistema possui um administrador configurado que pode:
- ✅ Deletar qualquer comentário
- ✅ Botão diferenciado (cor laranja)
- ❌ Não pode editar comentários de outros usuários

Para configurar um novo administrador, consulte o arquivo `ADMIN_SETUP.md`.

## 🚀 Deploy

A aplicação está configurada para deploy no Render com:
- Build automático via Docker
- Variáveis de ambiente configuradas
- PostgreSQL como banco de dados


## 🤝 Contribuição

Contribuições são bem-vindas! Por favor:
1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📞 Contato

- **Desenvolvedor**: Jonas Lima 
- **Email**: jonaslimadomingues@gmail.com
- **LinkedIn**: [Seu LinkedIn](https://www.linkedin.com/in/devjonas/)

---

⭐ Se este projeto te ajudou, considere dar uma estrela no repositório!