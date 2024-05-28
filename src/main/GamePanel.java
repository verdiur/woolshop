package main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import javax.imageio.ImageIO;		// TODO importer images avec classe ImageLoader

import actor.Player;
import actor.Client;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

/**
 * Panel principal du jeu contenant la map principale
 *
 */
public class GamePanel extends JPanel implements Runnable{
	
	//Paramètres de l'écran
	final int ORIGINAL_TILE_SIZE = 32; 							// une tuile de taille 16x16
	final int SCALE = 2; 										// échelle utilisée pour agrandir l'affichage
	public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; 	// 48x48
	public final int MAX_SCREEN_COL = 16;
	public final int MAX_SCREEN_ROW = 12; 					 	// ces valeurs donnent une résolution 4:3
	public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
	public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;	// 576 pixels

	// FPS : taux de rafraichissement
	int m_FPS;
	
	// Cr�ation des diff�rentes instances (Player, KeyHandler, TileManager, GameThread ...)
	// KeyHandler m_keyH;
	Thread m_gameThread;
	Player m_player;
	TileManager m_tileM;
	KeyListener m_keyH;
	Client m_client;
	Timer m_timer;

		
	/**
	 * Constructeur
	 */
	public GamePanel() {
		m_FPS = 60;
		m_timer = new Timer();
		TimerTask t_update = new TimerTask() {
			public void run(){
				update_time();
			}
		};

		m_timer.scheduleAtFixedRate(t_update, new Date(), 1000);


		m_keyH = new KeyHandler();

		BufferedImage player_sprite = null;
		try {
			player_sprite =
			ImageIO.read(getClass().getResource("/player/bebert.png"));
		} catch (IOException e) {
			System.out.println(e);
		}
		m_player = new Player(
			this, 
			2, 
			2,
			player_sprite
		);

		BufferedImage client_sprite = null;
		try {
			client_sprite =
			ImageIO.read(getClass().getResource("/player/testclient.png"));
		} catch (IOException e) {
			System.out.println(e);
		}
		m_client = new Client(
			this, 
			3, 
			4,
			client_sprite
		);




		m_tileM = new TileManager(this);
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(m_keyH);
		this.addKeyListener(m_player.getKeyAdapter());
		this.setFocusable(true);
	}
	
	/**
	 * Lancement du thread principal
	 */
	public void startGameThread() {
		m_gameThread = new Thread(this);
		m_gameThread.start();
	}
	
	public void run() {
		
		double drawInterval = 1000000000/m_FPS; // rafraichissement chaque 0.0166666 secondes
		double nextDrawTime = System.nanoTime() + drawInterval; 
		
		while(m_gameThread != null) { //Tant que le thread du jeu est actif
			
			//Permet de mettre � jour les diff�rentes variables du jeu
			this.update();
			
			//Dessine sur l'�cran le personnage et la map avec les nouvelles informations. la m�thode "paintComponent" doit obligatoirement �tre appel�e avec "repaint()"
			this.repaint();
			
			//Calcule le temps de pause du thread
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * Mise à jour des données des entités
	 */
	public void update() {
		m_player.update();
			

	}

	public void update_time(){
		if (m_client != null){
			m_client.update();
		}
		
	}
	
	/**
	 * Affichage des �l�ments
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		m_tileM.draw(g2);
		m_player.draw(g2);
		m_client.draw(g2);
		g2.dispose();
	}
	
}
