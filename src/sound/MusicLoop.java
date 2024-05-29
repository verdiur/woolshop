package sound;

import javax.sound.sampled.*;



public class MusicLoop
{
    private AudioInputStream m_is;
    private Clip m_clip;

    public MusicLoop(AudioInputStream is) {
        try {
            m_is = is;
            m_clip = AudioSystem.getClip();
            m_clip.open(is);
            m_clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void playLoop() {
        m_clip.start();
    }

    public void stopLoop() {
        m_clip.stop();
    }
}
