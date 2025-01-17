import java.util.HashMap;
import java.util.Set;

 /**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author Sivaharan
 */
public class Room
{
    private String                   aDescription; // Description du lieu
    private HashMap<String, Room>    aExits; /* Pour dire que HashMap prend des String et Room pour des sorties*/
    private String                   aImageName;
    private ItemList                 aItemsList; /* Pour stocké plusieur Items*/
    private HashMap<String, Boolean> aTrapDoor;
    
    /**
     * Constructeur Naturelle Room(.) 
     * pour la classe Room qui renvoie la description de 
     * la pièce par des chaînes de caractère.
     */
    public Room(final String pDescription, final String pImage)
    {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aImageName = pImage;   
        this.aItemsList = new ItemList();
        this.aTrapDoor = new HashMap<>();
    } //Room
    
    /**
     * Création de l'Accesseur getDescription() 
     * pour la classe Room qui permettra de récupérer 
     * la description affecter à l'attribut
     */
    public String getDescription() { return this.aDescription; } // getDescription()
    
    /**
     * Création de la procèdure setExit() maintenant permet de 
     * définir une sortie pour la pièce qu'on désigne.
     */
    public void setExits(final String pDirection, final Room pNeighbor, final boolean pTrapDoor)
    {
        this.aExits.put(pDirection, pNeighbor);
        this.aTrapDoor.put(pDirection, pTrapDoor);
    } // setExit(.)
    
    /**
     * Création d'un accesseur, qui permet de récupérer les directions
     * via pDirection et de les "insérers" dans aExits une variable de
     * HashMap.
     */
    public Room getExit(final String pDirection)
    {
        return this.aExits.get(pDirection);
    }// getExit(final String pDirection)
    
    /**
     * Création d'une méthode getExitString() qui utilise les propriétés
     * de HashMap. 
     * 
     * On créer une variable local vExitString qui prendra un String. 
     * 
     * Ensuite Set<Type_des_clés(String)> ma_variable(vKeys) = (erreur non existente 
     * sur la génération des 2 javadoc)
     * ma_HashMap(aExits).keySet();
     * met dans ma_variable l'ensemble des clés de ma_HashMap(aExits) qui 
     * sera crée dans la classe Game à l'aide des fonction de la classe 
     * Room (Set veut dire ensemble et fait partie des collections en Java).
     * 
     * Enfin for (Type_des_éléments(String) nom_d_un_élément(vExit) : conteneur_de_données) 
     * (KeySet() est une fonction qui va contenir tous les éléments de aExits)
     * { instructions }
     * n'est pas une condition mais une boucle (appelée boucle for each), 
     * comme la boucle for avec les deux points-virgules, mais sans avoir 
     * besoin d'un compteur de boucle. conteneur_de_données peut être soit 
     * un tableau soit une collection de données, telle qu'un ensemble 
     * (mais il existe aussi des listes, des piles, ...). A chaque tour 
     * de boucle, nom_d_un_élément(vExit) vaudra l'élément suivant dans 
     * le conteneur_de_données ; quand tous les éléments ont été 
     * ainsi parcourus, la boucle s'arrête.
     */
    public String getExitString()
    {
        String vExitString = " Les Sorties possibles:";
        Set<String> vKeys = this.aExits.keySet(); 
        for(String vExit : vKeys) /* vKeys variable qui va contenir 
                                    l'attribut aExit qui prendra des String
                                    (haut, bas, sud,...)*/
        {
            vExitString += " " + vExit;
        }
        return vExitString;
    }// getExitString()
    
    /**
     * Renvoie la description de la pièce sous forme :
     *      Vous êtes ....
     *      Sortie : nord ...
     * retourne la description de lma pièce avec les issue possible
     */
    public String getLongDescription()
    {
        return " Vous êtes " + this.getDescription() + "\n" +
               this.getItemsString() + "\n" + this.getExitString();
    }// getLongDescription()
    
    /**
     * Retourner un String décrivant le nom de l'image du room
     */
    public String getImageName()
    {
        return this.aImageName;
    }// getImageName()
    
    // /**
     // * Création de la procèdure setItem() maintenant permet de 
     // * définir un item pour la pièce qu'on désigne.
     // */
    // public void setItem(final Item pItem)
    // {
        // this.aItem = pItem;
    // } // setItem()
    
    /**
     * Création d'une procédure setItems() pour 
     * contenir plusieurs items dans une pièce.
     */
    public void setItems(final String pItemDescription, final Item pItems)
    {
        this.aItemsList.setItemList(pItemDescription, pItems);
    } // setItems()
    
    /**
     * Création d'un accesseur, qui permet de récupérer les Items
     * via pItemNom et de les "insérers" dans aItems une variable de
     * HashMap.
     */
    public Item getItems(final String pItemNom)
    {
        return this.aItemsList.getItemList(pItemNom);
    }// getExit(final String pDirection)
    
    /**
     * Une fonction pour retirer un item de la HashMap
     * de l'item
     */
    public void removeItem(final String pItem)
    { this.aItemsList.removeItemList(pItem); } //removeItem(.)
    
    /**
     * Création d'une méthode getItemString() qui utilise les propriétés
     * de HashMap.
     */
    public String getItemsString()
    {
        String vItemsString = " Les Items disponibles :" +
                             this.aItemsList.getItemListeString();
        return vItemsString;
    }// getItemsString()
    
    /**
     * Une méthode pour voir si le joueur à traverser une TrapDoor.
     */
    public Boolean isTrapDoor(final String pDirection)
    {
        return this.aTrapDoor.get(pDirection);
    }// isTrapDoor(.)
    
} // Room
