package webitc.gui.ppd;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import webitc.common.StrRes;
import webitc.gui.common.PanelAbstract;

class PanelSetting
  extends PanelAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JComboBox mCmbEndHour = new JComboBox();
  JComboBox mCmbEndMin = new JComboBox();
  JComboBox mCmbStartHour = new JComboBox();
  JComboBox mCmbStartMin = new JComboBox();
  JLabel mLblColon1 = new JLabel();
  JLabel mLblColon2 = new JLabel();
  JLabel mLblEnd = new JLabel();
  JLabel mLblStart = new JLabel();
  JLabel mLblWDay = new JLabel();
  JRadioButton mRadioOff = new JRadioButton();
  JRadioButton mRadioOn = new JRadioButton();
  String mStrWDay = "";
  
  public PanelSetting(String paramString)
  {
    try
    {
      mStrWDay = paramString;
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void errorPerformed()
  {
    mRadioOn.setEnabled(false);
    mRadioOff.setEnabled(false);
    mCmbStartHour.setEnabled(false);
    mCmbStartMin.setEnabled(false);
    mCmbEndHour.setEnabled(false);
    mCmbEndMin.setEnabled(false);
  }
  
  public PpdPeriod getSchedule()
  {
    PpdPeriod localPpdPeriod = new PpdPeriod(false);
    localPpdPeriod.setStartHour((char)mCmbStartHour.getSelectedIndex());
    localPpdPeriod.setStartMin((char)mCmbStartMin.getSelectedIndex());
    localPpdPeriod.setEndHour((char)mCmbEndHour.getSelectedIndex());
    localPpdPeriod.setEndMin((char)mCmbEndMin.getSelectedIndex());
    return localPpdPeriod;
  }
  
  public boolean isEnableSchedule()
  {
    return mRadioOn.isSelected();
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    mLblWDay.setText(mStrWDay);
    mRadioOn.setText(StrRes.getStr("IDS_COMMON_VALID"));
    mRadioOff.setText(StrRes.getStr("IDS_COMMON_INVALID"));
    mLblColon1.setText(StrRes.getStr("IDS_COMMON_SEPARATER_COLON"));
    mLblStart.setText(StrRes.getStr("IDS_PPDEXCLUSIONPERIOD_START"));
    mLblEnd.setText(StrRes.getStr("IDS_PPDEXCLUSIONPERIOD_END"));
    mLblColon2.setText(StrRes.getStr("IDS_COMMON_SEPARATER_COLON"));
    mRadioOn.addActionListener(new DlgSetExclusionPeriod_mRadioOn_actionAdapter(this));
    mRadioOff.addActionListener(new DlgSetExclusionPeriod_mRadioOff_actionAdapter(this));
    add(mLblWDay, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 10, 0, 10), 0, 0));
    add(mRadioOn, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 5), 0, 0));
    add(mRadioOff, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 20), 0, 0));
    add(mLblStart, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mCmbStartHour, new GridBagConstraints(4, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mLblColon1, new GridBagConstraints(5, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mCmbStartMin, new GridBagConstraints(6, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 30), 0, 0));
    add(mLblEnd, new GridBagConstraints(7, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mCmbEndHour, new GridBagConstraints(8, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mLblColon2, new GridBagConstraints(9, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mCmbEndMin, new GridBagConstraints(10, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    ButtonGroup localButtonGroup = new ButtonGroup();
    localButtonGroup.add(mRadioOn);
    localButtonGroup.add(mRadioOff);
    for (int i = 0; i < 24; i++)
    {
      mCmbStartHour.addItem(String.valueOf(i));
      mCmbEndHour.addItem(String.valueOf(i));
    }
    for (int j = 0; j < 60; j++)
    {
      mCmbStartMin.addItem(String.valueOf(j));
      mCmbEndMin.addItem(String.valueOf(j));
    }
    mRadioOff.setSelected(true);
    mCmbStartHour.setSelectedIndex(0);
    mCmbEndHour.setSelectedIndex(0);
    mCmbStartMin.setSelectedIndex(0);
    mCmbEndMin.setSelectedIndex(0);
    update();
  }
  
  void mRadioOff_actionPerformed(ActionEvent paramActionEvent)
  {
    update();
  }
  
  void mRadioOn_actionPerformed(ActionEvent paramActionEvent)
  {
    update();
  }
  
  public void setSchedule(boolean paramBoolean, PpdPeriod paramPpdPeriod)
  {
    if (paramBoolean) {
      mRadioOn.setSelected(true);
    } else {
      mRadioOff.setSelected(true);
    }
    update();
    if (paramPpdPeriod == null) {
      return;
    }
    mCmbStartHour.setSelectedIndex(paramPpdPeriod.getStartHour());
    mCmbStartMin.setSelectedIndex(paramPpdPeriod.getStartMin());
    mCmbEndHour.setSelectedIndex(paramPpdPeriod.getEndHour());
    mCmbEndMin.setSelectedIndex(paramPpdPeriod.getEndMin());
  }
  
  private void update()
  {
    boolean bool = mRadioOn.isSelected();
    mLblStart.setEnabled(bool);
    mLblEnd.setEnabled(bool);
    mLblColon1.setEnabled(bool);
    mLblColon2.setEnabled(bool);
    mCmbStartHour.setEnabled(bool);
    mCmbStartMin.setEnabled(bool);
    mCmbEndHour.setEnabled(bool);
    mCmbEndMin.setEnabled(bool);
  }
  
  class DlgSetExclusionPeriod_mRadioOff_actionAdapter
    implements ActionListener
  {
    PanelSetting adaptee;
    
    DlgSetExclusionPeriod_mRadioOff_actionAdapter(PanelSetting paramPanelSetting)
    {
      adaptee = paramPanelSetting;
    }
    
    public void actionPerformed(ActionEvent paramActionEvent)
    {
      adaptee.mRadioOff_actionPerformed(paramActionEvent);
    }
  }
  
  class DlgSetExclusionPeriod_mRadioOn_actionAdapter
    implements ActionListener
  {
    PanelSetting adaptee;
    
    DlgSetExclusionPeriod_mRadioOn_actionAdapter(PanelSetting paramPanelSetting)
    {
      adaptee = paramPanelSetting;
    }
    
    public void actionPerformed(ActionEvent paramActionEvent)
    {
      adaptee.mRadioOn_actionPerformed(paramActionEvent);
    }
  }
}
