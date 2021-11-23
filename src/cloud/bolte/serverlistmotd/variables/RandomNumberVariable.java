package cloud.bolte.serverlistmotd.variables;

import java.util.Random;

import cloud.bolte.serverlistmotd.SpigotConfig;

public class RandomNumberVariable {
	
	private final static Random random = new Random();

	private RandomNumberVariable() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Get a random number based on min/ max from config
	 * Only used in VersionText at the moment which you
	 * can use to fake an online count / slots
	 * @return random number
	 */
	public static int getRandomNumber() {
		return random.nextInt(SpigotConfig.getRandomMax() - SpigotConfig.getRandomMin()) + SpigotConfig.getRandomMin();
	}
}
