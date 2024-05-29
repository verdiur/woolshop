package sound;

import javax.sound.sampled.*;



public class SoundPlay
{
    private AudioInputStream m_is;
    private Clip m_clip;
    private boolean m_has_played;

    public SoundPlay(AudioInputStream is) {
        m_has_played = false;
        try {
            m_is = is;
            m_clip = AudioSystem.getClip();
            m_clip.open(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void play() {
        if (!m_has_played) {
            m_clip.start();
            m_has_played = true;
        }
    }

    public void stop() {
        m_clip.stop();
    }
}
