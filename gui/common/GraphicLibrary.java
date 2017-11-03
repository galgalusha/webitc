package webitc.gui.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import webitc.AppletMain;
import webitc.common.AppletAbst;
import webitc.common.StrRes;
import webitc.common.SystemInfo;

public class GraphicLibrary
{
  public static final int ARROW_BOTTOM = 3;
  public static final int ARROW_LEFT = 0;
  public static final int ARROW_RIGHT = 2;
  public static final int ARROW_TOP = 1;
  public static final int BG_TYPE_GRAD = 2;
  public static final int BG_TYPE_GRAD_2 = 4;
  public static final int BG_TYPE_GRAD_T = 18;
  public static final int BG_TYPE_GRAD_T_2 = 20;
  public static final int BG_TYPE_SOLID = 1;
  public static final int BG_TYPE_SOLID_T = 17;
  public static final int BG_TYPE_TRANSPARENT = 16;
  public static final int BTN_DISABLE = 2;
  public static final int BTN_FOCUS = 3;
  public static final int BTN_NORMAL = 0;
  public static final int BTN_PRESSED = 1;
  public static final int BTN_ROLLOVER = 4;
  public static final int FG_ALIGN_CENTER = 2;
  public static final int FG_ALIGN_LEFT = 1;
  public static final int FG_ALIGN_NEWLINE = 16;
  
  public GraphicLibrary() {}
  
  private static Color decrementColor(Color paramColor)
  {
    int i = paramColor.getAlpha();
    int j = paramColor.getRed() + 3;
    int k = paramColor.getGreen() + 3;
    int m = paramColor.getBlue() + 3;
    return getColor(j, k, m, i);
  }
  
  private static void drawArrow(Graphics paramGraphics, Dimension paramDimension, int paramInt1, Color paramColor, int paramInt2)
  {
    int i = width;
    int j = height;
    if (paramColor == null) {
      return;
    }
    Color localColor = paramColor;
    if ((paramInt1 & 0x10) != 0) {
      localColor = getTransparentColor(paramColor);
    }
    paramGraphics.setColor(localColor);
    int k = 5;
    int m = i - 5;
    int n = i / 2;
    int i1 = 5;
    int i2 = j - 5;
    int i3 = j / 2;
    int[] arrayOfInt1;
    int[] arrayOfInt2;
    if (paramInt2 == 1)
    {
      arrayOfInt1 = new int[] { k, m, n };
      arrayOfInt2 = new int[] { i2, i2, i1 };
      paramGraphics.fillPolygon(arrayOfInt1, arrayOfInt2, 3);
    }
    else if (paramInt2 == 3)
    {
      arrayOfInt1 = new int[] { k, m, n };
      arrayOfInt2 = new int[] { i1, i1, i2 };
      paramGraphics.fillPolygon(arrayOfInt1, arrayOfInt2, 3);
    }
    else if (paramInt2 == 2)
    {
      arrayOfInt1 = new int[] { k, k, m };
      arrayOfInt2 = new int[] { i1, i2, i3 };
      paramGraphics.fillPolygon(arrayOfInt1, arrayOfInt2, 3);
    }
    else if (paramInt2 == 0)
    {
      arrayOfInt1 = new int[] { k, m, m };
      arrayOfInt2 = new int[] { i3, i1, i2 };
      paramGraphics.fillPolygon(arrayOfInt1, arrayOfInt2, 3);
    }
  }
  
