package webitc.gui.simple;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import webitc.gui.common.ColorRes;
import webitc.gui.common.GraphicLibrary;

public class ArrowButton
  extends JButton
{
  public static final int BG_TYPE_GRAD = 2;
  public static final int BG_TYPE_SOLID = 1;
  private Color mArrowColor = ColorRes.SIMPLE_MODE_BTN_TEXT;
  private Color mBgColor = ColorRes.SIMPLE_MODE_BTN;
  private int mBgType = 1;
  private int mDirection = 1;
  
  public ArrowButton()
  {
    setPreferredSize(new Dimension(23, 23));
  }
  
  public Color getArrowColor()
  {
    return mArrowColor;
  }
  
  public Color getBgColor()
  {
    return mBgColor;
  }
  
  public int getBgType()
  {
    return mBgType;
  }
  
  public int getDirection()
  {
    return mDirection;
  }
  
  public void paint(Graphics paramGraphics)
  {
    Dimension localDimension = getSize();
    int i = 0;
    if (!getModel().isEnabled()) {
      i = 2;
    } else if (getModel().isPressed() == true) {
      i = 1;
    } else if (getModel().isRollover() == true) {
      i = 4;
    } else if (isFocusOwner() == true) {
      i = 3;
    } else {
      i = 0;
    }
    Color localColor = getParent().getBackground();
    GraphicLibrary.drawArrowButton(paramGraphics, localDimension, i, localColor, mBgColor, mBgType, true, mArrowColor, mDirection);
  }
  
  public void setArrowColor(Color paramColor)
  {
    mArrowColor = paramColor;
  }
  
  public void setBgColor(Color paramColor)
  {
    mBgColor = paramColor;
  }
  
  public void setBgType(int paramInt)
  {
    mBgType = paramInt;
  }
  
  public void setDirection(int paramInt)
  {
    mDirection = paramInt;
  }
}
