package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import webitc.common.StrRes;
import webitc.gui.common.ColorRes;
import webitc.gui.common.IconRes;
import webitc.gui.common.MultiIconPanel;
import webitc.gui.common.PanelAbstract;

public class PanelSwitchStateIcon
  extends PanelAbstract
{
  public static final int OFF = 4096;
  public static final int ON = 8192;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  MultiIconPanel mIcon = new MultiIconPanel();
  PanelSign mSign = new PanelSign();
  
  public PanelSwitchStateIcon()
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
  
  private void jbInit()
    throws Exception
  {
    setBackground(ColorRes.SIMPLE_MODE_BG);
    setLayout(gridBagLayout1);
    setMinimumSize(new Dimension(150, 150));
    mIcon.setBackground(ColorRes.SIMPLE_MODE_BG);
    add(mIcon, new GridBagConstraints(0, 0, 1, 1, 0.5D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    add(mSign, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  public void setControlSign(boolean paramBoolean)
  {
    mSign.setControl(paramBoolean);
  }
  
  public void setCtrlTargetSign(boolean paramBoolean)
  {
    mSign.setCtrlTarget(paramBoolean);
  }
  
  public void setFilterSign(boolean paramBoolean)
  {
    mSign.setFilterSign(paramBoolean);
  }
  
  public void setIconState(int paramInt1, int paramInt2)
  {
    mIcon.clearImage();
    if (paramInt2 == 8192) {
      mIcon.addImage(IconRes.getIcon(156));
    } else {
      mIcon.addImage(IconRes.getIcon(155));
    }
    if (paramInt1 != 0) {
      if (paramInt1 == 2)
      {
        mIcon.addImage(IconRes.getIcon(163));
        mIcon.setText(StrRes.getStr("IDS_COMMON_ERR_LEVEL_5"));
      }
      else if (paramInt1 == 1)
      {
        mIcon.addImage(IconRes.getIcon(164));
        mIcon.setText(StrRes.getStr("IDS_COMMON_COMM_ERR"));
      }
    }
    mIcon.repaint();
    mSign.repaint();
  }
  
  public void setVisible(boolean paramBoolean)
  {
    super.setVisible(paramBoolean);
    mIcon.setVisible(paramBoolean);
  }
}
