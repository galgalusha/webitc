package webitc.common;

import java.util.EventListener;

public abstract interface ListenerPntUpdate
  extends EventListener
{
  public abstract void exceptionalUpdated();
  
  public abstract void statusUpdated();
}
