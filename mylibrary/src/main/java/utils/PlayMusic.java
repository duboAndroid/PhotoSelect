package utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;

import application.MyApplication;


public class PlayMusic {
	private SoundPool soundPool;
	private AudioManager audioManager;
	private float volume;
	private int currentStreamId;
	public static PlayMusic playMusic;
	int id;

	public PlayMusic() {
		// TODO Auto-generated constructor stub
		if (soundPool == null) {
			soundPool = new SoundPool(50, AudioManager.STREAM_MUSIC, 0);
		}
//		id = soundPool.load(MyApplication.instance, R.raw.new_order_sound, 1);
	}

	public static PlayMusic getinstance() {
		if (playMusic == null) {
			synchronized (PlayMusic.class) {
				if (playMusic == null) {
					playMusic = new PlayMusic();

				}
			}
		}
		return playMusic;
	}

	public void play() {

		playSoundHander.sendEmptyMessage(1);
	}

	public Handler playSoundHander = new Handler() {
		public void handleMessage(Message msg) {
			if (audioManager == null) {
				audioManager = (AudioManager) MyApplication.instance.getSystemService(Context.AUDIO_SERVICE);
				float streamVolumeCurrent = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
				float streamVolumeMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				volume = streamVolumeCurrent / streamVolumeMax;
			}
			currentStreamId = soundPool.play(id, 1, 1, 0, 2, 0);
		}
	};

}
