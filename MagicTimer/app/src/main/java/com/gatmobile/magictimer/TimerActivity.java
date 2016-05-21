package com.gatmobile.magictimer;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

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
				pause();
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
			public void onClick(View v) { next();	}
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

	long startTime = 0;

	private Player updatePlayer(int playerIndex){
		Player currentPlayer = players.get(playerIndex);
		((TextView)findViewById (R.id.player_current)).setText (currentPlayer.getName());
		return currentPlayer;
	}

	private void next(){
		long millis = SystemClock
				.elapsedRealtime()-chronometer.getBase();
		System.out.println("I clicked at " + TimeUnit.MILLISECONDS.toSeconds(millis) + " " +
				"seconds / elapse time is " + SystemClock.elapsedRealtime() + " (" +
				TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime())+
				")");


		Player currentPlayer = updatePlayer(currentNbPlayer);
		currentPlayer.getChronometer_total().stop();
		currentPlayer.setTimeChronometer(currentPlayer.getTimeChronometer() + millis);
		((TextView)findViewById (R.id.player_current)).setText (currentPlayer.getName());

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
		currentPlayer =  updatePlayer(currentNbPlayer);

		currentPlayer.getChronometer_total().setBase(SystemClock.elapsedRealtime() -
				(TimeUnit.MILLISECONDS.toMinutes(currentPlayer.getTimeChronometer()) *
						60000 + TimeUnit.MILLISECONDS.toSeconds(currentPlayer.getTimeChronometer
						()) *
						1000));
		currentPlayer.getChronometer_total().start();

		System.out.println("base is " + currentPlayer.getChronometer_total().getBase());
	}

	public void start(){
		//TODO : check if not already started before doing this
		if(firstTime) {
			Player currentPlayer = updatePlayer(currentNbPlayer);
			currentPlayer.getChronometer_total().setBase(SystemClock.elapsedRealtime());
			currentPlayer.getChronometer_total().start();
			firstTime = false;
			startTime = SystemClock.elapsedRealtime();
		}
		System.out.println("system time before: " + SystemClock.elapsedRealtime());
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();
		System.out.println("system time after: " + SystemClock.elapsedRealtime());

	}

	public void reset(){
		System.out.println("system time before: " + SystemClock.elapsedRealtime());
		chronometer.setBase(SystemClock.elapsedRealtime());
		System.out.println("system time after: " + SystemClock.elapsedRealtime());
	}

	private boolean stopped = false;

	private long timeStopped = 0;

	public void pause(){
		System.out.println("pausing.");
		if(!stopped) {
			Player currentPlayer = updatePlayer(currentNbPlayer);
			currentPlayer.getChronometer_total().stop();
			chronometer.stop();
			stopped = true;
			timeStopped = SystemClock.elapsedRealtime();
		}else{
			Player currentPlayer = updatePlayer(currentNbPlayer);
			long intervalOnPause = (SystemClock.elapsedRealtime() - timeStopped);
			chronometer.setBase(chronometer.getBase() + intervalOnPause);
			currentPlayer.getChronometer_total().setBase(currentPlayer.getChronometer_total()
					.getBase() + intervalOnPause);
			currentPlayer.getChronometer_total().start();
			chronometer.start();
			stopped = false;
		}
	}


}
