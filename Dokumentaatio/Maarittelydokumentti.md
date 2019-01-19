# Määrittelydokumentti

Toteutetaan laajennettu ristinolla ja sen [topologisia variaatioita](http://chalkdustmagazine.com/features/topological-tic-tac-toe/). Artikkelin esimerkeistä toteutetaan ainakin "The cylinder", "The Möbius strip" ja "The Klein bottle". Erilaiset variaatiot muuttavat voittoehtoja ja tätä kautta heurestiikkaa (heurestic evaluation function), mutta eivät varsinaisesti muuten vaikuta ohjelman rakenteeseen. Kentän koko on 12 x 12 ja voittoehtona viiden suora. 


## Käytettävät algoritmit ja tietorakenteet

Pelipuun läpikäyntiin toteutetaan aluksi syvyysrajoitettu minmax alpla-beta pruningilla. Lisäksi jos aikataulu sallii toteutetaan versio, jossa pyritään valitsemaan heuristiikan perusteella lupaavin siirto alpha-betaa varten ja pyritään täten nopeuttamaan algoritmin toimintaa. Lisäksi algoritmia voisi parantaa pitämällä kirjaa tiloista joihin ollaan jo törmätty jotain toista kautta, jolloin voidaan välttää samojen alipuiden läpikäynti uudestaan. Tällöin pitäisi luultavasti toteuttaa jonkin näköinenn hashmap.

Toinen vaihtoehto alpha-betan parantelun sijaan olisi toteuttaa vertailuksi esimerkiksi algoritmi, joka lähtisi laajentamaan pidemmälle "lupaavia" vaihtoehtoja. Tällöin toteutettaisiin myös priorisoitu jono. Myös monte carlo on jonkin verran suurien pelipuiden approksimointiin


## Algoritmien aika- ja tilavaativuudet

Pelipuun läpikäyntiin minmaxilla menee n! (jossa n on pelilaudan koko eli 12 x 12). Lisäksi jokaisessa solmussa käydään kenttä läpi, eli aikavaativuus yhden solmun läpikäymiseen on n. Minmaxin aikavaativuus on siis eksponentiaalinen O(n * n!) (eli O((n + 1)^n)). Alpha-betan pahimman tapauksen aikavaativuus on sama kuin min-maxin, mutta jos algoritmi valitsee aina ensin parhaimman polun, on aikavaativuus O(n * 1 * n * 1...) eli O(n^(n / 2)).

Minmaxin tilavaativuus on puun syvyys ja jokaisen solmun pelikentän koko eli O(n ^ 2). Hashtablen tilavaativuus (jos sellainen päädyttäisiin toteuttamaan) olisi tilavaativuudeltaan eksponentiaalinen, sillä mahdollisia pelitilanteita on 3^n (tosin tässä on mukana myös "laittomat" pelitilanteet kuten sellaiset joissa on ristien ja nollien lukumäärän ero on isompi kuin 1).


Lähteet:
http://chalkdustmagazine.com/features/topological-tic-tac-toe/
https://materiaalit.github.io/intro-to-ai-18/part2/
https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
https://www.uio.no/studier/emner/matnat/ifi/INF4130/h17/undervisningsmateriale/chess-algorithms-theory-and-practice_ver2017.pdf
https://en.wikipedia.org/wiki/Game_complexity