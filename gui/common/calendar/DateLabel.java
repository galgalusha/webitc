package webitc.gui.common.calendar;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import webitc.common.FatalException;
import webitc.data.DayType;
import webitc.gui.common.BorderRes;
import webitc.gui.common.ColorRes;
import webitc.gui.common.FocusBorder;
import webitc.gui.common.PanelAbstract;

public class DateLabel
  extends PanelAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JLabel mLblFig = new JLabel();
  JLabel mLblText = new JLabel();
  private ArrayList mListenerList = new ArrayList();
  public static final Font sFont = new Font("Dialog", 1, 12);
  
  public DateLabel(DayType paramDayType, String paramString)
  {
    try
    {
      jbInit();
      partsInit(paramDayType, paramString);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public DateLabel()
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
  
  public void addActionListener(ActionListener paramActionListener)
  {
    mListenerList.add(paramActionListener);
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    addMouseListener(new DateLabel_this_mouseAdapter(this));
    add(mLblFig, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mLblText, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  private void partsInit(DayType paramDayType, String paramString)
  {
    mLblFig.setText(Integer.toString(fDay));
    mLblFig.setFont(sFont);
    mLblFig.setPreferredSize(new Dimension(25, 25));
    mLblFig.setHorizontalAlignment(0);
    switch (fType)
    {
    case 0: 
      mLblFig.setForeground(ColorRes.CALENDAR_BLACK);
      mLblFig.setBorder(null);
      break;
    case 1: 
      mLblFig.setForeground(ColorRes.CALENDAR_RED);
      mLblFig.setBorder(BorderRes.getBorder(4));
      break;
    case 2: 
      mLblFig.setForeground(ColorRes.CALENDAR_GREEN);
      mLblFig.setBorder(BorderRes.getBorder(5));
      break;
    case 3: 
      mLblFig.setForeground(ColorRes.CALENDAR_BLUE);
      mLblFig.setBorder(BorderRes.getBorder(6));
      break;
    case 4: 
      mLblFig.setForeground(ColorRes.CALENDAR_BLACK);
      mLblFig.setBorder(BorderRes.getBorder(3));
      break;
    case 5: 
      mLblFig.setForeground(ColorRes.CALENDAR_RED);
      mLblFig.setBorder(BorderRes.getBorder(7));
      break;
    case 6: 
      mLblFig.setForeground(ColorRes.CALENDAR_GREEN);
      mLblFig.setBorder(BorderRes.getBorder(7));
      break;
    case 7: 
      mLblFig.setForeground(ColorRes.CALENDAR_BLUE);
      mLblFig.setBorder(BorderRes.getBorder(7));
      break;
    case 8: 
      mLblFig.setForeground(ColorRes.CALENDAR_BLACK);
      mLblFig.setBorder(BorderRes.getBorder(7));
      break;
    case 9: 
      mLblFig.setForeground(ColorRes.CALENDAR_RED);
      mLblFig.setBorder(null);
      break;
    case 10: 
      mLblFig.setForeground(ColorRes.CALENDAR_GREEN);
      mLblFig.setBorder(null);
      break;
    default: 
      throw new FatalException("DateLabel.partsInit");
    }
    mLblText.setText(paramString);
    mLblText.setBorder(null);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
  }
  
  public void setFocus(boolean paramBoolean)
  {
    if (paramBoolean == true) {
      mLblText.setBorder(FocusBorder.BORDER);
    } else {
      mLblText.setBorder(null);
    }
  }
  
  public void setText(String paramString)
  {
    mLblText.setText(paramString);
  }
  
  void this_mouseClicked(MouseEvent paramMouseEvent)
  {
    if (isEnabled() == true) {
      for (int i = 0; i < mListenerList.size(); i++)
      {
        ActionListener localActionListener = (ActionListener)mListenerList.get(i);
        localActionListener.actionPerformed(new ActionEvent(this, 0, ""));
      }
    }
  }
}
