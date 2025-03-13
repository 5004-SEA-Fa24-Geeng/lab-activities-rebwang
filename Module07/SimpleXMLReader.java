import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

public class SimpleXMLReader {
    // Reads an XML file at the specified filePath and parses it using SAX.
    public void read(String filePath) {
        try {
            File inputFile = new File(filePath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            List<Person> people = new ArrayList<>();
            SimpleXMLHandler handler = new SimpleXMLHandler(people);
            saxParser.parse(inputFile, handler);

            System.out.println(people);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This is a nested class
    // This class extends DefaultHandler and defines how SAX processes the XML elements.
    static class SimpleXMLHandler extends DefaultHandler {
        List<Person> people;
        Map<String, String> current;
        StringBuffer buffer = new StringBuffer();

        // Constructor
        SimpleXMLHandler(List<Person> people) {
            this.people = people;
        }

        // Called when an opening XML tag (e.g., <person>) is encountered
        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) {
            buffer.setLength(0); // reset the buffer
            if (qName.equalsIgnoreCase("person")) {
                current = new HashMap<>();
            }
        }

        // Called when a closing XML tag (e.g., </person>) us encountered
        @Override
        public void endElement(String uri, String localName, String qName) {
//             student to add
            if (qName.equalsIgnoreCase("person")) {
                people.add(Person.fromMap(current));
                current = null;
            } else {
                if (current != null) {
                    current.put(qName, buffer.toString());
                }
            }
        }

        // Called when character data (text between XML tags) is encountered.
        @Override
        public void characters(char[] ch, int start, int length) {
            buffer.append(ch, start, length);
        }

    }

    public static void main(String[] args) {
        SimpleXMLReader reader = new SimpleXMLReader();
        reader.read("simple.xml");
    }
}
