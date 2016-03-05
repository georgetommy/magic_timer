package com.gatmobile.magictimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.util.ArrayList;
import java.util.List;

public class TimerActivity extends AppCompatActivity {

	Chronometer chronometer;
	Button start, pause, reset, next;

	List<Player> players = new ArrayList<>();

	int totalNbPlayers = 6;
	int currentNbPlayer = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer);

		chronometer = (Chronometer) findViewById(R.id.chronometer_main);
		start = (Button) findViewById(R.id.start_chrono_main);
		pause = (Button) findViewById(R.id.pause_chrono_main);
		reset = (Button) findViewById(R.id.reset_chrono_main);
		next = (Button) findViewById(R.id.next_chrono_main);


		players.add(
				new Player(getString(R.string.player_1), (Chronometer) findViewById(R.id.chrono_1),
						(Chronometer) findViewById(R.id.chrono_total_1)));
		players.add(
				new Player(getString(R.string.player_2), (Chronometer) findViewById(R.id.chrono_2),
						(Chronometer) findViewById(R.id.chrono_total_2)));
		players.add(
				new Player(getString(R.string.player_3), (Chronometer) findViewById(R.id.chrono_3),
						(Chronometer) findViewById(R.id.chrono_total_3)));
		players.add(
				new Player(getString(R.string.player_4), (Chronometer) findViewById(R.id.chrono_4),
						(Chronometer) findViewById(R.id.chrono_total_4)));
		players.add(
				new Player(getString(R.string.player_5), (Chronometer) findViewById(R.id.chrono_5),
						(Chronometer) findViewById(R.id.chrono_total_5)));
		players.add(
				new Player(getString(R.string.player_6), (Chronometer) findViewById(R.id.chrono_6),
						(Chronometer) findViewById(R.id.chrono_total_6)));

		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chronometer.start();
			}
		});

		pause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chronometer.stop();
			}
		});

		reset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chronometer.setFormat(null);
			}
		});

		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("saving time for player: " + currentNbPlayer + " / " +
						players.get(currentNbPlayer).getName() + " = " +
						players.get(currentNbPlayer).getChronometer().getFormat());

				currentNbPlayer = (currentNbPlayer + 1) % totalNbPlayers;
				System.out.println("next player: " + currentNbPlayer + ".");

			}
		});


	}


}
