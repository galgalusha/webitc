package webitc.gui.common;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import webitc.common.StrRes;

public class DlgQuestion
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton mBtnNo = new JButton();
  JButton mBtnYes = new JButton();
  JLabel mLabel = new JLabel();
  
  public DlgQuestion(String paramString, Object paramObject)
  {
    super(false);
    try
    {
      jbInit();
      partsInit(paramString, paramObject);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void errorPerformed()
  {
    mBtnYes.setEnabled(false);
  }
  
  private void jbInit()
    throws Exception
  {
    getContentPane().setLayout(gridBagLayout1);
    mBtnYes.setText(StrRes.getStr("IDS_COMMON_YES"));
    mBtnYes.addActionListener(new DlgQuestion_mBtnYes_actionAdapter(this));
    mBtnNo.setText(StrRes.getStr("IDS_COMMON_NO"));
    mBtnNo.addActionListener(new DlgQuestion_mBtnNo_actionAdapter(this));
    getContentPane().add(mLabel, new GridBagConstraints(0, 0, 3, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mBtnYes, new GridBagConstraints(0, 1, 1, 1, 0.5D, 0.0D, 13, 0, new Insets(5, 5, 5, 10), 0, 0));
    getContentPane().add(mBtnNo, new GridBagConstraints(2, 1, 1, 1, 0.5D, 0.0D, 17, 0, new Insets(5, 10, 6, 12), 0, 0));
  }
  
  void mBtnNo_actionPerformed(ActionEvent paramActionEvent)
  {
    closeDialog(false);
  }
  
  void mBtnYes_actionPerformed(ActionEvent paramActionEvent)
  {
    closeDialog(true);
  }
  
  private void partsInit(String paramString, Object paramObject)
  {
    setTitle(paramString);
    mLabel.setIcon(IconRes.getIcon(29));
    if (paramObject.getClass().isArray() == true)
    {
      Object[] arrayOfObject = (Object[])paramObject;
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("<html>");
      for (int i = 0; i < arrayOfObject.length; i++)
      {
        localStringBuffer.append(arrayOfObject[i].toString());
        if (i != arrayOfObject.length - 1) {
          localStringBuffer.append("<br>");
        }
      }
      localStringBuffer.append("</html>");
      mLabel.setText(localStringBuffer.toString());
    }
    else
    {
      mLabel.setText(paramObject.toString());
    }
    pack();
  }
}
