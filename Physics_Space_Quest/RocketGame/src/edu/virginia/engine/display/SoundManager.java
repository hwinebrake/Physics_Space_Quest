package edu.virginia.engine.display;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import javax.sound.sampled.*;
import javax.swing.*;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;


public class SoundManager {
    //init hash maps
    HashMap soundHash = new HashMap();
    HashMap musicHash = new HashMap();

    public void LoadSoundEffect(String id, String filename){
        // from a wave File
        File soundFile = new File(filename);
        try{
            AudioInputStream audioIn = getAudioInputStream(soundFile);
            // Get a clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            //put in hash map
            soundHash.put(id, clip);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void PlaySoundEffect(String id){
        Clip clip = (Clip) soundHash.get(id);
        if (clip != null) {
            if (clip.isRunning())
                clip.stop();   // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
        }else{
            System.out.println("sound clip does not exist or loading failed");
        }
    }

    public void LoadMusic(String id, String filename){
        // from a wave File
        File musicFile = new File(filename);
        try{
            AudioInputStream audioIn = getAudioInputStream(musicFile);
            // Get a clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            //put in hash map
            musicHash.put(id, clip);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void PlayMusic(String id){
        Clip clip = (Clip) musicHash.get(id);
        if (clip != null) {
            if (clip.isRunning())
                clip.stop();   // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.loop(Clip.LOOP_CONTINUOUSLY);     // Start playing
        }else{
            System.out.println("sound clip does not exist or loading failed");
        }
    }

    public void StopMusic(String id){
        Clip clip = (Clip) musicHash.get(id);
        if (clip.isRunning()) clip.stop();
    }

}
