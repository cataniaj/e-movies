/*
 *	Author:      Jérémy Leyvraz
 *	Date:        21 sept. 2016
 */

package fr.imag.themoviedatabase;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import fr.imag.ejb.dbaccess.AdminDatabaseAccessEJB;
import fr.imag.ejb.dbaccess.MovieDatabaseAccessEJB;
import fr.imag.ejb.dbaccess.OrderAllDatabaseAccessEJB;
import fr.imag.ejb.dbaccess.OrderLineDatabaseAccessEJB;
import fr.imag.ejb.dbaccess.UserDatabaseAccessEJB;
import fr.imag.entities.Admin;
import fr.imag.entities.Movie;
import fr.imag.entities.MovieList;

/**
 * EJB qui se charge d'initialiser la base de données
 * @author Jerem
 *
 */
@Stateless
public class AdminRegistration {

	@EJB private AdminDatabaseAccessEJB adminDBaccess;
	@EJB private MovieDatabaseAccessEJB movieDBaccess;
	@EJB private UserDatabaseAccessEJB userDBaccess;
	@EJB private OrderAllDatabaseAccessEJB orderDBaccess;
	@EJB private OrderLineDatabaseAccessEJB orderLineDBaccess;
  
	private String manyFilm = ""
			+ "{\"movies\":["
			+ "{\"title\":\"Spider-Man\",\"director\":\"Sam Raimi\",\"year\":\"2002\",\"actors\":\"Tobey Maguire, Willem Dafoe, Kirsten Dunst, James Franco, Cliff Robertson\",\"overview\":\"Orphelin, Peter Parker est élevé par sa tante May et son oncle Ben dans le quartier Queens de New York. Tout en poursuivant ses études de biophysique à l'université, il trouve un emploi de photographe au journal Daily Bugle. Cependant, après avoir été mordu par une araignée radioactive, ce dernier est sujet à des transformations physiques : son agilité et sa force se sont accrues, et il se voit doter de pouvoirs surnaturels. Peter décide alors de participer à un combat de catch qu'il remporte avec une facilité déconcertante. Mais l'organisateur du spectacle, décrétant qu'il y a eu tricherie, refuse de lui remettre le prix de 3 000 dollars qui lui revient de droit. Au même moment, l'homme d'affaires mégalomane Norman Osborn expérimente sur lui-même de dangereuses solutions chimiques. Des mutations s'opèrent bientôt sur son corps et son esprit. Il se transforme alors en Bouffon Vert.\",\"genre\":\"Fantastique, Action\",\"poster\":\"https://image.tmdb.org/t/p/w300/A9BYH1DSetvC7bjbHWCaL17Qbp5.jpg\",\"id\":\"557\",\"runtime\":\"2 h 1 min\",\"video\":\"N/A\"},"
			+ "{\"title\":\"Spider-Man 3\",\"director\":\"Sam Raimi\",\"year\":\"2007\",\"actors\":\"Tobey Maguire, Kirsten Dunst, James Franco, Thomas Haden Church, Topher Grace\",\"overview\":\"Peter Parker a retrouvé un équilibre de vie et il veut se marier avec Mary Jane. Pendant une nuit au parc, alors que Peter et Mary Jane sont ensemble, une petite météorite tombe tout près du lieu où ils se trouvent, et une particule s'en échappe, libérant, en éclatant, une matière visqueuse, la Venom, qui s'attache à la mobylette de Peter. Pendant ce temps, Flint Marko s'échappe de la prison où il était détenu pour cambriolage afin d'aller revoir sa fillette qui lui manque terriblement, mais dont il n'a pas le droit de s'approcher à cause de l'injonction d'éloignement obtenue contre lui par son ex-femme ; pendant sa fuite, il tombe dans un accélérateur de particules, qui fond son corps avec le sable et il devient l’Homme-Sable. Le meilleur ami de Peter, Harry Osborn, veut venger la mort de son père, et, croyant que Spider-Man est la cause de cette mort, l'attaque.\",\"genre\":\"Fantastique, Action, Aventure\",\"poster\":\"https://image.tmdb.org/t/p/w300/62IWJ92nmLVSu5n5Xl4n8aT7gbd.jpg\",\"id\":\"559\",\"runtime\":\"2 h 19 min\",\"video\":\"N/A\"},"
			+ "{\"title\":\"Spider-Man 2\",\"director\":\"Sam Raimi\",\"year\":\"2004\",\"actors\":\"Tobey Maguire, Kirsten Dunst, James Franco, Alfred Molina, Rosemary Harris\",\"overview\":\"2 ans après avoir choisi sa vie de super-héros, Peter Parker ne parvient plus à gérer sa double vie. Il perd son boulot, Mary-Jane sait qu'elle ne peut plus compter sur lui, et ses études prennent le même chemin. Il décide de raccrocher le costume de Spiderman. Son ami Harry lui présente un ami de son professeur de science, le bienveillant docteur Otto Octavius sur lequel il doit rédiger un mémoire. Celui-ci travaille sur la fusion qu'il croit pouvoir contrôler. Mais une démonstration de ses recherches tourne mal, et le docteur se voit doter de 4 membres mécaniques supplémentaires greffés sur sa colonne vertébrale et qui prennent le contrôle de son esprit...\",\"genre\":\"Action, Aventure, Fantastique\",\"poster\":\"https://image.tmdb.org/t/p/w300/qtBFrsEQ4oXW8sKvRxkKnYuPLg.jpg\",\"id\":\"558\",\"runtime\":\"2 h 7 min\",\"video\":\"N/A\"},"
			+ "{\"title\":\"The Amazing Spider-Man\",\"director\":\"Marc Webb\",\"year\":\"2012\",\"actors\":\"Andrew Garfield, Emma Stone, Rhys Ifans, Denis Leary, Martin Sheen\",\"overview\":\"Abandonné par ses parents lorsqu’il était enfant, Peter Parker a été élevé par son oncle Ben et sa tante May. Il est aujourd’hui au lycée, mais il a du mal à s’intégrer. Comme la plupart des adolescents de son âge, Peter essaie de comprendre qui il est et d’accepter son parcours. Amoureux pour la première fois, lui et Gwen Stacy découvrent les sentiments, l’engagement et les secrets. En retrouvant une mystérieuse mallette ayant appartenu à son père, Peter entame une quête pour élucider la disparition de ses parents, ce qui le conduit rapidement à Oscorp et au laboratoire du docteur Curt Connors, l’ancien associé de son père. Spider-Man va bientôt se retrouver face au Lézard, l’alter ego de Connors. En décidant d’utiliser ses pouvoirs, il va choisir son destin…\",\"genre\":\"Action, Aventure, Fantastique\",\"poster\":\"https://image.tmdb.org/t/p/w300/7ySgEbHfUl42Ml0I6oMHpTzTCNo.jpg\",\"id\":\"1930\",\"runtime\":\"2 h 17 min\",\"video\":\"https://www.youtube.com/embed/mJlDpguadbk\"},"
			+ "{\"title\":\"The Amazing Spider-Man : Le Destin d'un héros\",\"director\":\"Marc Webb\",\"year\":\"2014\",\"actors\":\"Andrew Garfield, Emma Stone, Jamie Foxx, Dane DeHaan, Colm Feore\",\"overview\":\"Peter Parker a une vie très occupée partagée entre son combat contre les vilains et la personne qu’il aime, Gwen. Il attend donc son diplôme de fin de lycée avec impatience. Peter n’a pas oublié la promesse qu’il a faite au père de Gwen : la protéger en restant hors de son existence. Une promesse qu’il ne peut pas tenir. Les choses vont changer pour Peter quand un nouveau vilain fait son apparition, Electro, quand un vieil ami fait son retour, Harry Osborn, et quand Peter découvre de nouveaux indices sur son passé.\",\"genre\":\"Action, Aventure, Fantastique\",\"poster\":\"https://image.tmdb.org/t/p/w300/t5lzbyshCcE94eCKSTeUTVljoHO.jpg\",\"id\":\"102382\",\"runtime\":\"2 h 22 min\",\"video\":\"https://www.youtube.com/embed/5BkTOpSaKrU\"},"
			+ "{\"title\":\"Gremlins\",\"director\":\"Joe Dante\",\"year\":\"1984\",\"actors\":\"Zach Galligan, Phoebe Cates, Hoyt Axton, Polly Holliday, Dick Miller\",\"overview\":\"Rand Peltzer offre à son fils Billy un étrange animal : un mogwai. Son ancien propriétaire l'a bien mis en garde : il ne faut pas l'exposer à la lumiere, lui éviter tout contact avec l'eau, et surtout, surtout ne jamais le nourrir apres minuit... Sinon...\",\"genre\":\"Fantastique, Horreur, Comédie\",\"poster\":\"https://image.tmdb.org/t/p/w300/atBZUwRpx60ZpzcM9yOB4E8nuY.jpg\",\"id\":\"927\",\"runtime\":\"1 h 45 min\",\"video\":\"https://www.youtube.com/embed/QJwYQt8dHi4\"},"
			+ "{\"title\":\"Gremlins 2, la nouvelle génération\",\"director\":\"Joe Dante\",\"year\":\"1990\",\"actors\":\"Zach Galligan, Phoebe Cates, John Glover, Robert Prosky, Robert Picardo\",\"overview\":\"Billy et Kate habitent New York avec leur mogwai Gizmo. Malencontreusement mouillé, la petite créature donnent naissance à une nouvelle génération de gremlins. Les monstres prennent d'assaut un gratte-ciel high-tech...\",\"genre\":\"Comédie, Horreur, Fantastique\",\"poster\":\"https://image.tmdb.org/t/p/w300/jWKLkRstVRsNU9FBxCCVexqDaD.jpg\",\"id\":\"928\",\"runtime\":\"1 h 39 min\",\"video\":\"N/A\"},"
			+ "{\"title\":\"Gladiator\",\"director\":\"Ridley Scott\",\"year\":\"2000\",\"actors\":\"Russell Crowe, Joaquin Phoenix, Connie Nielsen, Oliver Reed, Richard Harris\",\"overview\":\"Le général romain Maximus est le plus fidèle soutien de l'empereur Marc Aurèle, qu'il a conduit de victoire en victoire avec une bravoure et un dévouement exemplaires. Jaloux du prestige de Maximus, et plus encore de l'amour que lui voue l'empereur, le fils de Marc Aurèle, Commode, s'arroge brutalement le pouvoir, puis ordonne l'arrestation du général et son exécution. Maximus échappe à ses assassins mais ne peut empêcher le massacre de sa famille. Capturé par un marchand d'esclaves, il devient gladiateur et prépare sa vengeance.\",\"genre\":\"Action, Drame, Aventure\",\"poster\":\"https://image.tmdb.org/t/p/w300/3IGZhnjEzZwStzcNOfwICfWikrX.jpg\",\"id\":\"98\",\"runtime\":\"2 h 35 min\",\"video\":\"https://www.youtube.com/embed/60bmeqZdC6Q\"},"
			+ "{\"title\":\"Contagion\",\"director\":\"Steven Soderbergh\",\"year\":\"2011\",\"actors\":\"Matt Damon, Gwyneth Paltrow, Kate Winslet, Jude Law, Marion Cotillard\",\"overview\":\"« Contagion » suit la rapide progression d'un virus mortel, qui tue en quelques jours. Alors que l'épidémie se propage à grande vitesse, la communauté médicale mondiale tente, dans une course effrénée contre la montre, de trouver un remède et de contrôler la panique qui se répand encore plus vite que le virus. Les gens eux se battent pour survivre dans une société qui se désagrège.\",\"genre\":\"Drame, Thriller, Science-Fiction\",\"poster\":\"https://image.tmdb.org/t/p/w300/4gA4HlPhcQzolaOmL1mDplHkNZ3.jpg\",\"id\":\"39538\",\"runtime\":\"1 h 46 min\",\"video\":\"https://www.youtube.com/embed/iw-crE8sthk\"},"
			+ "{\"title\":\"2 Fast 2 Furious\",\"director\":\"John Singleton\",\"year\":\"2003\",\"actors\":\"Paul Walker, Tyrese Gibson, Eva Mendes, Cole Hauser, Ludacris\",\"overview\":\"Brian O'Conner a signé sa plus belle action, mais aussi sa faute la plus grave, en laissant filer le chef du gang de voleurs de voitures qu'il avait mission d'infiltrer. Radié de la police de Los Angeles, ce jeune flic rebelle, fan de vitesse et de rodéos, a gardé intact son honneur mais a gâché une belle carrière. Après deux ans de galère, Brian O'Conner se retrouve à Miami et se voit offrir une ultime chance de se racheter. Le FBI et les douanes locales surveillent depuis plusieurs mois le puissant homme d'affaires Carter Verone, qu'ils soupçonnent de se livrer à des opérations de blanchiment d'argent. Mais leurs efforts sont restés vains, le seul indice dont ils disposent pour appâter et démasquer l'énigmatique criminel étant sa passion pour les rodéos. Le temps presse, Brian semble être le seul espoir...\",\"genre\":\"Action, Crime, Thriller\",\"poster\":\"https://image.tmdb.org/t/p/w300/hHSRrw4jJEJ2znE2FvgIHcrCUGZ.jpg\",\"id\":\"584\",\"runtime\":\"1 h 47 min\",\"video\":\"N/A\"},"
			+ "{\"title\":\"Pokémon 01 - Mewtwo contre Mew\",\"director\":\"Michael Haigney\",\"year\":\"1998\",\"actors\":\"Rica Matsumoto, Mayumi Iizuka, Yūji Ueda, Ikue Ōtani, Megumi Hayashibara\",\"overview\":\"Sur une île mystérieuse, des archéologues ont découvert un cheveu fossilisé de Mew, le Pokémon le plus rare du monde. Les chercheurs décident de créer un clone de Mew, Mewtwo, en utilisant cet échantillon, dans le but de l'offrir à Giovanni, le Maître de la Team Rocket. Malheureusement pour eux, il s'avère que le clone n'a aucune envie de devenir leur cobaye. Il détruit le laboratoire et tue tous ceux qui s'y trouvent grâce à son incroyable puissance. Suite à ces événements, il décide d'organiser un grand tournoi afin de rassembler les meilleurs dresseurs, dont Sacha, Ondine et Pierre, dans le but de s'emparer de leur Pokémon, de les cloner et de devenir le maître du monde.\",\"genre\":\"Animation\",\"poster\":\"https://image.tmdb.org/t/p/w300/aalTziefHGUVLg44iyWS8B7ee5u.jpg\",\"id\":\"10228\",\"runtime\":\"1 h 25 min\",\"video\":\"N/A\"},"
			+ "{\"title\":\"Men in Black\",\"director\":\"Barry Sonnenfeld\",\"year\":\"1997\",\"actors\":\"Tommy Lee Jones, Will Smith, Linda Fiorentino, Vincent D'Onofrio, Rip Torn\",\"overview\":\"Chargés de protéger la Terre de toute infraction extraterrestre et de réguler l'immigration intergalactique sur notre planète, les Men in black ou MIB opèrent dans le plus grand secret. Vêtus de costumes sombres et équipés des toutes dernières technologies, ils passent inaperçus aux yeux des humains dont ils effacent régulièrement la mémoire récente : la présence d'aliens sur notre sol doit rester secrète. Récemment séparé de son vieux partenaire, retourné à la vie civile sans aucun souvenir de sa vie d'homme en noir, K, le plus expérimenté des agents du MIB décide de former J, un jeune policier. Ensemble, ils vont afronter une nouvelle menace : Edgar le cafard...\",\"genre\":\"Action, Aventure, Comédie, Science-Fiction\",\"poster\":\"https://image.tmdb.org/t/p/w300/8zVanYvCNJ1fU1bsFJT5hwohFYp.jpg\",\"id\":\"607\",\"runtime\":\"1 h 38 min\",\"video\":\"https://www.youtube.com/embed/ZJ8HFSx3bvY\"},"
			+ "{\"title\":\"Alien - Le huitième passager\",\"director\":\"Ridley Scott\",\"year\":\"1979\",\"actors\":\"Sigourney Weaver, Tom Skerritt, Veronica Cartwright, Harry Dean Stanton, John Hurt\",\"overview\":\"2122. Le Nostromo, vaisseau de commerce, fait route vers la Terre avec à son bord un équipage de sept personnes en hibernation et une cargaison de minerais. Il interrompt soudain sa course suite à la réception d'un mystérieux message provenant d'une planète inexplorée. Réveillé par l'ordinateur de bord, l'équipage se rend sur place et découvre les restes d'un gigantesque vaisseau extraterrestre dont le seul passager semble être mort dans d'étranges circonstances...\",\"genre\":\"Horreur, Action, Thriller, Science-Fiction\",\"poster\":\"https://image.tmdb.org/t/p/w300/mDG8r5LcGfsR2DZfSOpG1Cvung4.jpg\",\"id\":\"348\",\"runtime\":\"1 h 57 min\",\"video\":\"N/A\"},"
			+ "{\"title\":\"Predator\",\"director\":\"John McTiernan\",\"year\":\"1987\",\"actors\":\"Arnold Schwarzenegger, Carl Weathers, Elpidia Carrillo, Bill Duke, Jesse Ventura\",\"overview\":\"Le Major Dutch Schaefer et son équipe sont envoyés dans la jungle sud-américaine avec pour mission de délivrer des alliés pris en otage par une bande de guérilleros. Mais pendant l'opération, Dutsch et ses hommes s'aperçoivent que les preneurs d'otages ont été... dépecés et suspendus à la plus haute cime des arbres. Très vite, le commando comprend qu'il a en face de lui un ennemi hors du commun. Un monstre invisible et indestructible venu d'une autre planète. Un extraterrestre rompu à toutes les formes d'attaques et qui ne poursuit qu'un seul but : la chasse à l'être humain. Schaefer et sa troupe sont pour lui du gibier de premier choix.\",\"genre\":\"Science-Fiction, Action, Aventure, Thriller\",\"poster\":\"https://image.tmdb.org/t/p/w300/6E0shu2iXpz4PnM6i0ScfxfHWBm.jpg\",\"id\":\"106\",\"runtime\":\"1 h 47 min\",\"video\":\"https://www.youtube.com/embed/d7Vwm3vlcBU\"},"
			+ "{\"title\":\"Grease\",\"director\":\"Randal Kleiser\",\"year\":\"1978\",\"actors\":\"John Travolta, Olivia Newton-John, Stockard Channing, Jeff Conaway, Didi Conn\",\"overview\":\"Durant l'été 1958, Sandy Olsson, une étudiante australienne en vacances aux États-Unis, rencontre Danny Zuko, le chef de la bande des T-Birds. Leur amour est cependant interrompu par la fin des vacances car Sandy doit retourner en Australie.Toutefois, le hasard fait que Sandy reste en Amérique et qu'elle intègre le lycée Rydell, le même que celui où étudie Danny. Elle y rencontre un groupe de filles, les « Pink Ladies », mené par Betty Rizzo. Sans savoir qu'ils sont tout proches, Danny et Sandy racontent, chacun de leur côté, à leurs amis leur amour de vacances. Danny et Sandy finissent par se rencontrer et décident de reprendre leur relation bien que tout les oppose au lycée...\",\"genre\":\"Musique\",\"poster\":\"https://image.tmdb.org/t/p/w300/7a9jmNPcdIle9YD5LcjfyrqLoc9.jpg\",\"id\":\"621\",\"runtime\":\"1 h 50 min\",\"video\":\"https://www.youtube.com/embed/2j17nSmVghI\"},"
			
