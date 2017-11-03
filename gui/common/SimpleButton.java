package webitc.gui.common;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SimpleButton
  extends JButton
{
  public static final int BG_TYPE_GRAD = 2;
  public static final int BG_TYPE_SOLID = 1;
  private Color mBgColor = ColorRes.SIMPLE_MODE_BTN;
  private int mBgType = 1;
  private boolean mDoNewLine = false;
  private Font mFont = new Font("Dialog", 0, 16);
  private ImageIcon mIcon = null;
  private int mIconMargin = 0;
  private boolean mShadow = true;
  private String mText = null;
  private Color mTextColor = ColorRes.SIMPLE_MODE_BTN_TEXT;
  private boolean mToggle = false;
  
  public SimpleButton(boolean paramBoolean)
  {
    this();
    mToggle = paramBoolean;
  }
  
  public SimpleButton()
  {
    setPreferredSize(new Dimension(95, 25));
  }
  
  public void doAllowNewLine(boolean paramBoolean)
  {
    mDoNewLine = paramBoolean;
  }
  
  public Color getBgColor()
  {
    return mBgColor;
  }
  
  public int getBgType()
  {
    return mBgType;
  }
  
  public Font getFont()
  {
    return mFont;
  }
  
  public int getIconMargin()
  {
    return mIconMargin;
  }
  
  public ImageIcon getImageIcon()
  {
    return mIcon;
  }
  
  public boolean getShadow()
  {
    return mShadow;
  }
  
  public String getText()
  {
    return mText;
  }
  
  public Color getTextColor()
  {
    return mTextColor;
  }
  
  public boolean getToggle()
  {
    return mToggle;
  }
  
  public boolean isAllowNewLine()
  {
    return mDoNewLine;
  }
  
  public void paint(Graphics paramGraphics)
  {
    Dimension localDimension = getSize();
    boolean bool1 = false;
    int i = 0;
    if (!getModel().isEnabled())
    {
      i = 2;
      bool1 = true;
    }
    else if (getModel().isSelected() == true)
    {
      i = 1;
    }
    else if (getModel().isPressed() == true)
    {
      i = 1;
    }
    else if (getModel().isRollover() == true)
    {
      i = 4;
    }
    else if (isFocusOwner() == true)
    {
      i = 3;
    }
    else
    {
      i = 0;
    }
    Color localColor = getParent().getBackground();
    int j = 2;
    if (mDoNewLine == true) {
      j |= 0x10;
    }
    boolean bool2 = false;
    if (mToggle == true) {
      bool2 = true;
    }
    GraphicLibrary.drawButton(paramGraphics, localDimension, i, localColor, mShadow, mBgColor, mBgType, bool1, mTextColor, mFont, mText, j, mIcon, this, mIconMargin, bool2);
  }
  
  public void setBgColor(Color paramColor)
  {
    mBgColor = paramColor;
  }
  
  public void setBgType(int paramInt)
  {
    mBgType = paramInt;
  }
  
  public void setFont(Font paramFont)
  {
    mFont = paramFont;
  }
  
  public void setIconMargin(int paramInt)
  {
    mIconMargin = paramInt;
  }
  
  public void setImageIcon(ImageIcon paramImageIcon)
  {
    mIcon = paramImageIcon;
  }
  
  public void setShadow(boolean paramBoolean)
  {
    mShadow = paramBoolean;
  }
  
  public void setText(String paramString)
  {
    mText = paramString;
  }
  
  public void setTextColor(Color paramColor)
  {
    mTextColor = paramColor;
  }
  
  public void setTextSize(float paramFloat)
  {
    mFont = mFont.deriveFont(paramFloat);
  }
  
  public void setToggle(boolean paramBoolean)
  {
    mToggle = paramBoolean;
  }
}
