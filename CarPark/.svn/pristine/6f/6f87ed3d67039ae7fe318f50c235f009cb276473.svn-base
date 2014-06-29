Navod na spustenie:
1. Vytvorte databazu s nasledovnymi parametrami:
	url: jdbc:derby://localhost:1527/pa165
	username: pa165
	password: pa165
2. Z adresara hlavneho projektu(rovnaky adresar, kde je ulozeny tento navod) z konzoly spustite prikaz mvn clean install
3. Deploy na embed server pre jednotlive projekty treba spravit zvlast a z priecinka projektu
   
   CarParkREST:
	 mvn tomcat7:run
	 deployuje sa na 	
	 http://localhost:8090/pa165/resources/ 

   CarParkClient:
	mvn tomcat7:run
	deployuje sa na 	
	http://localhost:8095/pa165/client/

	Klient vyuziva proxy servlet pre ajaxovu komunikaciu mimo svojho kontextu.
	defaultne je nastaveny na http://localhost:8090/pa165/resources/ V pripade potreby
	je mozne zmenit vo web.xml projektu.

   CarParkWeb:
	mvn tomcat7:run
	deployuje sa na 	
	http://localhost:8085/pa165/web/

	Pre prihlasenia sa do aplikacie sluzi virtualny uzivatel superuser(superuser/andrej).
	sluzi len na to, aby sa vytvorili zamestnanci s pozadovanymi pravami. Tento uzivatel si nemoze
        zakladat rezervacie aut.