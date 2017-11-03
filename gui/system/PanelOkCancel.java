package webitc.gui.system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import webitc.common.StrRes;
import webitc.gui.common.PanelAbstract;

class PanelOkCancel
  extends PanelAbstract
{
  public static final int CANCEL = 2;
  public static final int OK = 1;
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JButton mCancel = new JButton();
  JLabel mDummy = new JLabel();
  private PanelOkCancelListener mListener = null;
  private JButton mOk = new JButton();
  
  public PanelOkCancel(PanelOkCancelListener paramPanelOkCancelListener)
  {
    try
    {
      jbInit();
      mListener = paramPanelOkCancelListener;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void jbInit()
    throws Exception
  {
    mOk.setText(StrRes.getStr("IDS_COMMON_OK"));
    mOk.addActionListener(new PanelOkCancel_mOk_actionAdapter(this));
    mCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mCancel.addActionListener(new PanelOkCancel_mCancel_actionAdapter(this));
    setLayout(gridBagLayout1);
    add(mOk, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(10, 0, 0, 10), 0, 0));
    add(mCancel, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(10, 10, 0, 0), 0, 0));
    add(mDummy, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  void mCancel_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyCancelSelected();
    }
  }
  
  void mOk_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyOkSelected();
    }
  }
  
  public void setEnabled(int paramInt, boolean paramBoolean)
  {
    if (paramInt == 1) {
      mOk.setEnabled(paramBoolean);
    }
    if (paramInt == 2) {
      mCancel.setEnabled(paramBoolean);
    }
  }
  
  static abstract interface PanelOkCancelListener
  {
    public abstract void notifyCancelSelected();
    
    public abstract void notifyOkSelected();
  }
}
