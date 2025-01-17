 /**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + Sivaharan
 * @version 2008.03.30 + 2019.09.25 + 2024
 */
public class CommandWords
{
    // a constant array that will hold all valid command words
    private static final String aValidCommands[] =
    { "va", "aide", "quitter", "regarder", "manger", "retour", "teste", "prendre", "laisser", "inventaire", "charger", "teleporte" };
    private UserInterface aGui;
    // /**
     // * Constructor - initialise the command words.
     // */
    // public CommandWords()
    // {
        // this.aValidCommands = new String[5]; // [] : pour indiquer qu'on ne veut pas une seule String mais plusieurs
        // this.aValidCommands[0] = "va";
        // this.aValidCommands[1] = "aide";
        // this.aValidCommands[2] = "quitter";
        // this.aValidCommands[3] = "regarder";
        // this.aValidCommands[3] = "manger";
    // } // CommandWords()

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand( final String pString ) // pour vérifier si pString est une commande valide
    {
        for ( int vI=0; vI<this.aValidCommands.length; vI++ ) { // sValidCommands.length : vaut le nombre de cases du tableau, ici 3. i++ est strictement équivalent à i = i+1 (exécuté à la fin de chaque tour de boucle) donc, POUR i prenant les valeurs 0, 1, 2, FAIRE :
            if ( this.aValidCommands[vI].equals( pString ) ) // [i] pour indiquer qu'on veut accéder à la case n°i du tableau
                return true; // termine la méthode et retourne vrai puisqu'on a trouvé le mot parmi les commandes valides
        } // for
        // if we get here, the string was not found in the commands :
        return false; // retourne faux puisqu'on n'a pas trouvé le mot parmi les commandes valides
    } // isCommand()
    
    /**
     * Une procédure pour affiché les commandes initialiser par 
     * le développeur, showAll()
     */
    public void showAll()
    {
        for (String vCommand : aValidCommands)
        {
            this.aGui.print(vCommand + " ");
        }
    }// showAll()
    
    /**
     * Qui retourne une liste de commande différente de la méthode qui affiche
     * dans le terminal
     */
    public String getCommandList() 
    {
        StringBuilder commands = new StringBuilder();
        for(int i = 0; i < aValidCommands.length; i++) {
            commands.append( aValidCommands[i] + "  " );
        }
        return commands.toString();
    }// getCommandList()
} // CommandWords
