/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingerbot;

import java.util.List;


/**
 *
 * @author maksi_000
 */
public interface ITask
{
    public List<Journal.Note> getTask();
    public void setTask(List<Journal.Note> task);
    public void add(int id, String name, String url, int attempts, int goodattempts, long times);
    public int getId(int index);
    public String getName(int index);
    public String getURL(int index);
    public int getAttempts(int index); 
    public int getGoodAttempts(int index);
    public long getTimes(int index);
    public void setAttempts(int index,int attempts);
    public void setGoodAttempts(int index,int goodattempts);
    public void setTimes(int index,long times);
}
