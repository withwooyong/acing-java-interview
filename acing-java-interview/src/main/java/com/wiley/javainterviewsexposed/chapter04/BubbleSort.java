package com.wiley.javainterviewsexposed.chapter04;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 버블 정렬 알고리즘은 어떻게 구현하는가?
 * 
 * @author user
 *
 */
public class BubbleSort {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public void bubbleSort(int[] numbers) {
		boolean numbersSwitched;
		int compareCount = 0;
		do {
			numbersSwitched = false;
			for (int i = 0; i < numbers.length - 1; i++) {
				if (numbers[i + 1] < numbers[i]) {
					int tmp = numbers[i + 1];
					numbers[i + 1] = numbers[i];
					log.debug("numbers[i]={} tmp={}", numbers[i], tmp);
					numbers[i] = tmp;
					numbersSwitched = true;
					
				}
				compareCount++;
			}
		} while (numbersSwitched);
		log.debug("compareCount={}", compareCount);
	}

	@Test
	public void testBubble() {
		final int[] numbers = { 6, 4, 9, 5 };
		final int[] expected = { 4, 5, 6, 9 };

		bubbleSort(numbers);
		assertArrayEquals(expected, numbers);
	}
}
