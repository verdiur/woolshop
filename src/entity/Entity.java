package entity;

import java.awt.Graphics2D;

/**
 * Entité de base du jeu
 */
public abstract class Entity
{
	protected int m_x;
	protected int m_y;
	protected boolean m_isPhysical = false;

	/**
	 * Constructeur de classe.
	 * @param x position x en tiles
	 * @param y position y en tiles
	 */
	public Entity(int x, int y) {
		m_x = x;
		m_y = y;
	}

	public Entity(int x, int y, boolean isP) {
		m_x = x;
		m_y = y;
		m_isPhysical = isP;
	}

	public float getX() {
		return m_x;
	}

	public float getY() {
		return m_y;
	}

	public boolean isPhysical() {
		return m_isPhysical;
	}

	/**
	 * Méthode abstraite de dessin.
	 * @param g2 contexte graphique
	 */
	public abstract void draw(Graphics2D g2);

	/**
	 * Méthode abstraite update.
	 */
	public abstract void update();
}
