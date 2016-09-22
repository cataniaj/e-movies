/*
 *	Author:      Jérémy Leyvraz
 *	Date:        21 sept. 2016
 */

package fr.imag.themoviedatabase;

import java.io.StringReader;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.imag.ejb.dbaccess.AdminDatabaseAccessEJB;
import fr.imag.ejb.dbaccess.MovieDatabaseAccessEJB;
import fr.imag.entities.Admin;
import fr.imag.entities.Movie;
import fr.imag.entities.MovieList;
import fr.imag.entities.Product;

@Stateless
public class AdminRegistration {

	@EJB private AdminDatabaseAccessEJB adminDBaccess;
	@EJB private MovieDatabaseAccessEJB movieDBaccess;
  
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
			+ "{\"title\":\"Grease\",\"director\":\"Randal Kleiser\",\"year\":\"1978\",\"actors\":\"John Travolta, Olivia Newton-John, Stockard Channing, Jeff Conaway, Didi Conn\",\"overview\":\"Durant l'été 1958, Sandy Olsson, une étudiante australienne en vacances aux États-Unis, rencontre Danny Zuko, le chef de la bande des T-Birds. Leur amour est cependant interrompu par la fin des vacances car Sandy doit retourner en Australie.Toutefois, le hasard fait que Sandy reste en Amérique et qu'elle intègre le lycée Rydell, le même que celui où étudie Danny. Elle y rencontre un groupe de filles, les « Pink Ladies », mené par Betty Rizzo. Sans savoir qu'ils sont tout proches, Danny et Sandy racontent, chacun de leur côté, à leurs amis leur amour de vacances. Danny et Sandy finissent par se rencontrer et décident de reprendre leur relation bien que tout les oppose au lycée...\",\"genre\":\"Musique\",\"poster\":\"https://image.tmdb.org/t/p/w300/7a9jmNPcdIle9YD5LcjfyrqLoc9.jpg\",\"id\":\"621\",\"runtime\":\"1 h 50 min\",\"video\":\"https://www.youtube.com/embed/2j17nSmVghI\"}"
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
    
    public MovieList allMovies(){
    	return movieDBaccess.allMovies();
    }
    
    public void cleanMovies(){
    	movieDBaccess.cleanMovies();
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
    	
    	adminDBaccess.addProduct(movieDVD);
    	adminDBaccess.addProduct(movieBR);
    	adminDBaccess.addProduct(movieCN);
    	
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
			
			System.out.println("TEST - -------------------"+filmJson.getString("id"));
			
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
			dvd.setStock((int)(Math.random()*(20-9) + 9));
			dvd.setPrice((int)(Math.random()*(20-3) + 3));
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
			bluray.setStock((int)(Math.random()*(20-9) + 9));
			bluray.setPrice((int)(Math.random()*(20-3) + 3));
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
			numeric.setPrice((int)(Math.random()*(20-3) + 3));
			
			adminDBaccess.addProduct(numeric);
			adminDBaccess.addProduct(dvd);
			adminDBaccess.addProduct(bluray);
		}
	
		movieDBaccess.chargeDatabase(filmList);
    }

}
