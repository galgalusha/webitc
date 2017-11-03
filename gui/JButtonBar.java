package webitc.gui;

import java.util.ArrayList;
import javax.swing.JPanel;

public class JButtonBar
  extends JPanel
{
  protected ArrayList mList = new ArrayList();
  
  public JButtonBar() {}
  
  public void addButtonEventListener(ButtonEventListener paramButtonEventListener)
  {
    mList.add(paramButtonEventListener);
  }
  
  protected void fireButtonEvent(int paramInt)
  {
    for (int i = 0; i < mList.size(); i++)
    {
      ButtonEventListener localButtonEventListener = (ButtonEventListener)mList.get(i);
      localButtonEventListener.buttonSelected(paramInt);
    }
  }
}
