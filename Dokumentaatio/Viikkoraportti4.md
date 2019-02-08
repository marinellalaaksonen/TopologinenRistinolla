# Viikkoraportti 4

Työhön käytetty aika n 8,5 tuntia

**Mitä olen tehnyt tällä viikolla?**  
Toteuttanut minmaxin/tekoälyvastustajan loppuun sekä lisännyt evaluate-metodille voiton tarkistamisen testit.

**Miten ohjelma on edistynyt?**  
Tekoälyvastustaja valmis, toimii hyvin riittävän pienillä kentillä. Isommilla kentillä toimiakseen vaatii evaluate-metodin parantamista.

**Mitä opin tällä viikolla?**  
Min-maxin/alphabeta-pruningin toteutusta ja debuggausta. Testien tekoa.

**Mikä jäi epäselväksi tai tuottanut vaikeuksia?**  
AI:n/minmaxin debuggaus osoittautui haastavaksi. Seuraavana tehtävänä olevista evaluaten ja AI:n parannuksista myös hieman vielä epäselvää miten tai mihin suuntaan niitä kannattaisi lähteä parantamaan.

Kysymyksiä: Tulisiko tekstikäyttöliittymälle (TicTacToe.java) olla yksikkötestit? Entä pitäisikö Position-luokalla olla omat yksikkötestit? Tällä hetkellä muiden luokkien testit/metodit hyödyntävät myös positionin metodeja, joten jacoco näyttää että myös sen testikattavuus on hyvä, vaikka luokalla ei ole omia yksikkötestejä.

**Mitä teen seuraavaksi?**  
Evaluate-metodin parantaminen. Tämän jälkeen muiden pelityyppien toteutus ja mahdollisesti olemassa olevan AI:n parantelu esimerkiksi muistamalla jo vastaan tulleet pelitilanteet tai avaamalla ensin todennäköisesti parhaita siirtoja. Toinen vaihtoehto olisi jotakin toista algoritmia hyödyntävän AI:n toteutus.
