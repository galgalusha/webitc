package webitc.gui.common;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import webitc.common.StrRes;

public class DlgMessage
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton mButton = new JButton();
  JLabel mLabel = new JLabel();
  
  public DlgMessage(String paramString, Object paramObject, int paramInt)
  {
    super(false);
    try
    {
      jbInit();
      partsInit(paramString, paramObject, paramInt);
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
    mButton.addActionListener(new DlgMessage_mButton_actionAdapter(this));
    getContentPane().add(mLabel, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mButton, new GridBagConstraints(0, 1, 2, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
  }
  
  void mButton_actionPerformed(ActionEvent paramActionEvent)
  {
    closeDialog(true);
  }
  
  private void partsInit(String paramString, Object paramObject, int paramInt)
  {
    setTitle(paramString);
    mLabel.setIcon(IconRes.getIcon(paramInt));
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
