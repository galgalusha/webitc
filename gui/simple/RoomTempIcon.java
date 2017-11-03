package webitc.gui.simple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import webitc.common.StrRes;
import webitc.gui.common.GraphicLibrary;
import webitc.gui.common.IconRes;

class RoomTempIcon
  extends JComponent
{
  private static final Font TEMP_FONT = new Font("Dialog", 1, 16);
  private static final Font UNIT_FONT = new Font("Dialog", 1, 14);
  private String mText = null;
  
  public RoomTempIcon()
  {
    setPreferredSize(new Dimension(70, 36));
    setVisible(false);
  }
  
  public void paint(Graphics paramGraphics)
  {
    if (!isVisible()) {
      return;
    }
    ImageIcon localImageIcon = IconRes.getIcon(169);
    localImageIcon.paintIcon(this, paramGraphics, 0, 0);
    if (mText != null)
    {
      int i = getWidth();
      int j = getHeight();
      int k = 15;
      int m = 5;
      Rectangle localRectangle = new Rectangle(k, m, i, j);
      GraphicLibrary.drawTempString(paramGraphics, localRectangle, Color.BLACK, TEMP_FONT, UNIT_FONT, mText, 1);
    }
  }
  
  public void setTemp(String paramString)
  {
    mText = paramString;
  }
  
  public void setVisible(boolean paramBoolean)
  {
    super.setVisible(paramBoolean);
    if (paramBoolean == true) {
      setToolTipText(StrRes.getStr("IDS_COMMON_ROOMTEMP"));
    } else {
      setToolTipText(null);
    }
  }
}
