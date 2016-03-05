package com.gatmobile.magictimer;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

		for(Player player : players){
			player.getChronometer_total().setBase(SystemClock.elapsedRealtime());
		}

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

				long millis = SystemClock
						.elapsedRealtime()-chronometer.getBase();
				System.out.println("I clicked at " + TimeUnit.MILLISECONDS.toSeconds(millis) + " " +
						"seconds / elapse time is " + SystemClock.elapsedRealtime() + " (" +
						TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime())+
						")");

				Player currentPlayer = players.get(currentNbPlayer);
				currentPlayer.getChronometer_total().stop();
				currentPlayer.setTimeChronometer(currentPlayer.getTimeChronometer() + millis);

				reset();
				start();


				for(Player player : players){
					System.out.println(player.getName() + " time : " + TimeUnit.MILLISECONDS
							.toSeconds
							(player
							.getTimeChronometer()));
				}

				System.out.println("total elapsed: " + TimeUnit.MILLISECONDS.toSeconds
						(totalTimePassedInOtherTimers(currentPlayer)) + " base should be " +
						TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime() -
								totalTimePassedInOtherTimers(currentPlayer)));

				currentNbPlayer = (currentNbPlayer + 1) % totalNbPlayers;
				currentPlayer = players.get(currentNbPlayer);


				currentPlayer.getChronometer_total().setBase(SystemClock.elapsedRealtime() -
						totalTimePassedInOtherTimers(currentPlayer));
				currentPlayer.getChronometer_total().start();

				System.out.println("base is " + currentPlayer.getChronometer_total().getBase());


				//

				/*System.out.println("saving time for player: " + currentNbPlayer + " / " +
						currentPlayer.getName() + " = " + currentPlayer.getChronometer_total()
						.getBase() + " / " +
						chronometer.getBase() + " elapsed " + SystemClock.elapsedRealtime() + ", "

						);
				System.out.println("time gone in seconds " + TimeUnit.MILLISECONDS.toSeconds(
						(SystemClock.elapsedRealtime()-chronometer.getBase())));
				currentPlayer.setTimeChronometer(currentPlayer.getTimeChronometer() + SystemClock.elapsedRealtime()-chronometer.getBase());
				System.out.println("total time in seconds: " + TimeUnit.MILLISECONDS.toSeconds
						(currentPlayer.getTimeChronometer()));
				*//*currentPlayer.getChronometer_total().setBase(
						SystemClock.elapsedRealtime() -	(+currentPlayer.getChronometer_total()
								.getBase() + chronometer.getBase()));*//*
				currentPlayer.getChronometer_total().setBase(currentPlayer.getTimeChronometer());
				currentPlayer.getChronometer_total().start();
				currentPlayer.getChronometer_total().stop();
				currentNbPlayer = (currentNbPlayer + 1) % totalNbPlayers;


				currentPlayer = players.get(currentNbPlayer);
				System.out.println("the time for next player: " + currentNbPlayer + " / " +
						currentPlayer.getName() + " = " +
						chronometer.getBase());
				reset();
				start();
				chronometer.start();
				//currentPlayer.getChronometer_total().setBase(
				//		chronometer.getBase());
				*//*currentPlayer.getChronometer_total().setBase(
						SystemClock.elapsedRealtime() -	(+currentPlayer.getChronometer_total()
								.getBase() + chronometer.getBase()));*//*

				currentPlayer.getChronometer_total().start();

				//currentPlayer.getChronometer().setBase(SystemClock.elapsedRealtime());
				//currentPlayer.getChronometer().start();*/



			}
		});


	}

	boolean firstTime = true;

	public long totalTimePassedInOtherTimers(Player currentPlayer){
		long result = 0;
		for(int i = 0 ; i < players.size() ; i++){
			Player player = players.get(i);
			if(player.equals(currentPlayer)){
				System.out.println("not counting " + player.getName());
				//continue;
			}

			result += player.getTimeChronometer();
		}
		return result;
	}

	public long totalTimePassed(){
		long result = 0;
		for(Player player : players){
			result += player.getTimeChronometer();
		}
		return result;
	}

	public void start(){
		//TODO : check if not already started before doing this
		if(firstTime) {
			Player currentPlayer = players.get(currentNbPlayer);
			currentPlayer.getChronometer_total().setBase(SystemClock.elapsedRealtime());
			currentPlayer.getChronometer_total().start();
			firstTime = false;
		}
		//chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();

	}

	public void reset(){
		//chronometer.setBase(SystemClock.elapsedRealtime());
	}


}
