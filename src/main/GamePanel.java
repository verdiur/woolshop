package main;

import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import javax.imageio.ImageIO;		// TODO importer images avec classe ImageLoader

import entity.Entity;
import entity.Actor;
import entity.Tile;
import actor.Player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Panel principal du jeu contenant la map principale
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
	
	// Création des différentes instances (Player, KeyHandler, TileManager, GameThread ...)
	// KeyHandler m_keyH;
	Thread m_gameThread;
	Player m_player;
	// TileManager m_tileM;
	MapManager m_map_manager;
	KeyListener m_keyH;

	/** Conteneur pour toutes les entités */
	ArrayList<Entity> m_entity_arr;
	/** Conteneur pour les Actors (bougent) */
	ArrayList<Actor> m_actor_arr;
	/** Conteneur pour les Tiles (bougent pas) */
	ArrayList<Tile> m_tile_arr;
	/** Conteneur d'entités ayant collision */
	ArrayList<Entity> m_collision_arr;
		
	/**
	 * Constructeur
	 */
	public GamePanel() {
		m_FPS = 60;				
		m_keyH = new KeyHandler();

		/** Player */
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

		/** Conteneurs d'entités */
		m_entity_arr = new ArrayList<Entity>();
		m_actor_arr = new ArrayList<Actor>();
		m_tile_arr = new ArrayList<Tile>();
		m_collision_arr = new ArrayList<Entity>();
		
		m_entity_arr.add(m_player);
		m_actor_arr.add(m_player);
		m_collision_arr.add(m_player);

		/** TileManager */
		// m_tileM = new TileManager(this);
		m_map_manager = new MapManager(this);
		m_map_manager.loadMap("/maps/map.txt", MAX_SCREEN_COL, MAX_SCREEN_ROW);
		
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
			
			// Permet de mettre à jour les différentes variables du jeu
			this.update();
			
			// Dessine sur l'écran le personnage et la map avec les nouvelles informations. la méthode "paintComponent" doit obligatoirement être appelée avec "repaint()"
			this.repaint();
			
			// Calcule le temps de pause du thread
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * Mise à jour des données des entités
	 */
	public void update() {
		for (Entity e: m_entity_arr) {
			e.update(m_actor_arr, m_tile_arr, m_collision_arr);
		}
	}
	
	/**
	 * Affichage des éléments
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Entity e: m_entity_arr) {
			e.draw(g2);
		}
		m_player.draw(g2);
		g2.dispose();
	}
	
}
