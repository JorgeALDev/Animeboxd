# Animeboxd

<p align="center">
  <img src="https://raw.githubusercontent.com/JorgeALDev/Animeboxd/main/README/banner.png" alt="Animeboxd Banner">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-100%25-7F52FF?logo=kotlin" alt="Kotlin Badge"/>
  <img src="https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4" alt="Compose Badge"/>
  <img src="https://img.shields.io/badge/Room-Database-3DDC84" alt="Room Badge"/>
  <img src="https://img.shields.io/badge/MVVM-Architecture-orange" alt="MVVM Badge"/>
  <img src="https://img.shields.io/badge/Platform-Android-green" alt="Android Badge"/>
</p>

## Sobre o Projeto

O **Animeboxd** é um aplicativo Android inspirado no Letterboxd, desenvolvido utilizando **Kotlin**, **Jetpack Compose** e **Room**.

O aplicativo permite explorar um catálogo de animes, organizar títulos em uma lista pessoal, alterar seu status de acompanhamento e visualizar estatísticas relacionadas ao progresso do usuário.

O projeto foi desenvolvido com foco em conceitos modernos do desenvolvimento Android, incluindo arquitetura **MVVM**, persistência local de dados e interfaces declarativas.

---

## Funcionalidades

* Catálogo de animes com busca por nome ou gênero
* Adição de animes à lista pessoal
* Alteração de status (Assistindo, Concluído e Pausado)
* Remoção de animes da biblioteca pessoal
* Estatísticas de acompanhamento
* Persistência local utilizando Room
* Atualização reativa com Flow

---

## Tecnologias Utilizadas

* Kotlin
* Jetpack Compose
* Material 3
* Room Database
* Coroutines
* Flow
* MVVM
* Coil

---

## Estrutura do Projeto

```text
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
│   ├── catalog/
│   ├── home/
│   ├── mylist/
│   └── navigation/
│
└── ui/
    └── theme/
```

---

## Capturas de Tela

### Tela Inicial

<img src="https://raw.githubusercontent.com/JorgeALDev/Animeboxd/main/README/screenshots/home.png" width="250">

### Catálogo

<img src="https://raw.githubusercontent.com/JorgeALDev/Animeboxd/main/README/screenshots/catalog.png" width="250">

### Minha Lista

<img src="https://raw.githubusercontent.com/JorgeALDev/Animeboxd/main/README/screenshots/mylist.png" width="250">

### Estatísticas

<img src="https://raw.githubusercontent.com/JorgeALDev/Animeboxd/main/README/screenshots/stats.png" width="250">

---

## Como Executar

1. Clone o repositório

```bash
git clone https://github.com/JorgeALDev/Animeboxd.git
```

2. Abra o projeto no Android Studio

3. Aguarde a sincronização do Gradle

4. Execute em um emulador ou dispositivo Android

---

## Desenvolvido por

**Jorge Alves**

GitHub: https://github.com/JorgeALDev/Animeboxd
