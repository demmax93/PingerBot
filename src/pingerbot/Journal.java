/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingerbot;

import java.util.ArrayList;

/**
 *
 * @author maksi_000
 */
public class Journal //add interface to manipulate with model
{
    private ArrayList<Note> journal = new ArrayList<Note>();
    
    public void add(int id, String name, String url, int attempts, int god_attempts, long times)
    {
        this.journal.add(new Note(id,name,url,attempts,god_attempts,times));
    }
    
    public void remove(int id)
    {
        this.journal.remove(id);
    }

    public int size()
    {
        return this.journal.size();
    }
    
    public void clear()
    {
        this.journal.clear();
    }
    
    public int getId(int index)
    {
        return this.journal.get(index).getId();
    }
    
    public String getName(int index)
    {
        return this.journal.get(index).getName();
    }
    
    public String getURL(int index)
    {
        return this.journal.get(index).getUrl();
    }
    
    public int getAttempts(int index)
    {
        return this.journal.get(index).getAttempts();
    }
    
    public int getGodAttempts(int index)
    {
        return this.journal.get(index).getGood_attempts();
    }
    
    public long getTimes(int index)
    {
        return this.journal.get(index).getTimes();
    }
    
    public void setAttempts(int index,int attempts)
    {
        Note note = this.journal.get(index);
        note.setAttempts(attempts);
        this.journal.set(index, note);
    }
    
    public void setGoodAttempts(int index,int good_attempts)
    {
        Note note = this.journal.get(index);
        note.setGood_attempts(good_attempts);
        this.journal.set(index, note);
    }
   
    public void setTimes(int index,long times)
    {
        Note note = this.journal.get(index);
        note.setTimes(times);
        this.journal.set(index, note);
    }
 
    private class Note
    {
        private int id;
        private String name;
        private String url;
        private int attempts;
        private int good_attempts;
        private long times;

        public Note(int id, String name, String url, int attempts, int good_attempts, long times)
        {
            this.id = id;
            this.name = name;
            this.url = url;
            this.attempts = attempts;
            this.good_attempts = good_attempts;
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

        public int getGood_attempts()
        {
            return good_attempts;
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

        public void setGood_attempts(int god_attempts)
        {
            this.good_attempts = god_attempts;
        }

        public void setTimes(long times)
        {
            this.times = times;
        }
    }
}
