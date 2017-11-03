package webitc.gui.simple;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import webitc.common.StrRes;
import webitc.common.enum2.EnumDrvVentMode;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.gui.common.ColorRes;

class DriveModeCellRenderer
  extends SimpleCellRendererAbst
{
  DriveModeCellRenderer() {}
  
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    if (paramObject == null)
    {
      setColor(paramBoolean1);
      return this;
    }
    ID localID = (ID)paramObject;
    setHorizontalAlignment(0);
    if (paramBoolean1 == true)
    {
      setColor(true);
    }
    else
    {
      super.setForeground(Color.BLACK);
      super.setBackground(Color.WHITE);
    }
    EnumDrvVentMode localEnumDrvVentMode = DataMgr.getInstance().getDrvVentMode(localID);
    setStr(localEnumDrvVentMode, paramBoolean1);
    return this;
  }
  
  private void setStr(EnumDrvVentMode paramEnumDrvVentMode, boolean paramBoolean)
  {
    String str = StrRes.getStr("IDS_COMMON_UNKNOWN");
    Color localColor1 = ColorRes.SIMPLE_MODE_LIST_SELECT_F;
    Color localColor2 = ColorRes.SIMPLE_MODE_LIST_NO_SELECT_B;
    int i = SimpleCommon.getDrvVentMode(paramEnumDrvVentMode);
    switch (i)
    {
    case 8200: 
      str = StrRes.getStr("IDS_COMMON_MODE_AUTO");
      localColor2 = ColorRes.SIMPLE_MODE_BTN_AUTO;
      break;
    case 8194: 
      str = StrRes.getStr("IDS_COMMON_MODE_COOL");
      localColor2 = ColorRes.SIMPLE_MODE_BTN_COOL;
      break;
    case 8196: 
      str = StrRes.getStr("IDS_COMMON_MODE_FAN");
      localColor2 = ColorRes.SIMPLE_MODE_BTN_FAN;
      break;
    case 8193: 
      str = StrRes.getStr("IDS_COMMON_MODE_HEAT");
      localColor2 = ColorRes.SIMPLE_MODE_BTN_HEAT;
      break;
    case 9216: 
      str = StrRes.getStr("IDS_COMMON_VENT_MODE_NORMAL");
      localColor2 = ColorRes.SIMPLE_MODE_BTN_NORMAL_VENT;
      break;
    case 8704: 
      str = StrRes.getStr("IDS_COMMON_VENT_MODE_VENT");
      localColor2 = ColorRes.SIMPLE_MODE_BTN_TOTAL_VENT;
      break;
    case 8448: 
      str = StrRes.getStr("IDS_COMMON_VENT_MODE_AUTO");
      localColor2 = ColorRes.SIMPLE_MODE_BTN_AUTO_VENT;
      break;
    case 8224: 
      str = StrRes.getStr("IDS_COMMON_MODE_VENT");
      localColor2 = ColorRes.SIMPLE_MODE_BTN_NORMAL_VENT;
      break;
    default: 
      localColor1 = Color.BLACK;
      localColor2 = Color.WHITE;
    }
    if (!paramBoolean)
    {
      super.setForeground(localColor1);
      super.setBackground(localColor2);
    }
    setText(str);
  }
}
