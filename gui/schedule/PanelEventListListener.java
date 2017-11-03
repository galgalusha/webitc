package webitc.gui.schedule;

import java.util.EventListener;

public abstract interface PanelEventListListener
  extends EventListener
{
  public abstract void exceptionNameChanged(int paramInt, String paramString);
}
