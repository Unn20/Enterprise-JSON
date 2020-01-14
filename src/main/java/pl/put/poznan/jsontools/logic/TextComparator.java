package pl.put.poznan.jsontools.logic;

import org.apache.tomcat.util.buf.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class TextComparator {
	public static String compare(String x, String y) {
		Collection<String> xi = new ArrayList<String>();
		Collection<String> yi = new ArrayList<String>();
		if (x.equals("")) {
			var ys = y.split("\n");
			for (int i = 0; i < ys.length; i++) yi.add(String.valueOf(i));
		} else if (y.equals("")) {
			var xs = x.split("\n");
			for (int i = 0; i < xs.length; i++) xi.add(String.valueOf(i));
		} else {
			var xs = x.split("\n");
			var ys = y.split("\n");
			for (int i = 0; i < Math.max(xs.length, ys.length); i++) {
				if (i >= xs.length) {
					yi.add(String.valueOf(i));
				} else if (i >= ys.length) {
					xi.add(String.valueOf(i));
				} else if (xs[i].equals(ys[i])) {
					xi.add(String.valueOf(i));
					yi.add(String.valueOf(i));
				}
			}
		}
		return "{\"left\": ["+String.join(",",xi)+"], \"right\": ["+String.join(",",yi)+"]}";
	}
}
