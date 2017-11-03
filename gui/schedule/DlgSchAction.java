package webitc.gui.schedule;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import webitc.common.StrRes;
import webitc.data.schedule.AbstSchedule;
import webitc.data.schedule.Program;
import webitc.data.schedule.SchException;
import webitc.gui.common.DlgAbstract;

class DlgSchAction
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JButton mBtnCancel = new JButton();
  JButton mBtnOK = new JButton();
  private PanelActionSelect mPnlActionSelect = new PanelActionSelect();
  JPanel mPnlOKCancel = new JPanel();
  private Program mProgram;
  
  public DlgSchAction(Program paramProgram, int paramInt)
  {
    mProgram = paramProgram;
    try
    {
      jbInit();
      partsInit(paramInt);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void errorPerformed()
  {
    mPnlActionSelect.errorPerformed();
    mBtnOK.setEnabled(false);
  }
  
  private void jbInit()
    throws Exception
  {
    setTitle(StrRes.getStr("IDS_SCH_CHANGE_PATTERN"));
    getContentPane().setLayout(gridBagLayout1);
    mBtnOK.setText(StrRes.getStr("IDS_COMMON_OK"));
    mBtnOK.addActionListener(new DlgSchAction_mBtnOK_actionAdapter(this));
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mBtnCancel.addActionListener(new DlgSchAction_mBtnCancel_actionAdapter(this));
    mPnlOKCancel.setLayout(gridBagLayout2);
    mPnlOKCancel.add(mBtnCancel, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 5, 0, 5), 0, 0));
    getContentPane().add(mPnlOKCancel, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 14, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlOKCancel.add(mBtnOK, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 5, 0, 5), 0, 0));
    getContentPane().add(mPnlActionSelect, new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  void mBtnCancel_actionPerformed(ActionEvent paramActionEvent)
  {
    closeDialog(false);
  }
  
  void mBtnOK_actionPerformed(ActionEvent paramActionEvent)
  {
    mPnlActionSelect.saveCurrentDaily();
    mProgram.setWeekly(mPnlActionSelect.getWeekly());
    mProgram.setException(mPnlActionSelect.getException());
    closeDialog(true);
  }
  
  private void partsInit(int paramInt)
  {
    mPnlActionSelect.setSchedule((AbstSchedule)mProgram.getWeekly().clone(), (SchException)mProgram.getException().clone(), paramInt);
  }
}
