package webitc.gui.common;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ItcIcon
  extends ImageIcon
  implements Cloneable
{
  public static final int APPEND_ALL = 15;
  public static final int APPEND_AUTO_OP = 4;
  public static final int APPEND_DEMAND = 2;
  public static final int APPEND_FILTER = 8;
  public static final int APPEND_FORCE_STOP = 1;
  public static final int APPEND_NONE = 0;
  public static final int ICON_COMM_ERR = 3;
  public static final int ICON_OFF = 0;
  public static final int ICON_ON = 1;
  public static final int ICON_TARGET_ERR = 2;
  private int mState = 0;
  
  private ItcIcon(Image paramImage)
  {
    super(paramImage);
  }
  
  public ItcIcon(byte[] paramArrayOfByte)
  {
    super(paramArrayOfByte);
  }
  
  public Object clone()
  {
    ItcIcon localItcIcon = new ItcIcon(getImage());
    localItcIcon.setState(mState);
    return localItcIcon;
  }
  
  public int getIconHeight()
  {
    return 32;
  }
  
  public int getIconWidth()
  {
    return 52;
  }
  
  public void paintIcon(Component paramComponent, Graphics paramGraphics, int paramInt1, int paramInt2)
  {
    super.paintIcon(paramComponent, paramGraphics, paramInt1 + 10, paramInt2);
    Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
    if ((mState & 0x1) != 0) {
      paramGraphics.drawImage(IconRes.getIcon(21).getImage(), paramInt1, paramInt2 + 5, null);
    }
    if ((mState & 0x2) != 0) {
      paramGraphics.drawImage(IconRes.getIcon(22).getImage(), paramInt1, paramInt2 + 18, null);
    }
    if ((mState & 0x4) != 0) {
      paramGraphics.drawImage(IconRes.getIcon(23).getImage(), paramInt1 + 42, paramInt2 + 5, null);
    }
    if ((mState & 0x8) != 0) {
      paramGraphics.drawImage(IconRes.getIcon(24).getImage(), paramInt1 + 42, paramInt2 + 18, null);
    }
  }
  
  public void setState(int paramInt)
  {
    mState = paramInt;
  }
}
