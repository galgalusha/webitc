package webitc.gui.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class MultiIconPanel
  extends PanelAbstract
{
  public static final int TEXT_ALIGN_CENTER = -1;
  public static final int TEXT_ALIGN_LEFT = -2;
  public static final int TEXT_ALIGN_RIGHT = -3;
  Font mFont = new Font("Dialog", 0, 20);
  ArrayList mImageArray = new ArrayList();
  boolean mScaling = false;
  String mText = null;
  Color mTextColor = Color.RED;
  int mTextX = -1;
  int mTextY = 90;
  Color mTransparentColor = null;
  
  public MultiIconPanel(Image paramImage)
  {
    addImage(paramImage);
  }
  
  public MultiIconPanel(ImageIcon paramImageIcon)
  {
    addImage(paramImageIcon);
  }
  
  public MultiIconPanel() {}
  
  public void addImage(Image paramImage)
  {
    mImageArray.add(paramImage);
  }
  
  public void addImage(ImageIcon paramImageIcon)
  {
    addImage(paramImageIcon.getImage());
  }
  
  public void clearImage()
  {
    mImageArray.clear();
    mText = null;
  }
  
  public Image getImage(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= mImageArray.size())) {
      return null;
    }
    return (Image)mImageArray.get(paramInt);
  }
  
  public int getImageCount()
  {
    return mImageArray.size();
  }
  
  public Color getTransParentColor()
  {
    return mTransparentColor;
  }
  
  public boolean isScaling()
  {
    return mScaling;
  }
  
  public void paint(Graphics paramGraphics)
  {
    if (!isVisible()) {
      return;
    }
    Color localColor = paramGraphics.getColor();
    Font localFont = paramGraphics.getFont();
    paramGraphics.setColor(getBackground());
    paramGraphics.fillRect(0, 0, getSizewidth, getSizeheight);
    for (int i = 0; i < getImageCount(); i++)
    {
      Image localImage1 = (Image)mImageArray.get(i);
      if (localImage1 != null)
      {
        Image localImage2 = GraphicLibrary.getTransparentImage(localImage1, mTransparentColor, !isEnabled());
        if (mScaling == true) {
          localImage2 = GraphicLibrary.getScaleImage(localImage2, new Dimension(getSizewidth, getSizeheight));
        }
        ImageIcon localImageIcon = new ImageIcon(localImage2);
        localImageIcon.paintIcon(this, paramGraphics, 0, 0);
      }
    }
    if (mText != null)
    {
      if (mFont != null) {
        paramGraphics.setFont(mFont);
      }
      int j = mTextX;
      int k = getWidth();
      int m = paramGraphics.getFontMetrics().stringWidth(mText);
      if (mTextX == -1) {
        j = (k - m) / 2 - 5;
      } else if ((mTextX != -2) && (mTextX == -3)) {
        j = k - m;
      }
      if (mTextColor != null) {
        paramGraphics.setColor(mTextColor);
      }
      paramGraphics.drawString(mText, j, mTextY);
    }
    paramGraphics.setColor(localColor);
    paramGraphics.setFont(localFont);
  }
  
  public void setScaling(boolean paramBoolean)
  {
    mScaling = paramBoolean;
  }
  
  public void setText(String paramString)
  {
    mText = paramString;
  }
  
  public void setText(String paramString, int paramInt1, int paramInt2)
  {
    mText = paramString;
    mTextX = paramInt1;
    if (paramInt2 < 0) {
      paramInt2 = 0;
    }
    mTextY = paramInt2;
  }
  
  public void setText(Color paramColor, Font paramFont, String paramString, int paramInt1, int paramInt2)
  {
    mTextColor = paramColor;
    mFont = paramFont;
    mText = paramString;
    mTextX = paramInt1;
    if (paramInt2 < 0) {
      paramInt2 = 0;
    }
    mTextY = paramInt2;
  }
  
  public void setTransparentColor(Color paramColor)
  {
    mTransparentColor = paramColor;
  }
}
