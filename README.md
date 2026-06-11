# Animeboxd

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-100%25-7F52FF?logo=kotlin" alt="Kotlin Badge"/>
  <img src="https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4" alt="Compose Badge"/>
  <img src="https://img.shields.io/badge/Room-Database-3DDC84" alt="Room Badge"/>
  <img src="https://img.shields.io/badge/MVVM-Architecture-orange" alt="MVVM Badge"/>
  <img src="https://img.shields.io/badge/Platform-Android-green" alt="Android Badge"/>
</p>

## Sobre o Projeto

O **Animeboxd** é um aplicativo Android inspirado no Letterboxd, desenvolvido utilizando **Kotlin**, **Jetpack Compose** e **Room**. O aplicativo permite explorar um catálogo de animes, organizar títulos em uma lista pessoal, alterar seu status de acompanhamento e visualizar estatísticas relacionadas ao progresso do usuário.

O projeto foi desenvolvido com foco em conceitos modernos do desenvolvimento Android, incluindo arquitetura **MVVM**, persistência local de dados e interfaces declarativas.

## Funcionalidades

- Catálogo de animes com busca por nome ou gênero
- Adição e remoção de animes da lista pessoal
- Alteração de status (Assistindo, Concluído, Pausado)
- Estatísticas de tempo total assistido (apenas animes concluídos)
- Persistência local utilizando Room
- Atualização reativa com Flow

## Tecnologias Utilizadas

- Kotlin
- Jetpack Compose
- Material 3
- Room Database
- Coroutines
- Flow
- MVVM
- Coil

## Estrutura do Projeto

```
app/src/main/java/com/jorge/animeboxd/

├── data/
│   ├── local/
│   │   ├── AnimeDao.kt
│   │   ├── AnimeEntity.kt
│   │   └── AnimeDatabase.kt
│   │
│   └── repository/
│       └── AnimeRepository.kt
│
├── domain/
│   └── model/
│
├── presentation/
│   ├── catalogo/
│   ├── home/
│   ├── minhaLista/
│   └── navigation/
│
└── ui/
    └── theme/
```
    
## Capturas de Tela

### Tela Inicial

<img src="https://raw.githubusercontent.com/JorgeALDev/Animeboxd/main/home.jpeg" width="250">

### Catálogo

<img src="https://raw.githubusercontent.com/JorgeALDev/Animeboxd/main/catalogo.jpeg" width="250">

### Minha Lista

<img src="https://raw.githubusercontent.com/JorgeALDev/Animeboxd/main/minhaLista.jpeg" width="250">


## Como Executar

1.Clone o repositório:
git clone https://github.com/JorgeALDev/Animeboxd.git

2.Abra o projeto no Android Studio

3.Aguarde a sincronização do Gradle

4.Execute em um emulador ou dispositivo Android

Desenvolvido por
Jorge Araújo

GitHub: https://github.com/JorgeALDev
