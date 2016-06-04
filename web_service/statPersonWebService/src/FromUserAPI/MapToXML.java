package ru.geekbrains.userapi.service;

import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.geekbrains.userapi.model.Site;

public class MapToXML {

	public static String DocToXML(Document doc) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StringWriter stringWriter = new StringWriter();
		StreamResult streamResult = new StreamResult(stringWriter);
		transformer.transform(source, streamResult);
		String s = stringWriter.toString();

		return s;
	}
	
	public static String StatisticsToXML(Map<Site, Integer> stat) {
		String s = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// root element
			Element rootElement = doc.createElement("statistics");
			doc.appendChild(rootElement);

			for (Site site : stat.keySet()) {
				int rank = stat.get(site);
				
				Element siteRankElement = doc.createElement("siteRank");
				rootElement.appendChild(siteRankElement);

				Element rankElement = doc.createElement("rank");
				rankElement.appendChild(doc.createTextNode(String.valueOf(rank)));
				siteRankElement.appendChild(rankElement);
				
				Element siteElement = doc.createElement("site");
				siteRankElement.appendChild(siteElement);
				
				Element siteIdElement = doc.createElement("id");
				siteIdElement.appendChild(doc.createTextNode(String.valueOf(site.getId())));
				siteElement.appendChild(siteIdElement);
				
				Element siteNameElement = doc.createElement("name");
				siteNameElement.appendChild(doc.createTextNode(site.getName()));
				siteElement.appendChild(siteNameElement);
			}
			
			s = DocToXML(doc);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}

	public static String StatisticsInPeriodToXML(Map<Date, Map<Site, Integer>> stat) {
		String s = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// root element
			Element rootElement = doc.createElement("statistics");
			doc.appendChild(rootElement);

			for (Date date: stat.keySet()) {
				Element dateElement = doc.createElement("date");
				dateElement.appendChild(doc.createTextNode(DateUtils.StringFromDate(date)));
				rootElement.appendChild(dateElement);
				
				for (Site site : stat.get(date).keySet()) {
					int rank = stat.get(date).get(site);

					Element siteRankElement = doc.createElement("siteRank");
					dateElement.appendChild(siteRankElement);
	
					Element rankElement = doc.createElement("rank");
					rankElement.appendChild(doc.createTextNode(String.valueOf(rank)));
					siteRankElement.appendChild(rankElement);
					
					Element siteElement = doc.createElement("site");
					siteRankElement.appendChild(siteElement);
					
					Element siteIdElement = doc.createElement("id");
					siteIdElement.appendChild(doc.createTextNode(String.valueOf(site.getId())));
					siteElement.appendChild(siteIdElement);
					
					Element siteNameElement = doc.createElement("name");
					siteNameElement.appendChild(doc.createTextNode(site.getName()));
					siteElement.appendChild(siteNameElement);
				}
			}
			
			s = DocToXML(doc);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}
}
