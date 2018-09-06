import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * @author ccr
 * @date 2018/9/5
 */
public class SaxDemo {
    static class MyContentHandler extends DefaultHandler {
        @Override
        public void startDocument() throws SAXException {
            System.out.println("开始解析文档...");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            System.out.printf("解析标签：%s,%s,%s\n",uri,localName,qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            System.out.printf("文档内容:%s,%s,%s\n", ch.length,start,length);
            for (int i = start; i < length; i++) {
                System.out.print(ch[i]);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("解析文档结束....");

        }
    }
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLReader reader = parser.getXMLReader();
        reader.setContentHandler(new MyContentHandler());
        reader.parse("D:\\temp\\server.xml");

    }
}
