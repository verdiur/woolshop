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
                            break;
                        }

                        case 2: {
                            // floor
                            try { s = ImageIO.read(getClass().getResource("/tiles/FLOORSHADOW.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Tile(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            break;
                        }


                        case 3: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/TAPIS.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 4: {
                            // floor
                            try { s = ImageIO.read(getClass().getResource("/tiles/CORNERBOTTOMLEFT.png")); }
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
                            try { s = ImageIO.read(getClass().getResource("/tiles/CORNERBOTTOMRIGHT.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 9: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/CORNERTOPRIGHT.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 10: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/CORNERTOPRIGHT.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 11: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/CORNERTOPLEFT.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 12: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/DOORDOWNLEFT.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 13: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/DOORDOWNRIGHT.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 14: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/DOORTOPLEFT.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 15: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/DOORTOPRIGHT.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 16: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/DOORUPLEFT.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 17: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/DOORUPRIGHT.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 20: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/CHEST.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 21: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/CRAB.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }

                        case 22: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/SHELF.png")); }
                            catch (IOException ioe1) { System.out.println(ioe1); }
                            e = new Wall(m_gp, row, col, s);
                            m_gp.getEntities().add(e);
                            m_gp.getTiles().add(e);
                            m_gp.getCollisions().add(e);
                            break;
                        }
                        
                        case 23: {
                            try { s = ImageIO.read(getClass().getResource("/tiles/COUNTER.png")); }
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