			+ "{\"title\":\"Independence Day - Résurgence\",\"director\":\"Roland Emmerich\",\"year\":\"2016\",\"actors\":\"Liam Hemsworth, Jeff Goldblum, Bill Pullman, Maika Monroe, Sela Ward\",\"overview\":\"La Terre est menacée par une catastrophe d'une ampleur inimaginable. Pour la protéger, toutes les nations ont collaboré autour d'un programme de défense colossal exploitant la technologie extraterrestre récupérée. Mais rien ne peut nous préparer à la force de frappe sans précédent des aliens. Seule l'ingéniosité et le courage de quelques hommes et femmes peuvent sauver l'humanité de l'extinction.\",\"genre\":\"Action, Aventure, Science-Fiction\",\"poster\":\"https://image.tmdb.org/t/p/w300/etKQcUQdNzucY1sT7EbEBsqgM0e.jpg\",\"id\":\"47933\",\"runtime\":\"2 h 0 min\",\"video\":\"https://www.youtube.com/embed/K02fIwZe_sY\"},"
			
			+ "{\"title\":\"Independence Day\",\"director\":\"Roland Emmerich\",\"year\":\"1996\",\"actors\":\"Will Smith, Bill Pullman, Jeff Goldblum, Mary McDonnell, Judd Hirsch\",\"overview\":\"Une immense soucoupe volante envahit le ciel terrestre, libérant un nombre infini de plus petites soucoupes qui prennent position au-dessus des plus grandes villes du monde. Un informaticien new-yorkais décrypte les signaux émanant des etranges voyageurs. Ils ne sont pas du tout amicaux et ces extraterrestres se préparent à attaquer la Terre.\",\"genre\":\"Action, Aventure, Science-Fiction\",\"poster\":\"https://image.tmdb.org/t/p/w300/4B1JsecIgqNENf2uBKEUjhYriRU.jpg\",\"id\":\"602\",\"runtime\":\"2 h 20 min\",\"video\":\"N/A\"},"
			+ "{\"title\":\"Star Wars, Épisode VII - Le Réveil de la Force\",\"director\":\"J.J. Abrams\",\"year\":\"2015\",\"actors\":\"Daisy Ridley, John Boyega, Adam Driver, Harrison Ford, Carrie Fisher\",\"overview\":\"Il y a bien longtemps, dans une galaxie lointaine? Luke Skywalker est porté disparu. Le pilote Poe est en mission secrète sur une planète pour le retrouver. Au moment où la diabolique armée « Premier Ordre » apparaît en détruisant tout sur son passage, il arrive à cacher la position géographique de l'ancien maître Jedi dans son droïde BB-8. Capturé par les larbins du machiavélique Kylo Ren, Poe est libéré par le soldat ennemi Finn qui est en pleine crise existentielle. Pendant ce temps, BB-8 est recueillie par Rey, une pilleuse d'épaves qui sera bientôt plongée dans une quête qui la dépasse.\",\"genre\":\"Action, Aventure, Science-Fiction, Fantastique\",\"poster\":\"https://image.tmdb.org/t/p/w300/sHOYrricE1K0JriQ74FeN794UEl.jpg\",\"id\":\"140607\",\"runtime\":\"2 h 15 min\",\"video\":\"https://www.youtube.com/embed/ZHA7XEtxRMU\"},"
			+ "{\"title\":\"Star Wars, épisode I - La Menace fantôme\",\"director\":\"George Lucas\",\"year\":\"1999\",\"actors\":\"Liam Neeson, Ewan McGregor, Natalie Portman, Jake Lloyd, Ian McDiarmid\",\"overview\":\"Il y a bien longtemps, dans une galaxie très lointaine... La République connaît de nombreux tourments : la corruption fait vaciller ses bases, le Sénat s'embourbe dans des discussions politiques sans fin et de nombreux pouvoirs dissidents commencent à émerger, annonçant la chute d'un système autrefois paisible. Puissante et intouchable, la Fédération du Commerce impose par la force la taxation des routes commerciales. Refusant de céder, la pacifique planète Naboo, dirigée par la jeune Reine Amidala, subit un blocus militaire de la Fédération. Dépêchés par le Sénat pour régler cette affaire, les chevaliers Jedi Qui-Gon Jinn et Obi-Wan Kenobi découvrent qu'une véritable offensive de la Fédération est imminente. Libérant la Reine et ses proches, ils quittent la planète mais doivent se poser sur Tatooine pour réparer leur vaisseau...\",\"genre\":\"Aventure, Action, Science-Fiction\",\"poster\":\"https://image.tmdb.org/t/p/w300/etnrgeks0Al3wSo44Ji6xgaLBAW.jpg\",\"id\":\"1893\",\"runtime\":\"2 h 13 min\",\"video\":\"https://www.youtube.com/embed/iwkzRNrFlSg\"},"
			+ "{\"title\":\"Star Wars, épisode II - L'Attaque des clones\",\"director\":\"George Lucas\",\"year\":\"2002\",\"actors\":\"Ewan McGregor, Natalie Portman, Hayden Christensen, Ian McDiarmid, Samuel L. Jackson\",\"overview\":\"Depuis le blocus de la planète Naboo par la Fédération du commerce, la République, gouvernée par le Chancelier Palpatine, connaît une véritable crise. Un groupe de dissidents, mené par le sombre Jedi comte Dooku, manifeste son mécontentement envers le fonctionnement du régime. Le Sénat et la population intergalactique se montrent pour leur part inquiets face à l'émergence d'une telle menace.Certains sénateurs demandent à ce que la République soit dotée d'une solide armée pour empêcher que la situation ne se détériore davantage. Parallèlement, Padmé Amidala, devenue sénatrice, est menacée par les séparatistes et échappe de justesse à un attentat. Le Padawan Anakin Skywalker est chargé de sa protection. Son maître, Obi-Wan Kenobi, part enquêter sur cette tentative de meurtre et découvre la constitution d'une mystérieuse armée de clones...\",\"genre\":\"Aventure, Action, Science-Fiction\",\"poster\":\"https://image.tmdb.org/t/p/w300/rVYDjCnsqr0DD3pqziqEczEXv4R.jpg\",\"id\":\"1894\",\"runtime\":\"2 h 12 min\",\"video\":\"N/A\"},"
			+ "{\"title\":\"Star Wars, épisode III - La Revanche des Sith\",\"director\":\"George Lucas\",\"year\":\"2005\",\"actors\":\"Hayden Christensen, Ewan McGregor, Natalie Portman, Ian McDiarmid, Frank Oz\",\"overview\":\"La Guerre des Clones fait rage. Une franche hostilité oppose désormais le Chancelier Palpatine au Conseil Jedi. Anakin Skywalker, jeune Chevalier Jedi pris entre deux feux, hésite sur la conduite à tenir. Séduit par la promesse d'un pouvoir sans précédent, tenté par le côté obscur de la Force, il prête allégeance au maléfique Darth Sidious et devient Dark Vador. Les Seigneurs Sith s'unissent alors pour préparer leur revanche, qui commence par l'extermination des Jedi. Seuls rescapés du massacre, Yoda et Obi-Wan se lancent à la poursuite des Sith. La traque se conclut par un spectaculaire combat au sabre entre Anakin et Obi-Wan, qui décidera du sort de la galaxie...\",\"genre\":\"Science-Fiction, Aventure, Action\",\"poster\":\"https://image.tmdb.org/t/p/w300/rxNnK6AfnqxGRJ8ehCqLKcUCtAG.jpg\",\"id\":\"1895\",\"runtime\":\"2 h 20 min\",\"video\":\"N/A\"},"
			+ "{\"title\":\"Star Wars, épisode IV : Un nouvel espoir\",\"director\":\"George Lucas\",\"year\":\"1977\",\"actors\":\"Mark Hamill, Harrison Ford, Carrie Fisher, Peter Cushing, Alec Guinness\",\"overview\":\"Il y a bien longtemps, dans une galaxie très lointaine... La guerre civile fait rage entre l'Empire galactique et l'Alliance rebelle. Capturée par les troupes de choc de l'Empereur menées par le sombre et impitoyable Dark Vador, la princesse Leia Organa dissimule les plans de l’Étoile Noire, une station spatiale invulnérable, à son droïde R2-D2 avec pour mission de les remettre au Jedi Obi-Wan Kenobi. Accompagné de son fidèle compagnon, le droïde de protocole C-3PO, R2-D2 s'échoue sur la planète Tatooine et termine sa quête chez le jeune Luke Skywalker. Rêvant de devenir pilote mais confiné aux travaux de la ferme, ce dernier se lance à la recherche de ce mystérieux Obi-Wan Kenobi, devenu ermite au cœur des montagnes désertiques de Tatooine...\",\"genre\":\"Aventure, Action, Science-Fiction\",\"poster\":\"https://image.tmdb.org/t/p/w300/yVaQ34IvVDAZAWxScNdeIkaepDq.jpg\",\"id\":\"11\",\"runtime\":\"2 h 1 min\",\"video\":\"https://www.youtube.com/embed/GdXLGZkYabE\"},"
			+ "{\"title\":\"Star Wars, épisode V - L'Empire contre-attaque\",\"director\":\"Irvin Kershner\",\"year\":\"1980\",\"actors\":\"Mark Hamill, Harrison Ford, Carrie Fisher, David Prowse, Billy Dee Williams\",\"overview\":\"Malgré la destruction de l’Étoile Noire, l'Empire maintient son emprise sur la galaxie, et poursuit sans relâche sa lutte contre l'Alliance rebelle. Basés sur la planète glacée de Hoth, les rebelles essuient un assaut des troupes impériales. Parvenus à s'échapper, la princesse Leia, Han Solo, Chewbacca et C-3P0 se dirigent vers Bespin, la cité des nuages gouvernée par Lando Calrissian, ancien compagnon de Han. Suivant les instructions d'Obi-Wan Kenobi, Luke Skywalker se rend quant à lui vers le système de Dagobah, planète marécageuse où il doit recevoir l'enseignement du dernier maître Jedi, Yoda. Apprenant l'arrestation de ses compagnons par les stormtroopers de Dark Vador après la trahison de Lando, Luke décide d'interrompre son entraînement pour porter secours à ses amis et affronter le sombre seigneur Sith...\",\"genre\":\"Aventure, Action, Science-Fiction\",\"poster\":\"https://image.tmdb.org/t/p/w300/nqY9dJeRaSEJlmljOpPA5Tc9moQ.jpg\",\"id\":\"1891\",\"runtime\":\"2 h 4 min\",\"video\":\"N/A\"},"
			+ "{\"title\":\"Star Wars, épisode VI - Le Retour du Jedi\",\"director\":\"Richard Marquand\",\"year\":\"1983\",\"actors\":\"Mark Hamill, Harrison Ford, Carrie Fisher, Billy Dee Williams, Anthony Daniels\",\"overview\":\"L'Empire galactique est plus puissant que jamais : la construction de la nouvelle arme, l’Étoile de la Mort, menace l'univers tout entier... Arrêté après la trahison de Lando Calrissian, Han Solo est remis à l'ignoble contrebandier Jabba Le Hutt par le chasseur de primes Boba Fett. Après l'échec d'une première tentative d'évasion menée par la princesse Leia, également arrêtée par Jabba, Luke Skywalker et Lando parviennent à libérer leurs amis. Han, Leia, Chewbacca, C-3PO et Luke, devenu un Jedi, s'envolent dès lors pour une mission d'extrême importance sur la lune forestière d'Endor, afin de détruire le générateur du bouclier de l’Étoile de la Mort et permettre une attaque des pilotes de l'Alliance rebelle. Conscient d'être un danger pour ses compagnons, Luke préfère se rendre aux mains de Dark Vador, son père et ancien Jedi passé du côté obscur de la Force.\",\"genre\":\"Aventure, Action, Science-Fiction\",\"poster\":\"https://image.tmdb.org/t/p/w300/kBjuLfGqNRsby9TzWQaTlAUe3LB.jpg\",\"id\":\"1892\",\"runtime\":\"2 h 13 min\",\"video\":\"N/A\"},"

