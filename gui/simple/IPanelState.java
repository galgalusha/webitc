package webitc.gui.simple;

import javax.swing.JScrollPane;
import webitc.data.ID;
import webitc.data.point.PntSet;

public abstract interface IPanelState
{
  public abstract ID getCurrentPntID();
  
  public abstract PntSet getCurrentPntSet();
  
  public abstract JScrollPane getScrollPane();
  
  public abstract boolean isChangeSetting();
  
  public abstract void notifyEndSendOperation();
  
  public abstract void resetChangeFlag();
  
  public abstract void setID(ID paramID);
  
  public static abstract interface PanelStateListener
  {
    public abstract void notifyOperation();
  }
}
