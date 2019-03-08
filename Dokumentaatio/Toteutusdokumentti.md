# Toteutusdokumentti


## Ohjelman yleisrakenne

Ohjelman rakenne koostuu teksitikäyttöliittymästä (TicTacToe.java), pelin logiikan toteuttavista luokista (kansio TicTacToeGames), eri pelityypeistä (kansio GameTypes) ja pelaajien toiminnallisuuden toteuttavista luokista (kansio Players). Tekstikäyttöliittymä luo halutunlaisen pelin, eli olion TicTacToeGame, jolla on riippuvuus Evaluatoriin. Evaluator hyödyntää pelityyppikohtaisissa siirroissa (laudan reunojen yli menemiset) GameType-rajapinnan toteuttavien pelin tyyppi-luokkien avulla. Peliin liittyy aina myös sen hetkinen pelitilanne Position. Tekstikäyttöliittymä luo myös kummallekin pelaajalle omat Player-rajapinnan toteuttavat oliot (HumanPlayer tai AI), jotka huolehtivat siirtojen teosta.

todo: UML-kaavio


## Suorituskyky

Ohjelman suorituskykyä säädellään tällä hetkellä kahdella muuttujalla: AI-luokan depht-muuttuja määrittelee kuinka monta tasoa minmaxia käydään läpi ja Position-luokan amountOfChildren-muuttuja määrittelee kuinka monta viimeistä siirtoa lähimpänä olevaa lasta käydään läpi. Depht-muuttujan kasvattaminen parantaa tekoälyvastustajan pelaamista, mutta varsinkin isoilla pelikentillä muuttujan kasvattaminen hidastaa algoritmia huomattavasti johtuen minmaxin exponentiaalisesta aikavaativuuedsta suhteessa läpikäytyjen lasten määrään. Tällä hetkellä depht:n arvo määräytyy dynaamisesti suhteessa jäljellä olevien siirtojen määrään kaavalla 80/currentPosition.getMovesLeft() + 28/10.

AmountOfChildrenin arvolla säädellään huomioitavan pelikentän osan laajuutta. Jos arvo on pieni yhden minmaxin tason läpikäyminen on nopeampaa, koska läpikäytyjen lasten määrä on pienempi. Toisaalta pieni arvo aiheuttaa tietyissä tilanteissa sen, että algoritmi optimoi tulosta ainoastaan lokaalisti, jolloin se saattaa esimerkiksi jättää tekemättä voittavan siirron vaikka sellaiseen olisi mahdollisuus, koska voittava siirto on liian kaukana edellisestä siirrosta. Tällä hetkellä amounOfChildrenin arvo on 144 (12*12), mikä mahdollistaan globaalin optimoinnin kentän koilla 12x12 ja tätä pienemmillä kentillä, mutta mahdollistaa pelaamisen myös isommilla kentillä. TODO: Tällä hetkellä lähimpiä lapsia etsitään vain...


## Saavutetut aika- ja tilavaativuudet

Tällä hetkellä minmax-algoritmin syvyys on määritelty kaavalla 80/movesLeft + 28/10 (nopea AI) tai 80/movesLeft + 38/10 (hidas AI), jolloin sen aikavaativuus on n^(80/movesLeft + 28/10), missä n on kentällä olevien ruutujen määrä. Tällöin se menee syvyyteen 3 kun siirtoja on jäljellä 67 tai enemmän ja syvyyteen 4 kun siirotja on jäljellä 37-66. Minmaxin käyttämä position-luokan generateChildren-metodi kopioi jokaiselle lapselle (enintään n) uuden pelilaudan, eli sen aikavaativuus on n * n. Minmaxin käyttämät won ja evaluate -metodien aikavaativuus on lineaarinen suhteessa voittosuoran pituuteen (enintään 8 * wincondition), jolloin ne eivät merkittävästi vaikuta aikavaativuuteen. Tällöin koko algoritmin aikavaativuus on O(n ^ (2 * (80/n + 28/10))). Käytännössä algoritmin aikavaativuus on usein alphabetapruningin ansiosta parempi, mutta myös sen huonoimman tapauksen aikavaativuus on sama kuin perusmuotoisella alphabetalla.

Minmaxin jokaisella solmulla on lapsensa (n - tehdyt siirrot) listana, jolloin jokaisen solmun tilavaativuus on n*n. Koska minmaxia käydään läpi syvyyshaulla on solmuja kerrallaan muistissa korkeintaan syvyyden verran, jolloin algoritmin tilavaativuus on (80/movesLeft + 28/10) * n^2 eli O(n^2).


## Puutteet ja parannusehdotukset

AI-vastustajaa vastaan pystyy tällä hetkellä pelaamaan pahinta aikavaativuutta pelaamalla oikeaan alanurkkaan. Tämä hidastaa varsinkin hitaamman AI-vastustajan suoriutumista merkittävästi. Tätä voisi korjata aloittamalla lasten luonti olemassa olevien pelisiirtojen läheltä (esimerkiksi viimeisimmä siirron ympäriltä, mikä mahdollistaisi myös isomman pelialueen kuin 12x12 käytön jos myös lasten määrää samalla rajoitettaisiin.

Evaluate-metodin voisi muuttaa suosimaan siirtoja, joissa suoran molemmat päät ovat vapaat ja mahdollisesti huomioimaan myös blokit suoraan eikä ainoastaan välillisesti (estämällä voitot). Mahdollisesti myös suosimaan tasapeliä häviämisen sijaan tai depht:n laittamista osaksi evaluate-arvoa?

Voitaisiin myös nopeuttaa selvästi pitämällä kirjaa jo kohdatuista tilanteista?


## Lähteet
