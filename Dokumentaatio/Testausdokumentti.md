# Testausdokumentti

Ohjelman koodin toiminnallisuuden oikeellisuus on varmistettu kattavalla automatisoidulla yksikkötestauksella. Lisäksi suorituskykyä on jonkin verran testattu empiirisesti erillaisilla minmaxin syvyyksillä suhteessa pelilaudan kokoon. Tämän hetkinen arvo (288/jäljellä olevien vuorojen määrä) kasvaa liian nopeasti suhteessa jäljellä olevien siirtojen määrään ja  hidastuu ainakin 9x9 laudalla liikaa. Tätä arvoa testattu erityisesti raja-arvoilla 12x12 ja 9x9.
