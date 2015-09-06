/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingerbot;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class DAOJAXB implements IData
{
    private final String pathXml;
    
    public DAOJAXB(String pathXml)
    {
        this.pathXml=pathXml;
    }
    
    @Override
    public Journal load()
    {
        Journal list = new Journal();
        try 
        {
            File xml = new File(this.pathXml);
            JAXBContext context = JAXBContext.newInstance(Journal.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            list = (Journal) unmarshaller.unmarshal(xml);
        } 
        catch (JAXBException exception) 
        {
            Logger.getLogger(Application.class.getName()).
              log(Level.SEVERE, "marshallExample threw JAXBException", exception);
        }
        return list;
    }

    @Override
    public void save(ITask list)
    {
        try 
        {
            File xml = new File(this.pathXml);
            JAXBContext context = JAXBContext.newInstance(Journal.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(list, xml);
            
        } 
        catch (JAXBException exception) 
        {
            Logger.getLogger(Application.class.getName()).
            log(Level.SEVERE, "marshallExample threw JAXBException", exception);
        }
    }
}
