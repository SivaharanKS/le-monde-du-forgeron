 /**
 * Classe Command - une commande du jeu d'aventure Zuul.
 *
 * @author Sivaharan
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     * Création d'un Constructeur Naturel pour la classe Command,
     * qui stockera les paramétres pCommandWord et pSecondWord dans 
     * leurs attributs respectives car nos attribut sont private.
     */
    public Command(final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord  = pSecondWord;
    } // Command
    
    /**
     * Création de l'Accesseur pour la classe Command, permet d'appeler
     * les attribut comme ils sont private on ne pourra pas les appeler
     * dans d'autre classes, donc cette fonction nous permettra d'appeler
     * les attribut de la class room dans d'autre classe.
     */
    public String getCommandWord() { return this.aCommandWord; }
    public String getSecondWord() { return this.aSecondWord; }
    
    /**
     * Création d'une fonction Booléenne pour l'existence du second mot
     * pour la classe Command.
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord != null; // renvoie true s'il y a un second mot
    } // hasSecondWord
    
    /**
     * Création d'un fonction Booléenne pour l'existence du premier mot
     * pour la classe Command.
     */
    public boolean isUnknown()
    {
        return this.aCommandWord == null; // renvoie true s'il y a pas le 1er mot
    } // isUnknown
} // Command
