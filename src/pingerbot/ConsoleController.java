/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingerbot;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author maksi_000
 */
public class ConsoleController
{
    private final Journal list;
    private final ConsoleView view;
    private final Pinger ping;
    
    public ConsoleController(Journal list,ConsoleView view)
    {
        this.list=list;
        this.view=view;
        this.ping = new Pinger(list); 
    }
    
    public boolean validateName(String userNameString){  
        Pattern p = Pattern.compile("^[[à-ÿÀ-ßa-zA-Z]{2,}|\\s{1,}|[.,]{1,}]{1,}$");  
        Matcher m = p.matcher(userNameString);  
        return m.matches();  
    }
    public boolean validateURL(String userNameString){  
        Pattern p = Pattern.compile("(http://|https://)(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?");  
        Matcher m = p.matcher(userNameString);  
        return m.matches();  
    }
    
    public void start(DAOXML xml, String pathXml)
    {
        int index;
        String name,url,temp;
        this.view.printStart();
        Scanner scan=new Scanner(System.in);
        String command="";
        while(!"exit".equals(command))
        {
            ping.StartConnect();
            command=scan.next();
            switch(command)
            {
                case "show": this.view.printJournal(list); this.view.printStart(); break;
                case "help": this.view.printHelp(); this.view.printStart(); break;
                case "get": index=scan.nextInt(); this.view.printGet(list, index-1); this.view.printStart(); break;
                case "add": this.view.printAddName(); name=scan.next(); this.view.printAddURL(); url=scan.next(); this.list.add(5,name, url, 0, 0, 0); this.view.printStart();break;
                case "save": xml.XmlSave(pathXml, this.list); this.view.printStart(); break;
                case "delete": index=scan.nextInt(); this.list.remove(index-1); this.view.printDelete(index); this.view.printStart(); break;
                case "exit": ping.TimerClose(); break;
                default: this.view.printHelp(); this.view.printStart(); break;
            }
        }
    }

    
    
}
