package com.nagot.mediaplayerservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startPlaybackButton, stopPlaybackButton;
    Intent playbackServiceIntent;
    String text = "passou!";
    BackgroundAudioService backgroundAudioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startPlaybackButton = (Button) this.findViewById(R.id.StartPlaybackButton);
        stopPlaybackButton = (Button) this.findViewById(R.id.StopPlaybackButton);

        playbackServiceIntent = new Intent(this, BackgroundAudioService.class);
        playbackServiceIntent.putExtra("passou", text);


        startPlaybackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(playbackServiceIntent);
                //finish();
            }
        });

        stopPlaybackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(playbackServiceIntent);
                //finish();
                //stopPlaybackButton.setText(String.valueOf(new BackgroundAudioService().getCurrentPosition()));
                //stopPlaybackButton.setText(String.valueOf(backgroundAudioService.getCurrentPosition()));
            }
        });
    }
}
