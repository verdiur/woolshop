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

	Client m_client;
	Timer m_timer;

	int m_current_room = 1;

	/** Conteneur pour toutes les entités */
	ArrayList<Entity> m_entity_arr_room1;
	/** Conteneur pour les Actors (bougent) */
	ArrayList<Actor> m_actor_arr_room1;
	/** Conteneur pour les Tiles (bougent pas) */
	ArrayList<Tile> m_tile_arr_room1;
	/** Conteneur d'entités ayant collision */
	ArrayList<Entity> m_collision_arr_room1;
	
	/** Conteneur pour toutes les entités */
	ArrayList<Entity> m_entity_arr_room2;
	/** Conteneur pour les Actors (bougent) */
	ArrayList<Actor> m_actor_arr_room2;
	/** Conteneur pour les Tiles (bougent pas) */
	ArrayList<Tile> m_tile_arr_room2;
	/** Conteneur d'entités ayant collision */
	ArrayList<Entity> m_collision_arr_room2;

	public ArrayList<Entity> getEntities() {
		switch(m_current_room) {
			case 1:
				return m_entity_arr_room1;
			case 2:
				return m_entity_arr_room2;
		}
		return null;
	}

	public ArrayList<Actor> getActors() {
		switch(m_current_room) {
			case 1:
				return m_actor_arr_room1;
			case 2:
				return m_actor_arr_room2;
		}
		return null;
	}

	public ArrayList<Tile> getTiles() {
		switch(m_current_room) {
			case 1:
				return m_tile_arr_room1;
			case 2:
				return m_tile_arr_room2;
		}
		return null;
	}

	public ArrayList<Entity> getCollisions() {
		switch(m_current_room) {
			case 1:
				return m_collision_arr_room1;
			case 2:
				return m_collision_arr_room2;
		}
		return null;
	}

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

		/** Conteneurs d'entités room 1 */
		m_entity_arr_room1 = new ArrayList<Entity>();
		m_actor_arr_room1 = new ArrayList<Actor>();
		m_tile_arr_room1 = new ArrayList<Tile>();
		m_collision_arr_room1 = new ArrayList<Entity>();

		/** Conteneurs d'entités room 2 */
		m_entity_arr_room2 = new ArrayList<Entity>();
		m_actor_arr_room2 = new ArrayList<Actor>();
		m_tile_arr_room2 = new ArrayList<Tile>();
		m_collision_arr_room2 = new ArrayList<Entity>();

		/** MapManager */
		m_map_manager = new MapManager(this);

		switch(m_current_room) {
			case 1:
				m_entity_arr_room1.add(m_player);
				m_actor_arr_room1.add(m_player);
				m_collision_arr_room1.add(m_player);
				m_map_manager.loadMap("/maps/map.txt", MAX_SCREEN_COL, MAX_SCREEN_ROW);
				break;
			case 2:
				m_entity_arr_room2.add(m_player);
				m_actor_arr_room2.add(m_player);
				m_collision_arr_room2.add(m_player);
				m_map_manager.loadMap("/maps/map2.txt", MAX_SCREEN_COL, MAX_SCREEN_ROW);
				break;
		}


		// m_entity_arr.add(m_client);
		// m_actor_arr.add(m_client);
		// m_collision_arr.add(m_client);

		// System.out.println(m_entity_arr_room1);

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

  	/********* ?????????? */

	/**
	 * Mise à jour des données des entités
	 */
	public void update() {
		switch(m_current_room) {
			case 1:
				for (Entity e: m_entity_arr_room1) {
					e.update(m_actor_arr_room1, m_tile_arr_room1, m_collision_arr_room1);
				}
				break;
			case 2:
				for (Entity e: m_entity_arr_room2) {
					e.update(m_actor_arr_room2, m_tile_arr_room2, m_collision_arr_room2);
				}
				break;
		}

		if (m_player.getX() == (int)0 && m_player.getY() == (int)1 && m_current_room == 1) {
			m_current_room = 2;
			m_entity_arr_room1.remove(m_player);
			m_actor_arr_room1.remove(m_player);
			m_collision_arr_room1.remove(m_player);
			m_entity_arr_room2.add(m_player);
			m_actor_arr_room2.add(m_player);
			m_collision_arr_room2.add(m_player);
			m_player.setX(MAX_SCREEN_COL - 2);
			m_player.setY(1);
			m_map_manager.loadMap("/maps/map2.txt", MAX_SCREEN_COL, MAX_SCREEN_ROW);
		}
		else if (m_player.getX() == MAX_SCREEN_COL-1 && m_player.getY() == (int)1 && m_current_room == 2) {
			m_current_room = 1;
			m_entity_arr_room2.remove(m_player);
			m_actor_arr_room2.remove(m_player);
			m_collision_arr_room2.remove(m_player);
			m_entity_arr_room1.add(m_player);
			m_actor_arr_room1.add(m_player);
			m_collision_arr_room1.add(m_player);
			m_player.setX(1);
			m_player.setY(1);
			m_map_manager.loadMap("/maps/map.txt", MAX_SCREEN_COL, MAX_SCREEN_ROW);
		}

  	}

	public void update_time() {
		if (m_client != null) {
			m_client.update(m_actor_arr_room1, m_tile_arr_room1, m_collision_arr_room1);
		}
	}
  
   	/********* ?????????? */
	
	/**
	 * Affichage des éléments
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// affichage tiles
		switch(m_current_room) {
			case 1:
				for (Entity e: m_tile_arr_room1) {
					e.draw(g2);
				}
				// affichage actors par dessus les tiles
				for (Entity e: m_actor_arr_room1) {
					e.draw(g2);
				}
				m_client.draw(g2);
				break;
			case 2:
				for (Entity e: m_tile_arr_room2) {
					e.draw(g2);
				}
				// affichage actors par dessus les tiles
				for (Entity e: m_actor_arr_room2) {
					e.draw(g2);
				}
				break;
		}
		g2.dispose();
	}
}
