package webitc.gui.common;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.border.AbstractBorder;

public class BrokenLineBorder
  extends AbstractBorder
{
  static final float[] dash1 = { 2.0F };
  static final BasicStroke dashed = new BasicStroke(1.0F, 0, 0, 1.0F, dash1, 0.0F);
  
  public BrokenLineBorder() {}
  
  public Insets getBorderInsets(Component paramComponent, Insets paramInsets)
  {
    bottom = 1;
    left = 1;
    right = 1;
    top = 1;
    return paramInsets;
  }
  
  public Insets getBorderInsets(Component paramComponent)
  {
    return new Insets(1, 1, 1, 1);
  }
  
  public boolean isBorderOpaque()
  {
    return false;
  }
  
  public void paintBorder(Component paramComponent, Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
    localGraphics2D.setStroke(dashed);
    localGraphics2D.draw(new Rectangle(paramInt1, paramInt2, paramInt3, paramInt4));
  }
}
