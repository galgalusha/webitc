package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;
import webitc.gui.common.ColorRes;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.SimpleButtonRes;

public class PanelTransmit
  extends PanelAbstract
{
  public static final int BTN_ALL = 2;
  public static final int BTN_CANCEL = 1;
  public static final int BTN_TRANSMIT = 0;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  AbstractButton mBtnCancel = SimpleButtonRes.getButton(12);
  AbstractButton mBtnSubmit = SimpleButtonRes.getButton(11);
  private PanelTransmitListener mListener = null;
  
  public PanelTransmit()
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
    setMinimumSize(new Dimension(150, 40));
    setPreferredSize(new Dimension(150, 40));
    setLayout(gridBagLayout1);
    mBtnSubmit.addActionListener(new PanelTransmit_mBtnSubmit_actionAdapter(this));
    mBtnCancel.addActionListener(new PanelTransmit_mBtnCancel_actionAdapter(this));
    add(mBtnSubmit, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 5), 0, 0));
    add(mBtnCancel, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 35, 0, 0), 0, 0));
  }
  
  void mBtnCancel_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyCancel();
    }
  }
  
  void mBtnSubmit_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyTransmit();
    }
  }
  
  public void setEnabled(boolean paramBoolean, int paramInt)
  {
    if (paramInt == 0)
    {
      mBtnSubmit.setEnabled(paramBoolean);
    }
    else if (paramInt == 1)
    {
      mBtnCancel.setEnabled(paramBoolean);
    }
    else
    {
      mBtnSubmit.setEnabled(paramBoolean);
      mBtnCancel.setEnabled(paramBoolean);
    }
  }
  
  public void setListener(PanelTransmitListener paramPanelTransmitListener)
  {
    mListener = paramPanelTransmitListener;
  }
  
  public static abstract interface PanelTransmitListener
  {
    public abstract void notifyCancel();
    
    public abstract void notifyTransmit();
  }
}
