package com.nagot.mediaplayerservice;

/**
 * Created by Nagot on 25/09/2016.
 */
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class BackgroundAudioService extends Service implements OnCompletionListener {
    static MediaPlayer mediaPlayer;
    String text;

    public class LocalBinder extends Binder {
        BackgroundAudioService getService() {
            return BackgroundAudioService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.s);// raw/s.mp3
        //mediaPlayer = MediaPlayer.create(this,)
        mediaPlayer.setOnCompletionListener(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extras = intent.getExtras();
        text = extras.getString("passou");

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        } else {
            mediaPlayer.pause();
        }
        return START_STICKY;
    }

    public void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    public void onCompletion(MediaPlayer _mediaPlayer) {
        stopSelf();
    }

    public int getCurrentPosition(){
        return mediaPlayer.getCurrentPosition();
    }

}
