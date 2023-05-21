package managers;

import java.util.ArrayList;
import java.util.Random;

import events.Wave;
import scenes.Playing;

public class WaveManager {
	
	@SuppressWarnings("unused")
	private Playing playing;
	private ArrayList<Wave> waves = new ArrayList<>();
	
	private int enemySpawnTickLimit = 60 * 1;
	private int enemySpawnTick = 60;
	private int enemyIndex;
	private int waveIndex;
	private int waveTickLimit = 60 * 5;
	private int waveTick = 0;
	private boolean waveStartTimer;
	private boolean waveTickTimerOver;
	private int waveSize;
	
	Random random;
	
	public WaveManager(Playing playing) {
		this.playing  = playing;
		this.waveSize = 5;
		this.random = new Random();
		createWaves();
	}
	
	public void update() {
		if(enemySpawnTick < enemySpawnTickLimit) {
			enemySpawnTick++;
		}
		
		if(waveStartTimer) {
			waveTick++;
			if(waveTick >= waveTickLimit) {
				waveTickTimerOver = true;
			}
		}
	}
	
	public void increaseWaveIndex() {
		waveIndex++;
		waveTick = 0;
		waveTickTimerOver = false;
		waveStartTimer = false;
		waveSize += random.nextInt(3);
		createWaves();
	}
	
	public int getNextEnemy() {
		enemySpawnTick = 0;
		return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
	}

	private void createWaves() {
		ArrayList<Integer> wave = new ArrayList<>();
		for(int i = 0; i < waveSize; i++) {
			wave.add(random.nextInt(3));
		}
		waves.add(new Wave(wave));
	}

	public ArrayList<Wave> getWaves() {
		return waves;
	}

	public boolean isTimeForNewEnemy() {
		return enemySpawnTick >= enemySpawnTickLimit;
	}
	
	public boolean isThereEnemyLeftInWave() {
		return enemyIndex < waves.get(waveIndex).getEnemyList().size();
	}

	public boolean isThereMoreWaves() {
		return true;
		//return waveIndex + 1 < waves.size();
	}

	public void startWaveTimer() {
		waveStartTimer = true;
	}

	public boolean isWaveTimerOver() {
		return waveTickTimerOver;
	}

	public void resetEnemyIndex() {
		enemyIndex = 0;
	}
	
	public int getWaveIndex() {
		return waveIndex;
	}
	
	public float getTimeLeft() {
		float ticksLeft = waveTickLimit - waveTick;
		return ticksLeft / 60.0f;
	}

	public boolean isWaveTimerStarted() {
		return waveStartTimer;
	}

	public void reset() {
		waves.clear();
		createWaves();
		enemyIndex = 0;
		waveIndex = 0;
		waveStartTimer = false;
		waveTickTimerOver = false;
		waveTick = 0;
		enemySpawnTick = enemySpawnTickLimit;
		waveSize = 5;
	}
}
