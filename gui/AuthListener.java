package webitc.gui;

import java.util.EventListener;

public abstract interface AuthListener
  extends EventListener
{
  public static final int PANEL_DETAIL = 0;
  public static final int PANEL_SIMPLE = 1;
  
  public abstract void authSucceeded(int paramInt);
  
  public abstract void logout();
}
