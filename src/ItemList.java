import java.util.HashMap;
import java.util.Set;

/**
 * Classe ItemList ici.
 *
 * Sivaharan
 * @version 2024
 */
public class ItemList
{
    private HashMap<String, Item> aInventary;
    
    /**
     * Constructeur pour l'ItemList
     */
    public ItemList()
    {
        this.aInventary = new HashMap<>();
    }// ItemListe()
    
    /**
     * Création d'une méthode getItemListeString() qui utilise les propriétés
     * de HashMap.
     */
    public String getItemListeString()
    {
        String vItemListeString = " ";
        Set<String> vKeys = this.aInventary.keySet(); 
        for(String vItems : vKeys) /* vKeys variable qui va contenir 
                                    l'attribut aExit qui prendra des String
                                    (haut, bas, sud,...)*/
        {
            vItemListeString += " " + vItems;
        }
        return vItemListeString;
    }// getItemListeString()
    
    /**
     * Création de fonction pour take, fessant appel a une fonction de HashMap
     */
    public void takeItemList(final String pItem, final Item pTakeItem)
    { this.aInventary.put(pItem, pTakeItem); } //takeItemList(.)
    
    /**
     * Création de fonction pour drop, fessant appel a une fonction de HashMap
     */
    public void dropItemList(final String pItem)
    { this.aInventary.remove(pItem); } //dropItemList(.)
    
    /**
     * Fonction pour prendre un Item de l'inventaire
     */
    public Item getItemList(final String pItem)
    { return this.aInventary.get(pItem);} //getItemList(.)
    
    /**
     * Création d'une procédure setItems() pour 
     * contenir plusieurs items dans une pièce.
     */
    public void setItemList(final String pItemDescription, final Item pItems)
    {
        this.aInventary.put(pItemDescription, pItems);
    } // setItemListe()
    
    /**
     * Une fonction pour retirer un item de la HashMap
     * de l'item
     */
    public void removeItemList(final String pItem)
    { this.aInventary.remove(pItem); } //removeItem(.)
    
    /**
     * Fonction pour savoir le nombre d'élément dans l'inventaire
     */
    public int getNbInventaire()
    {
        return this.aInventary.size();
    }//getNbInventaire()
}//ItemListe
