package webitc.gui.common;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import webitc.common.StrRes;

public class DlgMsgList
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton mButton = new JButton();
  JLabel mLabel = new JLabel();
  TextArea mTextCtrl = new TextArea();
  
  public DlgMsgList(String paramString1, String paramString2, Object paramObject)
  {
    try
    {
      jbInit();
      partsInit(paramString1, paramString2, paramObject);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void jbInit()
    throws Exception
  {
    getContentPane().setLayout(gridBagLayout1);
    mButton.setText(StrRes.getStr("IDS_COMMON_OK"));
    mButton.addActionListener(new DlgMsgList_mButton_actionAdapter(this));
    mTextCtrl.setEditable(false);
    mTextCtrl.setFont(mLabel.getFont());
    getContentPane().add(mLabel, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mTextCtrl, new GridBagConstraints(0, 1, 2, 1, 1.0D, 1.0D, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mButton, new GridBagConstraints(1, 2, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(5, 5, 5, 5), 0, 0));
  }
  
  void mButton_actionPerformed(ActionEvent paramActionEvent)
  {
    closeDialog(true);
  }
  
  private void partsInit(String paramString1, String paramString2, Object paramObject)
  {
    setTitle(paramString1);
    mLabel.setText(paramString2);
    if (paramObject.getClass().isArray() == true)
    {
      String str = "\n";
      Object[] arrayOfObject = (Object[])paramObject;
      StringBuffer localStringBuffer = new StringBuffer();
      for (int i = 0; i < arrayOfObject.length; i++)
      {
        localStringBuffer.append(arrayOfObject[i].toString());
        if (i != arrayOfObject.length - 1) {
          localStringBuffer.append(str);
        }
      }
      mTextCtrl.setText(localStringBuffer.toString());
    }
    else
    {
      mTextCtrl.setText(paramObject.toString());
    }
    pack();
  }
}
