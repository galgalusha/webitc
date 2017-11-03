package webitc.gui.ppd;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.gui.common.CommonUse;
import webitc.gui.common.DlgAbstract;

public class DlgModifyContractPwr
  extends DlgAbstract
  implements PanelOkCancel.PanelOkCancelListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JCheckBox mBtnMonth1 = new JCheckBox();
  JCheckBox mBtnMonth10 = new JCheckBox();
  JCheckBox mBtnMonth11 = new JCheckBox();
  JCheckBox mBtnMonth12 = new JCheckBox();
  JCheckBox mBtnMonth2 = new JCheckBox();
  JCheckBox mBtnMonth3 = new JCheckBox();
  JCheckBox mBtnMonth4 = new JCheckBox();
  JCheckBox mBtnMonth5 = new JCheckBox();
  JCheckBox mBtnMonth6 = new JCheckBox();
  JCheckBox mBtnMonth7 = new JCheckBox();
  JCheckBox mBtnMonth8 = new JCheckBox();
  JCheckBox mBtnMonth9 = new JCheckBox();
  PanelOkCancel mButtons = new PanelOkCancel(this);
  int[] mContractPwr = null;
  JTextField mEditPower1 = new JTextField();
  JTextField mEditPower10 = new JTextField();
  JTextField mEditPower11 = new JTextField();
  JTextField mEditPower12 = new JTextField();
  JTextField mEditPower2 = new JTextField();
  JTextField mEditPower3 = new JTextField();
  JTextField mEditPower4 = new JTextField();
  JTextField mEditPower5 = new JTextField();
  JTextField mEditPower6 = new JTextField();
  JTextField mEditPower7 = new JTextField();
  JTextField mEditPower8 = new JTextField();
  JTextField mEditPower9 = new JTextField();
  JLabel mLblUnit1 = new JLabel();
  JLabel mLblUnit10 = new JLabel();
  JLabel mLblUnit11 = new JLabel();
  JLabel mLblUnit12 = new JLabel();
  JLabel mLblUnit2 = new JLabel();
  JLabel mLblUnit3 = new JLabel();
  JLabel mLblUnit4 = new JLabel();
  JLabel mLblUnit5 = new JLabel();
  JLabel mLblUnit6 = new JLabel();
  JLabel mLblUnit7 = new JLabel();
  JLabel mLblUnit8 = new JLabel();
  JLabel mLblUnit9 = new JLabel();
  
  public DlgModifyContractPwr(int[] paramArrayOfInt)
  {
    try
    {
      mContractPwr = paramArrayOfInt;
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private int checkContractPwr(JTextField paramJTextField)
    throws Exception
  {
    int i = Integer.parseInt(paramJTextField.getText());
    if ((i < 0) || (i > 9999999)) {
      throw new Exception();
    }
    return i;
  }
  
  public void errorPerformed()
  {
    mBtnMonth1.setEnabled(false);
    mBtnMonth2.setEnabled(false);
    mBtnMonth3.setEnabled(false);
    mBtnMonth4.setEnabled(false);
    mBtnMonth5.setEnabled(false);
    mBtnMonth6.setEnabled(false);
    mBtnMonth7.setEnabled(false);
    mBtnMonth8.setEnabled(false);
    mBtnMonth9.setEnabled(false);
    mBtnMonth10.setEnabled(false);
    mBtnMonth11.setEnabled(false);
    mBtnMonth12.setEnabled(false);
    mEditPower1.setEnabled(false);
    mEditPower2.setEnabled(false);
    mEditPower3.setEnabled(false);
    mEditPower4.setEnabled(false);
    mEditPower5.setEnabled(false);
    mEditPower6.setEnabled(false);
    mEditPower7.setEnabled(false);
    mEditPower8.setEnabled(false);
    mEditPower9.setEnabled(false);
    mEditPower10.setEnabled(false);
    mEditPower11.setEnabled(false);
    mEditPower12.setEnabled(false);
    mButtons.setEnabled(1, false);
  }
  
  public int[] getContractPwr()
  {
    return mContractPwr;
  }
  
  private void jbInit()
    throws Exception
  {
    setTitle(StrRes.getStr("IDS_PPDCOLLECTION_CONTRACT_PWR"));
    if (mContractPwr.length != 12) {
      throw new FatalException("DlgModifyContractPwr init failed");
    }
    mBtnMonth1.setText(StrRes.getStr("IDS_COMMON_MONTH_01"));
    mBtnMonth1.addActionListener(new DlgModifyContractPwr_mBtnMonth1_actionAdapter(this));
    mBtnMonth2.setText(StrRes.getStr("IDS_COMMON_MONTH_02"));
    mBtnMonth2.addActionListener(new DlgModifyContractPwr_mBtnMonth2_actionAdapter(this));
    mBtnMonth3.setText(StrRes.getStr("IDS_COMMON_MONTH_03"));
    mBtnMonth3.addActionListener(new DlgModifyContractPwr_mBtnMonth3_actionAdapter(this));
    mBtnMonth4.setText(StrRes.getStr("IDS_COMMON_MONTH_04"));
    mBtnMonth4.addActionListener(new DlgModifyContractPwr_mBtnMonth4_actionAdapter(this));
    mBtnMonth5.setText(StrRes.getStr("IDS_COMMON_MONTH_05"));
    mBtnMonth5.addActionListener(new DlgModifyContractPwr_mBtnMonth5_actionAdapter(this));
    mBtnMonth6.setText(StrRes.getStr("IDS_COMMON_MONTH_06"));
    mBtnMonth6.addActionListener(new DlgModifyContractPwr_mBtnMonth6_actionAdapter(this));
    mBtnMonth7.setText(StrRes.getStr("IDS_COMMON_MONTH_07"));
    mBtnMonth7.addActionListener(new DlgModifyContractPwr_mBtnMonth7_actionAdapter(this));
    mBtnMonth8.setText(StrRes.getStr("IDS_COMMON_MONTH_08"));
    mBtnMonth8.addActionListener(new DlgModifyContractPwr_mBtnMonth8_actionAdapter(this));
    mBtnMonth9.setText(StrRes.getStr("IDS_COMMON_MONTH_09"));
    mBtnMonth9.addActionListener(new DlgModifyContractPwr_mBtnMonth9_actionAdapter(this));
    mBtnMonth10.setText(StrRes.getStr("IDS_COMMON_MONTH_10"));
    mBtnMonth10.addActionListener(new DlgModifyContractPwr_mBtnMonth10_actionAdapter(this));
    mBtnMonth11.setText(StrRes.getStr("IDS_COMMON_MONTH_11"));
    mBtnMonth11.addActionListener(new DlgModifyContractPwr_mBtnMonth11_actionAdapter(this));
    mBtnMonth12.setText(StrRes.getStr("IDS_COMMON_MONTH_12"));
    mBtnMonth12.addActionListener(new DlgModifyContractPwr_mBtnMonth12_actionAdapter(this));
    mEditPower1.setText(String.valueOf(mContractPwr[0]));
    mEditPower2.setText(String.valueOf(mContractPwr[1]));
    mEditPower3.setText(String.valueOf(mContractPwr[2]));
    mEditPower4.setText(String.valueOf(mContractPwr[3]));
    mEditPower5.setText(String.valueOf(mContractPwr[4]));
    mEditPower6.setText(String.valueOf(mContractPwr[5]));
    mEditPower7.setText(String.valueOf(mContractPwr[6]));
    mEditPower8.setText(String.valueOf(mContractPwr[7]));
    mEditPower9.setText(String.valueOf(mContractPwr[8]));
    mEditPower10.setText(String.valueOf(mContractPwr[9]));
    mEditPower11.setText(String.valueOf(mContractPwr[10]));
    mEditPower12.setText(String.valueOf(mContractPwr[11]));
    mEditPower1.setPreferredSize(new Dimension(100, 20));
    mEditPower2.setPreferredSize(new Dimension(100, 20));
    mEditPower3.setPreferredSize(new Dimension(100, 20));
    mEditPower4.setPreferredSize(new Dimension(100, 20));
    mEditPower5.setPreferredSize(new Dimension(100, 20));
    mEditPower6.setPreferredSize(new Dimension(100, 20));
    mEditPower7.setPreferredSize(new Dimension(100, 20));
    mEditPower8.setPreferredSize(new Dimension(100, 20));
    mEditPower9.setPreferredSize(new Dimension(100, 20));
    mEditPower10.setPreferredSize(new Dimension(100, 20));
    mEditPower11.setPreferredSize(new Dimension(100, 20));
    mEditPower12.setPreferredSize(new Dimension(100, 20));
    mEditPower1.setEditable(false);
    mEditPower2.setEditable(false);
    mEditPower3.setEditable(false);
    mEditPower4.setEditable(false);
    mEditPower5.setEditable(false);
    mEditPower6.setEditable(false);
    mEditPower7.setEditable(false);
    mEditPower8.setEditable(false);
    mEditPower9.setEditable(false);
    mEditPower10.setEditable(false);
    mEditPower11.setEditable(false);
    mEditPower12.setEditable(false);
    mLblUnit1.setText(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
    mLblUnit2.setText(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
    mLblUnit3.setText(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
    mLblUnit4.setText(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
    mLblUnit5.setText(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
    mLblUnit6.setText(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
    mLblUnit7.setText(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
    mLblUnit8.setText(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
    mLblUnit9.setText(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
    mLblUnit10.setText(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
    mLblUnit11.setText(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
    mLblUnit12.setText(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
    getContentPane().setLayout(gridBagLayout1);
    getContentPane().add(mBtnMonth1, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mEditPower1, new GridBagConstraints(1, 0, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblUnit1, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mBtnMonth2, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mEditPower2, new GridBagConstraints(1, 1, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblUnit2, new GridBagConstraints(2, 1, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mBtnMonth3, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mEditPower3, new GridBagConstraints(1, 2, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblUnit3, new GridBagConstraints(2, 2, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mBtnMonth4, new GridBagConstraints(0, 3, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mEditPower4, new GridBagConstraints(1, 3, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblUnit4, new GridBagConstraints(2, 3, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mBtnMonth5, new GridBagConstraints(0, 4, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mEditPower5, new GridBagConstraints(1, 4, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblUnit5, new GridBagConstraints(2, 4, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mBtnMonth6, new GridBagConstraints(0, 5, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mEditPower6, new GridBagConstraints(1, 5, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblUnit6, new GridBagConstraints(2, 5, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mBtnMonth7, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mEditPower7, new GridBagConstraints(4, 0, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblUnit7, new GridBagConstraints(5, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 10), 0, 0));
    getContentPane().add(mBtnMonth8, new GridBagConstraints(3, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mEditPower8, new GridBagConstraints(4, 1, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblUnit8, new GridBagConstraints(5, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 10), 0, 0));
    getContentPane().add(mBtnMonth9, new GridBagConstraints(3, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mEditPower9, new GridBagConstraints(4, 2, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblUnit9, new GridBagConstraints(5, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 10), 0, 0));
    getContentPane().add(mBtnMonth10, new GridBagConstraints(3, 3, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mEditPower10, new GridBagConstraints(4, 3, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblUnit10, new GridBagConstraints(5, 3, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 10), 0, 0));
    getContentPane().add(mBtnMonth11, new GridBagConstraints(3, 4, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mEditPower11, new GridBagConstraints(4, 4, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblUnit11, new GridBagConstraints(5, 4, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 10), 0, 0));
    getContentPane().add(mBtnMonth12, new GridBagConstraints(3, 5, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mEditPower12, new GridBagConstraints(4, 5, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblUnit12, new GridBagConstraints(5, 5, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 10), 0, 0));
    getContentPane().add(mButtons, new GridBagConstraints(3, 6, 3, 1, 0.0D, 0.0D, 14, 0, new Insets(10, 10, 10, 10), 0, 0));
  }
  
  void mBtnMonth10_actionPerformed(ActionEvent paramActionEvent)
  {
    mEditPower10.setEditable(mBtnMonth10.isSelected());
  }
  
  void mBtnMonth11_actionPerformed(ActionEvent paramActionEvent)
  {
    mEditPower11.setEditable(mBtnMonth11.isSelected());
  }
  
  void mBtnMonth12_actionPerformed(ActionEvent paramActionEvent)
  {
    mEditPower12.setEditable(mBtnMonth12.isSelected());
  }
  
  void mBtnMonth1_actionPerformed(ActionEvent paramActionEvent)
  {
    mEditPower1.setEditable(mBtnMonth1.isSelected());
  }
  
  void mBtnMonth2_actionPerformed(ActionEvent paramActionEvent)
  {
    mEditPower2.setEditable(mBtnMonth2.isSelected());
  }
  
  void mBtnMonth3_actionPerformed(ActionEvent paramActionEvent)
  {
    mEditPower3.setEditable(mBtnMonth3.isSelected());
  }
  
  void mBtnMonth4_actionPerformed(ActionEvent paramActionEvent)
  {
    mEditPower4.setEditable(mBtnMonth4.isSelected());
  }
  
  void mBtnMonth5_actionPerformed(ActionEvent paramActionEvent)
  {
    mEditPower5.setEditable(mBtnMonth5.isSelected());
  }
  
  void mBtnMonth6_actionPerformed(ActionEvent paramActionEvent)
  {
    mEditPower6.setEditable(mBtnMonth6.isSelected());
  }
  
  void mBtnMonth7_actionPerformed(ActionEvent paramActionEvent)
  {
    mEditPower7.setEditable(mBtnMonth7.isSelected());
  }
  
  void mBtnMonth8_actionPerformed(ActionEvent paramActionEvent)
  {
    mEditPower8.setEditable(mBtnMonth8.isSelected());
  }
  
  void mBtnMonth9_actionPerformed(ActionEvent paramActionEvent)
  {
    mEditPower9.setEditable(mBtnMonth9.isSelected());
  }
  
  public void notifyCancelSelected()
  {
    closeDialog(false);
  }
  
  public void notifyOkSelected()
  {
    try
    {
      mContractPwr[0] = checkContractPwr(mEditPower1);
      mContractPwr[1] = checkContractPwr(mEditPower2);
      mContractPwr[2] = checkContractPwr(mEditPower3);
      mContractPwr[3] = checkContractPwr(mEditPower4);
      mContractPwr[4] = checkContractPwr(mEditPower5);
      mContractPwr[5] = checkContractPwr(mEditPower6);
      mContractPwr[6] = checkContractPwr(mEditPower7);
      mContractPwr[7] = checkContractPwr(mEditPower8);
      mContractPwr[8] = checkContractPwr(mEditPower9);
      mContractPwr[9] = checkContractPwr(mEditPower10);
      mContractPwr[10] = checkContractPwr(mEditPower11);
      mContractPwr[11] = checkContractPwr(mEditPower12);
    }
    catch (Exception localException)
    {
      String[] arrayOfString = new String[2];
      arrayOfString[0] = StrRes.getStr("IDS_COMMON_OUT_OF_RANGE");
      arrayOfString[1] = StrRes.getStr("IDS_UTILTIMEYMD_DATE");
      int tmp208_207 = 1;
      String[] tmp208_206 = arrayOfString;
      tmp208_206[tmp208_207] = (tmp208_206[tmp208_207] + StrRes.getStr("IDS_COMMON_SEPARATER_COLON"));
      int tmp234_233 = 1;
      String[] tmp234_232 = arrayOfString;
      tmp234_232[tmp234_233] = (tmp234_232[tmp234_233] + String.valueOf(0));
      int tmp259_258 = 1;
      String[] tmp259_257 = arrayOfString;
      tmp259_257[tmp259_258] = (tmp259_257[tmp259_258] + StrRes.getStr("IDS_KBD_SYM_ON_0"));
      int tmp285_284 = 1;
      String[] tmp285_283 = arrayOfString;
      tmp285_283[tmp285_284] = (tmp285_283[tmp285_284] + String.valueOf(9999999));
      CommonUse.showErrorDlg(arrayOfString, StrRes.getStr("IDS_PPDCOLLECTION_CONTRACT_PWR"));
      return;
    }
    closeDialog(true);
  }
}
