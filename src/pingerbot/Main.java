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
    public static void main(String[] args) //observer pattern
    {   
       
        ConsoleView view = new ConsoleView();
        String pathXml = "src\\pingerbot\\task.xml";
        IData xml = new DAOXML(pathXml);
        Journal list = xml.load(); //methods name first char to lowercase
        ConsoleController controller = new ConsoleController(list, view); //View подписывается на сообщения от controller и от model
        controller.start(xml);
    }
}
