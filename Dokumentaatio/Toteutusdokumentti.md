# Toteutusdokumentti


## Ohjelman yleisrakenne

Ohjelman rakenne koostuu teksitikäyttöliittymästä (TicTacToe.java), pelin logiikan toteuttavista luokista (kansio TicTacToeGames) ja pelaajien toiminnallisuuden toteuttavista luokista (kansio Players). Tekstikäyttöliittymä luo halutunlaisen pelin, eli olion TicTacToeGame, jolla on riippuvuus Evaluatoriin. Evaluator hyödyntää pelityyppikohtaisissa siirroissa (laudan reunojen yli menemiset) GameType-rajapinnan toteuttavien pelin tyyppi-luokkien avulla. Peliin liittyy aina myös sen hetkinen pelitilanne Position. Tekstikäyttöliittymä luo myös kummallekin pelaajalle omat Player-rajapinnan toteuttavat oliot (HumanPlayer tai AI), jotka huolehtivat siirtojen teosta.

todo: UML-kaavio


## Suorituskyky

Ohjelman suorituskykyä säädellään tällä hetkellä kahdella muuttujalla: AI-luokan depht-muuttuja määrittelee kuinka monta tasoa minmaxia käydään läpi ja Position-luokan amountOfChildren-muuttuja määrittelee kuinka monta viimeistä siirtoa lähimpänä olevaa lasta käydään läpi. Depht-muuttujan kasvattaminen parantaa tekoälyvastustajan pelaamista, mutta varsinkin isoilla pelikentillä muuttujan kasvattaminen hidastaa algoritmia huomattavasti johtuen minmaxin exponentiaalisesta aikavaativuuedsta suhteessa läpikäytyjen lasten määrään. Tällä hetkellä depht:n arvo määräytyy dynaamisesti suhteessa jäljellä olevien siirtojen määrään kaavalla 80/currentPosition.getMovesLeft() + 28/10.

AmountOfChildrenin arvolla säädellään huomioitavan pelikentän osan laajuutta. Jos arvo on pieni yhden minmaxin tason läpikäyminen on nopeampaa, koska läpikäytyjen lasten määrä on pienempi. Toisaalta pieni arvo aiheuttaa tietyissä tilanteissa sen, että algoritmi optimoi tulosta ainoastaan lokaalisti, jolloin se saattaa esimerkiksi jättää tekemättä voittavan siirron vaikka sellaiseen olisi mahdollisuus, koska voittava siirto on liian kaukana edellisestä siirrosta. Tällä hetkellä amounOfChildrenin arvo on 144 (12*12), mikä mahdollistaan globaalin optimoinnin kentän koilla 12x12 ja tätä pienemmillä kentillä, mutta mahdollistaa pelaamisen myös isommilla kentillä. TODO: Tällä hetkellä lähimpiä lapsia etsitään vain...


## Saavutetut aika- ja tilavaativuudet

Tällä hetkellä minmax-algoritmi on isommilla kentillä rajoitettu syvyyteen 3, jolloin sen aikavaativuus on n^3. Aikavaativuus muuttuu riippuen Jäljellä olevien siirtojen määrästä kaavalla 80/MovesLeft + 28/10. Tällöin se menee syvyyteen 3 kun siirtoja on jäljellä 67 tai enemmän ja syvyyteen 4 kun siirotja on jäljellä 37-66.

todo: won ja evaluate -metodien aikavaativuudet, tilavaativuus 


## Työn mahdolliset puutteet ja parannusehdotukset
## Lähteet
