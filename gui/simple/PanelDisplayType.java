package webitc.gui.simple;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import webitc.gui.common.ColorRes;
import webitc.gui.common.IconRes;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.SimpleButton;
import webitc.gui.common.SimpleButtonRes;

public class PanelDisplayType
  extends PanelAbstract
{
  private static final Font FONT_ZONE_NAME = new Font("Dialog", 0, 16);
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  SimpleButton mBtnExpansion = SimpleButtonRes.getButton(15);
  JLabel mLabelZoneName = new JLabel();
  private PanelDisplayTypeListener mListener = null;
  
  public PanelDisplayType()
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
    mLabelZoneName.setBackground(ColorRes.SIMPLE_MODE_BG);
    mLabelZoneName.setOpaque(true);
    mLabelZoneName.setFont(FONT_ZONE_NAME);
    mBtnExpansion.addActionListener(new PanelDisplayType_mBtnExpansion_actionAdapter(this));
    add(mLabelZoneName, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 5, 0, 5), 0, 0));
    add(mBtnExpansion, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 5), 0, 0));
  }
  
  void mBtnExpansion_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyExpansion();
    }
  }
  
  public void setExpansionMode(boolean paramBoolean)
  {
    if (paramBoolean == true) {
      mBtnExpansion.setImageIcon(IconRes.getIcon(161));
    } else {
      mBtnExpansion.setImageIcon(IconRes.getIcon(162));
    }
  }
  
  public void setListener(PanelDisplayTypeListener paramPanelDisplayTypeListener)
  {
    mListener = paramPanelDisplayTypeListener;
  }
  
  public void setZoneName(Icon paramIcon, String paramString)
  {
    mLabelZoneName.setText(paramString);
    mLabelZoneName.setIcon(paramIcon);
  }
  
  public static abstract interface PanelDisplayTypeListener
  {
    public abstract void notifyExpansion();
  }
}
