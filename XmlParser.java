/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author MatthewVillegas
 */
public class XmlParser {
    ConnectionData connectionData = new ConnectionData();
    Document document;
    Logger log = Logger.getLogger(XmlParser.class.getName());

    /**
     *
     * @param filePath stores the file path to the XML document
     */
    public XmlParser(String filePath)
    {
      log.log(Level.INFO, "Fetching XML Data");
      parseXmlFile(filePath);
      
    }
    
    /**
     *
     * @param filePath holds the XML file path and is then parsed
     */
    // takes in a file and builds an XML document from it, then parses the
    // information in that file
    public void parseXmlFile(String filePath)
    {
        log.log(Level.INFO, "Parsing XML");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try 
        {
            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Load and Parse the XML document
            //document contains the complete XML as a Tree.
            document = db.parse(ClassLoader.getSystemResourceAsStream(filePath));
            
            //Iterating through the nodes and extracting the data.
            NodeList nodeList = document.getDocumentElement().getChildNodes();

            for(int i = 0; i < nodeList.getLength(); i++) 
            {
                //get the <Employee> element
                Node node = nodeList.item(i);

                if(node instanceof Element)
                {
                   
                    String type = node.getAttributes().getNamedItem("type").getNodeValue();
                    String url = "";
                    String login = "";
                    String ipaddress = "";
                    String database = "";
                    String port = "";
                    String password = "";
                    
                    NodeList childNodes = node.getChildNodes();

                    for (int j = 0; j < childNodes.getLength(); j++) 
                    {
                        Node cNode = childNodes.item(j);  
 
                        //Identifying the child tag of employee encountered.
                        if (cNode instanceof Element) 
                        {
                            String content = cNode.getLastChild().getTextContent().trim();
                            switch (cNode.getNodeName()) 
                            {
                                case "url":
                                  url = content;
                                  break;
                                case "ipaddress":
                                  ipaddress = content;
                                  break;
                                case "port":
                                  port = content;
                                  break;
                                case "database":
                                  database = content;
                                case "login":
                                  login = content;
                                case "password":
                                  password = content;
                            }
                        }
                    }
                    //Create a new Employee with the value read from the xml nodes
                    
                    connectionData.setType(type);
                    connectionData.setUrl(url);
                    connectionData.setDatabase(database);
                    connectionData.setIpaddress(ipaddress);
                    connectionData.setLogin(login);
                    connectionData.setPassword(password);
                    connectionData.setPort(port);

               }
            }
        }
        catch(ParserConfigurationException pce) 
        {
            log.log(Level.SEVERE, "Rip");
            pce.printStackTrace();
        }
        catch(SAXException se) 
        {
            se.printStackTrace();
        }
        catch(IOException ioe) 
        {
            ioe.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public ConnectionData getConnectionData() {
        return connectionData;
    }
}