			+ "{\"title\":\"Interstellar\",\"director\":\"Christopher Nolan\",\"year\":\"2014\",\"actors\":\"Matthew McConaughey, Anne Hathaway, Jessica Chastain, Michael Caine, Casey Affleck\",\"overview\":\"Dans un futur proche, face à une Terre exsangue, un groupe d'explorateurs utilise un vaisseau interstellaire pour franchir un trou de ver permettant de parcourir des distances jusque-là infranchissables. Leur but :  trouver un nouveau foyer pour l'humanité.\",\"genre\":\"Aventure, Drame, Science-Fiction\",\"poster\":\"https://image.tmdb.org/t/p/w300/qAr3kvQeKu1UMOOrt50kUqEkdlr.jpg\",\"id\":\"157336\",\"runtime\":\"2 h 49 min\",\"video\":\"https://www.youtube.com/embed/USns-G2mxoc\"},"
			+ "{\"title\":\"Le Cinquième Élément\",\"director\":\"Luc Besson\",\"year\":\"1997\",\"actors\":\"Bruce Willis, Gary Oldman, Ian Holm, Milla Jovovich, Chris Tucker\",\"overview\":\"Égypte, 1914. Un groupe de Mondo-shawans débarque sur Terre afin de récupérer quatre pierres représentant les quatre éléments de la vie, afin de les soustraire à l'arrivée imminente de la Première Guerre mondiale. Ils promettent de les rapporter dans 300 ans, lorsque le Mal reviendra, afin d'établir une paix durable. Mais à leur retour, trois siècles plus tard, leur vaisseau est détruit et les pierres disparaissent mystérieusement. Cependant, dans les décombres se trouve une main, à partir de laquelle les scientifiques parviennent à reconstituer Leeloo (Leeloo Mina Lekatariba-Laminatcha Ekbat De Sebat) qui n'est autre que le cinquième élément, une jeune femme dotée d'étonnantes facultés. Alors qu'elle s'enfuit, elle tombe dans un taxi conduit par Korben Dallas...\",\"genre\":\"Aventure, Fantastique, Action, Thriller, Science-Fiction\",\"poster\":\"https://image.tmdb.org/t/p/w300/93b6qmTZaT8hS4aKU3Aqcj217yt.jpg\",\"id\":\"18\",\"runtime\":\"2 h 6 min\",\"video\":\"https://www.youtube.com/embed/5_FGaEq-aCY\"},"
			+ "{\"title\":\"Need for Speed\",\"director\":\"Scott Waugh\",\"year\":\"2014\",\"actors\":\"Aaron Paul, Dominic Cooper, Imogen Poots, Rami Malek, Ramón Rodríguez\",\"overview\":\"À sa sortie de prison, un pilote clandestin trahi par son riche associé participe à une course à travers le pays et prépare sa revanche. Ayant eu vent de ce projet, le traître promet, dès le début de la course, une prime énorme à qui l'empêchera d'aller plus loin.\",\"genre\":\"Action, Crime, Drame, Thriller\",\"poster\":\"https://image.tmdb.org/t/p/w300/asZ0CfhN6zyVO3qb2JprjHFtFVw.jpg\",\"id\":\"136797\",\"runtime\":\"2 h 5 min\",\"video\":\"https://www.youtube.com/embed/dPAax8PhrHo\"},"
			+ "{\"title\":\"Cinquante nuances de Grey\",\"director\":\"Sam Taylor-Johnson\",\"year\":\"2015\",\"actors\":\"Dakota Johnson, Jamie Dornan, Jennifer Ehle, Eloise Mumford, Victor Rasuk\",\"overview\":\"Anastasia Steele accepte de remplacer sa colocataire malade, Katherine, pour interviewer l'homme d'affaires et milliardaire Christian Grey. Jeune PDG séduisant et mystérieux, ce dernier l'intimide. À sa grande surprise, Christian Grey vient la voir au magasin où elle travaille, prétextant des achats. Très attirée par lui, elle se verra rapidement devenir sa soumise. Pour cela un contrat va être rédigé pour permettre de définir les règles de ce jeu dangereux. Cependant, ce contrat devient souvent un sujet tabou et sera changé sans cesse.  À mesure que leur relation progresse, la jeune et innocente Ana est confrontée à un tout nouvel univers aux côtés du riche entrepreneur. Christian a cependant une face sombre : il est adepte du BDSM. La jeune femme doit alors décider si elle est prête ou non à entrer dans cet univers.\",\"genre\":\"Drame, Romance\",\"poster\":\"https://image.tmdb.org/t/p/w300/7HOybXrLLlsqxTeztLjHVEp2Ko4.jpg\",\"id\":\"216015\",\"runtime\":\"2 h 5 min\",\"video\":\"https://www.youtube.com/embed/BVk15K_VYgM\"},"
			+ "{\"title\":\"Indiana Jones et le Temple maudit\",\"director\":\"Steven Spielberg\",\"year\":\"1984\",\"actors\":\"Harrison Ford, Kate Capshaw, Jonathan Ke Quan, Amrish Puri, Roshan Seth\",\"overview\":\"L'archéologue Indiana Jones est obligé de fuir un cabaret de Shanghai après une négociation houleuse. En compagnie de la chanteuse Willie Scott et du petit Short Round, il se retrouve sur un glacier himalayen, dans un village dont une secte a dérobé le diamant magique et enlevé les enfants afin de les sacrifier.\",\"genre\":\"Aventure, Action\",\"poster\":\"https://image.tmdb.org/t/p/w300/kn0AehrLmu5JiV7FV6R4orb8u99.jpg\",\"id\":\"87\",\"runtime\":\"1 h 58 min\",\"video\":\"https://www.youtube.com/embed/http://youtu.be/OdV5jWs676k\"},"
			+ "{\"title\":\"Indiana Jones et la Dernière Croisade\",\"director\":\"Steven Spielberg\",\"year\":\"1989\",\"actors\":\"Harrison Ford, Sean Connery, Denholm Elliott, Alison Doody, John Rhys-Davies\",\"overview\":\"Indiana Jones se lance cette fois-ci à la recherche du Saint Graal, accompagné de l'éminent et très maladroit Dr Henry Jones, son père. Malheureusement, ils ne sont pas les seuls lancés sur la trace du trésor, les nazis le convoitent également.\",\"genre\":\"Aventure, Action\",\"poster\":\"https://image.tmdb.org/t/p/w300/ftdvraBCzt0XiAxIyDwbXu8BgVr.jpg\",\"id\":\"89\",\"runtime\":\"2 h 7 min\",\"video\":\"https://www.youtube.com/embed/http://youtu.be/fE7rnmfVJsQ\"},"
			+ "{\"title\":\"Les Aventuriers De l'Arche Perdue\",\"director\":\"Steven Spielberg\",\"year\":\"1981\",\"actors\":\"Harrison Ford, Karen Allen, Paul Freeman, Ronald Lacey, John Rhys-Davies\",\"overview\":\"1936. Parti à la recherche d'une idole sacrée en pleine jungle péruvienne, l'aventurier Indiana Jones échappe de justesse à une embuscade tendue par son plus coriace adversaire : le Français René Belloq. Revenu à la vie civile à son poste de professeur universitaire d'archéologie, il est mandaté par les services secrets et par son ami Marcus Brody, conservateur du National Museum de Washington, pour mettre la main sur le Médaillon de Râ, en possession de son ancienne amante Marion Ravenwood, désormais tenancière d'un bar au Tibet. Cet artefact égyptien serait en effet un premier pas sur le chemin de l'Arche d'Alliance, celle-là même où Moïse conserva les Dix Commandements. Une pièce historique aux pouvoir inimaginables dont Hitler cherche à s'emparer...\",\"genre\":\"Aventure, Action\",\"poster\":\"https://image.tmdb.org/t/p/w300/4fSlleLcmU2DJcibJugh2lLk6Fh.jpg\",\"id\":\"85\",\"runtime\":\"1 h 55 min\",\"video\":\"https://www.youtube.com/embed/RvXJqRml_Jo\"},"
			+ "{\"title\":\"Indiana Jones et le Royaume du crâne de cristal\",\"director\":\"Steven Spielberg\",\"year\":\"2008\",\"actors\":\"Harrison Ford, Cate Blanchett, Shia LaBeouf, Ray Winstone, Karen Allen\",\"overview\":\"La nouvelle aventure d'Indiana Jones débute dans un désert du sud-ouest des États-Unis. Nous sommes en 1957, en pleine guerre froide. Indy et son copain Mac viennent tout juste d'échapper à une bande d'agents soviétiques à la recherche d'une mystérieuse relique surgie du fond des temps. De retour au Marshall College, le Professeur Jones apprend une très mauvaise nouvelle : ses récentes activités l'ont rendu suspect aux yeux du gouvernement américain. Le doyen Stanforth, qui est aussi un proche ami, se voit contraint de le licencier. À la sortie de la ville, Indiana fait la connaissance d'un jeune motard rebelle, Mutt, qui lui fait une proposition inattendue.\",\"genre\":\"Aventure, Action\",\"poster\":\"https://image.tmdb.org/t/p/w300/eXWk89oBwTpVYb1ClgVhQ2aMIMD.jpg\",\"id\":\"217\",\"runtime\":\"2 h 2 min\",\"video\":\"https://www.youtube.com/embed/09SOPcC-brU\"},"
			+ "{\"title\":\"Transformers\",\"director\":\"Michael Bay\",\"year\":\"2007\",\"actors\":\"Shia LaBeouf, Josh Duhamel, Megan Fox, Rachael Taylor, Tyrese Gibson\",\"overview\":\"Une guerre sans merci oppose depuis des temps immémoriaux deux races de robots extraterrestres : les Autobots et les cruels Decepticons. Son enjeu : la maîtrise de l'univers... Dans les premières années du 21ème siècle, le conflit s'étend à la Terre, et le jeune Sam Witwicky devient, à son insu, l'ultime espoir de l'humanité. Semblable à des milliers d'adolescents, Sam n'a connu que les soucis de son âge : le lycée, les amis, les voitures, les filles.. .Entraîné avec sa nouvelle copine, Mikaela, au cœur d'un mortel affrontement, il ne tardera pas à comprendre le sens de la devise de la famille Witwicky : \\\"Sans sacrifice, point de victoire !\\\"\",\"genre\":\"Aventure, Science-Fiction, Action\",\"poster\":\"https://image.tmdb.org/t/p/w300/v4OFh2kitfqMUO5xcGmFHPbh3hi.jpg\",\"id\":\"1858\",\"runtime\":\"2 h 25 min\",\"video\":\"https://www.youtube.com/embed/4N2YWRJ-ppo\"},"
			+ "{\"title\":\"Transformers 4 : L'Âge de l'extinction\",\"director\":\"Michael Bay\",\"year\":\"2014\",\"actors\":\"Mark Wahlberg, Stanley Tucci, Nicola Peltz, Jack Reynor, Li Bingbing\",\"overview\":\"Alors que l’humanité panse ses plaies, après les événements de Transformers : La Face cachée de la Lune, les Autobots et les Decepticons ont disparu de la surface de la Terre. Mais un groupe formé de financiers et scientifiques puissants et ingénieux étudient les invasions successives des Transformers, afin de repousser les limites de la technologie au-delà de ce qu’ils peuvent contrôler. Et pendant ce temps, une menace Transformer ancienne et puissante prend la Terre pour cible.\",\"genre\":\"Science-Fiction, Action, Aventure\",\"poster\":\"https://image.tmdb.org/t/p/w300/qNrQD5arbk10PTCTMB8Xh2VejLr.jpg\",\"id\":\"91314\",\"runtime\":\"2 h 45 min\",\"video\":\"N/A\"},"
			+ "{\"title\":\"Transformers 2 : La Revanche\",\"director\":\"Michael Bay\",\"year\":\"2009\",\"actors\":\"Shia LaBeouf, Megan Fox, Josh Duhamel, Rainn Wilson, Tyrese Gibson\",\"overview\":\"Si Sam a fait ce qu'il a pu pour tirer un trait sur le conflit qui a eu lieu à Mission City et revenir à ses préoccupations quotidiennes, la guerre entre les Autobots et les Decepticons, tout en étant classée secret défense, a entraîné plusieurs changements. Le secteur 7 a ainsi été dissout et son plus fidèle soldat, l'agent Simmons, a été révoqué sans ménagement. Résultat : une nouvelle agence, NEST, a été mise en place...\",\"genre\":\"Science-Fiction, Action, Aventure\",\"poster\":\"https://image.tmdb.org/t/p/w300/eKeSLc1l3urEgyOzaXXpSy9DmqC.jpg\",\"id\":\"8373\",\"runtime\":\"2 h 31 min\",\"video\":\"https://www.youtube.com/embed/CbNnFiedYtM\"},"
			+ "{\"title\":\"Transformers 3 : La Face cachée de la Lune\",\"director\":\"Michael Bay\",\"year\":\"2011\",\"actors\":\"Shia LaBeouf, John Malkovich, Ken Jeong, Frances McDormand, Josh Duhamel\",\"overview\":\"Les Autobots Bumblebee, Ironhide, Ratchet et Sideswipe menés par Optimus Prime sont de retour en action, toujours contre les maléfiques Decepticons, bien décidés à se venger de leur défaite. Dans ce nouvel opus, Autobots et Decepticons s'avèrent avoir été impliqués dans la course à l'espace que s'étaient lancés les États-Unis et l'URSS. Une course qui va remonter jusqu'à Sam Witwicky, qui aura à nouveau besoin de l'aide de ses amis robots. De nouveaux Transformers rejoignent la bataille dont l'ancien Decepticons Shockwave, devenu dictateur sur la planète Cybertron alors que les Autobots et les Decepticons poursuivent leur guerre sur Terre.\",\"genre\":\"Action, Science-Fiction, Aventure\",\"poster\":\"https://image.tmdb.org/t/p/w300/tPAZjcLPXgjc3GztVmZNj1SHOZ3.jpg\",\"id\":\"38356\",\"runtime\":\"2 h 18 min\",\"video\":\"https://www.youtube.com/embed/4Iec8A7NR1s\"},"
			+ "{\"title\":\"Deadpool\",\"director\":\"Tim Miller\",\"year\":\"2016\",\"actors\":\"Ryan Reynolds, Morena Baccarin, T.J. Miller, Ed Skrein, Brianna Hildebrand\",\"overview\":\"Deadpool, est l'anti-héros le plus atypique de l'univers Marvel. À l'origine, il s'appelle Wade Wilson : un ancien militaire des Forces Spéciales devenu mercenaire. Après avoir subi une expérimentation hors norme qui va accélérer ses pouvoirs de guérison, il va devenir Deadpool. Armé de ses nouvelles capacités et d'un humour noir survolté, Deadpool va traquer l'homme qui a bien failli anéantir sa vie.\",\"genre\":\"Action, Aventure, Comédie, Romance\",\"poster\":\"https://image.tmdb.org/t/p/w300/eJyRzC5uFjQryu8Hm61yqtrzj4S.jpg\",\"id\":\"293660\",\"runtime\":\"1 h 48 min\",\"video\":\"https://www.youtube.com/embed/XWtH7anz7io\"}"
			
