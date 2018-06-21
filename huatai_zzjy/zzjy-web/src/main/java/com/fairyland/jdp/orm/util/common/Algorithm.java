package com.fairyland.jdp.orm.util.common;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
@SuppressWarnings("unchecked")
public class Algorithm {
	private static Logger log = LoggerFactory.getLogger(Algorithm.class);
    public static int binarySearch(List<?> items, Object val, Comparator comparator) {
	int s = 0;
	int startIndex = 0;
	int stopIndex = items.size() - 1;
	int middle = (int) Math.floor(Double.valueOf(stopIndex + startIndex).doubleValue() / 2.0D);
	while (((s = middle < 0 ? (middle = 0) : comparator.compare(items.get(middle), val)) != 0) && (startIndex < stopIndex)) {
	    if (s > 0)
		stopIndex = middle - 1;
	    else if (s < 0) {
		startIndex = middle + 1;
	    }
	    middle = (int) Math.floor((stopIndex + startIndex) / 2);
	}
	return s != 0 ? middle + 1 : s > 0 ? middle : middle;
    }

    public static int binarySearch(Object[] items, Object val, Comparator comparator) {
	int startIndex = 0;
	int stopIndex = items.length - 1;
	int middle = (int) Math.floor(Double.valueOf(stopIndex + startIndex).doubleValue() / 2.0D);
	int s;
	while (((s = comparator.compare(items[middle], val)) != 0) && (startIndex < stopIndex)) {
	    if (s > 0)
		stopIndex = middle - 1;
	    else if (s < 0) {
		startIndex = middle + 1;
	    }
	    middle = (int) Math.floor((stopIndex + startIndex) / 2);
	}
	return s != 0 ? middle + 1 : s > 0 ? middle - 1 : middle;
    }

    public static int binarySearch(String[] items, String val) {
	int startIndex = 0;
	int stopIndex = items.length - 1;
	int middle = (int) Math.floor(Double.valueOf(stopIndex + startIndex).doubleValue() / 2.0D);
	int s;
	while (((s = items[middle].compareTo(val)) != 0) && (startIndex < stopIndex)) {
	    if (s > 0)
		stopIndex = middle - 1;
	    else if (s < 0) {
		startIndex = middle + 1;
	    }
	    middle = (int) Math.floor((stopIndex + startIndex) / 2);
	}
	return s != 0 ? middle + 1 : s > 0 ? middle - 1 : middle;
    }

    public static void bubbleSort(Object[] src, Comparator comparator) {
	int len = src.length;
	int n = 0;
	for (int i = 0; i < len; n = 0) {
	    for (int j = len - 1; j > i; j--) {
		if (comparator.compare(src[j], src[(j - 1)]) > 0) {
		    swap(src, j, j - 1);
		    n++;
		}
	    }
	    if (n == 0) {
		log.debug("已经有序!");
		printResult(i, src);
		break;
	    }
	    System.out.print("交换次数:" + n + "\t");
	    printResult(i, src);

	    i++;
	}
    }

    public static void insertSort(Object[] src, Comparator comparator) {
	int len = src.length;
	for (int i = 1; i < len; i++)
	    for (int j = 0; j < i; j++)
		if (comparator.compare(src[j], src[i]) > 0) {
		    log.debug("发生交换");
		    Object[] dest = new Object[i + 1];
		    System.arraycopy(src, 0, dest, 0, i + 1);
		    printResult(i, dest);
		    swap(src, j, i);
		    System.arraycopy(src, 0, dest, 0, i + 1);
		    printResult(i, dest);
		    log.debug("=================");
		}
    }

    public static void insertSort(List<?> src, Comparator comparator) {
	int len = src.size();
	for (int i = 1; i < len; i++)
	    for (int j = 0; j < i; j++)
		;
    }

    public static void swap(List<?> data, int i, int j) {
    }

    public static void swap(Object[] data, int i, int j) {
	Object temp = data[i];
	data[i] = data[j];
	data[j] = temp;
    }

    private static void printResult(int i, Object[] src) {
	log.debug(i + "|" + Arrays.toString(src));
    }

//    public static void main(String[] args) {
//	String[] items = { "roles", "subMenus", "parentMenu" };
//
//	log.debug(binarySearch(items, "roles"));
//	log.debug("===========================");
//	log.debug(binarySearch(items, "subMenus"));
//	log.debug("===========================");
//	log.debug(binarySearch(items, "parentMenu"));
//	log.debug("===========================");
//	log.debug(binarySearch(items, "type"));
//    }
}