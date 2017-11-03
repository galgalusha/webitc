package webitc.gui.common;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import webitc.common.AppletAbst;

public class DlgAbstract
  extends JDialog
{
  public static final int DLG_LOCATION_CENTER = 1;
  public static final int DLG_LOCATION_NW = 0;
  private static final int TOP_IMAGE_HEIGHT = 80;
  private ComponentListener mComponentListener = null;
  private static ArrayList mDlgList = new ArrayList();
  private static boolean mEnable = false;
  private boolean mIsOK = false;
  private Dimension mMinimumSize = null;
  private boolean mResizeable = true;
  private static HashMap mSizeMap = new HashMap();
  
  public DlgAbstract(boolean paramBoolean)
  {
    super((Frame)SwingUtilities.getAncestorOfClass(Frame.class, AppletAbst.getInstance()), true);
    constract(paramBoolean);
  }
  
  public DlgAbstract()
  {
    super((Frame)SwingUtilities.getAncestorOfClass(Frame.class, AppletAbst.getInstance()), true);
    constract(true);
  }
  
  public static void closeAll()
  {
    for (int i = 0; i < mDlgList.size(); i++)
    {
      DlgAbstract localDlgAbstract = (DlgAbstract)mDlgList.get(i);
      if (localDlgAbstract != null) {
        localDlgAbstract.dispose();
      }
    }
  }
  
  protected void closeDialog(boolean paramBoolean)
  {
    mIsOK = paramBoolean;
    dispose();
    mDlgList.remove(this);
  }
  
  private void constract(boolean paramBoolean)
  {
    mResizeable = paramBoolean;
    setModal(true);
    setResizable(mResizeable);
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent paramAnonymousWindowEvent)
      {
        this_windowClosing(paramAnonymousWindowEvent);
      }
    });
    if (mResizeable == true)
    {
      setGlassPane(new GlassPane());
      getGlassPane().setVisible(true);
    }
    mDlgList.add(this);
  }
  
  public void doModal(int paramInt)
  {
    if (!mEnable) {
      return;
    }
    for (int i = 0; i < mDlgList.size(); i++)
    {
      localObject = (DlgAbstract)mDlgList.get(i);
      if ((getClass() == localObject.getClass()) && (((DlgAbstract)localObject).isVisible() == true)) {
        return;
      }
    }
    int m;
    if (mResizeable == true)
    {
      localObject = getRecordedSize();
      pack();
      Dimension localDimension = getSize();
      FontMetrics localFontMetrics = getFontMetrics(getFont());
      m = localFontMetrics.stringWidth(getTitle()) + 100;
      if (m > localDimension.getWidth()) {
        width = m;
      }
      mMinimumSize = new Dimension(localDimension);
      if (localObject != null)
      {
        int n = (int)localDimension.getWidth();
        int i1 = (int)localDimension.getHeight();
        if (((Dimension)localObject).getWidth() >= localDimension.getWidth()) {
          n = (int)((Dimension)localObject).getWidth();
        }
        if (((Dimension)localObject).getHeight() >= localDimension.getHeight()) {
          i1 = (int)((Dimension)localObject).getHeight();
        }
        setSize(n, i1);
      }
      else
      {
        setSize(localDimension);
      }
      mComponentListener = new ComponentListener()
      {
        public void componentHidden(ComponentEvent paramAnonymousComponentEvent) {}
        
        public void componentMoved(ComponentEvent paramAnonymousComponentEvent) {}
        
        public void componentResized(ComponentEvent paramAnonymousComponentEvent)
        {
          int i = getWidth();
          int j = getHeight();
          if (i < mMinimumSize.getWidth()) {
            i = (int)mMinimumSize.getWidth();
          }
          if (j < mMinimumSize.getHeight()) {
            j = (int)mMinimumSize.getHeight();
          }
          setSize(i, j);
        }
        
        public void componentShown(ComponentEvent paramAnonymousComponentEvent) {}
      };
      addComponentListener(mComponentListener);
    }
    else
    {
      localObject = getFontMetrics(getFont());
      int j = ((FontMetrics)localObject).stringWidth(getTitle()) + 100;
      if (j > getWidth()) {
        setSize(j, getHeight());
      }
    }
    Object localObject = AppletAbst.getInstance();
    if ((localObject != null) && (((AppletAbst)localObject).isVisible() == true))
    {
      Point localPoint = ((AppletAbst)localObject).getLocationOnScreen();
      switch (paramInt)
      {
      case 0: 
        y -= 80;
        setLocation(localPoint);
        break;
      case 1: 
        int k = (((AppletAbst)localObject).getWidth() - getWidth()) / 2;
        m = (((AppletAbst)localObject).getHeight() - getHeight()) / 2;
        setLocation(x + k, y + m);
        break;
      }
    }
    super.setVisible(true);
  }
  
  public void doModal()
  {
    doModal(0);
  }
  
  public void errorPerformed() {}
  
  private Dimension getRecordedSize()
  {
    String str = getClass().getName();
    return (Dimension)mSizeMap.get(str);
  }
  
  public static HashMap getSizeMap()
  {
    return mSizeMap;
  }
  
  public boolean isOK()
  {
    return mIsOK;
  }
  
  public static void notifyError()
  {
    for (int i = 0; i < mDlgList.size(); i++)
    {
      DlgAbstract localDlgAbstract = (DlgAbstract)mDlgList.get(i);
      localDlgAbstract.errorPerformed();
    }
  }
  
  public static void setEnable(boolean paramBoolean)
  {
    mEnable = paramBoolean;
  }
  
  public void setSize(int paramInt1, int paramInt2)
  {
    super.setSize(paramInt1, paramInt2);
    String str = getClass().getName();
    mSizeMap.put(str, new Dimension(paramInt1, paramInt2));
  }
  
  protected void this_windowClosing(WindowEvent paramWindowEvent)
  {
    mIsOK = false;
  }
  
  class GlassPane
    extends JComponent
  {
    GlassPane() {}
    
    protected void paintComponent(Graphics paramGraphics)
    {
      int i = getSizewidth;
      int j = getSizeheight;
      paramGraphics.setColor(SystemColor.controlShadow);
      paramGraphics.drawLine(i - 3, j, i, j - 3);
      paramGraphics.drawLine(i - 4, j, i, j - 4);
      paramGraphics.drawLine(i - 7, j, i, j - 7);
      paramGraphics.drawLine(i - 8, j, i, j - 8);
      paramGraphics.drawLine(i - 11, j, i, j - 11);
      paramGraphics.drawLine(i - 12, j, i, j - 12);
      paramGraphics.setColor(SystemColor.controlLtHighlight);
      paramGraphics.drawLine(i - 5, j, i, j - 5);
      paramGraphics.drawLine(i - 9, j, i, j - 9);
      paramGraphics.drawLine(i - 13, j, i, j - 13);
    }
  }
}
