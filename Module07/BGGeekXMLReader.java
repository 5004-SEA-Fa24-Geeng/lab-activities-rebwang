
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

public class BGGeekXMLReader {

    public static Set<SimpleBoardGame> read(InputStream in) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            Set<SimpleBoardGame> games = new HashSet<>();
            BGGeekXMLHandler handler = new BGGeekXMLHandler(games);
            saxParser.parse(in, handler);

            return games;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Set.of();// return the empty set
    }

    public static SimpleBoardGame buildRecordFromMap(Map<String, String> map) {
        String name = map.getOrDefault("name", "Unknown"); // discuss what does this do?
        String thumbnail = map.getOrDefault("thumbnail", "");
        String description = map.getOrDefault("description", "");
        int yearPublished = Integer.parseInt(map.getOrDefault("yearPublished", "0"));
        return new SimpleBoardGame(name, thumbnail, description, yearPublished);
    }

    static class BGGeekXMLHandler extends DefaultHandler {
        Set<SimpleBoardGame> games;
        Map<String, String> current;
        StringBuffer buffer = new StringBuffer();

        BGGeekXMLHandler(Set<SimpleBoardGame> games) {
            this.games = games;
        }

        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes attributes) {
            buffer.setLength(0); // reset the buffer
            if (qName.equalsIgnoreCase("item")) {
                String type = attributes.getValue("type"); // since the xml is focused on attributes
                if (type.equals("boardgame")) {
                    current = new HashMap<>();
                }
            } else if (qName.equalsIgnoreCase("name")) {
                String isPrimary = attributes.getValue("type");
                if (isPrimary.equals("primary")) {
                    String name = attributes.getValue("value");
                    current.put("name", name);
                }
            } // add more for yearPublished here
            else if (qName.equalsIgnoreCase("yearPublished")) {
                String yearPublished = attributes.getValue("value");
                current.put("yearPublished", yearPublished);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equalsIgnoreCase("item")) {
                games.add(buildRecordFromMap(current));
                current = null;
            } // add an else that handles description and thumbnail
            else if (qName.equalsIgnoreCase("description")) {
                if (current != null) {
                    current.put("description", buffer.toString().trim());
                }
            }
            else if (qName.equalsIgnoreCase("thumbnail")) {
                if (current != null) {
                    current.put("thumbnail", buffer.toString().trim());
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            buffer.append(ch, start, length);
        }

    }

}
