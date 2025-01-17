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
} // Game
