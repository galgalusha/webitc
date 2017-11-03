package webitc.gui.simple;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import javax.swing.JLabel;
import webitc.gui.common.ColorRes;
import webitc.gui.common.GraphicLibrary;

public class SimpleBar
  extends JLabel
{
  private boolean mGraduation = true;
  private boolean mShadow = false;
  private boolean mUnderLine = false;
  
  public SimpleBar() {}
  
  public void paint(Graphics paramGraphics)
  {
    Color localColor1 = getParent().getBackground();
    int i = 1;
    if (mGraduation == true) {
      i = 4;
    }
    Color localColor2 = getBackground();
    Color localColor3 = ColorRes.SIMPLE_MODE_TOOL_BAR_TEXT;
    int j = 1;
    GraphicLibrary.drawBar(paramGraphics, getSize(), localColor1, localColor2, i, localColor3, getText(), j, mUnderLine, mShadow);
  }
  
  public void setGraduation(boolean paramBoolean)
  {
    mGraduation = paramBoolean;
  }
  
  public void setShadow(boolean paramBoolean)
  {
    mShadow = paramBoolean;
  }
  
  public void setUnderLine(boolean paramBoolean)
  {
    mUnderLine = paramBoolean;
  }
  
  private void update()
  {
    Graphics localGraphics = getGraphics();
    if (localGraphics != null) {
      update(localGraphics);
    }
  }
}
