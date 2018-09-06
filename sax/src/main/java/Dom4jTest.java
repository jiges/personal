import org.dom4j.*;
import org.dom4j.io.DOMReader;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author ccr
 * @date 2018/9/5
 */
public class Dom4jTest {

    public static void main(String[] args) throws IOException, DocumentException {
//        DOMReader reader = new DOMReader();
//        SAXReader reader = new SAXReader();
//        Document document = reader.read();
//        createDom();
        read();
    }

    public static void read() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("D:\\temp\\server.xml"));
        Element root = document.getRootElement();
        System.out.println(root.getName());
        List<Attribute> rootAttrList = root.attributes();
        rootAttrList.forEach( attr -> System.out.println(attr.getName() + ":" + attr.getValue()));

        List<Element> elements = root.elements();
        elements.forEach(element -> {
            System.out.println(element.getName());
            List<Attribute> eleAttr = element.attributes();
            eleAttr.forEach( attr -> System.out.println(attr.getName() + ":" + attr.getValue()));
        });
    }

    public static void createDom() throws IOException {
        Document document = DocumentHelper.createDocument();
        document.addComment("Licensed to the Apache Software Foundation (ASF) under one or more\n" +
                "  contributor license agreements.  See the NOTICE file distributed with\n" +
                "  this work for additional information regarding copyright ownership.\n" +
                "  The ASF licenses this file to You under the Apache License, Version 2.0\n" +
                "  (the \"License\"); you may not use this file except in compliance with\n" +
                "  the License.  You may obtain a copy of the License at\n" +
                "\n" +
                "      http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "  Unless required by applicable law or agreed to in writing, software\n" +
                "  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "  See the License for the specific language governing permissions and\n" +
                "  limitations under the License.");
        Element server = DocumentHelper.createElement("server");
        Attribute port = DocumentHelper.createAttribute(server,"port","8005");
        Attribute shutdown = DocumentHelper.createAttribute(server,"shutdown","SHUTDOWN");
        server.add(port);
        server.add(shutdown);
        document.add(server);

        Element listener = DocumentHelper.createElement("Listener");
        Attribute className = DocumentHelper.createAttribute(listener,"className","org.apache.catalina.startup.VersionLoggerListener");
        server.addComment("Security listener. Documentation at /docs/config/listeners.html");
        listener.add(className);
        server.add(listener);

        Element listener2 = DocumentHelper.createElement("Listener");
        Attribute className2 = DocumentHelper.createAttribute(listener2,"className","org.apache.catalina.core.AprLifecycleListener");
        server.addComment("Security listener. Documentation at /docs/config/listeners.html");
        listener2.add(className2);
        server.add(listener2);

        OutputFormat format = new OutputFormat("    ", true);
        XMLWriter xmlWriter = new XMLWriter(format);
        xmlWriter.write(document);
    }
}
