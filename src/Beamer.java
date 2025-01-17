/**
 * La Classe Beamer et une sorte d'Item donc Beamer est une sous class de Item,
 * qui permettra à l'utilisateur de se téléporté grace à un room,
 * cette item appelé "Beamer" et pour l'utiliser il faudra la rechargé.
 * Les conditions pour charger sont :
 * - doit être ramasser dans une Room ;
 * - doit être recharger dans une autre room que celle où elle a été ramasser, 
 * qui lui permettra d'enregistrer la room dans laquelle elle veut revenir avec la téléportation ;
 * - toit être utiliser dans une autre room, pas où elle a été ramasser et où elle a été 
 * recharger.
 * 
 * @author Sivaharan.
 * @version 2025
 */
public class Beamer extends Item
{
    private boolean aCharger;
    private Room    aRoomTeleport;
    
    /**
     * Un constructeur naturelle qui permet de d'utiliser les attributs et les méthodes de Item, 
     * Beamer est une sous-classe d'Item. On fessant appel à super() on fait appel au 
     * constructeur de Item, à la même occasion on programme que le Beamer n'est pas Charger.
     */
    public Beamer(final String pItemNom, final String pItemDescription, final String pItemPrix, final int pItemPoids, final String pReponse)
    {
        super(pItemNom, pItemDescription, pItemPrix, pItemPoids, pReponse);
        this.aCharger = false;
    }// Beamer(.)
    
    /**
     * Un constructeur quelconques, qui utilise à nouveau le constructeur de Item en ajoutant 
     * un paramètre en plus pour pour "sauvegarder" la room dans laquelle on aimerais être 
     * téléporté lors de l'utilisation du Beamer.
     */
    public Beamer(final String pItemNom, final String pItemDescription, final String pItemPrix, final int pItemPoids, final String pReponse, final Room pRoomTeleport)
    {
        super(pItemNom, pItemDescription, pItemPrix, pItemPoids, pReponse);
        this.aCharger = true;
        this.aRoomTeleport = pRoomTeleport;
    }// Beamer(.)
    
    /**
     * Une procédure qui permettra de vérifié si le Beamer est Chargé.
     */
    public boolean isCharged()
    {
        return this.aCharger;
    }// isCharged(.)
    
    /**
     * Une procédure qui permettra de Charger le Beamer.
     */
    public void charge(final Room pRoomTeleport)
    {
        this.aCharger = true;
        this.aRoomTeleport = pRoomTeleport;
    }// charge(.)
    
    /**
     * Une procédure qui permettra de "ddéchargé une fois que le Beamer est utiliser.
     */
    public void disCharge()
    {
        this.aCharger = false;
    }// disCharge()
    
    /**
     * Un getteur, un accesseur pour récupérer la room dans laquell on veutt ce téléporté.
     */
    public Room getRoomTeleport()
    {
        return this.aRoomTeleport;
    }// getRoomTeleport()
}//Beamer
