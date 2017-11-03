package webitc.common;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import webitc.com.SockHttp;
import webitc.common.enum2.EnumLang;
import webitc.gui.common.CommonUse;
import webitc.gui.common.ImageRes;

public abstract class AppletAbst
  extends JApplet
{
  private boolean isStandalone = false;
  private static AppletAbst mObj = null;
  boolean mSimpleMode = false;
  public static Cursor sArrowWaitCursor;
  public static final Cursor sDefaultCursor;
  public static final UUID sUUID;
  public static Cursor sWaitCursor = new Cursor(3);
  
  static
  {
    sArrowWaitCursor = null;
    sDefaultCursor = new Cursor(0);
    sUUID = new UUID();
  }
  
  public AppletAbst() {}
  
  private void UIInit()
  {
    try
    {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      Font localFont = null;
      localFont = new Font("Dialog", 0, 12);
      UIManager.put("Button.font", localFont);
      UIManager.put("ToggleButton.font", localFont);
      UIManager.put("RadioButton.font", localFont);
      UIManager.put("CheckBox.font", localFont);
      UIManager.put("ColorChooser.font", localFont);
      UIManager.put("ComboBox.font", localFont);
      UIManager.put("Label.font", localFont);
      UIManager.put("List.font", localFont);
      UIManager.put("MenuBar.font", localFont);
      UIManager.put("MenuItem.font", localFont);
      UIManager.put("MenuItem.acceleratorFont", localFont);
      UIManager.put("RadioButtonMenuItem.font", localFont);
      UIManager.put("RadioButtonMenuItem.acceleratorFont", localFont);
      UIManager.put("CheckBoxMenuItem.font", localFont);
      UIManager.put("CheckBoxMenuItem.acceleratorFont", localFont);
      UIManager.put("Menu.font", localFont);
      UIManager.put("Menu.acceleratorFont", localFont);
      UIManager.put("PopupMenu.font", localFont);
      UIManager.put("OptionPane.font", localFont);
      UIManager.put("Panel.font", localFont);
      UIManager.put("ProgressBar.font", localFont);
      UIManager.put("ScrollPane.font", localFont);
      UIManager.put("ViewPort.font", localFont);
      UIManager.put("TabbedPane.font", localFont);
      UIManager.put("Table.font", localFont);
      UIManager.put("TableHeader.font", localFont);
      UIManager.put("TextField.font", localFont);
      UIManager.put("PasswordField.font", localFont);
      UIManager.put("TextArea.font", localFont);
      UIManager.put("TextPane.font", localFont);
      UIManager.put("EditorPane.font", localFont);
      UIManager.put("TitledBorder.font", localFont);
      UIManager.put("ToolBar.font", localFont);
      UIManager.put("ToolTip.font", localFont);
      UIManager.put("Tree.font", localFont);
      SwingUtilities.updateComponentTreeUI(this);
    }
    catch (Exception localException)
    {
      CommonUse.AppErr(localException, "AppletDbacs.UIInit");
    }
  }
  
  public abstract void forceLogout();
  
  public static AppletAbst getInstance()
  {
    return mObj;
  }
  
  public String getParameter(String paramString1, String paramString2)
  {
    return getParameter(paramString1) != null ? getParameter(paramString1) : isStandalone ? System.getProperty(paramString1, paramString2) : paramString2;
  }
  
  public void init()
  {
    try
    {
      mObj = this;
      resInit();
      String str1 = getParameter("demo", "false");
      String str2 = getParameter("host", "");
      if (str1.compareTo("true") == 0) {
        SockHttp.createInstance(str2, 80, true);
      } else if (str2.compareTo("") == 0)
      {
        if (getCodeBase().getPort() < 0) {
          SockHttp.createInstance(getCodeBase().getHost(), 80, false);
        } else {
          SockHttp.createInstance(getCodeBase().getHost(), getCodeBase().getPort(), false);
        }
      }
      else {
        SockHttp.createInstance(str2, 80, false);
      }
      ImageIcon localImageIcon = new ImageIcon(ImageRes.resWaitCursor);
      sWaitCursor = Toolkit.getDefaultToolkit().createCustomCursor(localImageIcon.getImage(), new Point(0, 0), "");
      UIInit();
    }
    catch (Exception localException)
    {
      CommonUse.AppErr(localException, "AppletDbacs.init");
    }
  }
  
  public boolean isSimpleMode()
  {
    return mSimpleMode;
  }
  
  private void resInit()
    throws Exception
  {
    String str = getParameter("lang", "en");
    SystemInfo.setLang(EnumLang.getEnum(str));
  }
  
  public void setArrowWaitCursor()
  {
    setCursor(sArrowWaitCursor);
  }
  
  public void setDefaultCursor()
  {
    setCursor(sDefaultCursor);
  }
  
  public void setSimpleMode(boolean paramBoolean)
  {
    mSimpleMode = paramBoolean;
    Font localFont = null;
    if (paramBoolean == true) {
      localFont = new Font("Dialog", 0, 12);
    } else {
      return;
    }
    UIManager.put("Button.font", localFont);
    UIManager.put("ToggleButton.font", localFont);
    UIManager.put("RadioButton.font", localFont);
    UIManager.put("CheckBox.font", localFont);
    UIManager.put("ColorChooser.font", localFont);
    UIManager.put("ComboBox.font", localFont);
    UIManager.put("Label.font", localFont);
    UIManager.put("List.font", localFont);
    UIManager.put("MenuBar.font", localFont);
    UIManager.put("MenuItem.font", localFont);
    UIManager.put("MenuItem.acceleratorFont", localFont);
    UIManager.put("RadioButtonMenuItem.font", localFont);
    UIManager.put("RadioButtonMenuItem.acceleratorFont", localFont);
    UIManager.put("CheckBoxMenuItem.font", localFont);
    UIManager.put("CheckBoxMenuItem.acceleratorFont", localFont);
    UIManager.put("Menu.font", localFont);
    UIManager.put("Menu.acceleratorFont", localFont);
    UIManager.put("PopupMenu.font", localFont);
    UIManager.put("OptionPane.font", localFont);
    UIManager.put("Panel.font", localFont);
    UIManager.put("ProgressBar.font", localFont);
    UIManager.put("ScrollPane.font", localFont);
    UIManager.put("ViewPort.font", localFont);
    UIManager.put("TabbedPane.font", localFont);
    UIManager.put("Table.font", localFont);
    UIManager.put("TableHeader.font", localFont);
    UIManager.put("TextField.font", localFont);
    UIManager.put("PasswordField.font", localFont);
    UIManager.put("TextArea.font", localFont);
    UIManager.put("TextPane.font", localFont);
    UIManager.put("EditorPane.font", localFont);
    UIManager.put("TitledBorder.font", localFont);
    UIManager.put("ToolBar.font", localFont);
    UIManager.put("ToolTip.font", localFont);
    UIManager.put("Tree.font", localFont);
    SwingUtilities.updateComponentTreeUI(this);
  }
  
  public void setWaitCursor()
  {
    setCursor(sWaitCursor);
  }
}
