package com.example.a_i_fitco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Angle;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;

import nl.dionsegijn.konfetti.core.Spread;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.xml.KonfettiView;



public class finished_easy extends AppCompatActivity {
    EmitterConfig emitterConfig = new Emitter(5, TimeUnit.SECONDS).perSecond(30);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_easy);
        final KonfettiView konfettiView = findViewById(R.id.konfettiView);
                konfettiView.start(new PartyFactory(emitterConfig)
                                .angle(Angle.RIGHT - 45)
                                .spread(Spread.SMALL)

                                .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                                .setSpeedBetween(10f, 30f)
                                .position(new Position.Relative(0.0, 0.5))
                                .build(),
                        new PartyFactory(emitterConfig)
                                .angle(Angle.LEFT + 45)
                                .spread(Spread.SMALL)
                                .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                                .setSpeedBetween(10f, 30f)
                                .position(new Position.Relative(1.0, 0.5))
                                .build());


    }



}
