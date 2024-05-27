package tile;

import java.awt.image.BufferedImage;

/**
 * 
 * Element graphique de la carte
 */
public class Tile_old {
	public BufferedImage m_image;		//image
	public boolean m_collision;			//d�but de gestion de collision entre �l�ments
	
	Tile_old(){
		m_collision = false;
	}
}
