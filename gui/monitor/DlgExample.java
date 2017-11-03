package webitc.gui.monitor;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import webitc.common.StrRes;
import webitc.gui.common.BorderRes;
import webitc.gui.common.ColorIcon;
import webitc.gui.common.ColorRes;
import webitc.gui.common.DlgAbstract;
import webitc.gui.common.IconRes;

public class DlgExample
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  JButton mBtnOK = new JButton();
  JLabel mLblIconAuto = new JLabel();
  JLabel mLblIconComErr = new JLabel();
  JLabel mLblIconDemand = new JLabel();
  JLabel mLblIconErr = new JLabel();
  JLabel mLblIconFilter = new JLabel();
  JLabel mLblIconForceStop = new JLabel();
  JLabel mLblIconOff = new JLabel();
  JLabel mLblIconOn = new JLabel();
  JLabel mLblListAuto = new JLabel();
  JLabel mLblListComErr = new JLabel();
  JLabel mLblListDemand = new JLabel();
  JLabel mLblListErr = new JLabel();
  JLabel mLblListFilter = new JLabel();
  JLabel mLblListForce = new JLabel();
  JLabel mLblListOff = new JLabel();
  JLabel mLblListOn = new JLabel();
  JPanel mPnlIcon = new JPanel();
  JPanel mPnlList = new JPanel();
  
  public DlgExample()
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
    setTitle(StrRes.getStr("IDS_COMMON_EXAMPLE"));
    getContentPane().setLayout(gridBagLayout1);
    mBtnOK.setText(StrRes.getStr("IDS_COMMON_CLOSE"));
    mBtnOK.addActionListener(new DlgExample_mBtnOK_actionAdapter(this));
    mPnlIcon.setLayout(gridBagLayout2);
    mPnlList.setLayout(gridBagLayout3);
    mPnlIcon.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_CCLEGEND_ICON_SAMPLE")));
    mPnlList.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_CCLEGEND_LIST_SAMPLE")));
    mLblIconOff.setText(StrRes.getStr("IDS_COMMON_STOP"));
    mLblIconOff.setIcon(IconRes.getItcIcon(0, 0, 0));
    mLblIconOff.setVerticalTextPosition(3);
    mLblIconOff.setHorizontalTextPosition(0);
    mLblIconOff.setHorizontalAlignment(0);
    mLblIconOff.setIconTextGap(2);
    mLblIconOn.setText(StrRes.getStr("IDS_COMMON_START"));
    mLblIconOn.setIcon(IconRes.getItcIcon(0, 1, 0));
    mLblIconOn.setVerticalTextPosition(3);
    mLblIconOn.setHorizontalTextPosition(0);
    mLblIconOn.setHorizontalAlignment(0);
    mLblIconOn.setIconTextGap(2);
    mLblIconErr.setText(StrRes.getStr("IDS_COMMON_ERR_LEVEL_5"));
    mLblIconErr.setIcon(IconRes.getItcIcon(0, 2, 0));
    mLblIconErr.setVerticalTextPosition(3);
    mLblIconErr.setHorizontalTextPosition(0);
    mLblIconErr.setHorizontalAlignment(0);
    mLblIconErr.setIconTextGap(2);
    mLblIconComErr.setText(StrRes.getStr("IDS_COMMON_COMM_ERR"));
    mLblIconComErr.setIcon(IconRes.getItcIcon(0, 3, 0));
    mLblIconComErr.setVerticalTextPosition(3);
    mLblIconComErr.setHorizontalTextPosition(0);
    mLblIconComErr.setHorizontalAlignment(0);
    mLblIconComErr.setIconTextGap(2);
    mLblIconForceStop.setText(StrRes.getStr("IDS_CCLEGEND_FORCE_STOP"));
    mLblIconForceStop.setIcon(IconRes.getItcIcon(0, 0, 1));
    mLblIconForceStop.setVerticalTextPosition(3);
    mLblIconForceStop.setHorizontalTextPosition(0);
    mLblIconForceStop.setHorizontalAlignment(0);
    mLblIconForceStop.setIconTextGap(2);
    mLblIconDemand.setText(StrRes.getStr("IDS_CCLEGEND_CONTROL"));
    mLblIconDemand.setIcon(IconRes.getItcIcon(0, 0, 2));
    mLblIconDemand.setVerticalTextPosition(3);
    mLblIconDemand.setHorizontalTextPosition(0);
    mLblIconDemand.setHorizontalAlignment(0);
    mLblIconDemand.setIconTextGap(2);
    mLblIconAuto.setText(StrRes.getStr("IDS_CCLEGEND_CTRL_TARGET"));
    mLblIconAuto.setIcon(IconRes.getItcIcon(0, 0, 4));
    mLblIconAuto.setVerticalTextPosition(3);
    mLblIconAuto.setHorizontalTextPosition(0);
    mLblIconAuto.setHorizontalAlignment(0);
    mLblIconAuto.setIconTextGap(2);
    mLblIconFilter.setText(StrRes.getStr("IDS_CCLEGEND_FILTER_SIGN"));
    mLblIconFilter.setIcon(IconRes.getItcIcon(0, 0, 8));
    mLblIconFilter.setVerticalTextPosition(3);
    mLblIconFilter.setHorizontalTextPosition(0);
    mLblIconFilter.setHorizontalAlignment(0);
    mLblIconFilter.setIconTextGap(2);
    mLblListOff.setText(StrRes.getStr("IDS_COMMON_STOP"));
    mLblListOff.setIcon(new ColorIcon(ColorRes.OFF));
    mLblListOn.setText(StrRes.getStr("IDS_COMMON_START"));
    mLblListOn.setIcon(new ColorIcon(ColorRes.ON));
    mLblListErr.setText(StrRes.getStr("IDS_COMMON_ERR_LEVEL_5"));
    mLblListErr.setIcon(new ColorIcon(ColorRes.ERROR));
    mLblListComErr.setText(StrRes.getStr("IDS_COMMON_COMM_ERR"));
    mLblListComErr.setIcon(new ColorIcon(ColorRes.COM_ERROR));
    mLblListForce.setText("S " + StrRes.getStr("IDS_CCLEGEND_FORCE_STOP"));
    mLblListDemand.setText("P " + StrRes.getStr("IDS_CCLEGEND_CONTROL"));
    mLblListAuto.setText("A " + StrRes.getStr("IDS_CCLEGEND_CTRL_TARGET"));
    mLblListFilter.setText("F " + StrRes.getStr("IDS_CCLEGEND_FILTER_SIGN"));
    getContentPane().add(mPnlIcon, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 11, 2, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mPnlList, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 11, 2, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mBtnOK, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 12, 0, new Insets(5, 5, 6, 12), 0, 0));
    mPnlIcon.add(mLblIconOff, new GridBagConstraints(0, 0, 1, 1, 0.25D, 0.0D, 10, 0, new Insets(0, 0, 10, 0), 0, 0));
    mPnlIcon.add(mLblIconOn, new GridBagConstraints(1, 0, 1, 1, 0.25D, 0.0D, 10, 0, new Insets(0, 0, 10, 0), 0, 0));
    mPnlIcon.add(mLblIconErr, new GridBagConstraints(2, 0, 1, 1, 0.25D, 0.0D, 10, 0, new Insets(0, 0, 10, 0), 0, 0));
    mPnlIcon.add(mLblIconComErr, new GridBagConstraints(3, 0, 1, 1, 0.25D, 0.0D, 10, 0, new Insets(0, 0, 10, 0), 0, 0));
    mPnlIcon.add(mLblIconForceStop, new GridBagConstraints(0, 1, 1, 1, 0.25D, 0.0D, 10, 0, new Insets(10, 0, 0, 0), 0, 0));
    mPnlIcon.add(mLblIconDemand, new GridBagConstraints(1, 1, 1, 1, 0.25D, 0.0D, 10, 0, new Insets(10, 0, 0, 0), 0, 0));
    mPnlIcon.add(mLblIconAuto, new GridBagConstraints(2, 1, 1, 1, 0.25D, 0.0D, 10, 0, new Insets(10, 0, 0, 0), 0, 0));
    mPnlIcon.add(mLblIconFilter, new GridBagConstraints(3, 1, 1, 1, 0.25D, 0.0D, 10, 0, new Insets(10, 0, 0, 0), 0, 0));
    mPnlList.add(mLblListOff, new GridBagConstraints(0, 0, 1, 1, 0.25D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlList.add(mLblListOn, new GridBagConstraints(1, 0, 1, 1, 0.25D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlList.add(mLblListErr, new GridBagConstraints(2, 0, 1, 1, 0.25D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlList.add(mLblListComErr, new GridBagConstraints(3, 0, 1, 1, 0.25D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlList.add(mLblListForce, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlList.add(mLblListDemand, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlList.add(mLblListAuto, new GridBagConstraints(2, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlList.add(mLblListFilter, new GridBagConstraints(3, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
  }
  
  void mBtnOK_actionPerformed()
  {
    closeDialog(false);
  }
}
