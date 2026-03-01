# 📚 Livros Firebase

App Android de gerenciamento de livros com autenticação de usuário e banco de dados em tempo real, desenvolvido com **Kotlin**, **Jetpack Compose** e **Firebase**.

---

## 📱 Funcionalidades

- 🔐 **Login e cadastro** de usuário com Firebase Authentication
- ➕ **Cadastrar** livros com título, autor, ano e gênero
- 📋 **Listar** todos os livros cadastrados em tempo real
- ✏️ **Editar** informações de um livro existente
- 🗑️ **Excluir** livros da lista
- ☁️ Dados sincronizados na nuvem via **Cloud Firestore**

---

## 🛠️ Tecnologias

| Tecnologia | Uso |
|---|---|
| Kotlin | Linguagem principal |
| Jetpack Compose | Interface declarativa |
| Firebase Authentication | Login e cadastro de usuários |
| Cloud Firestore | Banco de dados NoSQL em tempo real |
| Material 3 | Design system |

---

## 🗂️ Estrutura do Projeto

```
app/
└── src/main/java/
    └── com.example.livrosfirebase/
        ├── MainActivity.kt       → Entrada do app e navegação
        ├── LoginScreen.kt        → Tela de login/cadastro
        ├── LivrosScreen.kt       → Lista de livros
        └── CadastroLivroScreen.kt → Cadastro e edição de livros
```

---

## 📋 Campos de um Livro

| Campo | Tipo |
|---|---|
| Título | Texto |
| Autor | Texto |
| Ano | Número |
| Gênero | Texto |

---

## ⚙️ Como rodar o projeto

### Pré-requisitos
- Android Studio instalado
- Conta no [Firebase Console](https://console.firebase.google.com/)

### Passos

1. Clone o repositório:
```bash
git clone https://github.com/Pavani9190/Livros-Firebase.git
```

2. Abra a pasta no **Android Studio**

3. No [Firebase Console](https://console.firebase.google.com/), crie um projeto e:
   - Ative o **Authentication** (método: Email/Senha)
   - Ative o **Cloud Firestore**
   - Faça o download do arquivo `google-services.json`

4. Cole o `google-services.json` dentro da pasta `app/`

5. Sincronize o Gradle e execute o app em um emulador ou dispositivo (Android 7.0+)

---

## 💡 Aprendizados

Este foi meu primeiro projeto integrando Firebase ao Android. Os principais desafios e aprendizados foram:

- Configurar Firebase Authentication para login com email e senha
- Estruturar coleções no Cloud Firestore e realizar operações de leitura, escrita, atualização e exclusão (CRUD)
- Integrar dados em tempo real do Firestore com o estado do Jetpack Compose
- Gerenciar navegação entre telas autenticadas e não autenticadas

---

## 👨‍💻 Autor
**Gustavo Pavani**
<br><br>
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://linkedin.com/in/gustavo-pavani)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=flat&logo=github&logoColor=white)](https://github.com/Pavani9190)
