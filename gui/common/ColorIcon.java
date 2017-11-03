package webitc.gui.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

public class ColorIcon
  implements Icon
{
  private final Color mColor;
  
  public ColorIcon(Color paramColor)
  {
    mColor = paramColor;
  }
  
  public int getIconHeight()
  {
    return 12;
  }
  
  public int getIconWidth()
  {
    return 16;
  }
  
  public void paintIcon(Component paramComponent, Graphics paramGraphics, int paramInt1, int paramInt2)
  {
    paramGraphics.setColor(mColor);
    paramGraphics.fillRect(paramInt1, paramInt2, getIconWidth(), getIconHeight());
    paramGraphics.setColor(Color.BLACK);
    paramGraphics.drawRect(paramInt1, paramInt2, getIconWidth(), getIconHeight());
  }
}
