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
public interface IView
{
    public void printStart();
    public void printJournal();
    public void printGet(int index);
    public void printHelp();
    public void printAddURL();
    public void printAddName();
    public void printDelete(int index);
    public void printErrorName();
    public void printErrorURL();
}
