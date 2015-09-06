/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingerbot;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author maksi_000
 */

@XmlRootElement(name = "collections")
public class Journal implements ITask//add interface to manipulate with model
{
    private List<Note> task = new ArrayList<Note>();
    
    public Journal(){}
    
    public List<Note> getTask()
    {
        return task;
    }
    
    public void setTask(List<Note> task)
    {
        this.task = task;
    }
    
    @Override
    public void add(int id, String name, String url, int attempts, int goodattempts, long times)
    {
        this.task.add(new Note(id,name,url,attempts,goodattempts,times));
    }
    
    @Override
    public int getId(int index)
    {
        return this.task.get(index).getId();
    }
    
    @Override
    public String getName(int index)
    {
        return this.task.get(index).getName();
    }
    
    @Override
    public String getURL(int index)
    {
        return this.task.get(index).getUrl();
    }
    
    @Override
    public int getAttempts(int index)
    {
        return this.task.get(index).getAttempts();
    }
    
    @Override
    public int getGoodAttempts(int index)
    {
        return this.task.get(index).getGoodattempts();
    }
    
    @Override
    public long getTimes(int index)
    {
        return this.task.get(index).getTimes();
    }
    
    @Override
    public void setAttempts(int index,int attempts)
    {
        Note note = this.task.get(index);
        note.setAttempts(attempts);
        this.task.set(index, note);
    }
    
    @Override
    public void setGoodAttempts(int index,int goodattempts)
    {
        Note note = this.task.get(index);
        note.setGoodattempts(goodattempts);
        this.task.set(index, note);
    }
   
    @Override
    public void setTimes(int index,long times)
    {
        Note note = this.task.get(index);
        note.setTimes(times);
        this.task.set(index, note);
    }
@XmlType(propOrder = {"id","name","url","attempts","goodattempts","times"})
    public static class Note
    {
        private int id;
        private String name;
        private String url;
        private int attempts;
        private int goodattempts;
        private long times;

        public Note(){}

        public Note(int id, String name, String url, int attempts, int goodattempts, long times)
        {
            this.id = id;
            this.name = name;
            this.url = url;
            this.attempts = attempts;
            this.goodattempts = goodattempts;
            this.times = times;
        }

        public int getId()
        {
            return id;
        }

        public String getName()
        {
            return name;
        }

        public String getUrl()
        {
            return url;
        }

        public int getAttempts()
        {
            return attempts;
        }

        public int getGoodattempts()
        {
            return goodattempts;
        }

        public long getTimes()
        {
            return times;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public void setAttempts(int attempts)
        {
            this.attempts = attempts;
        }

        public void setGoodattempts(int goodattempts)
        {
            this.goodattempts = goodattempts;
        }

        public void setTimes(long times)
        {
            this.times = times;
        }
    }
}
