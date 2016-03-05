package com.gatmobile.magictimer;

import android.os.Bundle;
import android.os.SystemClock;
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
				new Player(getString(R.string.player_1),
						(Chronometer) findViewById(R.id.chrono_total_1)));
		players.add(
				new Player(getString(R.string.player_2),
						(Chronometer) findViewById(R.id.chrono_total_2)));
		players.add(
				new Player(getString(R.string.player_3),
						(Chronometer) findViewById(R.id.chrono_total_3)));
		players.add(
				new Player(getString(R.string.player_4),
						(Chronometer) findViewById(R.id.chrono_total_4)));
		players.add(
				new Player(getString(R.string.player_5),
						(Chronometer) findViewById(R.id.chrono_total_5)));
		players.add(
				new Player(getString(R.string.player_6),
						(Chronometer) findViewById(R.id.chrono_total_6)));

		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				start();
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
				reset();
			}
		});

		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Player currentPlayer = players.get(currentNbPlayer);

				System.out.println("saving time for player: " + currentNbPlayer + " / " +
						currentPlayer.getName() + " = " +
						chronometer.getBase());
				currentPlayer.getChronometer_total().setBase( chronometer.getBase());
				currentPlayer.getChronometer_total().stop();
				currentNbPlayer = (currentNbPlayer + 1) % totalNbPlayers;

				System.out.println("next player: " + currentNbPlayer + ".");
				currentPlayer = players.get(currentNbPlayer);

				reset();
				start();
				chronometer.start();
				currentPlayer.getChronometer_total().setBase( chronometer.getBase());
				currentPlayer.getChronometer_total().start();

				//currentPlayer.getChronometer().setBase(SystemClock.elapsedRealtime());
				//currentPlayer.getChronometer().start();



			}
		});


	}

	public void start(){
		//TODO : check if not already started before doing this
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();

	}

	public void reset(){
		chronometer.setBase(SystemClock.elapsedRealtime());
	}


}
