# Toteutusdokumentti

Ohjelman rakenne koostuu teksitikäyttöliittymästä (TicTacToe.java), pelin logiikan toteuttavista luokista (kansio TicTacToeGames) ja pelaajien toiminnallisuuden toteuttavista luokista (kansio Players). Tekstikäyttöliittymä luo halutunlaisen pelin, eli olion TicTacToeGame, jolla on riippuvuus johonkin GameType-rajapinnan toteuttavaan olioon (esimerkiksi BasicTicTacToe). Peliin liittyy aina myös sen hetkinen pelitilanne Position. Tekstikäyttöliittymä luo myös kummallekin pelaajalle omat Player-rajapinnan toteuttavat oliot (HumanPlayer tai AI), jotka huolehtivat siirtojen teosta.