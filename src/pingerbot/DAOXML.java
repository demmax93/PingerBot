/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingerbot;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author maksi_000
 */
public class DAOXML implements IData
{
    private final String pathXml;
    
    public DAOXML(String pathXml)
    {
        this.pathXml=pathXml;
    }

    @Override
    public Journal load() //path to local var //rename
    {
        Journal list= new Journal();
        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLParser saxp = new XMLParser();
            File file = new File(this.pathXml);
            parser.parse(file, saxp);
            list = saxp.getResult();
        } catch (ParserConfigurationException | SAXException | IOException ex)
        {
            Logger.getLogger(DAOXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //use jaxb https://dev64.wordpress.com/2012/05/15/using-annotations-with-jaxb/
    @Override
    public void save(Journal list)
    {
        try
        {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(this.pathXml);
            Node collections = document.getElementsByTagName("collections").item(0);

            NodeList listener = collections.getChildNodes();
            for(int i = 0; i < listener.getLength(); i++)
            {
                Node node = listener.item(i);
                if("task".equals(node.getNodeName()))
                    collections.removeChild(node);
            }
            
            for(int index=0;index<list.size();index++)
            {
                Element task = document.createElement("task");
                Element id = document.createElement("id");
                Element name = document.createElement("name");
                Element url = document.createElement("url");
                Element attempts = document.createElement("attempts");
                Element god_attempts = document.createElement("god_attempts");
                Element times = document.createElement("times");
                id.appendChild(document.createTextNode(""+list.getId(index)));
                name.appendChild(document.createTextNode(list.getName(index)));
                url.appendChild(document.createTextNode(list.getURL(index)));
                attempts.appendChild(document.createTextNode(""+list.getAttempts(index)));
                god_attempts.appendChild(document.createTextNode(""+list.getGodAttempts(index)));
                times.appendChild(document.createTextNode(""+list.getTimes(index)));
                task.appendChild(id);
                task.appendChild(name);
                task.appendChild(url);
                task.appendChild(attempts);
                task.appendChild(god_attempts);
                task.appendChild(times);
                collections.appendChild(task);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(this.pathXml));
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            transformer.transform(domSource, streamResult);
        }
        catch (ParserConfigurationException | TransformerException | IOException | SAXException pce)
        {
            System.out.println(pce.getLocalizedMessage());
        }
    }
    
}
