package crauler.java;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Андрей on 31.05.2016.
 */
public class ParsingHTML {

	public static int[] rankPerson(String pageHTML, List<String> keywords) {
		int[] rank = new int[keywords.size()];
		for (int i = 0; i < rank.length; i++) {
			rank[i] = 0;
			Pattern p = Pattern.compile("\\b" + keywords.get(i) + "\\b",
					Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(pageHTML);
			while (m.find()) {
				rank[i]++;
			}
		}
		return rank;
	}
}
