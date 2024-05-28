package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import entity.Tile;
import tile.Wall;

/**
 * Gestionnaire de map
 */
public class MapManager {
    GamePanel m_gp;

    /**
     * Constructeur
     * @param gp
     */
    public MapManager(GamePanel gp) {
        this.m_gp = gp;
    }

    /**
	 * Lecture du fichier txt contenant la map et chargement des tuiles correspondantes.
	 */
    public void loadMap(String file_path, int col_amt, int row_amt) {
        try
        {
            InputStream is = getClass().getResourceAsStream(file_path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            for (int col = 0; col < row_amt; col++)
            {
                String line[] = br.readLine().split("\s+");
                for (int row = 0; row < line.length; row++)
                {
                    int id = Integer.parseInt(line[row]);
                    BufferedImage s = null;
                    Tile e = null;
                    switch (id)
                    {
                        case 0: {
                            // floor
                            try { s = ImageIO.read(getClass().getResource("/tiles/FLOOR.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Tile(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            break;
                        }
                        case 1: {

                            try { s = ImageIO.read(getClass().getResource("/tiles/SIDEWALL.png")); }

                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Tile(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            break;
                        }
                        case 2: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/SHELF.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }
                        case 3: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/COUNTER.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }
                        case 4: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/TAPIS.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 5: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/TOPWALL.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 6: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/SIDEWALL.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 7: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/BOTTOMWALL.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 8: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/CORNERWALL.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }
                    }
                }
            }
        }
        catch (IOException ioe2) {
            ioe2.printStackTrace();
        }
    } 
}
