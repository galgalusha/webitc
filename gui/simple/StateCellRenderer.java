package webitc.gui.simple;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import webitc.common.StrRes;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.PntState;
import webitc.gui.common.GraphicLibrary;
import webitc.gui.common.IconRes;
import webitc.gui.common.ItcIconInfo;

class StateCellRenderer
  extends SimpleCellRendererAbst
{
  private ImageIcon mFanImage = null;
  
  StateCellRenderer() {}
  
  private ImageIcon getDrvModeImage(ID paramID)
  {
    ImageIcon localImageIcon = getStateImage(paramID);
    if (localImageIcon != null) {
      return localImageIcon;
    }
    int i = SimpleCommon.getDrvMode(paramID);
    switch (i)
    {
    case 4096: 
      localImageIcon = null;
      break;
    case 8194: 
      localImageIcon = IconRes.getIcon(94);
      break;
    case 8193: 
      localImageIcon = IconRes.getIcon(95);
      break;
    case 8200: 
      localImageIcon = IconRes.getIcon(96);
      break;
    case 8196: 
      localImageIcon = IconRes.getIcon(97);
      break;
    case 8208: 
      localImageIcon = IconRes.getIcon(98);
      break;
    default: 
      localImageIcon = IconRes.getIcon(97);
    }
    return localImageIcon;
  }
  
  private String getInfoStr(ID paramID)
  {
    String str = "";
    ItcIconInfo localItcIconInfo = DataMgr.getInstance().getIconInfo(paramID);
    if ((fIconAppend & 0x1) != 0) {
      str = str + StrRes.getStr("IDS_CCLEGEND_FORCE_STOP");
    }
    if ((fIconAppend & 0x2) != 0)
    {
      if (str.length() != 0) {
        str = str + ", ";
      }
      str = str + StrRes.getStr("IDS_CCLEGEND_CONTROL");
    }
    if ((fIconAppend & 0x4) != 0)
    {
      if (str.length() != 0) {
        str = str + ", ";
      }
      str = str + StrRes.getStr("IDS_CCLEGEND_CTRL_TARGET");
    }
    if ((fIconAppend & 0x8) != 0)
    {
      if (str.length() != 0) {
        str = str + ", ";
      }
      str = str + StrRes.getStr("IDS_CCLEGEND_FILTER_SIGN");
    }
    return str;
  }
  
  private ImageIcon getLightImage(ID paramID)
  {
    PntState localPntState = DataMgr.getInstance().getState(paramID);
    int i = SimpleCommon.getOnOff(fOnOffState);
    if (i == 4096) {
      return IconRes.getIcon(153);
    }
    return IconRes.getIcon(154);
  }
  
  private ImageIcon getStateImage(ID paramID)
  {
    int i = SimpleCommon.getState(paramID);
    if (i == 0) {
      return null;
    }
    if (i == 2) {
      return IconRes.getIcon(167);
    }
    if (i == 1) {
      return IconRes.getIcon(168);
    }
    return null;
  }
  
  private ImageIcon getSwitchImage(ID paramID)
  {
    PntState localPntState = DataMgr.getInstance().getState(paramID);
    int i = SimpleCommon.getOnOff(fOnOffState);
    if (i == 4096) {
      return IconRes.getIcon(159);
    }
    return IconRes.getIcon(160);
  }
  
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    setColor(paramBoolean1);
    if (paramObject != null)
    {
      ID localID = (ID)paramObject;
      int i = SimpleCommon.getPntType(localID);
      switch (i)
      {
      case 0: 
        setIcon(getZoneImage(localID));
        mFanImage = getStateImage(localID);
        break;
      case 1: 
        setIcon(IconRes.getIcon(93));
        mFanImage = getDrvModeImage(localID);
        break;
      case 2: 
        setIcon(IconRes.getIcon(145));
        mFanImage = getVentModeImage(localID);
        break;
      case 3: 
        setIcon(getLightImage(localID));
        mFanImage = getStateImage(localID);
        break;
      case 4: 
      case 5: 
      default: 
        setIcon(getSwitchImage(localID));
        mFanImage = getStateImage(localID);
      }
      setText(getInfoStr(localID));
    }
    return this;
  }
  
  private ImageIcon getVentModeImage(ID paramID)
  {
    ImageIcon localImageIcon = getStateImage(paramID);
    if (localImageIcon != null) {
      return localImageIcon;
    }
    int i = SimpleCommon.getVentMode(paramID);
    switch (i)
    {
    case 4096: 
      localImageIcon = null;
      break;
    case 8448: 
      localImageIcon = IconRes.getIcon(146);
      break;
    case 8704: 
      localImageIcon = IconRes.getIcon(147);
      break;
    case 9216: 
      localImageIcon = IconRes.getIcon(148);
      break;
    default: 
      localImageIcon = IconRes.getIcon(148);
    }
    return localImageIcon;
  }
  
  private ImageIcon getZoneImage(ID paramID)
  {
    PntState localPntState = DataMgr.getInstance().getState(paramID);
    int i = SimpleCommon.getOnOff(fOnOffState);
    if (i == 4096) {
      return IconRes.getIcon(119);
    }
    return IconRes.getIcon(120);
  }
  
  public void paint(Graphics paramGraphics)
  {
    Color localColor = paramGraphics.getColor();
    Font localFont = paramGraphics.getFont();
    int i = getWidth();
    int j = getHeight();
    paramGraphics.setColor(getBackground());
    paramGraphics.fillRect(0, 0, i, j);
    if (getIcon() == null)
    {
      paramGraphics.setColor(localColor);
      return;
    }
    Icon localIcon = getIcon();
    int k = localIcon.getIconWidth();
    int m = localIcon.getIconHeight();
    int n = 5;
    int i1 = (j - m) / 2;
    localIcon.paintIcon(this, paramGraphics, n, i1);
    if (mFanImage != null) {
      mFanImage.paintIcon(this, paramGraphics, n, i1);
    }
    int i2 = k + 3;
    int i3 = 0;
    int i4 = i - i2 - 10;
    int i5 = j;
    int i6 = 1;
    int i7 = 1;
    Rectangle localRectangle = new Rectangle(i2, i3, i4, i5);
    GraphicLibrary.drawString(paramGraphics, localRectangle, i6, getForeground(), getFont(), getText(), i7, false, true);
    paramGraphics.setColor(localColor);
    paramGraphics.setFont(localFont);
  }
}
