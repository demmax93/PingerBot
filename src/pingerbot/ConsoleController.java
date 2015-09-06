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
public class ConsoleController implements IConsole
{
    private static final String NAME_REG_EXP = "^[[à-ÿÀ-ßa-zA-Z]{2,}|\\s{1,}|[.,]{1,}]{1,}$";
    private static final String URL_REG_EXP = "(http://|https://)(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?";
    private final Pattern nameReg = Pattern.compile(NAME_REG_EXP);
    private final Pattern urlReg = Pattern.compile(URL_REG_EXP);
    private final ITask list;
    private final IView view;
    private final Pinger ping;
    
    public ConsoleController(ITask list,IView view)
    {
        this.list=list;
        this.view=view;
        this.ping = new Pinger(list); 
    }
    
    public boolean validateName(String userNameString){  //use validation
        Matcher m = nameReg.matcher(userNameString);  
        return m.matches();  
    }
    public boolean validateURL(String userNameString){   //use validation & exceptions

        Matcher m = urlReg.matcher(userNameString);  
        return m.matches();  
    }
    
    @Override
    public void start(IData xml)
    {
        int index; //use index from collection
        String name,url,temp;
        this.view.printStart();
        Scanner scan=new Scanner(System.in);
        String command="";
        ping.startConnect();
        while(!"exit".equals(command))
        {
            command=scan.next();
            switch(command)
            {
                case "show":
                    this.view.printJournal(); 
                    this.view.printStart(); 
                    break;
                case "help": 
                    this.view.printHelp(); 
                    this.view.printStart(); 
                    break;
                case "get": 
                    index=scan.nextInt(); 
                    this.view.printGet(index-1); 
                    this.view.printStart(); 
                    break;
                case "add": 
                    this.view.printAddName(); 
                    temp=scan.next();
                    if(validateName(temp))
                    {
                        name=temp;
                    }
                    else
                    {
                        while(!validateName(temp))
                        {
                            this.view.printErrorName();
                            temp=scan.next();
                        }
                        name=temp;
                    }
                    this.view.printAddURL(); 
                    temp=scan.next();
                    if(validateURL(temp))
                    {
                        url=temp;
                    }
                    else
                    {
                        while(!validateURL(temp))
                        {
                            this.view.printErrorURL();
                            temp=scan.next();
                        }
                        url=temp;
                    }
                    this.list.add(list.getTask().size()+1,name, url, 0, 0, 0); 
                    this.view.printStart();
                    ping.startConnect();
                    break;
                case "save": 
                    xml.save(this.list); 
                    this.view.printStart();
                    break;
                case "delete": 
                    index=scan.nextInt(); 
                    this.list.getTask().remove(index-1); 
                    this.view.printDelete(index);
                    this.view.printStart(); 
                    break;
                case "exit": 
                    ping.stopConnect(); 
                    break;
                default: 
                    this.view.printHelp(); 
                    this.view.printStart(); 
                    break;
            }
        }
    }
}
