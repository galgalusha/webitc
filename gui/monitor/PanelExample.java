package webitc.gui.monitor;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import webitc.common.StrRes;
import webitc.gui.common.ColorIcon;
import webitc.gui.common.ColorRes;
import webitc.gui.common.PanelAbstract;

class PanelExample
  extends PanelAbstract
{
  Border border1;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton mBtn = new JButton();
  JLabel mLblCom = new JLabel();
  JLabel mLblErr = new JLabel();
  JLabel mLblOff = new JLabel();
  JLabel mLblOn = new JLabel();
  
  public PanelExample()
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
    setLayout(gridBagLayout1);
    border1 = BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 151));
    mLblOff.setText(StrRes.getStr("IDS_COMMON_STOP"));
    mLblOff.setIcon(new ColorIcon(ColorRes.OFF));
    mLblOn.setText(StrRes.getStr("IDS_COMMON_START"));
    mLblOn.setIcon(new ColorIcon(ColorRes.ON));
    mLblErr.setText(StrRes.getStr("IDS_COMMON_ERR_LEVEL_5"));
    mLblErr.setIcon(new ColorIcon(ColorRes.ERROR));
    mLblCom.setText(StrRes.getStr("IDS_COMMON_COMM_ERR"));
    mLblCom.setIcon(new ColorIcon(ColorRes.COM_ERROR));
    mBtn.setText(StrRes.getStr("IDS_COMMON_EXAMPLE"));
    mBtn.addActionListener(new PanelExample_mBtn_actionAdapter(this));
    setBorder(border1);
    add(mLblOff, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.25D, 17, 0, new Insets(3, 3, 3, 3), 0, 0));
    add(mLblOn, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.25D, 17, 0, new Insets(3, 3, 3, 3), 0, 0));
    add(mLblErr, new GridBagConstraints(0, 3, 1, 1, 0.0D, 0.25D, 17, 0, new Insets(3, 3, 3, 3), 0, 0));
    add(mLblCom, new GridBagConstraints(0, 4, 1, 1, 0.0D, 0.25D, 17, 0, new Insets(3, 3, 3, 3), 0, 0));
    add(mBtn, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(5, 3, 5, 3), 0, 0));
  }
  
  void mBtn_actionPerformed(ActionEvent paramActionEvent)
  {
    DlgExample localDlgExample = new DlgExample();
    localDlgExample.doModal();
  }
}
