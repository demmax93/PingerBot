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
public interface IData
{
    public Journal XmlUpload(String pathXML);
    public void XmlSave(String pathXML,Journal list);
}
