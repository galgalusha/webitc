package webitc.gui.system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import webitc.gui.common.PanelAbstract;

public class PanelVerticalButtons
  extends PanelAbstract
{
  public static final int BUTTON1 = 1;
  public static final int BUTTON2 = 2;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton mBtn1 = new JButton();
  JButton mBtn2 = new JButton();
  JLabel mLabel1 = null;
  JLabel mLabel2 = null;
  private SelectedListener mListener = null;
  
  public PanelVerticalButtons(SelectedListener paramSelectedListener, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    try
    {
      if (paramString3 != null) {
        mLabel1 = new JLabel(paramString3);
      }
      if (paramString4 != null) {
        mLabel2 = new JLabel(paramString4);
      }
      jbInit();
      mBtn1.setText(paramString1);
      mBtn2.setText(paramString2);
      mListener = paramSelectedListener;
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
    mBtn1.addActionListener(new PanelVerticalButtons_mBtn1_actionAdapter(this));
    mBtn2.addActionListener(new PanelVerticalButtons_mBtn2_actionAdapter(this));
    int i = 0;
    if (mLabel1 != null)
    {
      add(mLabel1, new GridBagConstraints(0, i, 1, 1, 0.0D, 0.5D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
      i++;
    }
    add(mBtn1, new GridBagConstraints(0, i, 1, 1, 0.0D, 0.5D, 15, 0, new Insets(5, 0, 5, 0), 0, 0));
    i++;
    if (mLabel2 != null)
    {
      add(mLabel2, new GridBagConstraints(0, i, 1, 1, 0.0D, 0.5D, 15, 0, new Insets(0, 0, 0, 0), 0, 0));
      i++;
    }
    add(mBtn2, new GridBagConstraints(0, i, 1, 1, 0.0D, 0.5D, 11, 0, new Insets(5, 0, 5, 0), 0, 0));
  }
  
  void mBtn1_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyButton1Selected();
    }
  }
  
  void mBtn2_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyButton2Selected();
    }
  }
  
  public void setEnabled(int paramInt, boolean paramBoolean)
  {
    if (paramInt == 1) {
      mBtn1.setEnabled(paramBoolean);
    }
    if (paramInt == 2) {
      mBtn2.setEnabled(paramBoolean);
    }
  }
  
  static abstract interface SelectedListener
  {
    public abstract void notifyButton1Selected();
    
    public abstract void notifyButton2Selected();
  }
}
