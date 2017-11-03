package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import webitc.common.StrRes;
import webitc.gui.common.AnimationPanel;
import webitc.gui.common.ColorRes;
import webitc.gui.common.IconRes;
import webitc.gui.common.PanelAbstract;

public class PanelStateIcon
  extends PanelAbstract
{
  public static final int AUTO = 8200;
  public static final int COOL = 8194;
  public static final int FAN = 8196;
  public static final int FAN_1 = 0;
  public static final int FAN_2 = 1;
  public static final int FAN_3 = 2;
  public static final int FAN_4 = 3;
  public static final int FAN_5 = 4;
  public static final int HEAT = 8193;
  public static final int HIGH = 1;
  public static final int LOW = 0;
  public static final int STOP = 4096;
  public static final int SUBMIT = 8208;
  public static final int SWING = 5;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  AnimationPanel mIcon = new AnimationPanel(IconRes.getIcon(35), 0);
  PanelSign mSign = new PanelSign();
  
  public PanelStateIcon()
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
    mSign.addRoomTemp();
    add(mIcon, new GridBagConstraints(0, 0, 1, 1, 0.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    add(mSign, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 15, 2, new Insets(0, 0, 0, 0), 0, 0));
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
  
  public void setIconState(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mIcon.endAnimation();
    mIcon.clearAnimationImage();
    if (paramInt1 != 0)
    {
      if (paramInt1 == 2)
      {
        mIcon.addAnimationImage(IconRes.getIcon(163));
        mIcon.setText(StrRes.getStr("IDS_COMMON_ERR_LEVEL_5"));
      }
      else if (paramInt1 == 1)
      {
        mIcon.addAnimationImage(IconRes.getIcon(164));
        mIcon.setText(StrRes.getStr("IDS_COMMON_COMM_ERR"));
      }
      else
      {
        return;
      }
      mIcon.updateImage(0);
      mSign.repaint();
      return;
    }
    if ((paramInt3 < 0) || (paramInt3 > 1)) {
      paramInt3 = 0;
    }
    if ((paramInt4 < 0) || (paramInt4 > 5)) {
      paramInt4 = 0;
    }
    if ((paramInt2 != 4096) && (paramInt2 != 8194) && (paramInt2 != 8193) && (paramInt2 != 8200) && (paramInt2 != 8196) && (paramInt2 != 8208)) {
      paramInt2 = 8196;
    }
    switch (paramInt2)
    {
    case 8194: 
      switch (paramInt4)
      {
      case 0: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(36));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(37));
        }
        mIcon.updateImage(0);
        break;
      case 1: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(38));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(39));
        }
        mIcon.updateImage(0);
        break;
      case 2: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(40));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(41));
        }
        mIcon.updateImage(0);
        break;
      case 3: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(42));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(43));
        }
        mIcon.updateImage(0);
        break;
      case 4: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(44));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(45));
        }
        mIcon.updateImage(0);
        break;
      case 5: 
        if (paramInt3 == 0)
        {
          mIcon.addAnimationImage(IconRes.getIcon(36));
          mIcon.addAnimationImage(IconRes.getIcon(38));
          mIcon.addAnimationImage(IconRes.getIcon(40));
          mIcon.addAnimationImage(IconRes.getIcon(42));
          mIcon.addAnimationImage(IconRes.getIcon(44));
          mIcon.addAnimationImage(IconRes.getIcon(42));
          mIcon.addAnimationImage(IconRes.getIcon(40));
          mIcon.addAnimationImage(IconRes.getIcon(38));
        }
        else
        {
          mIcon.addAnimationImage(IconRes.getIcon(37));
          mIcon.addAnimationImage(IconRes.getIcon(39));
          mIcon.addAnimationImage(IconRes.getIcon(41));
          mIcon.addAnimationImage(IconRes.getIcon(43));
          mIcon.addAnimationImage(IconRes.getIcon(45));
          mIcon.addAnimationImage(IconRes.getIcon(43));
          mIcon.addAnimationImage(IconRes.getIcon(41));
          mIcon.addAnimationImage(IconRes.getIcon(39));
        }
        mIcon.beginAnimation();
      }
      break;
    case 8193: 
      switch (paramInt4)
      {
      case 0: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(46));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(47));
        }
        mIcon.updateImage(0);
        break;
      case 1: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(48));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(49));
        }
        mIcon.updateImage(0);
        break;
      case 2: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(50));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(51));
        }
        mIcon.updateImage(0);
        break;
      case 3: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(52));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(53));
        }
        mIcon.updateImage(0);
        break;
      case 4: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(54));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(55));
        }
        mIcon.updateImage(0);
        break;
      case 5: 
        if (paramInt3 == 0)
        {
          mIcon.addAnimationImage(IconRes.getIcon(46));
          mIcon.addAnimationImage(IconRes.getIcon(48));
          mIcon.addAnimationImage(IconRes.getIcon(50));
          mIcon.addAnimationImage(IconRes.getIcon(52));
          mIcon.addAnimationImage(IconRes.getIcon(54));
          mIcon.addAnimationImage(IconRes.getIcon(52));
          mIcon.addAnimationImage(IconRes.getIcon(50));
          mIcon.addAnimationImage(IconRes.getIcon(48));
        }
        else
        {
          mIcon.addAnimationImage(IconRes.getIcon(47));
          mIcon.addAnimationImage(IconRes.getIcon(49));
          mIcon.addAnimationImage(IconRes.getIcon(51));
          mIcon.addAnimationImage(IconRes.getIcon(53));
          mIcon.addAnimationImage(IconRes.getIcon(55));
          mIcon.addAnimationImage(IconRes.getIcon(53));
          mIcon.addAnimationImage(IconRes.getIcon(51));
          mIcon.addAnimationImage(IconRes.getIcon(49));
        }
        mIcon.beginAnimation();
      }
      break;
    case 8200: 
      switch (paramInt4)
      {
      case 0: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(56));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(57));
        }
        mIcon.updateImage(0);
        break;
      case 1: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(58));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(59));
        }
        mIcon.updateImage(0);
        break;
      case 2: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(60));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(61));
        }
        mIcon.updateImage(0);
        break;
      case 3: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(62));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(63));
        }
        mIcon.updateImage(0);
        break;
      case 4: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(64));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(65));
        }
        mIcon.updateImage(0);
        break;
      case 5: 
        if (paramInt3 == 0)
        {
          mIcon.addAnimationImage(IconRes.getIcon(56));
          mIcon.addAnimationImage(IconRes.getIcon(58));
          mIcon.addAnimationImage(IconRes.getIcon(60));
          mIcon.addAnimationImage(IconRes.getIcon(62));
          mIcon.addAnimationImage(IconRes.getIcon(64));
          mIcon.addAnimationImage(IconRes.getIcon(62));
          mIcon.addAnimationImage(IconRes.getIcon(60));
          mIcon.addAnimationImage(IconRes.getIcon(58));
        }
        else
        {
          mIcon.addAnimationImage(IconRes.getIcon(57));
          mIcon.addAnimationImage(IconRes.getIcon(59));
          mIcon.addAnimationImage(IconRes.getIcon(61));
          mIcon.addAnimationImage(IconRes.getIcon(63));
          mIcon.addAnimationImage(IconRes.getIcon(65));
          mIcon.addAnimationImage(IconRes.getIcon(63));
          mIcon.addAnimationImage(IconRes.getIcon(61));
          mIcon.addAnimationImage(IconRes.getIcon(59));
        }
        mIcon.beginAnimation();
      }
      break;
    case 8196: 
      switch (paramInt4)
      {
      case 0: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(66));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(67));
        }
        mIcon.updateImage(0);
        break;
      case 1: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(68));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(69));
        }
        mIcon.updateImage(0);
        break;
      case 2: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(70));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(71));
        }
        mIcon.updateImage(0);
        break;
      case 3: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(72));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(73));
        }
        mIcon.updateImage(0);
        break;
      case 4: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(74));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(75));
        }
        mIcon.updateImage(0);
        break;
      case 5: 
        if (paramInt3 == 0)
        {
          mIcon.addAnimationImage(IconRes.getIcon(66));
          mIcon.addAnimationImage(IconRes.getIcon(68));
          mIcon.addAnimationImage(IconRes.getIcon(70));
          mIcon.addAnimationImage(IconRes.getIcon(72));
          mIcon.addAnimationImage(IconRes.getIcon(74));
          mIcon.addAnimationImage(IconRes.getIcon(72));
          mIcon.addAnimationImage(IconRes.getIcon(70));
          mIcon.addAnimationImage(IconRes.getIcon(68));
        }
        else
        {
          mIcon.addAnimationImage(IconRes.getIcon(67));
          mIcon.addAnimationImage(IconRes.getIcon(69));
          mIcon.addAnimationImage(IconRes.getIcon(71));
          mIcon.addAnimationImage(IconRes.getIcon(73));
          mIcon.addAnimationImage(IconRes.getIcon(75));
          mIcon.addAnimationImage(IconRes.getIcon(73));
          mIcon.addAnimationImage(IconRes.getIcon(71));
          mIcon.addAnimationImage(IconRes.getIcon(69));
        }
        mIcon.beginAnimation();
      }
      break;
    case 8208: 
      switch (paramInt4)
      {
      case 0: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(76));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(77));
        }
        mIcon.updateImage(0);
        break;
      case 1: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(78));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(79));
        }
        mIcon.updateImage(0);
        break;
      case 2: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(80));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(81));
        }
        mIcon.updateImage(0);
        break;
      case 3: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(82));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(83));
        }
        mIcon.updateImage(0);
        break;
      case 4: 
        if (paramInt3 == 0) {
          mIcon.addAnimationImage(IconRes.getIcon(84));
        } else {
          mIcon.addAnimationImage(IconRes.getIcon(85));
        }
        mIcon.updateImage(0);
        break;
      case 5: 
        if (paramInt3 == 0)
        {
          mIcon.addAnimationImage(IconRes.getIcon(76));
          mIcon.addAnimationImage(IconRes.getIcon(78));
          mIcon.addAnimationImage(IconRes.getIcon(80));
          mIcon.addAnimationImage(IconRes.getIcon(82));
          mIcon.addAnimationImage(IconRes.getIcon(84));
          mIcon.addAnimationImage(IconRes.getIcon(82));
          mIcon.addAnimationImage(IconRes.getIcon(80));
          mIcon.addAnimationImage(IconRes.getIcon(78));
        }
        else
        {
          mIcon.addAnimationImage(IconRes.getIcon(77));
          mIcon.addAnimationImage(IconRes.getIcon(79));
          mIcon.addAnimationImage(IconRes.getIcon(81));
          mIcon.addAnimationImage(IconRes.getIcon(83));
          mIcon.addAnimationImage(IconRes.getIcon(85));
          mIcon.addAnimationImage(IconRes.getIcon(83));
          mIcon.addAnimationImage(IconRes.getIcon(81));
          mIcon.addAnimationImage(IconRes.getIcon(79));
        }
        mIcon.beginAnimation();
      }
      break;
    case 4096: 
    default: 
      mIcon.updateImage();
    }
    mSign.repaint();
  }
  
  public void setRoomTemp(String paramString)
  {
    mSign.setRoomTemp(paramString);
  }
  
  public void setVisible(boolean paramBoolean)
  {
    super.setVisible(paramBoolean);
    mIcon.setVisible(paramBoolean);
  }
}