			+ "]"
			+"}";
	
	public AdminRegistration(){
	}
    
    public boolean isAdmin(Admin admin){
    	List<Admin> adminList = adminDBaccess.allAdmin();
    	for(Admin a : adminList){
    		if(a.getIdAdmin().compareTo(admin.getIdAdmin())==0 &&
    				a.getPassword().compareTo(admin.getPassword())==0){
    			return true;
    		}
    	}
    	return false;
    }
    
    public ArrayList<String> allUser(){
    	return userDBaccess.all();
    }
    
    public String removeUser(String user){
    	return userDBaccess.removeUser(user);
    }
    
    public String infoUser(String user){
    	return userDBaccess.infoUser(user).toString();
    }
    
    public String allOrder(String user){
    	return orderDBaccess.getAllOrder(user).toString();
    }
    
    public String getAllOrderLine(String idOrder){
    	return orderLineDBaccess.getAllOrderLine(idOrder).toString();
    }
    
    public MovieList allMovies(){
    	return movieDBaccess.allMovies();
    }
    
    public void cleanAll(){
    	userDBaccess.clean();
    	orderDBaccess.clean();
    	orderLineDBaccess.clean();
    	movieDBaccess.clean();
    }
    
    public void updateProduct(String idProduct, String newStock, String newPrice){
    	movieDBaccess.updateProduct(idProduct, newStock, newPrice);
    }
    
