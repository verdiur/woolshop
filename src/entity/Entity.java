package entity;

import java.awt.Graphics2D;

/**
 * Entité de base du jeu
 */
public abstract class Entity
{
	private float m_x;
	private float m_y;

	/**
	 * Constructeur de classe.
	 * @param x position x
	 * @param y position y
	 */
	public Entity(float x, float y) {
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
