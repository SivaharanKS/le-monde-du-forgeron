import java.util.HashMap;
import java.util.Set;
/**
 * Classe Items, qui est une classe qui "renferme" tous les items du jeu
 *
 * @author Sivaharan
 */
public class Item
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private String aItemNom;
    private String aItemDescription;
    private String aItemPrix;
    private int    aItemPoids;
    private String aReponse;

    /**
     * Constructeur Naturelle Item(.)
     * pour la classe Items qui renvoie la description de 
     * l'item et le prix par des chaînes de caractère.
     */
    public Item(final String pItemNom, final String pItemDescription, final String pItemPrix, final int pItemPoids, final String pReponse)
    {
        this.aItemNom = pItemNom;
        this.aItemDescription = pItemDescription;
        this.aItemPrix = pItemPrix;  // ici le prix sera modélisé par une question
        this.aItemPoids = pItemPoids;
        this.aReponse = pReponse;
    } //Iteam()
    
    /**
     * Création d'un Accesseur qui renvoie le Nom de 
     * l'Item
     */
    public String getItemNom() { return this.aItemNom; }// getItemNom(.)
    
    /**
     * Création de l'Accesseur getItemDescription() 
     * pour la classe Item qui permettra de récupérer 
     * la description affecter à l'attribut
     */
    public String getItemDescription() { return this.aItemDescription; } // getItemDescription()
    
    /**
     * Création de l'Accesseur getItemPrix() 
     * pour la classe Item qui permettra de récupérer 
     * le prix à payer affecter à l'attribut
     */
    public String getItemPrix() { return this.aItemPrix; } // getItemPrix()
    
    /**
     * Création de l'Accesseur getItemPoids() 
     * pour la classe Item qui permettra de récupérer 
     * le poids affecter à l'attribut
     */
    public int getItemPoids() { return this.aItemPoids; } // getItemPrix()
    
    /**
     * Création d'un accesseur qui renvoie la réponse liées à cette
     * item.
     */
    public String getReponse() { return this.aReponse; } //getReponse()
}//Item
