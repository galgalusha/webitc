package webitc.gui.monitor;

import java.util.EventListener;
import webitc.data.ID;

public abstract interface AcDataSelectionListener
  extends EventListener
{
  public abstract void acDataSelected(ID paramID);
}
