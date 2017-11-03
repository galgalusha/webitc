package webitc.gui.monitor;

import webitc.data.ID;

public abstract interface AcDataDisplayer
{
  public abstract ID getSelectedPnt();
  
  public abstract void refresh();
  
  public abstract void setSelectedPnt(ID paramID);
  
  public abstract void showZone(ID paramID);
}
