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

public class PanelVentStateIcon
  extends PanelAbstract
{
  public static final int DRV_AUTO = 8448;
  public static final int DRV_NORMAL = 9216;
  public static final int DRV_STOP = 4096;
  public static final int DRV_TOTAL = 8704;
  public static final int VOL_AUTO = 2;
  public static final int VOL_HIGH = 1;
  public static final int VOL_LOW = 0;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  MultiIconPanel mIcon = new MultiIconPanel();
  PanelSign mSign = new PanelSign();
  
  public PanelVentStateIcon()
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
  
  public void setIconState(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    mIcon.clearImage();
    mIcon.addImage(IconRes.getIcon(128));
    if (paramInt1 != 0)
    {
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
      else
      {
        return;
      }
      mIcon.repaint();
      mSign.repaint();
      return;
    }
    if ((paramInt2 != 4096) && (paramInt2 != 8448) && (paramInt2 != 8704) && (paramInt2 != 9216)) {
      paramInt2 = 9216;
    }
    if ((paramInt3 != 0) && (paramInt3 != 1) && (paramInt3 != 2)) {
      paramInt2 = 0;
    }
    switch (paramInt2)
    {
    case 8448: 
      switch (paramInt3)
      {
      case 0: 
        if (paramBoolean == true)
        {
          mIcon.addImage(IconRes.getIcon(129));
          mIcon.addImage(IconRes.getIcon(138));
        }
        else
        {
          mIcon.addImage(IconRes.getIcon(129));
        }
        mIcon.repaint();
        break;
      case 1: 
        if (paramBoolean == true)
        {
          mIcon.addImage(IconRes.getIcon(130));
          mIcon.addImage(IconRes.getIcon(138));
        }
        else
        {
          mIcon.addImage(IconRes.getIcon(130));
        }
        mIcon.repaint();
        break;
      case 2: 
        if (paramBoolean == true)
        {
          mIcon.addImage(IconRes.getIcon(131));
          mIcon.addImage(IconRes.getIcon(138));
        }
        else
        {
          mIcon.addImage(IconRes.getIcon(131));
        }
        mIcon.repaint();
      }
      break;
    case 8704: 
      switch (paramInt3)
      {
      case 0: 
        if (paramBoolean == true)
        {
          mIcon.addImage(IconRes.getIcon(132));
          mIcon.addImage(IconRes.getIcon(139));
        }
        else
        {
          mIcon.addImage(IconRes.getIcon(132));
        }
        mIcon.repaint();
        break;
      case 1: 
        if (paramBoolean == true)
        {
          mIcon.addImage(IconRes.getIcon(133));
          mIcon.addImage(IconRes.getIcon(139));
        }
        else
        {
          mIcon.addImage(IconRes.getIcon(133));
        }
        mIcon.repaint();
        break;
      case 2: 
        if (paramBoolean == true)
        {
          mIcon.addImage(IconRes.getIcon(134));
          mIcon.addImage(IconRes.getIcon(139));
        }
        else
        {
          mIcon.addImage(IconRes.getIcon(134));
        }
        mIcon.repaint();
      }
      break;
    case 9216: 
      switch (paramInt3)
      {
      case 0: 
        if (paramBoolean == true)
        {
          mIcon.addImage(IconRes.getIcon(135));
          mIcon.addImage(IconRes.getIcon(140));
        }
        else
        {
          mIcon.addImage(IconRes.getIcon(135));
        }
        mIcon.repaint();
        break;
      case 1: 
        if (paramBoolean == true)
        {
          mIcon.addImage(IconRes.getIcon(136));
          mIcon.addImage(IconRes.getIcon(140));
        }
        else
        {
          mIcon.addImage(IconRes.getIcon(136));
        }
        mIcon.repaint();
        break;
      case 2: 
        if (paramBoolean == true)
        {
          mIcon.addImage(IconRes.getIcon(137));
          mIcon.addImage(IconRes.getIcon(140));
        }
        else
        {
          mIcon.addImage(IconRes.getIcon(137));
        }
        mIcon.repaint();
      }
      break;
    case 4096: 
    default: 
      mIcon.repaint();
    }
    mSign.repaint();
  }
  
  public void setVisible(boolean paramBoolean)
  {
    super.setVisible(paramBoolean);
    mIcon.setVisible(paramBoolean);
  }
}
