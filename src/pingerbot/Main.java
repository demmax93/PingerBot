/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingerbot;

/**
 *
 * @author maksi_000
 */
public class Main
{
    public static void main(String[] args)
    {   
        ConsoleView view = new ConsoleView();
        String pathXml = "src\\pingerbot\\task.xml";
        DAOXML xml = new DAOXML();
        Journal list = xml.XmlUpload(pathXml);
        ConsoleController controller = new ConsoleController(list, view);
        controller.start(xml,pathXml);
    }
}
