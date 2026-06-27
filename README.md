# Animeboxd

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-100%25-7F52FF?logo=kotlin" alt="Kotlin Badge"/>
  <img src="https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4" alt="Compose Badge"/>
  <img src="https://img.shields.io/badge/Room-Database-3DDC84" alt="Room Badge"/>
  <img src="https://img.shields.io/badge/MVVM-Architecture-orange" alt="MVVM Badge"/>
  <img src="https://img.shields.io/badge/Platform-Android-green" alt="Android Badge"/>
</p>

## Sobre o Projeto

O **Animeboxd** é um aplicativo Android inspirado no Letterboxd, desenvolvido utilizando **Kotlin**, **Jetpack Compose**, **Room** e **Retrofit**.

O aplicativo permite explorar um catálogo de animes consumindo dados da **Jikan API** (MyAnimeList), organizar títulos em uma lista pessoal, alterar seu status de acompanhamento e visualizar estatísticas relacionadas ao progresso do usuário.

O projeto foi desenvolvido com foco em conceitos modernos do desenvolvimento Android, incluindo arquitetura **MVVM**, persistência local de dados, consumo de APIs REST e interfaces declarativas.

---

## Funcionalidades

- **Autenticação (Login Fake)**: validação simples com `if/else` (usuário: `admin`, senha: `admin`), com persistência via SharedPreferences.
- **Catálogo de animes**: listagem dos animes mais populares com **paginação** (10 itens por página) e busca por nome ou gênero.
- **Integração com API**: consumo da **Jikan API** (MyAnimeList) utilizando **Retrofit** e **Gson**.
- **Lista pessoal**: adição e remoção de animes com persistência local via **Room Database**.
- **Alteração de status**: marque animes como "Assistindo", "Concluído" ou "Pausado".
- **Estatísticas**: total de animes na lista e horas assistidas (apenas animes concluídos).
- **Atualização reativa**: uso de **StateFlow** e **Flow** para sincronizar a UI com os dados.

---

## Tecnologias Utilizadas

- **Kotlin** – Linguagem principal.
- **Jetpack Compose** – UI declarativa.
- **Material 3** – Componentes visuais modernos.
- **Room Database** – Persistência local.
- **Retrofit + OkHttp** – Consumo de API REST.
- **Gson** – Serialização/deserialização JSON.
- **Coroutines e Flow** – Programação assíncrona e reatividade.
- **MVVM** – Arquitetura de separação de responsabilidades.
- **Coil** – Carregamento de imagens.

---

## Estrutura do Projeto

```
app/src/main/java/com/jorge/animeboxd/

├── data/
│   ├── local/               # Room (Entity, DAO, Database)
│   ├── remote/              # Retrofit (API, Models, Client)
│   └── repository/          # AnimeRepository (abstração de dados)
│
├── domain/
│   └── model/               # Modelos de domínio (Anime)
│
├── presentation/
│   ├── catalogo/            # Tela de catálogo (ViewModel + Screen)
│   ├── home/                # Tela inicial (HomeScreen)
│   ├── minhaLista/          # Tela da lista pessoal (ViewModel + Screen)
│   ├── login/               # Tela de login (Screen)
│   └── navigation/          # AppNavGraph e Screen
│
├── ui/
│   └── theme/               # Cores, componentes e tema
│
└── MainActivity.kt          # Ponto de entrada do app
```

## Capturas de Tela

### Tela de Login

<img src="https://raw.githubusercontent.com/JorgeALDev/Animeboxd/main/README/screenshots/login.png" width="250">

### Tela Inicial

<img src="https://raw.githubusercontent.com/JorgeALDev/Animeboxd/main/README/screenshots/home.png" width="250">

### Catálogo

<img src="https://raw.githubusercontent.com/JorgeALDev/Animeboxd/main/README/screenshots/catalog.png" width="250">

### Minha Lista

<img src="https://raw.githubusercontent.com/JorgeALDev/Animeboxd/main/README/screenshots/mylist.png" width="250">

### Como Executar
1 - Clone o repositório

git clone https://github.com/JorgeALDev/Animeboxd.git

2 - Abra o projeto no Android Studio

Aguarde a sincronização do Gradle

3 - Execute em um emulador ou dispositivo Android (API 24+)


## Credenciais de Login
Usuário: admin
Senha: admin

Desenvolvido por
Jorge Araújo
GitHub: https://github.com/JorgeALDev