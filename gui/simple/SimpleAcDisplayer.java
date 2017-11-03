package webitc.gui.simple;

import webitc.data.ID;

public abstract interface SimpleAcDisplayer
{
  public abstract ID getSelectedID();
  
  public abstract void setSelectedID(ID paramID);
}
