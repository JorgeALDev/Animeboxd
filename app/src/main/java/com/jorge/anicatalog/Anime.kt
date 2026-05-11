package com.jorge.anicatalog

data class Anime(
    val titulo: String,
    val genero: String,
    val nota: String,
    val sinopse: String,
    val imagemUrl: String
)

val animes = listOf(
    Anime(
        titulo = "Naruto",
        genero = "Ação / Aventura",
        nota = "8.3",
        sinopse = "Um jovem ninja com um demônio selado em seu corpo busca reconhecimento e sonha em se tornar o Hokage.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1141/142503.jpg"
    ),
    Anime(
        titulo = "Bleach",
        genero = "Ação / Sobrenatural",
        nota = "8.1",
        sinopse = "Ichigo Kurosaki se torna um Shinigami e passa a defender os vivos dos espíritos malignos chamados Hollows.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1541/147774.jpg"
    ),
    Anime(
        titulo = "One Piece",
        genero = "Aventura / Comédia",
        nota = "9.0",
        sinopse = "Monkey D. Luffy e seus amigos piratas navegam em busca do tesouro definitivo, o One Piece.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1244/138851.jpg"
    ),
    Anime(
        titulo = "Frieren: Beyond Journey's End",
        genero = "Fantasia / Drama",
        nota = "9.3",
        sinopse = "Uma elfa maga reflete sobre o tempo e os laços humanos após a morte de seus companheiros de aventura.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1015/138006.jpg"
    ),
    Anime(
        titulo = "Dandadan",
        genero = "Comédia / Sobrenatural",
        nota = "8.8",
        sinopse = "Uma garota que acredita em fantasmas e um garoto que acredita em aliens se envolvem em situações caóticas.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1584/143719.jpg"
    ),
    Anime(
        titulo = "Attack on Titan",
        genero = "Ação / Drama",
        nota = "9.9",
        sinopse = "A humanidade vive atrás de muros para se proteger de gigantes. Eren Yeager jura exterminar todos eles.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/10/47347.jpg"
    ),
    Anime(
        titulo = "Death Note",
        genero = "Suspense / Psicológico",
        nota = "9.5",
        sinopse = "Um estudante brilhante encontra um caderno capaz de matar qualquer pessoa cujo nome seja escrito nele.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1079/138100.jpg"
    ),
    Anime(
        titulo = "Demon Slayer",
        genero = "Ação / Sobrenatural",
        nota = "9.2",
        sinopse = "Tanjiro busca a cura para sua irmã transformada em demônio enquanto enfrenta criaturas sobrenaturais.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1286/99889.jpg"
    ),
    Anime(
        titulo = "Fullmetal Alchemist: Brotherhood",
        genero = "Aventura / Fantasia",
        nota = "9.7",
        sinopse = "Dois irmãos alquimistas buscam a Pedra Filosofal para recuperar seus corpos após uma alquimia proibida.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1208/94745.jpg"
    ),
    Anime(
        titulo = "Jujutsu Kaisen",
        genero = "Ação / Sobrenatural",
        nota = "9.0",
        sinopse = "Yuji Itadori engole um dedo amaldiçoado e passa a hospedar o espírito maldito mais poderoso já existente.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1171/109222.jpg"
    ),
    Anime(
        titulo = "Hunter x Hunter",
        genero = "Aventura / Fantasia",
        nota = "9.4",
        sinopse = "Gon Freecss parte em busca de seu pai, um lendário Hunter, enfrentando provas mortais pelo caminho.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1337/99013.jpg"
    ),
    Anime(
        titulo = "Neon Genesis Evangelion",
        genero = "Mecha / Psicológico",
        nota = "9.3",
        sinopse = "Jovens pilotos mechas gigantes para combater seres chamados Anjos, enquanto lidam com traumas profundos.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1314/108941.jpg"
    ),
    Anime(
        titulo = "The Apothecary Diaries",
        genero = "Mistério / Histórico",
        nota = "8.9",
        sinopse = "Uma jovem farmacêutica sequestrada para o palácio imperial usa seu talento para desvendar mistérios médicos na corte.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1708/138033.jpg"
    ),
    Anime(
        titulo = "Sakamoto Days",
        genero = "Ação / Comédia",
        nota = "8.6",
        sinopse = "O maior assassino de todos os tempos abandonou tudo por amor. Agora, com uma recompensa pela cabeça, precisa lutar novamente.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1026/146459.jpg"
    ),
    Anime(
        titulo = "Chainsaw Man",
        genero = "Ação / Dark Fantasy",
        nota = "8.7",
        sinopse = "Denji funde-se com seu cachorro-demônio e passa a trabalhar como caçador de demônios para sobreviver.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1806/126216.jpg"
    ),
    Anime(
        titulo = "Spy x Family",
        genero = "Comédia / Ação",
        nota = "8.5",
        sinopse = "Um espião, uma assassina e uma menina telepata formam uma família falsa — sem saber os segredos uns dos outros.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1441/122795.jpg"
    ),
    Anime(
        titulo = "My Hero Academia",
        genero = "Ação / Super-heróis",
        nota = "8.4",
        sinopse = "Em um mundo de super-poderes, um menino sem habilidades recebe os poderes do maior herói e entra na escola de heróis.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/10/78745.jpg"
    ),
    Anime(
        titulo = "Dragon Ball Z",
        genero = "Ação / Aventura",
        nota = "8.8",
        sinopse = "Goku e seus amigos defendem a Terra de vilões cada vez mais poderosos em batalhas épicas que definiram uma geração.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1277/142022.jpg"
    ),
    Anime(
        titulo = "Pokémon",
        genero = "Aventura / Fantasia",
        nota = "7.4",
        sinopse = "Ash Ketchum parte em uma jornada para se tornar o maior Mestre Pokémon do mundo ao lado de seu fiel Pikachu.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1787/140239.jpg"
    ),
    Anime(
        titulo = "JoJo's Bizarre Adventure: Steel Ball Run",
        genero = "Ação / Sobrenatural",
        nota = "9.2",
        sinopse = "Uma corrida transcontinental pelos EUA esconde uma conspiração envolvendo um artefato sagrado e poderes sobrenaturais.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1448/154111.jpg"
    ),
    Anime(
        titulo = "Witch Hat Atelier",
        genero = "Fantasia / Slice of Life",
        nota = "8.7",
        sinopse = "Coco sonha em ser bruxa desde criança. Ao descobrir que a magia vem de desenhos e símbolos, sua vida muda completamente.",
        imagemUrl = "https://cdn.myanimelist.net/images/anime/1726/155542.jpg"
    )
)