  public static void drawArrowButton(Graphics paramGraphics, Dimension paramDimension, int paramInt1, Color paramColor1, Color paramColor2, int paramInt2, boolean paramBoolean, Color paramColor3, int paramInt3)
  {
    Color localColor1 = paramGraphics.getColor();
    Color localColor2 = paramColor2;
    if (paramColor2 == null) {
      localColor2 = localColor1;
    }
    if (paramInt1 == 2)
    {
      if (paramBoolean == true) {
        localColor2 = ColorRes.SIMPLE_MODE_BTN_G;
      }
      if ((paramInt2 & 0x1) != 0) {
        fillRectWithShadow(paramGraphics, paramDimension, paramColor1, localColor2, 17);
      } else {
        fillRectWithShadow(paramGraphics, paramDimension, paramColor1, localColor2, 18);
      }
    }
    else if (paramInt1 == 3)
    {
      fillRectWithShadow(paramGraphics, paramDimension, paramColor1, localColor2, paramInt2);
      paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_F);
      paramGraphics.drawRect(0, 0, width - 3, height - 3);
    }
    else if (paramInt1 == 4)
    {
      fillRectWithShadow(paramGraphics, paramDimension, paramColor1, localColor2, paramInt2);
      paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_R);
      paramGraphics.drawRect(0, 0, width - 3, height - 3);
    }
    else
    {
      if (paramInt1 == 1)
      {
        paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_BG_2);
        paramGraphics.fillRect(0, 0, width, height);
        paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_BG_1);
        paramGraphics.fillRect(1, 1, width - 2, height - 2);
        int i = 2;
        int j = 2;
        int k = width - 3;
        int m = height - 3;
        Rectangle localRectangle = new Rectangle(i, j, k, m);
        localColor2 = getPressedColor(localColor2);
        fillRect(paramGraphics, localRectangle, localColor2, paramInt2);
        drawArrow(paramGraphics, new Dimension(width, height), paramInt2, paramColor3, paramInt3);
        paramGraphics.setColor(Color.WHITE);
        paramGraphics.drawRect(2, 2, width - 4, height - 4);
        paramGraphics.setColor(Color.BLACK);
        paramGraphics.drawRect(2, 2, width - 3, height - 3);
        paramGraphics.setColor(localColor1);
        return;
      }
      fillRectWithShadow(paramGraphics, paramDimension, paramColor1, localColor2, paramInt2);
      paramGraphics.setColor(Color.WHITE);
      paramGraphics.drawRect(0, 0, width - 3, height - 3);
    }
    drawArrow(paramGraphics, new Dimension(width - 2, height - 2), paramInt2, paramColor3, paramInt3);
    paramGraphics.setColor(localColor1);
  }
  
  public static void drawBar(Graphics paramGraphics, Dimension paramDimension, Color paramColor1, Color paramColor2, int paramInt1, Color paramColor3, String paramString, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    Color localColor = paramGraphics.getColor();
    if (paramColor1 != null)
    {
      paramGraphics.setColor(paramColor1);
      paramGraphics.fillRect(0, 0, width, height);
    }
    fillRect(paramGraphics, paramDimension, paramColor2, paramInt1);
    if (paramBoolean2 == true)
    {
      paramGraphics.setColor(ColorRes.SIMPLE_MODE_TOOL_BAR_BG_1);
      paramGraphics.drawLine(0, height - 2, width, height - 2);
      paramGraphics.drawLine(0, height - 1, width, height - 1);
      paramGraphics.setColor(ColorRes.SIMPLE_MODE_TOOL_BAR_BG_2);
      paramGraphics.drawLine(0, height, width, height);
    }
    drawString(paramGraphics, paramDimension, paramInt1, paramColor3, null, paramString, paramInt2, paramBoolean1);
    paramGraphics.setColor(localColor);
  }
  
  public static void drawButton(Graphics paramGraphics, Dimension paramDimension, int paramInt1, Color paramColor1, boolean paramBoolean1, Color paramColor2, int paramInt2, boolean paramBoolean2, Color paramColor3, Font paramFont, String paramString, int paramInt3, ImageIcon paramImageIcon, Component paramComponent, int paramInt4, boolean paramBoolean3)
  {
    if (!paramBoolean3) {
      drawButton2D(paramGraphics, paramDimension, paramInt1, paramColor1, paramBoolean1, paramColor2, paramInt2, paramBoolean2, paramColor3, paramFont, paramString, paramInt3, paramImageIcon, paramComponent, paramInt4);
    } else {
      drawButton3D(paramGraphics, paramDimension, paramInt1, paramColor1, paramBoolean1, paramColor2, paramInt2, paramBoolean2, paramColor3, paramFont, paramString, paramInt3, paramImageIcon, paramComponent, paramInt4);
    }
  }
  
  public static void drawButton2D(Graphics paramGraphics, Dimension paramDimension, int paramInt1, Color paramColor1, boolean paramBoolean1, Color paramColor2, int paramInt2, boolean paramBoolean2, Color paramColor3, Font paramFont, String paramString, int paramInt3, ImageIcon paramImageIcon, Component paramComponent, int paramInt4)
  {
    Color localColor1 = paramGraphics.getColor();
    Font localFont = paramGraphics.getFont();
    Color localColor2 = paramColor2;
    if (paramColor2 == null) {
      localColor2 = localColor1;
    }
    int i = 0;
    int j = 0;
    int k = 0;
    int m;
    if (paramImageIcon != null)
    {
      m = paramImageIcon.getIconWidth();
      j = paramInt4;
      k = (height - paramImageIcon.getIconHeight()) / 2;
      if (paramBoolean1 == true) {
        k--;
      }
      i = j + m;
    }
    if (paramInt1 == 2)
    {
      if (paramBoolean2 == true) {
        localColor2 = ColorRes.SIMPLE_MODE_BTN_G;
      }
      if ((paramInt2 & 0x1) != 0)
      {
        if (paramBoolean1 == true)
        {
          fillLetteredRectWithShadow(paramGraphics, paramDimension, paramColor1, localColor2, 17, paramColor3, paramFont, paramString, paramInt3, false, i);
          paramGraphics.setColor(Color.WHITE);
          paramGraphics.drawRect(0, 0, width - 3, height - 3);
        }
        else
        {
          fillLetteredRect(paramGraphics, paramDimension, paramColor1, localColor2, 17, paramColor3, paramFont, paramString, paramInt3, false, i);
        }
      }
      else if (paramBoolean1 == true)
      {
        fillLetteredRectWithShadow(paramGraphics, paramDimension, paramColor1, localColor2, 18, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(Color.WHITE);
        paramGraphics.drawRect(0, 0, width - 3, height - 3);
      }
      else
      {
        fillLetteredRect(paramGraphics, paramDimension, paramColor1, localColor2, 18, paramColor3, paramFont, paramString, paramInt3, false, i);
      }
    }
    else if (paramInt1 == 3)
    {
      if (paramBoolean1 == true)
      {
        fillLetteredRectWithShadow(paramGraphics, paramDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_F);
        paramGraphics.drawRect(0, 0, width - 3, height - 3);
      }
      else
      {
        fillLetteredRect(paramGraphics, paramDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_F);
        paramGraphics.drawRect(0, 0, width - 1, height - 1);
      }
    }
    else if (paramInt1 == 4)
    {
      if (paramBoolean1 == true)
      {
        fillLetteredRectWithShadow(paramGraphics, paramDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_R);
        paramGraphics.drawRect(0, 0, width - 3, height - 3);
      }
      else
      {
        fillLetteredRect(paramGraphics, paramDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_R);
        paramGraphics.drawRect(0, 0, width - 1, height - 1);
      }
    }
    else if (paramInt1 == 1)
    {
      localColor2 = getPressedColor(localColor2);
      int n;
      int i1;
      int i2;
      Rectangle localRectangle;
      if (paramBoolean1 == true)
      {
        paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_BG_2);
        paramGraphics.fillRect(0, 0, width, height);
        paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_BG_1);
        paramGraphics.fillRect(1, 1, width - 2, height - 2);
        m = 2;
        n = 2;
        i1 = width - 3;
        i2 = height - 3;
        localRectangle = new Rectangle(m, n, i1, i2);
        fillLetteredRect(paramGraphics, localRectangle, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(Color.WHITE);
        paramGraphics.drawRect(m, n, width - 3 - 1, height - 3 - 1);
        paramGraphics.setColor(Color.BLACK);
        paramGraphics.drawRect(m, n, width - 3, height - 3);
      }
      else
      {
        paramGraphics.setColor(localColor2);
        paramGraphics.fillRect(0, 0, width, height);
        m = 2;
        n = 2;
        i1 = width + 2;
        i2 = height + 2;
        localRectangle = new Rectangle(m, n, i1, i2);
        fillLetteredRect(paramGraphics, localRectangle, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(Color.BLACK);
        paramGraphics.drawRect(0, 0, width - 1, height - 1);
      }
      j++;
      k++;
    }
    else if (paramBoolean1 == true)
    {
      fillLetteredRectWithShadow(paramGraphics, paramDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
      paramGraphics.setColor(Color.WHITE);
      paramGraphics.drawRect(0, 0, width - 3, height - 3);
    }
    else
    {
      fillLetteredRect(paramGraphics, paramDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
    }
    if (paramImageIcon != null) {
      paramImageIcon.paintIcon(paramComponent, paramGraphics, j, k);
    }
    paramGraphics.setColor(localColor1);
    paramGraphics.setFont(localFont);
  }
  
  private static void drawButton3D(Graphics paramGraphics, Dimension paramDimension, int paramInt1, Color paramColor1, boolean paramBoolean1, Color paramColor2, int paramInt2, boolean paramBoolean2, Color paramColor3, Font paramFont, String paramString, int paramInt3, ImageIcon paramImageIcon, Component paramComponent, int paramInt4)
  {
    Color localColor1 = paramGraphics.getColor();
    Font localFont = paramGraphics.getFont();
    Color localColor2 = paramColor2;
    if (paramColor2 == null) {
      localColor2 = localColor1;
    }
    Dimension localDimension = paramDimension;
    if (paramBoolean1 == true)
    {
      localDimension = new Dimension(width - 5, height - 5);
      if (paramColor1 != null)
      {
        paramGraphics.setColor(paramColor1);
        paramGraphics.fillRect(0, 0, width, height);
      }
    }
    int[] arrayOfInt1 = { 0, 5, width, width };
    int[] arrayOfInt2 = { height, height, height, height };
    int[] arrayOfInt3 = { width, width, width, width };
    int[] arrayOfInt4 = { 0, height, height, 5 };
    int[] arrayOfInt5 = { 0, 5, 5, 0 };
    int[] arrayOfInt6 = { 0, 5, height, height };
    int[] arrayOfInt7 = { 0, 5, width + 1, width };
    int[] arrayOfInt8 = { 0, 5, 5, 0 };
    int i = 0;
    int j = 0;
    int k = 0;
    int m;
    if (paramImageIcon != null)
    {
      m = paramImageIcon.getIconWidth();
      j = paramInt4;
      k = (height - paramImageIcon.getIconHeight()) / 2;
      i = j + m;
    }
    if (paramInt1 == 2)
    {
      if (paramBoolean2 == true)
      {
        localColor2 = ColorRes.SIMPLE_MODE_BTN_G;
        paramColor3 = getTransparentColor(Color.BLACK);
      }
      if ((paramInt2 & 0x1) != 0)
      {
        if (paramBoolean1 == true)
        {
          paramGraphics.setColor(localColor2);
          fillLetteredRect(paramGraphics, localDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
          paramGraphics.setColor(getButtonWallColor(localColor2, 70));
          paramGraphics.fillPolygon(arrayOfInt3, arrayOfInt4, 4);
          paramGraphics.setColor(getButtonWallColor(localColor2, 50));
          paramGraphics.fillPolygon(arrayOfInt1, arrayOfInt2, 4);
        }
        else
        {
          fillLetteredRect(paramGraphics, paramDimension, paramColor1, localColor2, 17, paramColor3, paramFont, paramString, paramInt3, false, i);
        }
      }
      else if (paramBoolean1 == true)
      {
        paramGraphics.setColor(localColor2);
        fillLetteredRect(paramGraphics, localDimension, paramColor1, localColor2, 1, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(getButtonWallColor(localColor2, 70));
        paramGraphics.fillPolygon(arrayOfInt3, arrayOfInt4, 4);
        paramGraphics.setColor(getButtonWallColor(localColor2, 50));
        paramGraphics.fillPolygon(arrayOfInt1, arrayOfInt2, 4);
      }
      else
      {
        fillLetteredRect(paramGraphics, paramDimension, paramColor1, localColor2, 18, paramColor3, paramFont, paramString, paramInt3, false, i);
      }
    }
    else if (paramInt1 == 3)
    {
      if (paramBoolean1 == true)
      {
        paramGraphics.setColor(localColor2);
        fillLetteredRect(paramGraphics, localDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(getButtonWallColor(localColor2, 70));
        paramGraphics.fillPolygon(arrayOfInt3, arrayOfInt4, 4);
        paramGraphics.setColor(getButtonWallColor(localColor2, 50));
        paramGraphics.fillPolygon(arrayOfInt1, arrayOfInt2, 4);
        paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_F);
        paramGraphics.drawRect(0, 0, width, height);
      }
      else
      {
        fillLetteredRect(paramGraphics, localDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_F);
        paramGraphics.drawRect(0, 0, width - 1, height - 1);
      }
    }
    else if (paramInt1 == 4)
    {
      if (paramBoolean1 == true)
      {
        paramGraphics.setColor(localColor2);
        fillLetteredRect(paramGraphics, localDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(getButtonWallColor(localColor2, 70));
        paramGraphics.fillPolygon(arrayOfInt3, arrayOfInt4, 4);
        paramGraphics.setColor(getButtonWallColor(localColor2, 50));
        paramGraphics.fillPolygon(arrayOfInt1, arrayOfInt2, 4);
        paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_R);
        paramGraphics.drawRect(0, 0, width, height);
      }
      else
      {
        fillLetteredRect(paramGraphics, paramDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_R);
        paramGraphics.drawRect(0, 0, width - 1, height - 1);
      }
    }
    else if (paramInt1 == 1)
    {
      int n;
      int i1;
      int i2;
      Rectangle localRectangle;
      if (paramBoolean1 == true)
      {
        m = 4;
        n = 4;
        i1 = width;
        i2 = height;
        localRectangle = new Rectangle(m, n, i1, i2);
        fillLetteredRect(paramGraphics, localRectangle, paramColor1, getPressedColor(localColor2), paramInt2, Color.WHITE, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(Color.BLACK);
        paramGraphics.drawRect(m, n, i1, i2);
        paramGraphics.setColor(Color.RED);
        paramGraphics.fillRect(m, 0, i1 + 1, 3);
      }
      else
      {
        getPressedColor(localColor2);
        paramGraphics.setColor(localColor2);
        paramGraphics.fillRect(0, 0, width, height);
        m = 2;
        n = 2;
        i1 = width + 2;
        i2 = height + 2;
        localRectangle = new Rectangle(m, n, i1, i2);
        fillLetteredRect(paramGraphics, localRectangle, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
        paramGraphics.setColor(Color.BLACK);
        paramGraphics.drawRect(0, 0, width - 1, height - 1);
      }
      j += 5;
      k += 5;
    }
    else if (paramBoolean1 == true)
    {
      paramGraphics.setColor(localColor2);
      fillLetteredRect(paramGraphics, localDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
      paramGraphics.setColor(getButtonWallColor(localColor2, 70));
      paramGraphics.fillPolygon(arrayOfInt3, arrayOfInt4, 4);
      paramGraphics.setColor(getButtonWallColor(localColor2, 50));
      paramGraphics.fillPolygon(arrayOfInt1, arrayOfInt2, 4);
    }
    else
    {
      fillLetteredRect(paramGraphics, paramDimension, paramColor1, localColor2, paramInt2, paramColor3, paramFont, paramString, paramInt3, false, i);
    }
    if (paramImageIcon != null) {
      paramImageIcon.paintIcon(paramComponent, paramGraphics, j, k);
    }
    paramGraphics.setColor(localColor1);
    paramGraphics.setFont(localFont);
  }
  
  public static void drawString(Graphics paramGraphics, Rectangle paramRectangle, int paramInt1, Color paramColor, Font paramFont, String paramString, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = width;
    int j = height;
    if (paramString == null) {
      return;
    }
    Object localObject1 = paramColor;
    if ((paramInt1 & 0x10) != 0) {
      localObject1 = getTransparentColor(paramColor);
    }
    Color localColor = paramGraphics.getColor();
    if (localObject1 == null) {
      localObject1 = localColor;
    }
    paramGraphics.setColor((Color)localObject1);
    Font localFont = paramGraphics.getFont();
    if (paramFont != null) {
      paramGraphics.setFont(paramFont);
    }
    int k = paramGraphics.getFontMetrics().stringWidth(paramString);
    int m = paramGraphics.getFontMetrics().getHeight();
    int n = paramGraphics.getFontMetrics().getAscent();
    int i1 = paramGraphics.getFontMetrics().getDescent();
    Object localObject2;
    int i4;
    if ((paramInt2 & 0x10) != 0)
    {
      paramInt2 -= 16;
      if (k <= i)
      {
        drawString(paramGraphics, paramRectangle, paramInt1, paramColor, paramFont, paramString, paramInt2, paramBoolean1);
      }
      else
      {
        localObject2 = paramString.split(" |　");
        String[] arrayOfString = new String[localObject2.length + localObject2.length - 1];
        i4 = 0;
        for (int i5 = 0; i4 < localObject2.length - 1; i5++)
        {
          arrayOfString[i5] = localObject2[i4];
          arrayOfString[(++i5)] = " ";
          i4++;
        }
        arrayOfString[(arrayOfString.length - 1)] = localObject2[(localObject2.length - 1)];
        ArrayList localArrayList = new ArrayList();
        Object localObject3;
        for (StringBuffer localStringBuffer2 = 0; localStringBuffer2 < arrayOfString.length; localStringBuffer2++)
        {
          i6 = 0;
          i7 = localStringBuffer2;
          for (localStringBuffer3 = localStringBuffer2; localStringBuffer3 < arrayOfString.length; localStringBuffer3++)
          {
            i6 += paramGraphics.getFontMetrics().stringWidth(arrayOfString[localStringBuffer3]);
            if (i6 > i)
            {
              if (i7 == localStringBuffer3)
              {
                localArrayList.add(arrayOfString[localStringBuffer3]);
                break;
              }
              StringBuffer localStringBuffer4 = localStringBuffer3 - 1;
              localObject3 = new StringBuffer("");
              for (int i9 = i7; i9 <= localStringBuffer4; i9++) {
                ((StringBuffer)localObject3).append(arrayOfString[i9]);
              }
              localArrayList.add(((StringBuffer)localObject3).toString());
              localStringBuffer2 = localStringBuffer4;
              break;
            }
            if (localStringBuffer3 == arrayOfString.length - 1)
            {
              StringBuffer localStringBuffer5 = new StringBuffer("");
              for (localObject3 = i7; localObject3 <= localStringBuffer3; localObject3++) {
                localStringBuffer5.append(arrayOfString[localObject3]);
              }
              localArrayList.add(localStringBuffer5.toString());
              localStringBuffer2 = localStringBuffer3;
              break;
            }
          }
        }
        int i6 = n * localArrayList.size();
        int i7 = x;
        StringBuffer localStringBuffer3 = (j - i6) / 2 + y;
        for (int i8 = 0; i8 < localArrayList.size(); i8++)
        {
          localObject3 = new Rectangle(i7, localStringBuffer3, i, m);
          drawString(paramGraphics, (Rectangle)localObject3, paramInt1, paramColor, paramFont, (String)localArrayList.get(i8), paramInt2, paramBoolean1);
          localStringBuffer3 += n;
        }
      }
    }
    else
    {
      int i3;
      if ((paramBoolean2 == true) && (k > width))
      {
        localObject2 = new StringBuffer("");
        for (i3 = 0; i3 < paramString.length(); i3++)
        {
          ((StringBuffer)localObject2).append(paramString.charAt(i3));
          i4 = paramGraphics.getFontMetrics().stringWidth(new String(((StringBuffer)localObject2).toString() + "..."));
          if (i4 > width)
          {
            StringBuffer localStringBuffer1 = new StringBuffer(paramString.substring(0, i3) + "...");
            paramString = localStringBuffer1.toString();
            k = paramGraphics.getFontMetrics().stringWidth(paramString);
            break;
          }
        }
      }
      int i2;
      if ((paramInt2 & 0x2) != 0)
      {
        i2 = (i - k) / 2 + x;
        i3 = m - (n + i1) - 1;
        i4 = (j - m) / 2 + n + y + i3;
        paramGraphics.drawString(paramString, i2, i4);
        if (paramBoolean1 == true) {
          paramGraphics.drawLine(i2, i4 + 2, i2 + k, i4);
        }
      }
      else
      {
        i2 = x + 10;
        i3 = m - (n + i1) - 1;
        i4 = (j - m) / 2 + n + y + i3;
        paramGraphics.drawString(paramString, i2, i4);
        if (paramBoolean1 == true) {
          paramGraphics.drawLine(i2, i4 + 2, i2 + k, i4 + 2);
        }
      }
    }
    paramGraphics.setColor(localColor);
    paramGraphics.setFont(localFont);
  }
  
  public static void drawString(Graphics paramGraphics, Rectangle paramRectangle, int paramInt1, Color paramColor, Font paramFont, String paramString, int paramInt2, boolean paramBoolean)
  {
    drawString(paramGraphics, paramRectangle, paramInt1, paramColor, paramFont, paramString, paramInt2, paramBoolean, false);
  }
  
  private static void drawString(Graphics paramGraphics, Dimension paramDimension, int paramInt1, Color paramColor, Font paramFont, String paramString, int paramInt2, boolean paramBoolean)
  {
    Rectangle localRectangle = new Rectangle(0, 0, width, height);
    drawString(paramGraphics, localRectangle, paramInt1, paramColor, paramFont, paramString, paramInt2, paramBoolean);
  }
  
  public static void drawTempString(Graphics paramGraphics, Rectangle paramRectangle, Color paramColor, Font paramFont1, Font paramFont2, String paramString, int paramInt)
  {
    if (paramString == null) {
      return;
    }
    int i = width;
    int j = height;
    Color localColor1 = paramGraphics.getColor();
    Color localColor2 = paramColor;
    if (localColor2 == null) {
      localColor2 = localColor1;
    }
    paramGraphics.setColor(localColor2);
    Font localFont = paramGraphics.getFont();
    if (paramFont1 != null) {
      paramGraphics.setFont(paramFont1);
    }
    int k;
    int m;
    int n;
    int i1;
    int i2;
    String str;
    if (paramInt == 2)
    {
      k = paramGraphics.getFontMetrics().stringWidth(paramString);
      m = paramGraphics.getFontMetrics().getHeight();
      n = paramGraphics.getFontMetrics().getAscent();
      i1 = (i - k) / 2 + x;
      i2 = (j - m) / 2 + n + y;
      paramGraphics.drawString(paramString, i1, i2);
      if (paramFont2 != null) {
        paramGraphics.setFont(paramFont2);
      }
      i1 += k;
      if (SystemInfo.isCentigrade() == true) {
        str = StrRes.getStr("IDS_COMMON_UNIT_TEMP");
      } else {
        str = StrRes.getStr("IDS_COMMON_UNIT_TEMP_F");
      }
      paramGraphics.drawString(str, i1, i2);
    }
    else
    {
      k = x + 10;
      m = paramGraphics.getFontMetrics().getHeight();
      n = paramGraphics.getFontMetrics().getAscent();
      i1 = (j - m) / 2 + n + y;
      paramGraphics.drawString(paramString, k, i1);
      if (paramFont2 != null) {
        paramGraphics.setFont(paramFont2);
      }
      i2 = paramGraphics.getFontMetrics().stringWidth(paramString);
      k += i2 + 5;
      if (SystemInfo.isCentigrade() == true) {
        str = StrRes.getStr("IDS_COMMON_UNIT_TEMP");
      } else {
        str = StrRes.getStr("IDS_COMMON_UNIT_TEMP_F");
      }
      paramGraphics.drawString(str, k, i1);
    }
    paramGraphics.setColor(localColor1);
    paramGraphics.setFont(localFont);
  }
  
  public static void fillLetteredRect(Graphics paramGraphics, Rectangle paramRectangle, Color paramColor1, Color paramColor2, int paramInt1, Color paramColor3, Font paramFont, String paramString, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    Color localColor1 = paramGraphics.getColor();
    if (paramColor1 != null)
    {
      paramGraphics.setColor(paramColor1);
      paramGraphics.fillRect(x, y, width, height);
    }
    Color localColor2 = paramColor2;
    if (paramColor2 == null) {
      localColor2 = localColor1;
    }
    fillRect(paramGraphics, paramRectangle, localColor2, paramInt1);
    if (paramString != null)
    {
      localColor2 = paramColor3;
      if (paramColor3 == null) {
        localColor2 = localColor1;
      }
      Rectangle localRectangle = new Rectangle(x + paramInt3, y, width - paramInt3, height);
      drawString(paramGraphics, localRectangle, paramInt1, localColor2, paramFont, paramString, paramInt2, paramBoolean);
    }
    paramGraphics.setColor(localColor1);
  }
  
  public static void fillLetteredRect(Graphics paramGraphics, Dimension paramDimension, Color paramColor1, Color paramColor2, int paramInt1, Color paramColor3, Font paramFont, String paramString, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    Rectangle localRectangle = new Rectangle(paramDimension);
    fillLetteredRect(paramGraphics, localRectangle, paramColor1, paramColor2, paramInt1, paramColor3, paramFont, paramString, paramInt2, paramBoolean, paramInt3);
  }
  
  public static void fillLetteredRect(Graphics paramGraphics, Dimension paramDimension, Color paramColor1, Color paramColor2, int paramInt1, Color paramColor3, Font paramFont, String paramString, int paramInt2, boolean paramBoolean)
  {
    fillLetteredRect(paramGraphics, paramDimension, paramColor1, paramColor2, paramInt1, paramColor3, paramFont, paramString, paramInt2, paramBoolean, 0);
  }
  
  public static void fillLetteredRectWithShadow(Graphics paramGraphics, Rectangle paramRectangle, Color paramColor1, Color paramColor2, int paramInt1, Color paramColor3, Font paramFont, String paramString, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    Color localColor1 = paramGraphics.getColor();
    if (paramColor1 != null)
    {
      paramGraphics.setColor(paramColor1);
      paramGraphics.fillRect(x, y, width, height);
    }
    int i = x;
    int j = y;
    int k = width;
    int m = height;
    if ((paramInt1 & 0x10) != 0) {
      paramGraphics.setColor(getTransparentColor(ColorRes.SIMPLE_MODE_BTN_BG_2));
    } else {
      paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_BG_2);
    }
    paramGraphics.fillRect(i + 1, j + 1, k - 2, m - 2);
    if ((paramInt1 & 0x10) != 0) {
      paramGraphics.setColor(getTransparentColor(ColorRes.SIMPLE_MODE_BTN_BG_1));
    } else {
      paramGraphics.setColor(ColorRes.SIMPLE_MODE_BTN_BG_1);
    }
    paramGraphics.fillRect(i + 2, j + 2, k - 2, m - 2);
    Color localColor2 = paramColor2;
    if (paramColor2 == null) {
      localColor2 = localColor1;
    }
    fillRect(paramGraphics, new Rectangle(i, j, k - 3, m - 3), localColor2, paramInt1);
    if (paramString != null)
    {
      localColor2 = paramColor3;
      if (paramColor3 == null) {
        localColor2 = localColor1;
      }
      Rectangle localRectangle = new Rectangle(i + paramInt3, j, width - paramInt3 - 3, height - 3);
      drawString(paramGraphics, localRectangle, paramInt1, localColor2, paramFont, paramString, paramInt2, paramBoolean);
    }
    paramGraphics.setColor(localColor1);
  }
  
  public static void fillLetteredRectWithShadow(Graphics paramGraphics, Dimension paramDimension, Color paramColor1, Color paramColor2, int paramInt1, Color paramColor3, Font paramFont, String paramString, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    Rectangle localRectangle = new Rectangle(paramDimension);
    fillLetteredRectWithShadow(paramGraphics, localRectangle, paramColor1, paramColor2, paramInt1, paramColor3, paramFont, paramString, paramInt2, paramBoolean, paramInt3);
  }
  
  public static void fillLetteredRectWithShadow(Graphics paramGraphics, Dimension paramDimension, Color paramColor1, Color paramColor2, int paramInt1, Color paramColor3, Font paramFont, String paramString, int paramInt2, boolean paramBoolean)
  {
    fillLetteredRectWithShadow(paramGraphics, paramDimension, paramColor1, paramColor2, paramInt1, paramColor3, paramFont, paramString, paramInt2, paramBoolean, 0);
  }
  
  private static void fillRect(Graphics paramGraphics, Rectangle paramRectangle, Color paramColor, int paramInt)
  {
    Color localColor = paramColor;
    if ((paramInt & 0x10) != 0) {
      localColor = getTransparentColor(paramColor);
    }
    int i = x;
    int j = y;
    int k = width;
    int m = height;
    int n;
    if ((paramInt & 0x2) != 0)
    {
      for (n = 0; n <= k / 2; n++)
      {
        paramGraphics.setColor(localColor);
        paramGraphics.drawLine(i + n, j, i + n, j + m);
        paramGraphics.drawLine(i + k - n - 1, j, i + k - n - 1, j + m);
        localColor = decrementColor(localColor);
      }
    }
    else if ((paramInt & 0x4) != 0)
    {
      for (n = m + j; n >= j; n--)
      {
        paramGraphics.drawLine(i, n, k + i, n);
        localColor = decrementColor(localColor);
        paramGraphics.setColor(localColor);
      }
    }
    else
    {
      paramGraphics.setColor(localColor);
      paramGraphics.fillRect(i, j, k, m);
    }
  }
  
  private static void fillRect(Graphics paramGraphics, Dimension paramDimension, Color paramColor, int paramInt)
  {
    Rectangle localRectangle = new Rectangle(paramDimension);
    fillRect(paramGraphics, localRectangle, paramColor, paramInt);
  }
  
  public static void fillRect(Graphics paramGraphics, Dimension paramDimension, Color paramColor1, Color paramColor2, int paramInt)
  {
    fillLetteredRect(paramGraphics, paramDimension, paramColor1, paramColor2, paramInt, null, null, null, 0, false);
  }
  
  public static void fillRectWithShadow(Graphics paramGraphics, Dimension paramDimension, Color paramColor1, Color paramColor2, int paramInt)
  {
    fillLetteredRectWithShadow(paramGraphics, paramDimension, paramColor1, paramColor2, paramInt, null, null, null, 0, false);
  }
  
  private static Color getButtonWallColor(Color paramColor, int paramInt)
  {
    int i = paramColor.getAlpha();
    int j = paramColor.getRed() * paramInt / 100;
    int k = paramColor.getGreen() * paramInt / 100;
    int m = paramColor.getBlue() * paramInt / 100;
    return getColor(j, k, m, i);
  }
  
  public static Color getColor(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt4 > 255) {
      paramInt4 = 255;
    }
    if (paramInt4 < 0) {
      paramInt4 = 0;
    }
    if (paramInt1 > 255) {
      paramInt1 = 255;
    }
    if (paramInt1 < 0) {
      paramInt1 = 0;
    }
    if (paramInt2 > 255) {
      paramInt2 = 255;
    }
    if (paramInt2 < 0) {
      paramInt2 = 0;
    }
    if (paramInt3 > 255) {
      paramInt3 = 255;
    }
    if (paramInt3 < 0) {
      paramInt3 = 0;
    }
    return new Color(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static Image getColoredImage(Image paramImage, double paramDouble1, double paramDouble2, double paramDouble3)
  {
    AppletAbst localAppletAbst = AppletMain.getInstance();
    if (localAppletAbst == null) {
      return paramImage;
    }
    ColoredFilter localColoredFilter = new ColoredFilter();
    localColoredFilter.setColorRate(paramDouble1, paramDouble2, paramDouble3);
    FilteredImageSource localFilteredImageSource = new FilteredImageSource(paramImage.getSource(), localColoredFilter);
    Image localImage = localAppletAbst.createImage(localFilteredImageSource);
    return localImage;
  }
  
  public static Image getGrayImage(Image paramImage)
  {
    AppletAbst localAppletAbst = AppletMain.getInstance();
    if (localAppletAbst == null) {
      return paramImage;
    }
    FilteredImageSource localFilteredImageSource = new FilteredImageSource(paramImage.getSource(), new GrayFilter(false, 50));
    Image localImage = localAppletAbst.createImage(localFilteredImageSource);
    return localImage;
  }
  
  private static Color getPressedColor(Color paramColor)
  {
    int i = paramColor.getAlpha();
    int j = paramColor.getRed() * 50 / 100;
    int k = paramColor.getGreen() * 50 / 100;
    int m = paramColor.getBlue() * 50 / 100;
    return getColor(j, k, m, i);
  }
  
  public static Image getScaleImage(Image paramImage, Dimension paramDimension)
  {
    AppletAbst localAppletAbst = AppletMain.getInstance();
    if (localAppletAbst == null) {
      return paramImage;
    }
    int i = width;
    int j = height;
    FilteredImageSource localFilteredImageSource = new FilteredImageSource(paramImage.getSource(), new ScaleFilter(i, j));
    Image localImage = localAppletAbst.createImage(localFilteredImageSource);
    return localImage;
  }
  
  private static Color getTransparentColor(Color paramColor)
  {
    int i = 120;
    int j = paramColor.getRed();
    int k = paramColor.getGreen();
    int m = paramColor.getBlue();
    return getColor(j, k, m, i);
  }
  
  public static Image getTransparentImage(Image paramImage, Color paramColor, boolean paramBoolean)
  {
    AppletAbst localAppletAbst = AppletMain.getInstance();
    if (localAppletAbst == null) {
      return paramImage;
    }
    if ((paramColor == null) && (!paramBoolean)) {
      return paramImage;
    }
    TransparentFilter localTransparentFilter = new TransparentFilter();
    if (paramColor != null) {
      localTransparentFilter.setTransparentColor(paramColor);
    }
    localTransparentFilter.setDecColor(paramBoolean);
    FilteredImageSource localFilteredImageSource = new FilteredImageSource(paramImage.getSource(), localTransparentFilter);
    Image localImage = localAppletAbst.createImage(localFilteredImageSource);
    return localImage;
  }
}
