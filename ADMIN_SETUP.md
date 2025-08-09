# CONFIGURAÇÃO DO ADMINISTRADOR

## ✅ ADMINISTRADOR CONFIGURADO: znoogh

O sistema já está configurado com **znoogh** como administrador.

## Como funciona:

### 1. Privilégios do Administrador (znoogh):
✅ **Deletar qualquer comentário** - Botão 🗑️ aparece em todos os comentários  
✅ **Botão diferenciado** - Cor laranja para identificar privilégios de admin  
✅ **Não pode editar** - Apenas deletar comentários de outros usuários  
✅ **Pode editar próprios** - Comentários próprios têm botões de editar e deletar normais  

### 2. Configuração Atual:

**Backend:** `src/main/java/code/friendplaylist/services/CommentService.java`
```java
private static final String ADMIN_USER_ID = "znoogh"; // ID do administrador znoogh
```

**Frontend:** `src/main/resources/static/track.html`
```javascript
const ADMIN_USER_ID = "znoogh"; // ID do administrador znoogh
```

## Exemplo Visual:

**Seus comentários (znoogh):**
```
[Foto] znoogh                      ✏️ 🗑️
       Seu comentário...
```

**Comentários de outros (como admin):**
```
[Foto] Outro Usuário               🗑️ (laranja)
       Comentário de outro usuário...
```

**Comentários de outros (usuário normal):**
```
[Foto] Outro Usuário               
       Comentário de outro usuário...
```

## Para alterar o administrador:

Se precisar alterar o administrador no futuro:

### 1. Obter ID do novo admin:
1. Novo admin faz login no sistema
2. Acessa: `http://localhost:8081/user/id`
3. Copia o ID que aparecer

### 2. Atualizar Backend:
```java
// Linha 20 em CommentService.java
private static final String ADMIN_USER_ID = "novo_id_aqui";
```

### 3. Atualizar Frontend:
```javascript
// Linha 52 em track.html
const ADMIN_USER_ID = "novo_id_aqui";
```

### 4. Recompilar:
```bash
./mvnw clean package -DskipTests
```

---

**🎉 Sistema pronto! znoogh tem controle total sobre comentários.**