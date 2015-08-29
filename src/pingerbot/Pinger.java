/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingerbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maksi_000
 */
public class Pinger
{
    private final Journal list;
    private String url;
    private long times;
    private long before;
    private long after;
    private Timer mTimer;
    
    public Pinger(Journal list)
    {
        this.list=list;
        mTimer = new Timer();
    }
    
    public void timebefore()
    {
        this.before=System.currentTimeMillis();
    }
    
    public void timeafter()
    {
        this.after=System.currentTimeMillis();
    }
    
    public void times()
    {
        this.times=this.after-this.before;
    }
    
    public void timerClose() //method name
    {
        if(mTimer != null)
        {
            mTimer.cancel();
            mTimer=null;
        }
    }
    
    public void connectURL(int index)
    {
        try //try resource
        {
            this.url=this.list.getURL(index);
            URL test_01 = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection)test_01.openConnection();//timeout
            conn.setRequestMethod("GET");
            this.timebefore();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            in.close(); //close in finally
            this.timeafter();
            this.times();
            this.list.setTimes(index,this.times);
            this.list.setAttempts(index,this.list.getAttempts(index)+1); //надо учитывать что подключение может отвалиться
            this.list.setGoodAttempts(index,this.list.getGodAttempts(index)+1);
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(Pinger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(Pinger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void StartConnect() //name
    {
       TimerTask mTask = new TimerTask() {

           @Override
           public void run()
           {
               for(int index=0;index<list.size();index++)
               {
                   connectURL(index);
               }
           }
       }; 
       this.mTimer.schedule(mTask, 100, 10000);
    }
 
}
