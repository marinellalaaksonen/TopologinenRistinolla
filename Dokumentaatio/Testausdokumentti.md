# Testausdokumentti

# Yksikkötestaus

Logiikkaluokkien toimintaa on testattu yksikkötestauksella suhteellisen kattavasti ja testikattavuus on melko hyvä. Evaluate-metodille ei ole kesken olevien pelitilanteiden osalta tehty automatisoituja yksikkötestejä, sillä se muuttuu sen verran usein, ettei tämä olisi järkevää.


# Tekoälyvastustajan suorituskykytestaus

Tekoälyvastustajan suorituskykyä testattiin pelaamalla sitä vastaan erilaisilla asetuksilla. Aikavaativuutta testattiin tarkkailemalla tekoälyn siirtoihinsa käyttämää aikaa.

Tällä hetkellä tekoälyvastustaja toimii hyvin pienillä pelilaudoilla (5x5) päätyen lähes aina tasapeliin. Suuremmilla (9x9) laudoilla tekoäly ei ehdi laskea tarpeeksi pitkälle toimiakseen optimaalisesti ja ihmisvastustaja useimmiten voittaa. Tällä hetkellä tekoäly käyttää syvyytenään funktiota 80/MovesLeft + 28/10 tai 80/MovesLeft + 38/10. Tällöin se menee syvyyteen 3/4 kun siirtoja on jäljellä 67 tai enemmän ja syvyyteen 4/5 kun siirtoja on jäljellä 37-66. Kahden vaihtoehdon välillä on tällä hetkellä suuri ero algoritmin toimivuudessa, tällä hetkellä nopeampien tekoälyjen pelatessa keskenään 12x12 kentällä aloittava tekoäly voittaa suoraan viidellä siirrolla. Tätä pystyisi parantamaan myös evaluate-metodin paremmalla toteutuksella (katso toteutusdokumentin kohta "puutteet ja parannusehdotukset").

Tekoälyvastustajan ajalliseen suorituskykyyn vaikuttaa tällä hetkellä paljon se, missä kohtaa pelilautaa pelataan. Jos pelilaudan alussa (vasemmassa yläkulmassa) on paljon tyhjää toimii tekoäly tällä toteutuksella huomattavasti hitaammin kuin "optimaalisessa" pelitilanteessa. Tähän vaikuttaa alphabeta pruningin toiminta ja se järjestys, jossa pelisolmun lapset tällä hetkellä luodaan. Suorituskykyä voisi parantaa huomattavasti aloittamalla lasten lisääminen edellisiä siirtoja lähimpinä olevista lapsista, mutta tämä ei ehtinyt valmistua tähän palautukseen.

Algoritmin aikavaativuuden emppiirisiä tuloksia on vaikea esittää graaffisesti johtuen alphabeta pruningin aiheuttamasta suuresta aikojen vaihtelusta. Aikavaativuuden exponentiaalisuuden näkee kuitenkin hyvin vertailtaessa nopeamman ja hitaamman tekoälyn siirtoihin käyttämää aikahaarukkaa, joissa on suuri ero vaikka hitaampi tekoäly käy minmaxia vain yhden tason syvemmälle. Tällä hetkellä nopeamman tekoälyn siirtoihin menee 12x12 laudalla n 0.1-1 s kun hitaammalla menee ensimmäistä muutamaa siirtoa lukuun ottamatta siirtoihin n 4-20 s. Ensimmäiset siirrot ovat tästä hieman poikkeavia, sillä tekoäly tekee ensimmäisen siirron kovakoodatusti aina keskelle, jolloin tämä siirto on vakioaikainen, mutta hitaammalla tekoälyllä toinen siirto on selvästi hitaampi kuin muut (lähes 2 min).

Jos pelaa ihmisvastustaja pelaa tekoälyä vastaan huonointa mahdollista tapausta (oikeaa alanurkkaa) hitaamman tekoälyvastustajan käyttämä aika on n 1-2 minuuttia kun kentän koko on 12x12. Hitaalla tekoälyvastustajalla tässä ei ole suurta eroa muihin pelityyleihin verrattuna.