    public void addMovie(String product){
    	JsonReader r = Json.createReader(new StringReader(product));
    	JsonObject obj = r.readObject();
    	
    	Movie movieDVD = new Movie();
    	movieDVD.setTitle(obj.getString("title"));
    	movieDVD.setYear(obj.getString("year").substring(0, 4));
    	movieDVD.setRuntime(obj.getString("runtime"));
    	movieDVD.setGenre(obj.getString("genre"));
    	movieDVD.setDirector(obj.getString("director"));
    	movieDVD.setActors(obj.getString("actors"));
    	movieDVD.setOverview(obj.getString("overview"));
    	movieDVD.setPoster(obj.getString("poster"));
    	movieDVD.setTrailer(obj.getString("trailer"));
    	movieDVD.setIdTMDB(Integer.parseInt(obj.getString("idTMDB")));
    	movieDVD.setPrice(Integer.parseInt(obj.getString("priceDVD")));
    	movieDVD.setStock(Integer.parseInt(obj.getString("stockDVD")));
    	movieDVD.setSupport("DVD");
    	
    	Movie movieBR = new Movie();
    	movieBR.setTitle(obj.getString("title"));
    	movieBR.setYear(obj.getString("year").substring(0, 4));
    	movieBR.setRuntime(obj.getString("runtime"));
    	movieBR.setGenre(obj.getString("genre"));
    	movieBR.setDirector(obj.getString("director"));
    	movieBR.setActors(obj.getString("actors"));
    	movieBR.setOverview(obj.getString("overview"));
    	movieBR.setPoster(obj.getString("poster"));
    	movieBR.setTrailer(obj.getString("trailer"));
    	movieBR.setIdTMDB(Integer.parseInt(obj.getString("idTMDB")));
    	movieBR.setPrice(Integer.parseInt(obj.getString("priceBR")));
    	movieBR.setStock(Integer.parseInt(obj.getString("stockBR")));
    	movieBR.setSupport("BR");
    	
    	Movie movieCN = new Movie();
    	movieCN.setTitle(obj.getString("title"));
    	movieCN.setYear(obj.getString("year").substring(0, 4));
    	movieCN.setRuntime(obj.getString("runtime"));
    	movieCN.setGenre(obj.getString("genre"));
    	movieCN.setDirector(obj.getString("director"));
    	movieCN.setActors(obj.getString("actors"));
    	movieCN.setOverview(obj.getString("overview"));
    	movieCN.setPoster(obj.getString("poster"));
    	movieCN.setTrailer(obj.getString("trailer"));
    	movieCN.setIdTMDB(Integer.parseInt(obj.getString("idTMDB")));
    	movieCN.setPrice(Integer.parseInt(obj.getString("priceCN")));
    	movieCN.setStock(0);
    	movieCN.setSupport("CN");
    	
    	if(!movieDBaccess.containsMovie(movieDVD.getIdTMDB())){
        	adminDBaccess.addProduct(movieDVD);
        	adminDBaccess.addProduct(movieBR);
        	adminDBaccess.addProduct(movieCN);
    	}
    	
    }

