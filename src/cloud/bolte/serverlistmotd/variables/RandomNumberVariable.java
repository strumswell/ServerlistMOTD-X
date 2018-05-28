package cloud.bolte.serverlistmotd.variables;

import java.util.Random;

import cloud.bolte.serverlistmotd.SpigotConfig;

public class RandomNumberVariable {
	
	public static int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(SpigotConfig.getRandomMax() - SpigotConfig.getRandomMin()) + SpigotConfig.getRandomMin();
	}
}
