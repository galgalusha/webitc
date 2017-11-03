package webitc.gui.login;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumLang;
import webitc.gui.common.DlgAbstract;

class DlgLang
  extends DlgAbstract
{
  Border border1;
  ButtonGroup buttonGroup1 = new ButtonGroup();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  JPanel jPanel1 = new JPanel();
  JButton mBtnCancel = new JButton();
  JButton mBtnLeft = new JButton();
  JButton mBtnOK = new JButton();
  JButton mBtnRight = new JButton();
  private EnumLang mDispLang;
  JPanel mPnlLang = new JPanel();
  JRadioButton mRdoDe = new JRadioButton();
  JRadioButton mRdoEn = new JRadioButton();
  JRadioButton mRdoEs = new JRadioButton();
  JRadioButton mRdoFr = new JRadioButton();
  JRadioButton mRdoIt = new JRadioButton();
  JRadioButton mRdoJa = new JRadioButton();
  JRadioButton mRdoKr = new JRadioButton();
  JRadioButton mRdoZh = new JRadioButton();
  JTextField mTxtLang = new JTextField();
  
  public DlgLang()
  {
    try
    {
      jbInit();
      resetText();
      partsInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void errorPerformed()
  {
    mRdoJa.setEnabled(false);
    mRdoZh.setEnabled(false);
    mRdoEn.setEnabled(false);
    mRdoFr.setEnabled(false);
    mRdoDe.setEnabled(false);
    mRdoIt.setEnabled(false);
    mRdoEs.setEnabled(false);
    mRdoKr.setEnabled(false);
    mBtnOK.setEnabled(false);
  }
  
  private void jbInit()
    throws Exception
  {
    border1 = BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 151));
    getContentPane().setLayout(gridBagLayout1);
    mBtnOK.addActionListener(new DlgLang_mBtnOK_actionAdapter(this));
    mBtnCancel.addActionListener(new DlgLang_mBtnCancel_actionAdapter(this));
    mPnlLang.setLayout(gridBagLayout2);
    mPnlLang.setBorder(border1);
    mBtnLeft.addActionListener(new DlgLang_mBtnLeft_actionAdapter(this));
    mBtnRight.addActionListener(new DlgLang_mBtnRight_actionAdapter(this));
    jPanel1.setLayout(gridBagLayout3);
    mTxtLang.setEditable(false);
    getContentPane().add(mPnlLang, new GridBagConstraints(1, 1, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 25, 0));
    mPnlLang.add(mRdoJa, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    mPnlLang.add(mRdoZh, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    mPnlLang.add(mRdoEn, new GridBagConstraints(0, 2, 1, 1, 1.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    mPnlLang.add(mRdoFr, new GridBagConstraints(0, 3, 1, 1, 1.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    mPnlLang.add(mRdoDe, new GridBagConstraints(0, 4, 1, 1, 1.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    mPnlLang.add(mRdoIt, new GridBagConstraints(0, 5, 1, 1, 1.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    mPnlLang.add(mRdoEs, new GridBagConstraints(0, 6, 1, 1, 1.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    mPnlLang.add(mRdoKr, new GridBagConstraints(0, 7, 1, 1, 1.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    getContentPane().add(mBtnLeft, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(5, 5, 5, 10), 12, 0));
    getContentPane().add(mBtnRight, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 12), 12, 0));
    getContentPane().add(mTxtLang, new GridBagConstraints(1, 0, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(jPanel1, new GridBagConstraints(1, 2, 2, 1, 0.0D, 0.0D, 13, 0, new Insets(10, 0, 0, 0), 0, 0));
    jPanel1.add(mBtnOK, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 6, 5), 0, 0));
    jPanel1.add(mBtnCancel, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 6, 12), 0, 0));
    buttonGroup1.add(mRdoJa);
    buttonGroup1.add(mRdoZh);
    buttonGroup1.add(mRdoEn);
    buttonGroup1.add(mRdoFr);
    buttonGroup1.add(mRdoDe);
    buttonGroup1.add(mRdoIt);
    buttonGroup1.add(mRdoEs);
    buttonGroup1.add(mRdoKr);
  }
  
  void mBtnCancel_actionPerformed()
  {
    SystemInfo.setLang(mDispLang);
    closeDialog(false);
  }
  
  void mBtnLeft_actionPerformed()
  {
    EnumLang localEnumLang = SystemInfo.getLang();
    if (localEnumLang == EnumLang.JAPANESE) {
      SystemInfo.setLang(EnumLang.KOREAN);
    } else if (localEnumLang == EnumLang.CHINESE) {
      SystemInfo.setLang(EnumLang.JAPANESE);
    } else if (localEnumLang == EnumLang.ENGLISH) {
      SystemInfo.setLang(EnumLang.CHINESE);
    } else if (localEnumLang == EnumLang.FRENCH) {
      SystemInfo.setLang(EnumLang.ENGLISH);
    } else if (localEnumLang == EnumLang.GERMAN) {
      SystemInfo.setLang(EnumLang.FRENCH);
    } else if (localEnumLang == EnumLang.ITALIAN) {
      SystemInfo.setLang(EnumLang.GERMAN);
    } else if (localEnumLang == EnumLang.SPANISH) {
      SystemInfo.setLang(EnumLang.ITALIAN);
    } else if (localEnumLang == EnumLang.KOREAN) {
      SystemInfo.setLang(EnumLang.SPANISH);
    }
    resetText();
  }
  
  void mBtnOK_actionPerformed()
  {
    if (mRdoJa.isSelected() == true) {
      SystemInfo.setLang(EnumLang.JAPANESE);
    } else if (mRdoEn.isSelected() == true) {
      SystemInfo.setLang(EnumLang.ENGLISH);
    } else if (mRdoZh.isSelected() == true) {
      SystemInfo.setLang(EnumLang.CHINESE);
    } else if (mRdoFr.isSelected() == true) {
      SystemInfo.setLang(EnumLang.FRENCH);
    } else if (mRdoDe.isSelected() == true) {
      SystemInfo.setLang(EnumLang.GERMAN);
    } else if (mRdoIt.isSelected() == true) {
      SystemInfo.setLang(EnumLang.ITALIAN);
    } else if (mRdoEs.isSelected() == true) {
      SystemInfo.setLang(EnumLang.SPANISH);
    } else if (mRdoKr.isSelected() == true) {
      SystemInfo.setLang(EnumLang.KOREAN);
    }
    closeDialog(true);
  }
  
  void mBtnRight_actionPerformed()
  {
    EnumLang localEnumLang = SystemInfo.getLang();
    if (localEnumLang == EnumLang.JAPANESE) {
      SystemInfo.setLang(EnumLang.CHINESE);
    } else if (localEnumLang == EnumLang.CHINESE) {
      SystemInfo.setLang(EnumLang.ENGLISH);
    } else if (localEnumLang == EnumLang.ENGLISH) {
      SystemInfo.setLang(EnumLang.FRENCH);
    } else if (localEnumLang == EnumLang.FRENCH) {
      SystemInfo.setLang(EnumLang.GERMAN);
    } else if (localEnumLang == EnumLang.GERMAN) {
      SystemInfo.setLang(EnumLang.ITALIAN);
    } else if (localEnumLang == EnumLang.ITALIAN) {
      SystemInfo.setLang(EnumLang.SPANISH);
    } else if (localEnumLang == EnumLang.SPANISH) {
      SystemInfo.setLang(EnumLang.KOREAN);
    } else if (localEnumLang == EnumLang.KOREAN) {
      SystemInfo.setLang(EnumLang.JAPANESE);
    }
    resetText();
  }
  
  private void partsInit()
  {
    mDispLang = SystemInfo.getLang();
    if (mDispLang == EnumLang.JAPANESE) {
      mRdoJa.setSelected(true);
    } else if (mDispLang == EnumLang.CHINESE) {
      mRdoZh.setSelected(true);
    } else if (mDispLang == EnumLang.ENGLISH) {
      mRdoEn.setSelected(true);
    } else if (mDispLang == EnumLang.FRENCH) {
      mRdoFr.setSelected(true);
    } else if (mDispLang == EnumLang.GERMAN) {
      mRdoDe.setSelected(true);
    } else if (mDispLang == EnumLang.ITALIAN) {
      mRdoIt.setSelected(true);
    } else if (mDispLang == EnumLang.SPANISH) {
      mRdoEs.setSelected(true);
    } else if (mDispLang == EnumLang.KOREAN) {
      mRdoKr.setSelected(true);
    } else {
      throw new FatalException("DlgLang.partsInit");
    }
  }
  
  private void resetText()
  {
    setTitle(StrRes.getStr("IDS_LANG_TITLE"));
    mRdoJa.setText(StrRes.getStr("IDS_COMMON_JAPANESE"));
    mRdoZh.setText(StrRes.getStr("IDS_COMMON_CHINESE"));
    mRdoEn.setText(StrRes.getStr("IDS_COMMON_ENGLISH"));
    mRdoFr.setText(StrRes.getStr("IDS_COMMON_FRENCH"));
    mRdoDe.setText(StrRes.getStr("IDS_COMMON_GERMAN"));
    mRdoIt.setText(StrRes.getStr("IDS_COMMON_ITALIAN"));
    mRdoEs.setText(StrRes.getStr("IDS_COMMON_SPANISH"));
    mRdoKr.setText(StrRes.getStr("IDS_COMMON_KOREAN"));
    mBtnOK.setText(StrRes.getStr("IDS_COMMON_OK"));
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mBtnLeft.setText(StrRes.getStr("IDS_COMMON_LEFT"));
    mBtnRight.setText(StrRes.getStr("IDS_COMMON_RIGHT"));
    mTxtLang.setText(StrRes.getStr("IDS_COMMON_LANGUAGE"));
  }
}
