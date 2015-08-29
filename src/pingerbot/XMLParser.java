/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingerbot;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author maksi_000
 */
public class XMLParser extends DefaultHandler
{
    private String thisElement = "";
    private int id=0;
    private String name="";
    private String url="";
    private int attempts;
    private int god_attempts;// _
    private long times;
    private Journal list = new Journal();
    
    @Override 
    public void startDocument() throws SAXException
    {
        //System.out.println("Start parse XML");
    }
    
    @Override
    public void startElement(String namespcaseURI, String localName,String qName, Attributes atts) throws SAXException
    {
        thisElement = qName;
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        if(thisElement.equals("id")) //equals to constants
        {
            id=new Integer(new String(ch, start, length));
        }
        if(thisElement.equals("name"))
        {
            name=new String(ch, start, length);
        }
        if(thisElement.equals("url"))
        {
            url=new String(ch, start, length);
        }
        if(thisElement.equals("attempts"))
        {
            attempts=new Integer(new String(ch, start, length));
        }
        if(thisElement.equals("god_attempts"))
        {
            god_attempts=new Integer(new String(ch, start, length));
        }
        if(thisElement.equals("times"))
        {
            times=new Long(new String(ch, start, length));
        }
    }
    
    @Override
    public void endElement(String namespcaseURI, String localName,String qName) throws SAXException
    {
       thisElement = ""; 
       if(qName.equals("task"))
           list.add(id,name,url,attempts,god_attempts,times);
    }
    
    @Override
    public void endDocument()
    {
        //System.out.println("Stop parse XML...");
    }
    
    public void listZeroing()
    {
        this.list.clear();
    }
   
    public Journal getResult()
    {
        return this.list;
    }
    
}
