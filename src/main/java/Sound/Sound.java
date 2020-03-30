/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 *
 * @author utilisateur
 */
public class Sound {
    private Clip C;
    
    public Sound(String son){
        try {
            AudioInputStream si = AudioSystem.getAudioInputStream(getClass().getResource(son));
            C = AudioSystem.getClip();
            C.open(si);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void play(){
        C.start();
    }
    
    public void stop(){
        C.stop();
    }
    
    public Clip getClip(){
        return C;
    }
    
    public static void playTempSound(String son){
        Sound s = new Sound(son);
        s.play();
    }
}
