package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {

	public static synchronized void playSound(final String url, boolean muted) {
		new Thread(new Runnable() {

			public void run() {
				if (!muted) {
					try {
						Clip clip = AudioSystem.getClip();
						AudioInputStream inputStream = AudioSystem
								.getAudioInputStream(Audio.class
										.getResourceAsStream("/Samples/" + url));
						clip.open(inputStream);
						clip.start();
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
		}).start();

	}

}