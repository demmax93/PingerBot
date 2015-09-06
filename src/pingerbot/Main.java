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
   
     
    public static void main(String[] args)//observer pattern
    {   
       
        String pathXml = "src\\pingerbot\\task.xml";
        IData xml = new DAOJAXB(pathXml);
        ITask list = new Journal();
        list = xml.load();
        IView view = new ConsoleView(list);
        IConsole console = new ConsoleController(list, view);
        console.start(xml);
    }
}
