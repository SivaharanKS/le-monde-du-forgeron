import java.util.HashMap;
import java.util.Stack;

/**
 * La classe Player est pour récupérer quelque code de GameEngine et 
 * d'ajouté des nouvelle fonctionnalité.
 *
 * @author Sivaharan
 * @version 2024
 */
public class Player
{
    private String                aPseudo;
    private double                aPoids;
    private Room                  aLocalisation;
    private ItemList              aInventaire;
    private Stack<Room>           aPreviousRooms;

    /**
     * Constructeur d'objets de classe Player
     */
    public Player(final String pPseudo, final Room pLocalisation)
    {
        this.aPseudo = pPseudo;
        this.aLocalisation = pLocalisation;
        this.aPoids = 12;
        this.aPreviousRooms = new Stack();
        this.aInventaire = new ItemList();
    }// Player(.)

    /**
     * Acesseurs pour le Pseudo
     */
    public String getPseudo() 
    { return this.aPseudo; } //getPseudo()
    /**
     * Modificateur pour le Pseudo
     */
    public void setPseudo(final String pPseudo)
    { this.aPseudo = pPseudo; } //setPseudo(.)
    
    /**
     * Acesseurs pour le Poids
     */
    public double getPoids() 
    { return this.aPoids; }// getPoids()
    /**
     * Modificateur pour le Poids
     */
    public void setPoids(final double pPoids)
    { this.aPoids = pPoids; }//setPoids(.)  
    
    /**
     * Acesseurs pour la Localisation
     */
    public Room getLocalisation()
    { return this.aLocalisation;}// getLocalisation()
    /**
     * Modificateur pour la Localisation
     */
    public void setLocalisation(final Room pLocalisation)
    { this.aLocalisation = pLocalisation;}//setLocalisation(.)  
    
    /**
     * Accesseur pour le PreviousRoom
     */
    public Room getPreviousRooms()
    {return this.aPreviousRooms.pop();} // getPreviousRoom()
    /**
     * Modificateur pour le PreviousRoom
     */
    public void setPreviousRooms(final Room pLastRoom)
    {this.aPreviousRooms.push(pLastRoom);}//setPreviousRoom(.)
    
    /**
     * Booléan qui renvoie true si le room précédent est vide 
     */
    public boolean PreviousRoomIsEmpty()
    {return this.aPreviousRooms.empty();} // PreviousRoomIsEmpty()
    
    /**
     * Création de fonction pour take, fessant appel a une fonction de HashMap
     */
    public void takeintoInventaryItem(final String pItem, final Item pTakeItem)
    { this.aInventaire.takeItemList(pItem, pTakeItem); } //takeintoInventaryItem(.)
    
    /**
     * Création de fonction pour drop, fessant appel a une fonction de HashMap
     */
    public void dropInventaryItem(final String pItem)
    { this.aInventaire.dropItemList(pItem); } //dropInventaryItem(.)
    
    /**
     * Fonction pour prendre un Item de l'inventaire
     */
    public Item getInventaryItem(final String pItem)
    { return this.aInventaire.getItemList(pItem);} //getInventaryItem(.)
    
    /**
     * Création d'une fonction setter
     */
    public void setInventaryItem(final String pItemDescription, final Item pItems)
    { this.aInventaire.setItemList(pItemDescription, pItems); } // setItemListe()
    
    /**
     * Création d'une méthode getItemInventaryString() qui utilise les propriétés
     * de HashMap.
     */
    public String getItemInventaryString()
    {
        String vItemsString = " Les Items que vous disposez :" +
                             this.aInventaire.getItemListeString();
        return vItemsString;
    }// getItemInventaryString()
    
    /**
     * Une méthode permettant de vidé la pile lorsque le joueur traverse une 
     * TrapDoor.
     */
    public void resetBack()
    {
        this.aPreviousRooms.clear();
    }// ResetBack()
    
    /**
     * Fonction pour savoir le nombre d'élément dans l'inventaire
     */
    public int getNbInventaire()
    {
        return this.aInventaire.getNbInventaire();
    }// getNbInventaire()
}//Palyer
