 /**
 * Classe Game - le jeu du jeu d'aventure Zuul.
 *
 * @author Sivaharan
 */
public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;
    
    /**
     * Créer un jeu et initialise une map interne.
     * Créer l'interface et le lien pour jouer.
     */
    public Game()
    {
        this.aEngine = new GameEngine();
        this.aGui    = new UserInterface(this.aEngine);
        this.aEngine.setGUI(this.aGui);
    }// Game()

    /**
     * Point d'entrée pour lancer le jeu en dehors de BlueJ
     * (ligne de commande ou fichier .jar).
     */
    public static void main(final String[] pArgs)
    {
        javax.swing.SwingUtilities.invokeLater(Game::new);
    }// main(.)
} // Game
