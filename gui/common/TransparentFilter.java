package webitc.gui.common;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

class TransparentFilter
  extends RGBImageFilter
{
  private boolean mDecColor = false;
  private Color mTransparentColor = null;
  
  public TransparentFilter()
  {
    canFilterIndexColorModel = true;
  }
  
  public int filterRGB(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt3 >> 16 & 0xFF;
    int j = paramInt3 >> 8 & 0xFF;
    int k = paramInt3 >> 0 & 0xFF;
    if (mTransparentColor != null)
    {
      int m = mTransparentColor.getRed();
      int n = mTransparentColor.getGreen();
      int i1 = mTransparentColor.getBlue();
      if ((i == m) && (j == n) && (k == i1)) {
        return 0;
      }
    }
    if (mDecColor == true)
    {
      Color localColor = new Color(i, j, k, 50);
      return localColor.getRGB();
    }
    return paramInt3;
  }
  
  public Color getTransparentColor()
  {
    return mTransparentColor;
  }
  
  public void setDecColor(boolean paramBoolean)
  {
    mDecColor = paramBoolean;
  }
  
  public void setTransparentColor(Color paramColor)
  {
    mTransparentColor = paramColor;
  }
}
