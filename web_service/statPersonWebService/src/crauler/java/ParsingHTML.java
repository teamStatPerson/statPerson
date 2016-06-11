package crauler.java;

import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Андрей on 31.05.2016.
 */
public class ParsingHTML {
	private List<String> keywords = new ArrayList<String>();
	private int raiting;
	private String html;

	public ParsingHTML(String _html, List<String> _keywords) {
		keywords = _keywords;
		html = _html;
		raiting = 0;
	}

	public int raitingPerson() {
		DownloaderXML downloaderXML = new DownloaderXML(html);
		Document doc = downloaderXML.getDoc();
		String pageHTML = doc.html();
		// System.out.println("url = " + html);
		for (String keyword : keywords) {
			// System.out.println(keyword);

			Pattern p = Pattern.compile("\\b" + keyword + "\\b", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(pageHTML);
			while (m.find())
				raiting++;
			// System.out.println("raiting = " + raiting);
		}
		return raiting;
	}
}
