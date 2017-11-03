package webitc.gui.common;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import webitc.common.AppletAbst;
import webitc.common.StrRes;
import webitc.frame.Progress;
import webitc.frame.Result;

public class DlgWaitWithCancel
  extends JDialog
{
  private final int fCheckSec = 200;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JProgressBar mBar = null;
  JButton mBtnCancel = new JButton();
  private boolean mInWait = false;
  private JLabel mLabel = null;
  private static DlgWaitWithCancel mObj = null;
  private Progress mProgress = null;
  private Result mResult;
  private Timer mTimer = null;
  
  private DlgWaitWithCancel(Frame paramFrame)
    throws HeadlessException
  {
    super(paramFrame, true);
    setSize(250, 150);
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
    JPanel localJPanel1 = new JPanel();
    JPanel localJPanel2 = new JPanel();
    JPanel localJPanel3 = new JPanel();
    localJPanel2.setLayout(new BorderLayout());
    localJPanel3.setLayout(new BorderLayout());
    localJPanel1.setBorder(BorderRes.getBorder(0));
    localJPanel1.setLayout(gridBagLayout1);
    mLabel = new JLabel(StrRes.getStr("IDS_COMMON_WAIT"), IconRes.getIcon(18), 0);
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mBtnCancel.addActionListener(new DlgWaitWithCancel_mBtnCancel_actionAdapter(this));
    mBar = new JProgressBar(0, 100);
    localJPanel1.add(mBar, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 10, 5, 10), 0, 0));
    localJPanel2.add(mLabel, "Center");
    localJPanel3.add(mBtnCancel, "East");
    localJPanel1.add(localJPanel2, new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 305, 254));
    localJPanel1.add(localJPanel3, new GridBagConstraints(0, 2, 1, 1, 1.0D, 0.0D, 13, 0, new Insets(5, 0, 10, 10), 0, 0));
    getContentPane().add(localJPanel1);
  }
  
  void mBtnCancel_actionPerformed(ActionEvent paramActionEvent)
  {
    mBtnCancel.setEnabled(false);
    if (!mResult.cancel()) {
      CommonUse.showInformationDlg(StrRes.getStr("IDS_COMMON_ERROR_CANCEL"), StrRes.getStr("IDS_COMMON_ERROR"));
    }
  }
  
  private void showPrivate(Result paramResult, Progress paramProgress)
  {
    mBtnCancel.setEnabled(true);
    mLabel.setText(StrRes.getStr("IDS_COMMON_WAIT"));
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mInWait = true;
    mResult = paramResult;
    mProgress = paramProgress;
    if (mProgress != null)
    {
      mBar.setValue(mProgress.getProgress());
      mBar.setStringPainted(true);
    }
    else
    {
      mBar.setStringPainted(false);
      mBar.setVisible(false);
    }
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
  
  public static synchronized void showWaitDlg(Result paramResult, Progress paramProgress, boolean paramBoolean)
  {
    if (mObj == null)
    {
      AppletAbst localAppletAbst = AppletAbst.getInstance();
      Frame localFrame = (Frame)SwingUtilities.getAncestorOfClass(Frame.class, localAppletAbst);
      mObj = new DlgWaitWithCancel(localFrame);
    }
    mObjmBtnCancel.setVisible(paramBoolean);
    mObj.showPrivate(paramResult, paramProgress);
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
        if ((mBar != null) && (mProgress != null)) {
          mBar.setValue(mProgress.getProgress());
        }
        mTimer.restart();
      }
    }
  }
}
