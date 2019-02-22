# Toteutusdokumentti


## Ohjelman yleisrakenne

Ohjelman rakenne koostuu teksitikäyttöliittymästä (TicTacToe.java), pelin logiikan toteuttavista luokista (kansio TicTacToeGames) ja pelaajien toiminnallisuuden toteuttavista luokista (kansio Players). Tekstikäyttöliittymä luo halutunlaisen pelin, eli olion TicTacToeGame, jolla on riippuvuus Evaluatoriin. Evaluator hyödyntää pelityyppikohtaisissa siirroissa (laudan reunojen yli menemiset) GameType-rajapinnan toteuttavien pelin tyyppi-luokkien avulla. Peliin liittyy aina myös sen hetkinen pelitilanne Position. Tekstikäyttöliittymä luo myös kummallekin pelaajalle omat Player-rajapinnan toteuttavat oliot (HumanPlayer tai AI), jotka huolehtivat siirtojen teosta.

todo: UML-kaavio


## Saavutetut aika- ja tilavaativuudet

Tällä hetkellä minmax-algoritmi on isommilla kentillä rajoitettu syvyyteen 3, jolloin sen aikavaativuus on n^3. Aikavaativuus muuttuu riippuen Jäljellä olevien siirtojen määrästä kaavalla 80/MovesLeft + 28/10. Tällöin se menee syvyyteen 3 kun siirtoja on jäljellä 67 tai enemmän ja syvyyteen 4 kun siirotja on jäljellä 37-66.

todo: won ja evaluate -metodien aikavaativuudet, tilavaativuus 


## Työn mahdolliset puutteet ja parannusehdotukset
## Lähteet
