/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingerbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maksi_000
 */
public class Pinger
{
    private final ITask list;
    private String url;
    private long times;
    private long before;
    private long after;
    private final ScheduledExecutorService service;
    
    public Pinger(ITask list)
    {
        this.list=list;
        service = Executors.newScheduledThreadPool(10);
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
    
    public void connectURL(int index)
    {
        try
        {
            this.url=this.list.getURL(index);
            URL urlTask = new URL(this.url);
            URLConnection conn = urlTask.openConnection();//timeout
            conn.setConnectTimeout(2000);
            this.timebefore();
            try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));)
            {
                this.timeafter();
            }
            this.times();
            this.list.setGoodAttempts(index,this.list.getGoodAttempts(index)+1);
        } 
        catch (java.net.SocketTimeoutException | java.net.ConnectException ex)
        {
            this.list.setGoodAttempts(index,this.list.getGoodAttempts(index));
        } 
        catch (IOException ex)
        {
            Logger.getLogger(Pinger.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            this.list.setTimes(index,this.times);
            this.list.setAttempts(index,this.list.getAttempts(index)+1); //надо учитывать что подключение может отвалиться
        }
    }
    
    public void startConnect() //name
    {
        for(int i=0;i<list.getTask().size();i++) 
            service.scheduleWithFixedDelay(new threadConnect(i), 1, 10, TimeUnit.SECONDS);
    }
    
    public void stopConnect()
    {
        service.shutdown();
    }
    
    public class threadConnect implements Runnable
    {
        private final int index;
        public threadConnect(int index)
        {
            this.index = index;
        }

        @Override
        public void run()
        {
            connectURL(this.index);
        }
    }
}
