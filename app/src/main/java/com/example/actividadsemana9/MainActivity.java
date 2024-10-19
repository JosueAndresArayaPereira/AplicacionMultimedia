package com.example.actividadsemana9;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.never_gonna_give_you_up);
        videoView.setVideoURI(videoUri);

        Button playAudioButton = findViewById(R.id.PlayButton);
        Button stopAudioButton = findViewById(R.id.StopButton);

        // Reproducir el audio
        playAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pausar el video si está reproduciéndose
                if (videoView.isPlaying()) {
                    videoView.pause();
                }

                // Iniciar el audio si no está ya sonando
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.omfg_hello);
                    mediaPlayer.start();
                } else if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
            }
        });

        // Detener el audio
        stopAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;

                    // Reanudar el video si fue pausado
                    if (!videoView.isPlaying()) {
                        videoView.start();
                    }
                }
            }
        });

        // Reproducir el video automáticamente
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
