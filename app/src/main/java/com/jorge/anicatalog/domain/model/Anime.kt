package com.jorge.anicatalog.domain.model

data class Anime(
    val id: Int,
    val titulo: String,
    val genero: String,
    val sinopse: String,
    val imagemUrl: String,
    val episodios: Int,
    val duracaoEpMin: Int
)

val animes = listOf(
    Anime(
        id = 1,
        titulo = "Naruto",
        genero = "Ação / Aventura",
        sinopse = "Um jovem ninja com um demônio selado em seu corpo busca reconhecimento e sonha em se tornar o Hokage.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1141/142503.jpg",
        episodios = 220,
        duracaoEpMin = 23
    ),
    Anime(
        id = 2,
        titulo = "Bleach",
        genero = "Ação / Sobrenatural",
        sinopse = "Ichigo Kurosaki se torna um Shinigami e passa a defender os vivos dos espíritos malignos chamados Hollows.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1541/147774.jpg",
        episodios = 366,
        duracaoEpMin = 24
    ),
    Anime(
        id = 3,
        titulo = "One Piece",
        genero = "Aventura / Comédia",
        sinopse = "Monkey D. Luffy e seus amigos piratas navegam em busca do tesouro definitivo, o One Piece.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1244/138851.jpg",
        episodios = 1122,
        duracaoEpMin = 24
    ),
    Anime(
        id = 4,
        titulo = "Frieren: Beyond Journey's End",
        genero = "Fantasia / Drama",
        sinopse = "Uma elfa maga reflete sobre o tempo e os laços humanos após a morte de seus companheiros de aventura.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1015/138006.jpg",
        episodios = 28,
        duracaoEpMin = 24
    ),
    Anime(
        id = 5,
        titulo = "Dandadan",
        genero = "Comédia / Sobrenatural",
        sinopse = "Uma garota que acredita em fantasmas e um garoto que acredita em aliens se envolvem em situações caóticas.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1584/143719.jpg",
        episodios = 12,
        duracaoEpMin = 24
    ),
    Anime(
        id = 6,
        titulo = "Attack on Titan",
        genero = "Ação / Drama",
        sinopse = "A humanidade vive atrás de muros para se proteger de gigantes. Eren Yeager jura exterminar todos eles.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/10/47347.jpg",
        episodios = 87,
        duracaoEpMin = 24
    ),
    Anime(
        id = 7,
        titulo = "Death Note",
        genero = "Suspense / Psicológico",
        sinopse = "Um estudante brilhante encontra um caderno capaz de matar qualquer pessoa cujo nome seja escrito nele.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1079/138100.jpg",
        episodios = 37,
        duracaoEpMin = 23
    ),
    Anime(
        id = 8,
        titulo = "Demon Slayer",
        genero = "Ação / Sobrenatural",
        sinopse = "Tanjiro busca a cura para sua irmã transformada em demônio enquanto enfrenta criaturas sobrenaturais.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1286/99889.jpg",
        episodios = 44,
        duracaoEpMin = 23
    ),
    Anime(
        id = 9,
        titulo = "Fullmetal Alchemist: Brotherhood",
        genero = "Aventura / Fantasia",
        sinopse = "Dois irmãos alquimistas buscam a Pedra Filosofal para recuperar seus corpos após uma alquimia proibida.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1208/94745.jpg",
        episodios = 64,
        duracaoEpMin = 24
    ),
    Anime(
        id = 10,
        titulo = "Jujutsu Kaisen",
        genero = "Ação / Sobrenatural",
        sinopse = "Yuji Itadori engole um dedo amaldiçoado e passa a hospedar o espírito maldito mais poderoso já existente.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1171/109222.jpg",
        episodios = 47,
        duracaoEpMin = 24
    ),
    Anime(
        id = 11,
        titulo = "Hunter x Hunter",
        genero = "Aventura / Fantasia",
        sinopse = "Gon Freecss parte em busca de seu pai, um lendário Hunter, enfrentando provas mortais pelo caminho.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1337/99013.jpg",
        episodios = 148,
        duracaoEpMin = 23
    ),
    Anime(
        id = 12,
        titulo = "Neon Genesis Evangelion",
        genero = "Mecha / Psicológico",
        sinopse = "Jovens pilotam mechas gigantes para combater seres chamados Anjos, enquanto lidam com traumas profundos.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1314/108941.jpg",
        episodios = 26,
        duracaoEpMin = 24
    ),
    Anime(
        id = 13,
        titulo = "The Apothecary Diaries",
        genero = "Mistério / Histórico",
        sinopse = "Uma jovem farmacêutica sequestrada para o palácio imperial usa seu talento para desvendar mistérios médicos na corte.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1708/138033.jpg",
        episodios = 24,
        duracaoEpMin = 24
    ),
    Anime(
        id = 14,
        titulo = "Sakamoto Days",
        genero = "Ação / Comédia",
        sinopse = "O maior assassino de todos os tempos abandonou tudo por amor. Agora, com uma recompensa pela cabeça, precisa lutar novamente.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1026/146459.jpg",
        episodios = 13,
        duracaoEpMin = 24
    ),
    Anime(
        id = 15,
        titulo = "Chainsaw Man",
        genero = "Ação / Dark Fantasy",
        sinopse = "Denji funde-se com seu cachorro-demônio e passa a trabalhar como caçador de demônios para sobreviver.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1806/126216.jpg",
        episodios = 12,
        duracaoEpMin = 24
    ),
    Anime(
        id = 16,
        titulo = "Spy x Family",
        genero = "Comédia / Ação",
        sinopse = "Um espião, uma assassina e uma menina telepata formam uma família falsa — sem saber os segredos uns dos outros.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1441/122795.jpg",
        episodios = 37,
        duracaoEpMin = 24
    ),
    Anime(
        id = 17,
        titulo = "My Hero Academia",
        genero = "Ação / Super-heróis",
        sinopse = "Em um mundo de super-poderes, um menino sem habilidades recebe os poderes do maior herói e entra na escola de heróis.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/10/78745.jpg",
        episodios = 138,
        duracaoEpMin = 24
    ),
    Anime(
        id = 18,
        titulo = "Dragon Ball Z",
        genero = "Ação / Aventura",
        sinopse = "Goku e seus amigos defendem a Terra de vilões cada vez mais poderosos em batalhas épicas que definiram uma geração.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1277/142022.jpg",
        episodios = 291,
        duracaoEpMin = 24
    ),
    Anime(
        id = 19,
        titulo = "Pokémon",
        genero = "Aventura / Fantasia",
        sinopse = "Ash Ketchum parte em uma jornada para se tornar o maior Mestre Pokémon do mundo ao lado de seu fiel Pikachu.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1787/140239.jpg",
        episodios = 276,
        duracaoEpMin = 22
    ),
    Anime(
        id = 20,
        titulo = "JoJo's Bizarre Adventure: Steel Ball Run",
        genero = "Ação / Sobrenatural",
        sinopse = "Uma corrida transcontinental pelos EUA esconde uma conspiração envolvendo um artefato sagrado e poderes sobrenaturais.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1448/154111.jpg",
        episodios = 1,
        duracaoEpMin = 47
    ),
    Anime(
        id = 21,
        titulo = "Witch Hat Atelier",
        genero = "Fantasia / Slice of Life",
        sinopse = "Coco sonha em ser bruxa desde criança. Ao descobrir que a magia vem de desenhos e símbolos, sua vida muda completamente.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1726/155542.jpg",
        episodios = 12,
        duracaoEpMin = 24
    )
)