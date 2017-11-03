package webitc.gui;

import java.util.ArrayList;
import webitc.gui.common.PanelAbstract;

public abstract class PanelMainAbstract
  extends PanelAbstract
{
  private ArrayList mAuthListenerList = new ArrayList();
  
  public PanelMainAbstract() {}
  
  public void addAuthListener(AuthListener paramAuthListener)
  {
    mAuthListenerList.add(paramAuthListener);
  }
  
  public abstract void appletStopped();
  
  protected void fireLogoutEvent()
  {
    for (int i = 0; i < mAuthListenerList.size(); i++)
    {
      AuthListener localAuthListener = (AuthListener)mAuthListenerList.get(i);
      localAuthListener.logout();
    }
  }
}
