package webitc.gui;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeNodeAbst
  extends DefaultMutableTreeNode
{
  public static final int HISTORY = 6;
  public static final int INTERLOCK = 5;
  public static final int MONITOR = 2;
  public static final int MONITOR_ROOT = 1;
  public static final int PPD = 4;
  public static final int SCHEDULE = 3;
  public static final int SYSTEM = 7;
  public static final int UNKNOWN = 0;
  private ImageIcon mIcon = null;
  private String mName = null;
  private int mType = 0;
  
  public TreeNodeAbst(int paramInt, String paramString, ImageIcon paramImageIcon)
  {
    mType = paramInt;
    mName = paramString;
    mIcon = paramImageIcon;
  }
  
  public TreeNodeAbst(int paramInt)
  {
    mType = paramInt;
  }
  
  public ImageIcon getIcon()
  {
    return mIcon;
  }
  
  public String getName()
  {
    return mName;
  }
  
  public int getType()
  {
    return mType;
  }
  
  public String toString()
  {
    return "";
  }
}
