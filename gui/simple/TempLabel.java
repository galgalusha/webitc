package webitc.gui.simple;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JLabel;
import webitc.common.StrRes;
import webitc.common.SystemInfo;

class TempLabel
  extends JLabel
{
  private static final Font TEMP_FONT = new Font("Dialog", 1, 40);
  private int mTemp = 0;
  
  public TempLabel() {}
  
  public void downTemp()
  {
    setTemp(mTemp - 1);
  }
  
  public int getTemp()
  {
    return mTemp;
  }
  
  public void paint(Graphics paramGraphics)
  {
    Color localColor = paramGraphics.getColor();
    Font localFont1 = paramGraphics.getFont();
    Container localContainer = getParent();
    if (localContainer != null)
    {
      localObject = localContainer.getBackground();
      if (localObject != null)
      {
        paramGraphics.setColor((Color)localObject);
        paramGraphics.fillRect(0, 0, getWidth(), getHeight());
      }
    }
    paramGraphics.setColor(getForeground());
    paramGraphics.setFont(TEMP_FONT);
    Object localObject = String.valueOf(mTemp);
    if (!isEnabled())
    {
      localObject = StrRes.getStr("IDS_COMMON_UNKNOWN");
      paramGraphics.setColor(Color.GRAY);
    }
    int i = paramGraphics.getFontMetrics().stringWidth((String)localObject);
    int j = paramGraphics.getFontMetrics().getHeight();
    int k = paramGraphics.getFontMetrics().getAscent();
    int m = (getSizeheight - j) / 2 + k;
    int n = 80 - i;
    paramGraphics.drawString((String)localObject, n, m);
    Font localFont2 = TEMP_FONT.deriveFont(20.0F);
    paramGraphics.setFont(localFont2);
    n = 80;
    if (SystemInfo.isCentigrade() == true) {
      paramGraphics.drawString(StrRes.getStr("IDS_COMMON_UNIT_TEMP"), n, m);
    } else {
      paramGraphics.drawString(StrRes.getStr("IDS_COMMON_UNIT_TEMP_F"), n, m);
    }
    paramGraphics.setFont(localFont1);
    paramGraphics.setColor(localColor);
  }
  
  public void setTemp(int paramInt)
  {
    mTemp = paramInt;
    update();
  }
  
  public void upTemp()
  {
    setTemp(mTemp + 1);
  }
  
  private void update()
  {
    Graphics localGraphics = getGraphics();
    if ((localGraphics != null) && (isShowing()) && (isEnabled())) {
      paint(localGraphics);
    }
  }
}
