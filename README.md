# AniCatalog

<img src="https://img.shields.io/badge/Kotlin-100%25-7F52FF?logo=kotlin" alt="Kotlin Badge"/>
<img src="https://img.shields.io/badge/Compose-UI-blue" alt="Jetpack Compose Badge"/>
<img src="https://img.shields.io/badge/Platform-Android-green" alt="Android Badge"/>

## Sobre o Projeto

O **AniCatalog** é um aplicativo Android desenvolvido em Kotlin utilizando Jetpack Compose. Seu objetivo é simular um catálogo de animes com tela de login simples e, após o acesso, apresentar uma lista interativa de animes exibidos em cards com imagem, título, gênero, nota e sinopse.

O projeto foi criado como atividade acadêmica para aplicar conceitos fundamentais do desenvolvimento Android moderno como interfaces declarativas, navegação entre telas, modularização e uso de listas dinâmicas.

---

## Objetivos e Requisitos Atendidos

Este projeto atende às principais exigências propostas na atividade, incluindo:

- Utilização da **linguagem Kotlin**
- Criação de interface com **Jetpack Compose**
- Tela de login (usuário e senha)
- Navegação entre telas usando **Intents**
- Listagem dinâmica de animes (**LazyColumn** / equivalente moderno ao RecyclerView)
- Separação do código em múltiplos arquivos/componentes
- Aplicação de **tema personalizado** (Material 3)
- Estrutura de dados/modelo separado
- Carregamento de imagens via internet com **Coil**
- Separação clara de responsabilidades entre telas e componentes

---

## Funcionalidades

- **Tela de Login:** Campos para usuário e senha + botão de entrada
- **Navegação:** Login redireciona para a tela principal do catálogo
- **Tela Principal:** Exibe lista vertical de animes em formato de cards
- **Card de Anime:** Imagem, título, gênero, nota, sinopse
- **Botão de Sair:** Retorna para tela de login
- Estrutura de dados modularizada (`Animes.kt`)
- Carregamento de imagens a partir da web (Coil)
- Navegação entre telas via **Intent** clássico

---

## Tecnologias e Conceitos Utilizados

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Activities** e **Intents**
- **LazyColumn**
- **Componentização com Composables**
- **Gerenciamento de estado** (`remember`, `mutableStateOf`)
- **Coil** para imagens remotas
- **Organização modular** do código

---

## Estrutura do Projeto

```
├── MainActivity.kt     // Tela de login
├── HomeActivity.kt     // Tela do catálogo/lista
├── screens/
│   ├── LoginScreen.kt  // Composable da tela de login
│   └── HomeScreen.kt   // Composable da tela principal
├── components/
│   └── AnimeCard.kt    // Composable reutilizável do card de anime
├── data/
│   └── Animes.kt       // Modelo Anime e lista de dados
└── ui/
    └── theme/          // Tema e estilos do app
```

---

## Aprendizados e Práticas Realizadas

Durante o desenvolvimento, foram aplicados conceitos como:

- Construção de **interfaces declarativas** com Compose
- Criação de componentes **reutilizáveis** e modulares
- **Separação de responsabilidades** para maior manutenção e legibilidade
- **Navegação tradicional** entre telas (Activities + Intents)
- Utilização de listas eficientes (**LazyColumn**)
- Organização de projeto visando escalabilidade e boas práticas

---

## Considerações Finais

Este projeto é uma base exemplar para quem está iniciando com Android moderno e Jetpack Compose, podendo ser expandido futuramente com conceitos mais avançados como o uso de Navigation, ViewModel, persistência de dados, integração de API e muito mais.

Fique à vontade para explorar, sugerir melhorias ou adaptar para seus próprios estudos!

---

> Projeto desenvolvido por [JorgeALDev](https://github.com/JorgeALDev) | Atividade acadêmica.
