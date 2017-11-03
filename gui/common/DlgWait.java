package webitc.gui.common;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import webitc.common.AppletAbst;
import webitc.common.StrRes;
import webitc.frame.Result;

public class DlgWait
  extends JDialog
{
  private final int fCheckSec = 200;
  private boolean mInWait = false;
  private JLabel mLabel = null;
  private static DlgWait mObj = null;
  private Result mResult;
  private Timer mTimer = null;
  
  private DlgWait(Frame paramFrame)
    throws HeadlessException
  {
    super(paramFrame, true);
    setSize(250, 100);
    jbInit();
  }
  
  public static void deleteInstance()
  {
    mObj = null;
  }
  
  public static boolean isWaiting()
  {
    if (mObj == null) {
      return false;
    }
    return mObjmInWait;
  }
  
  private void jbInit()
  {
    setUndecorated(true);
    JPanel localJPanel = new JPanel();
    localJPanel.setLayout(new BorderLayout());
    localJPanel.setBorder(BorderRes.getBorder(0));
    getContentPane().setLayout(new GridLayout(1, 1));
    mLabel = new JLabel(StrRes.getStr("IDS_COMMON_WAIT"), IconRes.getIcon(18), 2);
    localJPanel.add(mLabel, "Center");
    getContentPane().add(localJPanel);
  }
  
  private void showPrivate(Result paramResult)
  {
    mLabel.setText(StrRes.getStr("IDS_COMMON_WAIT"));
    mInWait = true;
    mResult = paramResult;
    mTimer = new Timer(200, new TimerListener());
    mTimer.setInitialDelay(200);
    mTimer.start();
    AppletAbst localAppletAbst = AppletAbst.getInstance();
    Point localPoint = localAppletAbst.getLocationOnScreen();
    int i = (localAppletAbst.getWidth() - getWidth()) / 2;
    int j = (localAppletAbst.getHeight() - getHeight()) / 2;
    setLocation(x + i, y + j);
    setVisible(true);
  }
  
  public static synchronized void showWaitDlg(Result paramResult)
  {
    if (mObj == null)
    {
      AppletAbst localAppletAbst = AppletAbst.getInstance();
      Frame localFrame = (Frame)SwingUtilities.getAncestorOfClass(Frame.class, localAppletAbst);
      mObj = new DlgWait(localFrame);
    }
    mObj.showPrivate(paramResult);
  }
  
  class TimerListener
    implements ActionListener
  {
    TimerListener() {}
    
    public void actionPerformed(ActionEvent paramActionEvent)
    {
      mTimer.stop();
      if (mResult.isFinished() == true)
      {
        dispose();
        mInWait = false;
      }
      else
      {
        mTimer.restart();
      }
    }
  }
}
