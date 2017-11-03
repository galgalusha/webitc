package webitc.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import webitc.common.StrRes;
import webitc.gui.common.PanelAbstract;

public class PanelTime
  extends PanelAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JComboBox mCmbHour = new JComboBox();
  JComboBox mCmbMin = new JComboBox();
  JLabel mLblHour = new JLabel();
  JLabel mLblMin = new JLabel();
  protected ArrayList mListenerList = new ArrayList();
  
  public PanelTime()
  {
    try
    {
      jbInit();
      partsInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void addListener(PanelTimeListener paramPanelTimeListener)
  {
    mListenerList.add(paramPanelTimeListener);
  }
  
  public int getHour()
  {
    return mCmbHour.getSelectedIndex();
  }
  
  public int getMin()
  {
    return mCmbMin.getSelectedIndex();
  }
  
  private void hourSelected()
  {
    for (int i = 0; i < mListenerList.size(); i++)
    {
      PanelTimeListener localPanelTimeListener = (PanelTimeListener)mListenerList.get(i);
      localPanelTimeListener.hourSelected();
    }
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    mLblHour.setText(StrRes.getStr("IDS_COMMON_SEPARATER_COLON"));
    mLblMin.setText("");
    add(mCmbHour, new GridBagConstraints(0, 0, 1, 0, 0.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    add(mLblHour, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    add(mCmbMin, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    add(mLblMin, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    mCmbHour.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        PanelTime.this.hourSelected();
      }
    });
    mCmbMin.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        PanelTime.this.minSelected();
      }
    });
  }
  
  private void minSelected()
  {
    for (int i = 0; i < mListenerList.size(); i++)
    {
      PanelTimeListener localPanelTimeListener = (PanelTimeListener)mListenerList.get(i);
      localPanelTimeListener.minSelected();
    }
  }
  
  private void partsInit()
  {
    for (int i = 0; i < 24; i++) {
      mCmbHour.addItem("    " + Integer.toString(i) + "    ");
    }
    for (int j = 0; j < 60; j++) {
      mCmbMin.addItem("    " + Integer.toString(j) + "    ");
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    mCmbHour.setEnabled(paramBoolean);
    mCmbMin.setEnabled(paramBoolean);
  }
  
  public void setTime(int paramInt1, int paramInt2)
  {
    mCmbHour.setSelectedIndex(paramInt1);
    mCmbMin.setSelectedIndex(paramInt2);
  }
}
