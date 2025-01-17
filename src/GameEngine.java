import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Classe GameEngine - le moteur du jeu d'aventure Zuul.
 *
 * @author Sivaharan
 */
public class GameEngine
{
    private Room          aCurrentRoom; 
    private Parser        aParser;
    private UserInterface aGui;
    private Item          aItem;
    private Player        aPlayer;
    private ItemList      aInventary;
    private int           aTimeLimite;
    
    /**
     * Création d'un constructeur par défaut dans la classe Game
     * qui fait appel a la fonction createRoom() et une attribut
     * aParcer qui va se référent à l'objet new Parser().
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.createRooms();
        this.aPlayer = new Player("Moi", aCurrentRoom);
        this.aTimeLimite = 16;
    }// GameEngine()
    
    /**
     * Un accesseur pour le time
     */
    private int getTimeLimite()
    { return this.aTimeLimite; }// getTimeLimite()
    
    public void setGUI(final UserInterface pUserInterface)
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }// setGUI()
    
    /**
     * Un procédure privée qui accueil les joueurs dans la classe game,
     * on leur expliquant dans le monde dans laquelle ils/elles sont
     * et on leur donne les commandes à suivre pour se déplacé.
     */
    private void printWelcome() //privée
    {
        this.aGui.println("Bienvenue dans le Monde du Forgeron!");
        this.aGui.println("Le monde du Forgeron est une nouvelle aventure captivante,\nun jeu qui vous plonge dans l'univers du forgeron.");
        this.aGui.println( "" );
        this.aGui.println("Voici les commande mise a disposition :");
        this.aGui.println(this.aParser.getCommandString());
        this.aGui.println(" ");
        this.Touche();
        Room aCurrentRoom = this.aPlayer.getLocalisation();
        this.aGui.print(" ");
        this.aGui.println(this.aCurrentRoom.getLongDescription());
        this.aGui.print(" ");
        if ( this.aCurrentRoom.getImageName() != null )
            this.aGui.showImage( this.aCurrentRoom.getImageName() );
    }// printWelcome()
    
    /**
     * Création d'une Procédure privée dans la classe Game, cette fonction
     * permet d'une part de déclarer les Room's, les pièce de notre jeux
     * en donnant les descriptions de chaque pièce.
     * D'autre part permet aussi de déclarer les sorties de chaque pièce
     * pour ça en a utiliser une fonction issue de la class Room, 
     * setExits() pour chaque pièce qu'on a créer.
     * On initialise le départ du joueur avec aCurrentRoom en mettent
     * la pièce qu'on veut, ici c'est la Salle de Garde.
     */
    private void createRooms() //privée
    {
        /* Création des 11 lieux */
        
        Room vSalledeGarde    = new Room("dans la salle de Garde", "Images/SalledeGarde.png"); 
        Room vPiecedePassage1 = new Room("dans la pièce de passage entre la salle de Garde et l'Armurerie.", "Images/PieceVide1.png");
        Room vArmurerie       = new Room("dans l'Armurerie,où sont stockés les armes. ", "Images/Armurerie.png");
        Room vPiecedePassage2 = new Room("dans la pièce de passage entre l'Armurerie et le Cachot.", "Images/PieceVide2.png");
        Room vCachot          = new Room("dans la salle où les prisonniers sont enfermés pour la peine de mort.", "Images/Cachot.png");
        Room vPiecedePassage3 = new Room("dans la pièce de passage entre le Cachot et le Coin Repos.", "Images/PieceVide3.png");
        Room vCoinRepos       = new Room("dans le Coin Repos, salle où l’on ce repose,\n les gardes et d’autre qui vivent dans le sous-sol.", "Images/CoinRepos.png");
        Room vPiecedePassage4 = new Room("dans la pièce de passage entre le Coin Repos et la Cave de vin.", "Images/PieceVide4.png");
        Room vCaveaVin        = new Room("dans le Cave à vin, lieux où l’on a tous type de vin\n et même des brevages inconnue à l’extérieur du sous-sol.", "Images/CaveaVin.png");
        Room vSalledeTresor   = new Room("dans la salle de Trésor, salle renferment tous les trésors.", "Images/SalledeTresor.png");
        Room vAtelierdeForge  = new Room("dans l'Atelier de Forge, salle ultime du Forgeron\n où il conçoit ces armes et qui lie à la sortie", "Images/AtelierdeForge.png");
        Room vPieceAvisite1    = new Room("dans l'ancienne chambre secrète du roi, où il se retirait pour accomplir\n ses affaires personnelles. ", "Images/PieceVisite1.jpg");
        Room vPieceAvisite2    = new Room("dans la salle de réception où le roi accueillait ses invités,\n mais où des spectres résideraient.", "Images/PieceVisite2.jpg");
        Room vPieceAvisite3    = new Room("dans l'ancien coin de repos, désormais habité par des fantômes.", "Images/PieceVisite3.jpg");
        
        
        
        /* Positionner les sorties */
        /* On commence par le room qu'on commence,ensuite on fait appel 
        à la méthode. */
        
       vSalledeGarde.setExits("bas", vPiecedePassage1, false);
       vPiecedePassage1.setExits("haut", vSalledeGarde, true);
       vPiecedePassage1.setExits("gauche", vArmurerie, true);
       vArmurerie.setExits("sud", vPiecedePassage2, false);
       vPiecedePassage2.setExits("sud", vCachot, false);
       vCachot.setExits("droite", vPiecedePassage3, false);
       vPiecedePassage3.setExits("droite", vCoinRepos, true);
       vPiecedePassage3.setExits("bas", vPieceAvisite1, true);
       vCoinRepos.setExits("nord", vPiecedePassage4, false);
       vPiecedePassage4.setExits("nord", vCaveaVin, false);
       vCaveaVin.setExits("droite", vSalledeTresor, false);
       vCaveaVin.setExits("gauche", vPiecedePassage1, false);
       vSalledeTresor.setExits("haut", vAtelierdeForge, false);
       vSalledeTresor.setExits("gauche", vCaveaVin, false);
       vAtelierdeForge.setExits("bas", vSalledeTresor, false);
       vPieceAvisite1.setExits("haut", vPiecedePassage3, false);
       vPieceAvisite1.setExits("droite", vPieceAvisite2, false);
       vPieceAvisite2.setExits("gauche", vPieceAvisite1, false);
       vPieceAvisite2.setExits("droite", vPieceAvisite3, false);
       vPieceAvisite3.setExits("gauche", vPieceAvisite2, false);

       
       /* Création des items */
       
       //Pack Forgeron
       Item vGantsdeForge   = new Item("GantsDeForge", "Equipement primaire du forgeron.", "Ramasser ? (Les réponses sont en minuscule)", 1, "oui");
       Item vMasquedeForge  = new Item("MasqueDeForge", "Equipement pour se protégé des flammes.", "Qui êtes-vous ? (Les réponses sont en minuscule)", 1, "forgeron");
       Item vPotions        = new Item("Potions", "Liquide spéciale utilisé par le Forgeron.", "Ramasser ? (Les réponses sont en minuscule)", 1, "oui");
       
       //Marteau de Forge
       Item vFerBrut        = new Item("FerBrut", "Matière première pour forgé des arme robuste.", "Ramasser ? (Les réponses sont en minuscule)", 1, "oui");
       Item vBoisdeChene    = new Item("BoisDeChêne", "Matériau robuste et durable, \nparfait pour fabriquer des outils et des structures solides.", "Quel sont leurs objectifs principal ? \n Dans l’ordre suivant, objectif du révolutionnaire et du voleur.  (Les réponses sont en minuscule)", 1, "vengeance et richesse");
       Item vCuireRenforce  = new Item("CuireRenforcé", "Matériau solide et résistant, idéal pour fabriquer des armures et des équipements de protection durables.", "Ramasser ? (Les réponses sont en minuscule)", 1, "oui");
       
       //Enclume
       Item vAcierTrempe      = new Item("AcierTrempé", "Matériau extrêmement résistant et durable, \nidéal pour fabriquer des armes et des outils de haute qualité.", "Je suis le commencement de l’effroi. \n La fin de la durée et de l’espace. \n Le commencement de toute extrémité. \n Et la fin de chaque contrée. \n Qui suis-je ?  (Les réponses sont en minuscule)", 1, "la lettre e");
       Item vPierreVolcanique = new Item("PierreVolcanique", "Matériau rare et puissant, idéal pour forger \ndes armes magiques et des armures résistantes à la chaleur.", "Quel sont leurs objectifs principal ? \n Dans l’ordre suivant, objectif du révolutionnaire et du voleur.  (Les réponses sont en minuscule)", 1, "vengeance et richesse");
       Item vFerForge         = new Item("FerForgé", "Matériau solide et malléable, parfait pour créer des armes, \ndes outils et des décorations durables.", "Je suis le commencement de l’effroi. \n La fin de la durée et de l’espace. \n Le commencement de toute extrémité. \n Et la fin de chaque contrée. \n Qui suis-je ?  (Les réponses sont en minuscule)", 1, "la lettre e");
       
       //Fours de Forge
       Item vBriquesRefractaires = new Item("BriquesRefractaires", "Matériau résistant à haute température, \nidéal pour construire des fours et des cheminées durables.", "Ramasser ? (Les réponses sont en minuscule)", 1, "oui");
       Item vFonte               = new Item("Fonte", "Matériau lourd et résistant, parfait pour forger \ndes armes puissantes et des armures robustes.", "Qui êtes-vous ?  (Les réponses sont en minuscule)", 1, "forgeron");
       Item vCharbonSpecial      = new Item("CharbonSpécial", "Matériau rare et puissant, \nidéal pour déclenché un brassier.", "Ramasser ? (Les réponses sont en minuscule)", 1, "oui");
       
       // Item nouritture
       Item vViande = new Item("Viande", "Vous permet d'avoir +2 sur la capacité de l'inventaire", "Ramasser ? (Les réponses sont en minuscule)", 0, "oui");
       
       // Pour ne rien avoir dans les pièces
       Item vVide = new Item("", "", "", 0, "");
       
       // Création du Téléporteur
       Beamer vBeamer = new Beamer("Teleporteur", "Vous permet de vous téléporter dans une room où vous avez charger le téleporteur", "Ramasser ? (Les réponses sont en minuscule)", 2, "oui");
       
       /* Placé les item dans les rooms*/
       vSalledeGarde.setItems("Fonte", vFonte);
       vSalledeGarde.setItems("MasqueDeForge", vMasquedeForge);
       
       vArmurerie.setItems("AcierTrempé", vAcierTrempe);
       vArmurerie.setItems("FerForgé", vFerForge);
       
       vCaveaVin.setItems("BriquesRefractaires", vBriquesRefractaires);
       vCaveaVin.setItems("CharbonSpécial", vCharbonSpecial);
       
       vCoinRepos.setItems("Potions", vPotions);
       vCoinRepos.setItems("GantsDeForge", vGantsdeForge);
       
       vCachot.setItems("PierreVolcanique", vPierreVolcanique);
       vCachot.setItems("BoisDeChêne", vBoisdeChene);
       
       vSalledeTresor.setItems("FerBrut", vFerBrut);
       vSalledeTresor.setItems("CuireRenforcé", vCuireRenforce);
       
       vPiecedePassage1.setItems("", vVide);
       vPiecedePassage2.setItems("Viande", vViande);
       vPiecedePassage3.setItems("", vVide);
       vPiecedePassage4.setItems("", vVide);
       
       vAtelierdeForge.setItems("", vVide);
       
       vPieceAvisite2.setItems("teleporteur", vBeamer);
       
       /* Initialiser le lieu courant */
       this.aCurrentRoom = vSalledeGarde;
    }// createRooms()
    
    /**
     * Création d'une fonction privée booléenne avec comme paramètre 
     * command. Qui a pour but de vérifier tous les cas possibles,
     * c'est-à-dire vérifié la structure écrite avec le clavier et 
     * de renvoyer les messages adéquatte.
     */
    public void interpretCommand(final String pCommandLine) //privée
    {
        this.aGui.println( "> " + pCommandLine );
        Command vCommand = this.aParser.getCommand(pCommandLine);
        this.aGui.println(" ");
        
        if (vCommand.isUnknown())
        {
            this.aGui.println("Je n'ai pas compris ce que vous écrivez...");
            this.aGui.println(" ");
            return;
        }
        
        String vCommandWord = vCommand.getCommandWord();
        if (vCommandWord.equals("aide"))
        {   this.printHelp();  }
        
        else if (vCommandWord.equals("va"))
        {   
            this.goRoom(vCommand);
            this.aTimeLimite--;
        }
        
        else if (vCommandWord.equals("quitter")) 
        {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "quitter quoi? C'est juste 'quitter'." );
            else
                this.endGame();
        }
        
        else if (vCommandWord.equals("regarder"))
        {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "regarder quoi? C'est juste 'regarder'." );
            else
                this.look();
        }
        
        else if (vCommandWord.equals("manger"))
        {
            this.eat(vCommand);
        }
        
        else if (vCommandWord.equals("retour"))
        {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "retour quoi? C'est juste 'retour'." );
            else
                this.back();
        }
        
        else if (vCommandWord.equals("teste"))
        {
            if (!vCommand.hasSecondWord())
            {
                this.aGui.println("Que voulez-vous tester ?");
            }
            else 
            {
                this.teste(vCommand);
            }
        }
        
        else if (vCommandWord.equals("prendre"))
        {   
            String vItemQ = this.getItemQ(vCommand.getSecondWord());
            if (!vCommand.hasSecondWord())
            { this.aGui.println("Que voulez-vous prendre ?"); }
    
            else 
            { 
                String vRep = javax.swing.JOptionPane.showInputDialog(vItemQ);
                if (vRep.equals(this.getItemR(vCommand.getSecondWord())))
                {   this.take(vCommand); }
                else 
                { 
                    this.aGui.println(" Réponse Incorrecte ! Vous ne pouvez pas récupérer l'Item");
                    this.aGui.println(" Pour les questions 'Ramasser ?' répondez simplement par un 'oui'.");
                    this.aGui.println(" Retentez votre chance :)");
                }
        }
        }
        
        else if (vCommandWord.equals("laisser"))
        {
            if (!vCommand.hasSecondWord())
            { this.aGui.println("Que voulez-vous laisser ?"); }
            else
            { this.drop(vCommand); }
        }
        
        else if (vCommandWord.equals("inventaire"))
        {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "C'est juste 'inventaire' !!!" );
            else
                this.InventaryItem();
        }
        
        else if (vCommandWord.equals("charger"))
        {
            if (!vCommand.hasSecondWord())
            { this.aGui.println("charger quoi ?"); }
            else
            { this.charge(vCommand); }
        }
        
        else if (vCommandWord.equals("teleporte"))
        {
            if (!vCommand.hasSecondWord())
            { this.aGui.println("teleporte avec quoi ?"); }
            else
            { this.teleport(vCommand); }
        }
        
        else
        {
            this.aGui.println("Erreur du programmeur : commande non reconnue !");
            this.aGui.println(" ");
        }
        
        if (this.getTimeLimite() <= 0)
        {
            this.endGame();
        }
    }// interpretCommand(final String pCommandLine)
    
    /**
     * Une fonction permettent de récupérer les questions des items avec le nom de l'item
     */
    public String getItemQ(final String pItemName) 
    {
        Item vItem = this.aPlayer.getLocalisation().getItems(pItemName);
        if (vItem != null) 
        { return vItem.getItemPrix();}
        return null;
    }// getItemQ(.)
    
    /**
     * Une fonction permettent de récupérer les réponses des items avec le nom de l'item
     */
    public String getItemR(final String pItemName) 
    {
        Item vItem = this.aPlayer.getLocalisation().getItems(pItemName);
        if (vItem != null) 
        { return vItem.getReponse();}
        return null;
    }// getItemR(.)
    
    /**
     * Un procédure privée qui aide les joueurs dans la classe game,
     * qui leur rappel les instruction pour se déplacé et d'autre
     * comme "aide" ou "quitter".
     */
    private void printHelp() //privée
    {
        this.aGui.println(" ");
        this.aGui.println("Vous êtes perdu mentalement et/ou dans le jeux ?");
        this.aGui.println("Vous êtes dans le sous-sol d'un château/donjon.");
        this.aGui.println(" ");
        this.aGui.println("Vos commandes sont :");
        this.aGui.println(this.aParser.getCommandString());
        this.aGui.println(" ");
        this.Touche();
        this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
        this.aGui.print(" ");
    }// printHelp()

    /**
     * Création d'une procédure privée look() 
     */
    private void look()
    {
        this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
        this.aGui.println(" Il vous reste encore " + this.getTimeLimite() + " déplacement, arrivé à 0 vous perdez.");
        this.aGui.println(" ");
    }// look()
    
    /**
     * Création d'une procédure privée eat() qui sera utilise plus tard dans le jeux
     * qui reverra un simple message
     */
    private void eat(final Command pCommand)
    {
        if (!pCommand.hasSecondWord())
        {
            this.aGui.println("Que voulez-vous mangez ?");
            return;
        }
        
        String vNourriture = pCommand.getSecondWord();
        Item vItemNourriture = this.aPlayer.getInventaryItem(vNourriture);
        
        if (vItemNourriture == null)
        { this.aGui.println("Il n'y a rien de comestible !"); }
        else if (vNourriture.equals("Viande"))
        {
            this.aPlayer.getLocalisation().removeItem(vNourriture);
            this.aPlayer.setPoids(this.aPlayer.getPoids() + 2);
            this.aGui.println("Vous venez de manger de la Viande :)");
            this.aGui.println("Vous venez d'augmenter la capacité de votre inventaire de +2");
            this.aPlayer.dropInventaryItem(vNourriture);
            this.aGui.println("");
        }
        else if (!vNourriture.equals("Viande"))
        { this.aGui.println("Ce n'est pas comestible :( "); }
        else 
        { this.aGui.println("Vous avez déjà manger :) "); }
    }// eat()
    
    /**
     * Création d'une procédure privée goRoom dans la classe Game,
     * cette fonction prend une variable qui est la direction (pDirection)
     * qui sera le 2e mots vérifier par pDirection.hasSecondWord() 
     * pour voir l'existence du 2e mots.
     * Si c'est le cas on utilise getSecondWord(), pour définir d'office
     * que ce sera le 2e mots, en fonction du String qu'on aura écrit
     * il va se dirigé vers la sortie désiré en modifiant a CurrentRoom
     * avec la fonction getExit de la class Room.
     * Enfin pour n'importe quel réponse il lui renverra 
     * printLocalisationInfo().
     */
    private void goRoom(final Command pDirection) // pDirection contient deux variables
    {
        boolean vSecondRoom = pDirection.hasSecondWord(); // on verifie avec la méthode qui dans la classe Command
        if (vSecondRoom != true)
        { 
            this.aGui.println("Où voulez-vous allez ?"); 
            this.aGui.println(" ");
            return;
        }
        
        String vDirection = pDirection.getSecondWord();
        Room vNextRoom = this.aPlayer.getLocalisation().getExit(vDirection);
        
        if (vNextRoom == null) 
        {
            this.aGui.println("Direction inconnue !");
            this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
            this.aGui.println(" ");
            return;
        }
        else
        {
            if (this.aPlayer.getLocalisation().isTrapDoor(vDirection) == true)
            {
                this.aPlayer.resetBack();
                this.aGui.println("Vous venez d'entréer dans une pièce où la porte est piègé");
            }
            this.aPlayer.setPreviousRooms(this.aPlayer.getLocalisation()); 
            this.aPlayer.setLocalisation(vNextRoom);
            
            this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
            if ( this.aPlayer.getLocalisation().getImageName() != null )
                this.aGui.showImage( this.aPlayer.getLocalisation().getImageName() );
            this.aGui.println(" ");
        }
    }// goRoom(final Command pDirection)
    
    /**
     * Pour quitter le jeux
     */
    private void endGame()
    {
        if (this.getTimeLimite() == 0) 
        {
            this.aGui.println( " Votre temps est écoulé vous vous êtes déplacé plus de 16 fois");    
            this.aGui.println( " :( Vous êtes éliminé(e) !");
        }
        else if (this.aPlayer.getNbInventaire() == 13 && this.aPlayer.getLocalisation().getImageName() == "Images/AtelierdeForge.png")
        {
            this.aGui.println(" Vous avez gagné vous avez récupérer tous les items nécessaires !!!!! ;)");
        }
        else
        {
            this.aGui.println( " Merci d'avoir jouée.  A très bientôt !" );
        }
        this.aGui.enable( false );
    }// endGame()
    
    /**
    * Création d'une procédure privée qui fais appel
    * à la position du joueur et des chemins possible.
    * Pour cela il va renvoyer la description de la pièce à l'aide 
    * de la fonction getDescription() dans la class Room et les sorties 
    * possible avec getExitString() de la class Room pour les chemins
    * accessible.
    */
    private void printLocalisationInfo()
    {
        this.aGui.println(" ");
        this.aGui.println(this.aPlayer.getLocalisation().getDescription());
        this.aGui.println(this.aPlayer.getLocalisation().getExitString());
        this.aGui.println(" ");
    }// printLocalisationInfo()
    
    /**
     * Création d'une fonction pour les insctruction des 
     * touche de jeux
     */
    private void Touche()
    {
        this.aGui.println("Voici la structure de commande pour se déplacer:");
        this.aGui.println("    va nord");
        this.aGui.println("    va sud");
        this.aGui.println("    va droite");
        this.aGui.println("    va gauche");
        this.aGui.println("    va haut");
        this.aGui.println("    va bas");
        this.aGui.println(" ");
    }// Touche()
    
    /**
     * Création d'une procédure pour retourné dans la pièce
     * précédentes.
     */
    private void back()
    {
        if (this.aPlayer.PreviousRoomIsEmpty() == true) // vérifie si la pièce précédente est vide
        {
            this.aGui.println("Cette fonction n'est pas utilisable\n ");
        }
        else
        {
            this.aPlayer.setLocalisation(this.aPlayer.getPreviousRooms());
            this.aTimeLimite = this.aTimeLimite - 1;
        }
        
        this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
        if ( this.aPlayer.getLocalisation().getImageName() != null )
            this.aGui.showImage( this.aPlayer.getLocalisation().getImageName() );
        this.aGui.println(" ");
    }// back()
    
    /**
     * Création d'une commande teste pouer lire les fichiers,
     * qui lira dans un premier temps le fichier exécuté les commandes
     * lues dans le fichier en question qui sera en .txt (texte)
     */
    private void teste (final Command pCommand)
    {   
        String vFile = pCommand.getSecondWord();
        Scanner vScan = null;
        
        try // Pour "essayer" les instruction suivantes
        {
            vScan = new Scanner(new File("./"+vFile+".txt")); // Ouverture du fichier existant
            while (vScan.hasNextLine()) // tant qu'il y a encore une ligne à lire dans le fichier
            {
                String vLigne = vScan.nextLine(); // lecture de la ligne dans le fichier
                interpretCommand(vLigne);  
            }
        }
        catch (final FileNotFoundException pFNFE) // si le fichier n'existe pas
        {
            this.aGui.println("Le fichier est introuvable");
        }
        
        if (vScan != null)
            vScan.close();
    }// teste(.)
    
    /**
     * Fonction permettant de prendre un item
     */
    public void take(final Command pCommand)
    {
        String vItem = pCommand.getSecondWord();
        Item vTakeItem = this.aPlayer.getLocalisation().getItems(vItem);
        
        if (vTakeItem == null)
        { this.aGui.println("Il n'y a pas cette Item ici."); }
        else if (this.aPlayer.getPoids() < vTakeItem.getItemPoids())
        {
            this.aGui.println("Vous n'avez pas assez de place");
        }
        else
        {
            this.aPlayer.takeintoInventaryItem(vItem, vTakeItem);
            this.aPlayer.getLocalisation().removeItem(vItem);
            this.aGui.println("Item récupéré !");
            this.aGui.println("");
            this.aPlayer.setPoids(this.aPlayer.getPoids() - vTakeItem.getItemPoids());
        }
    }// take(.)
    
    /**
     * Fonction permettant de laisser un item
     */
    public void drop(final Command pCommand)
    {
        String vItem = pCommand.getSecondWord();
        Item vDropItem = this.aPlayer.getInventaryItem(vItem);
        
        if (vDropItem == null)
        { this.aGui.println("Vous ne possédé pas cette Item :("); }
        else
        {
            this.aPlayer.getLocalisation().setItems(vItem, vDropItem);
            this.aPlayer.dropInventaryItem(vItem);
            this.aGui.println("Item laissé !");
            this.aPlayer.setPoids(this.aPlayer.getPoids() + vDropItem.getItemPoids());
        }
    }// drop(.)
    
    /**
     * Fonction pour afficher ce qu'il y a dans l'inventaire
     */
    private void InventaryItem()
    {
        this.aGui.println(this.aPlayer.getItemInventaryString());
        this.aGui.println("Le poids disponible :" + this.aPlayer.getPoids());
    }// InventaryItem()
    
    /**
     * Une fonction qui permettra de charger le Beamer.
     */
    private void charge(final Command pCommand)
    {
        String vSBeamer = pCommand.getSecondWord();
        Beamer vBeamer = (Beamer)this.aPlayer.getInventaryItem(vSBeamer);
        
        if (vBeamer == null)
        {
            this.aGui.println("Vous n'avez pas encore le Téléporteur.");
        }
        else if (this.aPlayer.getLocalisation().getDescription().equals("dans la salle de réception où le roi accueillait ses invités,\n mais où des spectres résideraient."))
        {
            this.aGui.println("Vous ne pouvez pas charger dans cette pièce");
        }
        else
        {
            vBeamer.charge(this.aPlayer.getLocalisation());
            this.aGui.println("Le Téléporteur est chargé");
        }
    }// charge(.)
    
    /**
     * Une fonction qui permettra de ce téléporter.
     */
    private void teleport(final Command pCommand)
    {
        String vSBeamer = pCommand.getSecondWord();
        Beamer vBeamer = (Beamer)this.aPlayer.getInventaryItem(vSBeamer);
        
        if (vBeamer == null)
        {
            this.aGui.println("Vous n'avez pas encore le Téléporteur.");
        }
        else if (vBeamer.isCharged() == false)
        {
            this.aGui.println("Le Téléporteur n'est pas chargé !");
        }
        else
        {
            this.aPlayer.setLocalisation(vBeamer.getRoomTeleport());
            this.aGui.println("Vous vous êtes téléporté !");
            this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
            if ( this.aPlayer.getLocalisation().getImageName() != null )
                this.aGui.showImage( this.aPlayer.getLocalisation().getImageName() );
            this.aGui.println(" ");
            this.aPlayer.resetBack();
            vBeamer.disCharge();
        }
    }// teleport(.)
    
    // /**
     // * Une Procedure Play() sans paramètre dans la classe game,
     // * qui permet de "lancer" le jeux. Va afficher le message de Bienvenue,
     // * on créer vFinished est une variable qui permettra l'arrêt du jeu
     // * qu'on l'initialiser false et "s'arrête" quand il sera true.
     // * Dans la boucle while il va faire les action avec vCommand en 
     // * recupérent dans getCommand().
     // * vFinished sera constament verifié avec la fonction 
     // * processCommand.
     // */
    // public void play()
    // {
        // this.printWelcome();
        // Command vCommand;
        // boolean vFinished = false;
        // while (vFinished == false)
        // {
            // vCommand = this.aParser.getCommand();
            // vFinished = this.processCommand(vCommand);
        // }
        // this.aGui.println("Merci d'avoir jouée.  A très bientôt !");
    // }// play()
    
    // /**
     // * Une fonction boolean qui permet aux les joueurs de quitter
     // * dans la classe game, vérifie si il ne contient qu'un seul String 
     // * et non pas deux.
     // */
    // private boolean quit(final Command pQuit) //privée
    // {
        // if (pQuit.hasSecondWord() == true)
        // {
            // this.aGui.println("quitter quoi? C'est juste 'quitter'.");
            // this.aGui.println(" ");
            // return false;
        // }
        // else
        // {
            // return true;
        // }
    // }// quit(final Command pQuit)
} // GameEngine