    public void chargeAllMovies(){
		JsonReader r = Json.createReader(new StringReader(manyFilm));
		JsonObject obj = r.readObject();
		JsonArray array = obj.getJsonArray("movies");
		MovieList filmList = new MovieList();
		for(int i=0 ; i<array.size() ; i++){
			Movie dvd = new Movie();
			JsonObject filmJson = array.getJsonObject(i);
			if(movieDBaccess.containsMovie(Integer.parseInt(filmJson.getString("id")))){
				break;
			}
			
			try{
				dvd.setTitle(filmJson.getString("title"));
			}catch(Exception e){}
			try{
				dvd.setTrailer(filmJson.getString("video"));
			}catch(Exception e){}
			try{
				dvd.setOverview(filmJson.getString("overview"));
			}catch(Exception e){}
			try{
				dvd.setRuntime(filmJson.getString("runtime"));
			}catch(Exception e){}
			try{
				dvd.setActors(filmJson.getString("actors"));
			}catch(Exception e){}
			try{
				dvd.setGenre(filmJson.getString("genre"));
			}catch(Exception e){}
			try{
				dvd.setDirector(filmJson.getString("director"));
			}catch(Exception e){}
			try{
				dvd.setYear(filmJson.getString("year"));
			}catch(Exception e){}
			try{
				dvd.setIdTMDB(Integer.parseInt(filmJson.getString("id")));
			}catch(Exception e){}
			try{
				dvd.setPoster(filmJson.getString("poster"));
			}catch(Exception e){
			}
			try{
				dvd.setSupport("DVD");
			}catch(Exception e){
			}
			dvd.setStock(12);
			dvd.setPrice(7);
			filmList.add(dvd);
			
			Movie bluray = new Movie();
			try{
				bluray.setTitle(filmJson.getString("title"));
			}catch(Exception e){}
			try{
				bluray.setTrailer(filmJson.getString("video"));
			}catch(Exception e){}
			try{
				bluray.setOverview(filmJson.getString("overview"));
			}catch(Exception e){}
			try{
				bluray.setRuntime(filmJson.getString("runtime"));
			}catch(Exception e){}
			try{
				bluray.setActors(filmJson.getString("actors"));
			}catch(Exception e){}
			try{
				bluray.setGenre(filmJson.getString("genre"));
			}catch(Exception e){}
			try{
				bluray.setDirector(filmJson.getString("director"));
			}catch(Exception e){}
			try{
				bluray.setYear(filmJson.getString("year"));
			}catch(Exception e){}
			try{
				bluray.setIdTMDB(Integer.parseInt(filmJson.getString("id")));
			}catch(Exception e){}
			try{
				bluray.setPoster(filmJson.getString("poster"));
			}catch(Exception e){
			}
			try{
				bluray.setSupport("BR");
			}catch(Exception e){
			}
			bluray.setStock(15);
			bluray.setPrice(15);
			filmList.add(bluray);
			
			Movie numeric = new Movie();
			try{
				numeric.setTitle(filmJson.getString("title"));
			}catch(Exception e){}
			try{
				numeric.setTrailer(filmJson.getString("video"));
			}catch(Exception e){}
			try{
				numeric.setOverview(filmJson.getString("overview"));
			}catch(Exception e){}
			try{
				numeric.setRuntime(filmJson.getString("runtime"));
			}catch(Exception e){}
			try{
				numeric.setActors(filmJson.getString("actors"));
			}catch(Exception e){}
			try{
				numeric.setGenre(filmJson.getString("genre"));
			}catch(Exception e){}
			try{
				numeric.setDirector(filmJson.getString("director"));
			}catch(Exception e){}
			try{
				numeric.setYear(filmJson.getString("year"));
			}catch(Exception e){}
			try{
				numeric.setIdTMDB(Integer.parseInt(filmJson.getString("id")));
			}catch(Exception e){}
			try{
				numeric.setPoster(filmJson.getString("poster"));
			}catch(Exception e){
			}
			try{
				numeric.setSupport("CN");
			}catch(Exception e){
			}
			numeric.setStock(0);
			numeric.setPrice(4);
			
			adminDBaccess.addProduct(numeric);
			adminDBaccess.addProduct(dvd);
			adminDBaccess.addProduct(bluray);
		}
	
		movieDBaccess.chargeDatabase(filmList);
    }

}
