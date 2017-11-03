package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import webitc.common.StrRes;
import webitc.gui.common.ColorRes;
import webitc.gui.common.IconRes;
import webitc.gui.common.PanelAbstract;

public class PanelSign
  extends PanelAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JLabel mControl = new JLabel();
  JLabel mCtrlTarget = new JLabel();
  JLabel mFilter = new JLabel();
  RoomTempIcon mRoomTemp = new RoomTempIcon();
  
  public PanelSign()
  {
    try
    {
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void addRoomTemp()
  {
    mRoomTemp.setVisible(true);
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    setBackground(ColorRes.SIMPLE_MODE_BG);
    Dimension localDimension = new Dimension(22, 22);
    mControl.setPreferredSize(localDimension);
    mCtrlTarget.setPreferredSize(localDimension);
    mFilter.setPreferredSize(localDimension);
    add(mControl, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 15, 0, new Insets(0, 0, 0, 5), 0, 0));
    add(mCtrlTarget, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 15, 0, new Insets(0, 0, 0, 5), 0, 0));
    add(mFilter, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 15, 0, new Insets(0, 0, 0, 5), 0, 0));
    add(mRoomTemp, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 14, 0, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  public void setControl(boolean paramBoolean)
  {
    if (paramBoolean == true)
    {
      mControl.setIcon(IconRes.getIcon(173));
      mControl.setToolTipText(StrRes.getStr("IDS_CCLEGEND_CONTROL"));
    }
    else
    {
      mControl.setIcon(null);
      mControl.setToolTipText(null);
    }
  }
  
  public void setCtrlTarget(boolean paramBoolean)
  {
    if (paramBoolean == true)
    {
      mCtrlTarget.setIcon(IconRes.getIcon(172));
      mCtrlTarget.setToolTipText(StrRes.getStr("IDS_CCLEGEND_CTRL_TARGET"));
    }
    else
    {
      mCtrlTarget.setIcon(null);
      mCtrlTarget.setToolTipText(null);
    }
  }
  
  public void setFilterSign(boolean paramBoolean)
  {
    if (paramBoolean == true)
    {
      mFilter.setIcon(IconRes.getIcon(171));
      mFilter.setToolTipText(StrRes.getStr("IDS_CCLEGEND_FILTER_SIGN"));
    }
    else
    {
      mFilter.setIcon(null);
      mFilter.setToolTipText(null);
    }
  }
  
  public void setRoomTemp(String paramString)
  {
    if (mRoomTemp != null) {
      mRoomTemp.setTemp(paramString);
    }
  }
}
