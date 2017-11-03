package webitc.gui.ppd;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import webitc.common.StrRes;
import webitc.common.enum2.EnumPpdCollectionType;
import webitc.gui.common.PanelAbstract;

public class PanelRadioButtons
  extends PanelAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private RadioButtonsListener mListener = null;
  JRadioButton mPoint = new JRadioButton();
  JRadioButton mSum = new JRadioButton();
  JRadioButton mZone = new JRadioButton();
  
  public PanelRadioButtons(RadioButtonsListener paramRadioButtonsListener)
  {
    try
    {
      mListener = paramRadioButtonsListener;
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public EnumPpdCollectionType getSelectedValue()
  {
    if (mPoint.isSelected()) {
      return EnumPpdCollectionType.INNER;
    }
    if (mZone.isSelected()) {
      return EnumPpdCollectionType.ZONE;
    }
    return EnumPpdCollectionType.SUM;
  }
  
  private void jbInit()
    throws Exception
  {
    mPoint.setText(StrRes.getStr("IDS_COMMON_IU"));
    mPoint.addActionListener(new PanelRadioButtons_mPoint_actionAdapter(this));
    mZone.setText(StrRes.getStr("IDS_COMMON_ZONE"));
    mZone.addActionListener(new PanelRadioButtons_mZone_actionAdapter(this));
    mSum.setText(StrRes.getStr("IDS_PPDRESULTOUTPUT_ALL"));
    mSum.addActionListener(new PanelRadioButtons_mSums_actionAdapter(this));
    setLayout(gridBagLayout1);
    add(mPoint, new GridBagConstraints(0, 0, 1, 1, 0.3D, 1.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mZone, new GridBagConstraints(1, 0, 1, 1, 0.3D, 1.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mSum, new GridBagConstraints(2, 0, 1, 1, 0.3D, 1.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    ButtonGroup localButtonGroup = new ButtonGroup();
    localButtonGroup.add(mPoint);
    localButtonGroup.add(mZone);
    localButtonGroup.add(mSum);
    mPoint.setSelected(true);
  }
  
  void mPoint_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyPointSelected();
    }
  }
  
  void mSums_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifySumSelected();
    }
  }
  
  void mZone_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyZoneSelected();
    }
  }
  
  public void setEnable(boolean paramBoolean)
  {
    mPoint.setEnabled(paramBoolean);
    mZone.setEnabled(paramBoolean);
    mSum.setEnabled(paramBoolean);
  }
  
  static abstract interface RadioButtonsListener
  {
    public abstract void notifyPointSelected();
    
    public abstract void notifySumSelected();
    
    public abstract void notifyZoneSelected();
  }
}
