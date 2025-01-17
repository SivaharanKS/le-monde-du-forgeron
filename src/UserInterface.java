import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a 
 * text entry area, a text output area and an optional image.
 * 
 * @author Sivaharan
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JButton    aBoutonAide;
    private JButton    aBoutonRegarde;
    private JButton    aBoutonInventaire;
    private JButton    aBoutonQuitter;
    private JButton    aBoutonNord;
    private JButton    aBoutonSud;
    private JButton    aBoutonDroite;
    private JButton    aBoutonGauche;
    private JButton    aBoutonHaut;
    private JButton    aBoutonBas;
    
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param GameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the entry field.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( pOnOff ) { // enable
            this.aEntryField.getCaret().setBlinkRate( 500 ); // cursor blink
            this.aEntryField.addActionListener( this ); // reacts to entry
        }
        else { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Le Monde du Forgeron" ); 
        this.aEntryField = new JTextField( 34 );
        
        this.aBoutonAide = new JButton("Aide");
        this.aBoutonRegarde = new JButton("Regarder");
        this.aBoutonInventaire = new JButton("Inventaire");
        this.aBoutonQuitter = new JButton("Quitter");
        this.aBoutonNord = new JButton("Nord");
        this.aBoutonSud = new JButton("Sud");
        this.aBoutonDroite = new JButton("Droite");
        this.aBoutonGauche = new JButton("Gauche");
        this.aBoutonHaut = new JButton("Haut");
        this.aBoutonBas = new JButton("Bas");

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        this.aImage = new JLabel();

        JPanel vPanel = new JPanel();
        // JPanel vPanelD = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel vPanelD = new JPanel(new GridLayout(5, 2));
        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vPanel.add( vPanelD, BorderLayout.EAST );
        
        vPanelD.add( this.aBoutonHaut);
        vPanelD.add( this.aBoutonBas);
        vPanelD.add( this.aBoutonNord);
        vPanelD.add( this.aBoutonSud);
        vPanelD.add( this.aBoutonDroite);
        vPanelD.add( this.aBoutonGauche);
        vPanelD.add( this.aBoutonInventaire);
        vPanelD.add( this.aBoutonRegarde);
        vPanelD.add( this.aBoutonAide);
        vPanelD.add( this.aBoutonQuitter);

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aBoutonHaut.addActionListener( this );
        this.aBoutonBas.addActionListener( this );
        this.aBoutonNord.addActionListener( this );
        this.aBoutonSud.addActionListener( this );
        this.aBoutonDroite.addActionListener( this );
        this.aBoutonGauche.addActionListener( this );
        this.aBoutonInventaire.addActionListener( this );
        this.aBoutonRegarde.addActionListener( this );
        this.aBoutonAide.addActionListener( this );
        this.aBoutonQuitter.addActionListener( this );
        this.aEntryField.addActionListener( this );
        
        // to end program when window is closed
        this.aMyFrame.addWindowListener(
            new WindowAdapter() { // anonymous class
                @Override public void windowClosing(final WindowEvent pE)
                {
                    System.exit(0);
                }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
        
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     */
    @Override public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        if ( pE.getSource() == this.aBoutonHaut)
        { this.aEngine.interpretCommand("va haut"); }
             
        else if ( pE.getSource() == this.aBoutonBas)
        { this.aEngine.interpretCommand("va bas"); }
        
        else if ( pE.getSource() == this.aBoutonNord)
        { this.aEngine.interpretCommand("va nord"); }
        
        else if ( pE.getSource() == this.aBoutonSud)
        { this.aEngine.interpretCommand("va sud"); }
        
        else if ( pE.getSource() == this.aBoutonDroite)
        { this.aEngine.interpretCommand("va droite"); }
        
        else if ( pE.getSource() == this.aBoutonGauche)
        { this.aEngine.interpretCommand("va gauche"); }
        
        else if ( pE.getSource() == this.aBoutonInventaire)
        { this.aEngine.interpretCommand("inventaire"); }
        
        else if ( pE.getSource() == this.aBoutonRegarde)
        { this.aEngine.interpretCommand("regarder"); }
        
        else if ( pE.getSource() == this.aBoutonAide)
        { this.aEngine.interpretCommand("aide"); }
        
        else if ( pE.getSource() == this.aBoutonQuitter)
        { this.aEngine.interpretCommand("quitter"); }
        
        else if ( pE.getSource() == this.aEntryField)
        { this.processCommand();  } // never suppress this line
    } // actionPerformed(.)

    /**
     * A command has been entered in the entry field.  
     * Read the command and do whatever is necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()

} // UserInterface 
