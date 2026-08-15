# Projet d’expertise évalué : « Projet Application mobile Java Android » :

## Programmation d’un client de web service REST avec Googlemap

#### Spécifications
Construire une application mobile Android avec Java permettant d’afficher la liste des centres de
formation de Dawan.

L’application récupère les localisations depuis un service web REST :
https://dawan.org/public/location/

Et stocke les données sur device mobile (bdd SQLite) pour garantir l’accès aux sites hors
connexion.
Les différents centres devront figurer sur une carte Googlemap ou autre les différents centres
(épingles). Les coordonnées de géolocalisation (latitude/longitude) sont disponibles dans les
données récupérées.

Un évènement sur l’épingle permet d’afficher les détails du centre (Adresse complète) dans un fragment.

1) Définir le projet et les activités associées
2) Implémenter un service permettant d’appeler le web service REST (Retrofit ou Volley ou
   autre)
3) Afficher les données stockées en Bdd SQLite dans une map (Google ou autre)
4) Implémenter des tests avec Espresso

### Critères d'évaluation

- Le projet est organisé en couches
- Le projet est correctement configuré
- Les bonnes pratiques de codage sont maîtrisées (SOLID, Tell don’t ask, généricité, ...)
- Les exceptions sont gérées (individuellement ou globalement)
- Le code source est documenté (Javadoc).
- Des tests sont implémentés avec Espresso

### Grille d’évaluation critériée (TOTAL 20 points)
- Configuration du projet (/1)
- Organisation des packages (/1)
- Bonnes pratiques (/2)
- Documentation du code (/1)
- Couverture des exigences/Code opérationnel (/6)
- Maîtrise de la dette technique (/1)
- Gestion des exceptions (/1)
- Tests (/3)
- Présentation orale (/4)


