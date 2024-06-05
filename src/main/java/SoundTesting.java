import java.io.File; 
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundTesting {
    private static Clip clip;     
    public SoundTesting () {
        
    }

    public static void playSound(String path) {

        try {
            File soundFile = new File(path); 
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile); 
            clip = AudioSystem.getClip(); 
            clip.open(audioInputStream); 
            if (clip.isRunning()) {
                clip.flush();
                clip.close(); 
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception_e) {
            exception_e.printStackTrace();
        }

    }
}





