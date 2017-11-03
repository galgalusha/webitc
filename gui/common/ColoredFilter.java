package webitc.gui.common;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

class ColoredFilter
  extends RGBImageFilter
{
  private double rateBlue = 1.0D;
  private double rateGreen = 1.0D;
  private double rateRed = 1.0D;
  
  public ColoredFilter()
  {
    canFilterIndexColorModel = true;
  }
  
  public int filterRGB(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt3 >> 24 & 0xFF;
    int j = paramInt3 >> 16 & 0xFF;
    int k = paramInt3 >> 8 & 0xFF;
    int m = paramInt3 >> 0 & 0xFF;
    int n = (int)(j * rateRed);
    int i1 = (int)(k * rateGreen);
    int i2 = (int)(m * rateBlue);
    Color localColor = GraphicLibrary.getColor(n, i1, i2, i);
    return localColor.getRGB();
  }
  
  public void setColorRate(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    rateRed = paramDouble1;
    rateGreen = paramDouble2;
    rateBlue = paramDouble3;
    if (rateRed < 0.0D) {
      rateRed = 0.0D;
    }
    if (rateGreen < 0.0D) {
      rateGreen = 0.0D;
    }
    if (rateBlue < 0.0D) {
      rateBlue = 0.0D;
    }
  }
}
