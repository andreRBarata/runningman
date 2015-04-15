package game;

import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import sun.audio.*;
import java.io.IOException;
import java.io.*;

public class Audio {

	public static synchronized void playSound(final String url,
			final boolean muted) {
		new Thread(new Runnable() {

			public void run() {
				if (!muted) {
					try {
						Clip clip = AudioSystem.getClip();
						AudioInputStream inputStream = AudioSystem.getAudioInputStream(Audio.class
								.getResourceAsStream("/Samples/" + url));
						clip.open(inputStream);
						clip.start();
						// clip.loop(Clip.LOOP_CONTINUOUSLY);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
		}).start();

	}

	public static void music(MainMenu menu) {
		System.out.println("Music is called");

		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(Audio.class
							.getResourceAsStream("/Samples/Music.wav"));
			clip.open(inputStream);


			MuteListener ml = new MuteListener() {

				@Override
				public void muteAction(boolean isMuted) {
					try{
					if (isMuted) {
						stopClip(clip);
					}else
						playClipLoop(clip);
					}catch(Exception e){
						
					}
				}
			};

			menu.addMuteListener(ml);
			
			if(!menu.mute)
				playClipLoop(clip);

		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private static void playClipLoop(Clip clip) throws Exception {
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	private static void stopClip(Clip clip) throws Exception {
		clip.stop();
	}
}