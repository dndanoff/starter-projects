package io.github.dndanoff.itest.common;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RandomData {

	public static String getRandomUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static String getRandomId() {
		Random rand = new Random();
		return new Integer(rand.nextInt(60000)).toString();
	}

	public static String getRandomElementOfList(List<String> list) {
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}
	
}
