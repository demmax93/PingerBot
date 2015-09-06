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
public class ConsoleView implements IView
{
    private final ITask list;
    
    public ConsoleView(ITask list)
    {
        this.list=list;
    }
    
    @Override
    public void printStart()
    {
        System.out.print(">");
    }
    @Override
    public void printJournal()
    {
        System.out.println("id\tName\tURL\t\t\tAttempts\tOK Attempts\tTime(ms)");
        for(int index=0;index<list.getTask().size();index++)
        {
          System.out.println(list.getId(index)+"\t"+
                  list.getName(index)+"\t"+
                  list.getURL(index)+"\t"+
                  list.getAttempts(index)+"\t\t"+
                  list.getGoodAttempts(index)+"\t\t"+
                  list.getTimes(index));  
        } 
    }
    @Override
    public void printGet(int index)
    {
        System.out.println("id\tName\tURL\t\t\tAttempts\tOK Attempts\tTime(ms)");
        System.out.println(list.getId(index)+"\t"+
                list.getName(index)+"\t"+
                list.getURL(index)+"\t"+
                list.getAttempts(index)+"\t\t"+
                list.getGoodAttempts(index)+"\t\t"+
                list.getTimes(index));
    }
    @Override
    public void printHelp()
    {
        System.out.println("URL cheker is application\n"+
        "add\n\tAdd a new task:\n\t-URL\n\t-name\n"+
        "delete [id]\n\tDelete a task bu ID\n"+
        "get [id]\n\tShow a task by ID\n"+
        "show\n\tShow all tasks\n"+
        "save\n\tSave TaskList\n"+
        "exit\n\tExit the program");
    }
    
    @Override
    public void printAddURL()
    {
        System.out.print("URL:\t");
    }
    
    @Override
    public void printAddName()
    {
        System.out.print("Name:\t");
    }
    
    @Override
    public void printDelete(int index)
    {
        System.out.println("Task "+index+" was deleted");
    }

    @Override
    public void printErrorName()
    {
        System.out.println("Вы ввели недопустимое имя. Повторите попытку.");
        this.printAddName();
    }

    @Override
    public void printErrorURL()
    {
        System.out.println("Вы ввели недопустимый URL. Повторите попытку.");
        this.printAddURL();
    }

}
