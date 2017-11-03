package webitc.gui.schedule;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.data.DayType;
import webitc.data.schedule.SchException;
import webitc.gui.common.BorderRes;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.calendar.DateLabel;

class CalendarSelect
  extends PanelAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JCheckBox mCheckBox = new JCheckBox();
  DateLabel[] mDateArray = new DateLabel[11];
  DateLabel mDateEx1;
  DateLabel mDateEx10;
  DateLabel mDateEx2;
  DateLabel mDateEx3;
  DateLabel mDateEx4;
  DateLabel mDateEx5;
  DateLabel mDateEx6;
  DateLabel mDateEx7;
  DateLabel mDateEx8;
  DateLabel mDateEx9;
  DateLabel mDateWeek;
  ButtonGroup mGrp = new ButtonGroup();
  private ArrayList mListenerList = new ArrayList();
  JRadioButton[] mRdoArray = new JRadioButton[11];
  JRadioButton mRdoEx1 = new JRadioButton();
  JRadioButton mRdoEx10 = new JRadioButton();
  JRadioButton mRdoEx2 = new JRadioButton();
  JRadioButton mRdoEx3 = new JRadioButton();
  JRadioButton mRdoEx4 = new JRadioButton();
  JRadioButton mRdoEx5 = new JRadioButton();
  JRadioButton mRdoEx6 = new JRadioButton();
  JRadioButton mRdoEx7 = new JRadioButton();
  JRadioButton mRdoEx8 = new JRadioButton();
  JRadioButton mRdoEx9 = new JRadioButton();
  JRadioButton mRdoWeek = new JRadioButton();
  
  public CalendarSelect()
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
  
  public void addListener(CalendarSelectListener paramCalendarSelectListener)
  {
    mListenerList.add(paramCalendarSelectListener);
  }
  
  void date_actionPerformed(int paramInt)
  {
    mRdoArray[paramInt].setSelected(true);
    for (int i = 0; i < 11; i++) {
      if (i == paramInt) {
        mDateArray[i].setFocus(true);
      } else {
        mDateArray[i].setFocus(false);
      }
    }
    for (int j = 0; j < mListenerList.size(); j++)
    {
      CalendarSelectListener localCalendarSelectListener = (CalendarSelectListener)mListenerList.get(j);
      localCalendarSelectListener.typeSelected(paramInt);
    }
  }
  
  public void errorPerformed()
  {
    mRdoWeek.setEnabled(false);
    mRdoEx1.setEnabled(false);
    mRdoEx2.setEnabled(false);
    mRdoEx3.setEnabled(false);
    mRdoEx4.setEnabled(false);
    mRdoEx6.setEnabled(false);
    mRdoEx7.setEnabled(false);
    mRdoEx8.setEnabled(false);
    mRdoEx5.setEnabled(false);
    mRdoEx9.setEnabled(false);
    mRdoEx10.setEnabled(false);
    mCheckBox.setEnabled(false);
  }
  
  public int getDayType()
  {
    if (mRdoWeek.isSelected() == true) {
      return 0;
    }
    if (mRdoEx1.isSelected() == true) {
      return 1;
    }
    if (mRdoEx2.isSelected() == true) {
      return 2;
    }
    if (mRdoEx3.isSelected() == true) {
      return 3;
    }
    if (mRdoEx4.isSelected() == true) {
      return 4;
    }
    if (mRdoEx5.isSelected() == true) {
      return 5;
    }
    if (mRdoEx6.isSelected() == true) {
      return 6;
    }
    if (mRdoEx7.isSelected() == true) {
      return 7;
    }
    if (mRdoEx8.isSelected() == true) {
      return 8;
    }
    if (mRdoEx9.isSelected() == true) {
      return 9;
    }
    if (mRdoEx10.isSelected() == true) {
      return 10;
    }
    throw new FatalException("CalendarSelect.getDayType");
  }
  
  public boolean isCheckBoxSelected()
  {
    return mCheckBox.isSelected();
  }
  
  private void jbInit()
    throws Exception
  {
    mDateWeek = new DateLabel(new DayType(1, 0), StrRes.getStr("IDS_SCH_WEEK"));
    mDateEx1 = new DateLabel(new DayType(2, 1), "");
    mDateEx2 = new DateLabel(new DayType(3, 2), "");
    mDateEx3 = new DateLabel(new DayType(4, 3), "");
    mDateEx4 = new DateLabel(new DayType(5, 4), "");
    mDateEx5 = new DateLabel(new DayType(6, 5), "");
    mDateEx6 = new DateLabel(new DayType(7, 6), "");
    mDateEx7 = new DateLabel(new DayType(8, 7), "");
    mDateEx8 = new DateLabel(new DayType(9, 8), "");
    mDateEx9 = new DateLabel(new DayType(10, 9), "");
    mDateEx10 = new DateLabel(new DayType(11, 10), "");
    mDateArray[0] = mDateWeek;
    mDateArray[1] = mDateEx1;
    mDateArray[2] = mDateEx2;
    mDateArray[3] = mDateEx3;
    mDateArray[4] = mDateEx4;
    mDateArray[5] = mDateEx5;
    mDateArray[6] = mDateEx6;
    mDateArray[7] = mDateEx7;
    mDateArray[8] = mDateEx8;
    mDateArray[9] = mDateEx9;
    mDateArray[10] = mDateEx10;
    mRdoArray[0] = mRdoWeek;
    mRdoArray[1] = mRdoEx1;
    mRdoArray[2] = mRdoEx2;
    mRdoArray[3] = mRdoEx3;
    mRdoArray[4] = mRdoEx4;
    mRdoArray[5] = mRdoEx5;
    mRdoArray[6] = mRdoEx6;
    mRdoArray[7] = mRdoEx7;
    mRdoArray[8] = mRdoEx8;
    mRdoArray[9] = mRdoEx9;
    mRdoArray[10] = mRdoEx10;
    mDateWeek.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(0);
      }
    });
    mDateEx1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(1);
      }
    });
    mDateEx2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(2);
      }
    });
    mDateEx3.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(3);
      }
    });
    mDateEx4.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(4);
      }
    });
    mDateEx5.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(5);
      }
    });
    mDateEx6.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(6);
      }
    });
    mDateEx7.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(7);
      }
    });
    mDateEx8.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(8);
      }
    });
    mDateEx9.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(9);
      }
    });
    mDateEx10.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(10);
      }
    });
    setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_SCH_PATTERN_LIST")));
    setLayout(gridBagLayout1);
    mRdoWeek.setText("");
    mRdoWeek.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(0);
      }
    });
    mRdoEx1.setText("");
    mRdoEx1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(1);
      }
    });
    mRdoEx2.setText("");
    mRdoEx2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(2);
      }
    });
    mRdoEx3.setText("");
    mRdoEx3.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(3);
      }
    });
    mRdoEx4.setText("");
    mRdoEx4.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(4);
      }
    });
    mRdoEx5.setText("");
    mRdoEx5.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(5);
      }
    });
    mRdoEx6.setText("");
    mRdoEx6.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(6);
      }
    });
    mRdoEx7.setText("");
    mRdoEx7.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(7);
      }
    });
    mRdoEx8.setText("");
    mRdoEx8.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(8);
      }
    });
    mRdoEx9.setText("");
    mRdoEx9.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(9);
      }
    });
    mRdoEx10.setText("");
    mRdoEx10.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        date_actionPerformed(10);
      }
    });
    mCheckBox.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mCheckBox.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent paramAnonymousItemEvent)
      {
        mCheckBox_itemStateChanged();
      }
    });
    add(mDateWeek, new GridBagConstraints(1, 1, 3, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mRdoWeek, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx1, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mDateEx1, new GridBagConstraints(1, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mRdoEx2, new GridBagConstraints(2, 2, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mGrp.add(mRdoWeek);
    mGrp.add(mRdoEx1);
    add(mRdoEx3, new GridBagConstraints(4, 2, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx4, new GridBagConstraints(6, 2, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx6, new GridBagConstraints(2, 3, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx7, new GridBagConstraints(4, 3, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx8, new GridBagConstraints(6, 3, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx5, new GridBagConstraints(0, 3, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx9, new GridBagConstraints(0, 4, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx10, new GridBagConstraints(2, 4, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mDateEx2, new GridBagConstraints(3, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mDateEx3, new GridBagConstraints(5, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mDateEx4, new GridBagConstraints(7, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mDateEx5, new GridBagConstraints(1, 3, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mDateEx6, new GridBagConstraints(3, 3, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mDateEx7, new GridBagConstraints(5, 3, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mDateEx8, new GridBagConstraints(7, 3, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mDateEx9, new GridBagConstraints(1, 4, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mDateEx10, new GridBagConstraints(3, 4, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mCheckBox, new GridBagConstraints(0, 0, 3, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mGrp.add(mRdoEx2);
    mGrp.add(mRdoEx3);
    mGrp.add(mRdoEx4);
    mGrp.add(mRdoEx5);
    mGrp.add(mRdoEx6);
    mGrp.add(mRdoEx7);
    mGrp.add(mRdoEx8);
    mGrp.add(mRdoEx9);
    mGrp.add(mRdoEx10);
    mCheckBox_itemStateChanged();
  }
  
  void mCheckBox_itemStateChanged()
  {
    boolean bool = mCheckBox.isSelected();
    for (int i = 0; i < 11; i++)
    {
      mRdoArray[i].setEnabled(bool);
      mDateArray[i].setEnabled(bool);
    }
  }
  
  public void setDayType(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      mRdoWeek.setSelected(true);
      break;
    case 1: 
      mRdoEx1.setSelected(true);
      break;
    case 2: 
      mRdoEx2.setSelected(true);
      break;
    case 3: 
      mRdoEx3.setSelected(true);
      break;
    case 4: 
      mRdoEx4.setSelected(true);
      break;
    case 5: 
      mRdoEx5.setSelected(true);
      break;
    case 6: 
      mRdoEx6.setSelected(true);
      break;
    case 7: 
      mRdoEx7.setSelected(true);
      break;
    case 8: 
      mRdoEx8.setSelected(true);
      break;
    case 9: 
      mRdoEx9.setSelected(true);
      break;
    case 10: 
      mRdoEx10.setSelected(true);
    }
    for (int i = 0; i < 11; i++) {
      if (i == paramInt) {
        mDateArray[i].setFocus(true);
      } else {
        mDateArray[i].setFocus(false);
      }
    }
  }
  
  public void setException(SchException paramSchException)
  {
    mDateEx1.setText(paramSchException.getExceptionName(0));
    mDateEx2.setText(paramSchException.getExceptionName(1));
    mDateEx3.setText(paramSchException.getExceptionName(2));
    mDateEx4.setText(paramSchException.getExceptionName(3));
    mDateEx5.setText(paramSchException.getExceptionName(4));
    mDateEx6.setText(paramSchException.getExceptionName(5));
    mDateEx7.setText(paramSchException.getExceptionName(6));
    mDateEx8.setText(paramSchException.getExceptionName(7));
    mDateEx9.setText(paramSchException.getExceptionName(8));
    mDateEx10.setText(paramSchException.getExceptionName(9));
  }
}
