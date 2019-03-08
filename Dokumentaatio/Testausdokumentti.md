# Testausdokumentti

# Yksikkötestaus

Logiikkaluokkien toimintaa on testattu yksikkötestauksella. Ainakin evaluate-metodin yksikkötestaus ei ole kuitenkaan vielä kovin kattavaa.


# AI-vastustajan suorituskykytestaus

Jos pelaa huonointa mahdollista tapausta (oikea alanurkka) AI-vastustajan käyttämä aika on n 1-2 minuuttia kun kentän koko on 12x12.

Tällä hetkellä AI-vastustaja toimii hyvin pienillä pelilaudoilla (5x5) päätyen lähes aina tasapeliin. Suuremmilla (9x9) laudoilla AI ei ehdi laskea tarpeeksi pitkälle toimiakseen optimaalisesti ja ihmisvastustaja useimmiten voittaa. Tällä hetkellä AI käyttää syvyytenään funktiota 80/MovesLeft + 28/10 tai 80/MovesLeft + 38/10. Tällöin se menee syvyyteen 3/4 kun siirtoja on jäljellä 67 tai enemmän ja syvyyteen 4/5 kun siirtoja on jäljellä 37-66.

todo: siirron tekemiseen vaadittavan ajan testaus