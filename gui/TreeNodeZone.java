package webitc.gui;

import webitc.data.ID;
import webitc.gui.common.IconRes;

public class TreeNodeZone
  extends TreeNodeAbst
{
  public TreeNodeZone(ID paramID, String paramString)
  {
    super(2, paramString, IconRes.getIcon(8));
    setUserObject(paramID);
  }
}
