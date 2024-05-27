package entity;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Entité de base du jeu
 */
public abstract class Entity
{
	public float m_x;
	public float m_y;
	public HashMap<
		String, 
		ArrayList<BufferedImage>
	> m_sprite_map;

	/**
	 * Dessin par défaut, à changer si animations
	 * @param g2
	 */
	public abstract void draw(Graphics2D g2);
}
