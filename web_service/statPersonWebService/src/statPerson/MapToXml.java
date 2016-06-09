package statPerson;

import java.io.StringWriter;
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

import statPerson.element.person.Person;

public class MapToXml {
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
	
	public static String PersonsRankToXML(Map<Person, Integer> stat) {
		String s = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// root element
			Element rootElement = doc.createElement("statistics");
			doc.appendChild(rootElement);

			for (Person person : stat.keySet()) {
				int rank = stat.get(person);
				
				Element personRankElement = doc.createElement("personRank");
				rootElement.appendChild(personRankElement);

				Element rankElement = doc.createElement("rank");
				rankElement.appendChild(doc.createTextNode(String.valueOf(rank)));
				personRankElement.appendChild(rankElement);
				
				Element personElement = doc.createElement("person");
				personRankElement.appendChild(personElement);
				
				Element personIdElement = doc.createElement("id");
				personIdElement.appendChild(doc.createTextNode(String.valueOf(person.getId())));
				personElement.appendChild(personIdElement);
				
				Element personNameElement = doc.createElement("name");
				personNameElement.appendChild(doc.createTextNode(person.getName()));
				personElement.appendChild(personNameElement);
			}
			
			s = DocToXML(doc);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}
}
