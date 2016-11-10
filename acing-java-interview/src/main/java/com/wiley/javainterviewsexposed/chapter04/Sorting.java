package com.wiley.javainterviewsexposed.chapter04;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Comparable과 Comparator 인터페이스의 차이는 무엇인가?
 * @author user
 *
 */
public class Sorting {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * int 타입 배열은 순서대로 정렬하기
	 */
    @Test
    public void sortInts() {
        final int[] numbers = {-3, -5, 1, 7, 4, -2};
        final int[] expected = {-5, -3, -2, 1, 4, 7};
        Arrays.sort(numbers);
        assertArrayEquals(expected, numbers);        
    }
    
    /**
     * 객체를 순서대로 정렬하기
     */
    @Test
    public void sortObjects() {
        final String[] strings = {"z", "x", "y", "abc", "zzz", "zazzy"};
        final String[] expected = {"abc", "x", "y", "z", "zazzy", "zzz"};

        Arrays.sort(strings);
        assertArrayEquals(expected, strings);
    }
    
    private static class NotComparable {
        private int i;
        private NotComparable(final int i) {
            this.i = i;
        }
    }

    /**
     * Comparable 인터페이스 없이 정렬하기
     */
    @Test
    public void sortNotComparable() {
        final List<NotComparable> objects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            objects.add(new NotComparable(i));
        }

        try {
            Arrays.sort(objects.toArray());
        } catch (Exception e) {
            // correct behavior – cannot sort
            return;
        }

        fail();
    }
    
    /**
     * 사용자가 지정한 순서로 정렬하기
     */
    @Test
    public void customSorting() {
        final List<Integer> numbers = Arrays.asList(4, 7, 1, 6, 3, 5, 4);
        final List<Integer> expected = Arrays.asList(7, 6, 5, 4, 4, 3, 1);

        Collections.sort(numbers, new ReverseNumericalOrder());
        assertEquals(expected, numbers);
    }
}
