package webitc.gui.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.basic.BasicGraphicsUtils;

public class FocusBorder
  extends AbstractBorder
{
  public static FocusBorder BORDER = new FocusBorder();
  private static final Color color = UIManager.getColor("RadioButton.focus");
  
  private FocusBorder() {}
  
  public Insets getBorderInsets(Component paramComponent, Insets paramInsets)
  {
    top = 0;
    bottom = 0;
    left = 0;
    right = 0;
    return paramInsets;
  }
  
  public Insets getBorderInsets(Component paramComponent)
  {
    return new Insets(0, 0, 0, 0);
  }
  
  public boolean isBorderOpaque()
  {
    return false;
  }
  
  public void paintBorder(Component paramComponent, Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramGraphics.setColor(color);
    BasicGraphicsUtils.drawDashedRect(paramGraphics, paramInt1, paramInt2, paramInt3, paramInt4);
  }
}
