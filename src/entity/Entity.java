package entity;

import java.awt.Graphics2D;

/**
 * Entité de base du jeu
 */
public abstract class Entity
{
	protected int m_x;
	protected int m_y;

	/**
	 * Constructeur de classe.
	 * @param x position x en tiles
	 * @param y position y en tiles
	 */
	public Entity(int x, int y) {
		m_x = x;
		m_y = y;
	}

	public float getX() {
		return m_x;
	}

	public float getY() {
		return m_y;
	}

	public void setX(float x) {
		m_x = x;
	}

	public void setY(float y) {
		m_y = y;
	}

	/**
	 * Méthode abstraite de dessin.
	 * @param g2 contexte graphique
	 */
	public abstract void draw(Graphics2D g2);
}
