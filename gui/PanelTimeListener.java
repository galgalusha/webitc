package webitc.gui;

import java.util.EventListener;

public abstract interface PanelTimeListener
  extends EventListener
{
  public abstract void hourSelected();
  
  public abstract void minSelected();
}
