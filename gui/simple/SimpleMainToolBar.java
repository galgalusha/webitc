package webitc.gui.simple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import webitc.common.StrRes;
import webitc.gui.JButtonBar;
import webitc.gui.common.ColorRes;
import webitc.gui.common.SimpleButton;
import webitc.gui.common.SimpleButtonRes;

public class SimpleMainToolBar
  extends JButtonBar
{
  private static final Font FONT_USER_NAME = new Font("Dialog", 0, 14);
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JLabel mBar = new JLabel();
  SimpleButton mBtnIcon = SimpleButtonRes.getButton(13);
  SimpleButton mBtnList = SimpleButtonRes.getButton(14);
  SimpleButton mBtnLogout = SimpleButtonRes.getButton(10);
  SimpleButton mBtnUpdate = SimpleButtonRes.getButton(9);
  
  public SimpleMainToolBar()
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
  
  private void jbInit()
    throws Exception
  {
    setBackground(ColorRes.SIMPLE_MODE_BG);
    setPreferredSize(new Dimension(500, 25));
    setLayout(gridBagLayout1);
    mBar.setPreferredSize(new Dimension(10, 23));
    mBar.setBackground(ColorRes.SIMPLE_MODE_MAIN_TOOL_BAR);
    mBar.setOpaque(true);
    mBar.setForeground(Color.WHITE);
    mBar.setHorizontalAlignment(4);
    mBar.setFont(FONT_USER_NAME);
    mBtnIcon.addActionListener(new SimpleMainToolBar_mBtnIcon_actionAdapter(this));
    mBtnList.addActionListener(new SimpleMainToolBar_mBtnList_actionAdapter(this));
    mBtnUpdate.addActionListener(new SimpleMainToolBar_mBtnUpdate_actionAdapter(this));
    mBtnLogout.addActionListener(new SimpleMainToolBar_mBtnLogout_actionAdapter(this));
    ButtonGroup localButtonGroup = new ButtonGroup();
    localButtonGroup.add(mBtnIcon);
    localButtonGroup.add(mBtnList);
    mBtnIcon.setSelected(true);
    add(mBtnIcon, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 10, 0, 5), 0, 0));
    add(mBtnList, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 5), 0, 0));
    add(mBar, new GridBagConstraints(2, 0, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 5), 0, 0));
    add(mBtnUpdate, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 5), 0, 0));
    add(mBtnLogout, new GridBagConstraints(4, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 5), 0, 0));
  }
  
  void mBtnIcon_actionPerformed(ActionEvent paramActionEvent)
  {
    mBtnList.setSelected(false);
    mBtnIcon.setSelected(true);
    fireButtonEvent(4);
  }
  
  void mBtnList_actionPerformed(ActionEvent paramActionEvent)
  {
    mBtnList.setSelected(true);
    mBtnIcon.setSelected(false);
    fireButtonEvent(5);
  }
  
  void mBtnLogout_actionPerformed(ActionEvent paramActionEvent)
  {
    fireButtonEvent(2);
  }
  
  void mBtnUpdate_actionPerformed(ActionEvent paramActionEvent)
  {
    fireButtonEvent(3);
  }
  
  public void setLoginName(String paramString)
  {
    String str = StrRes.getStr("IDS_COMMON_USERNAME") + ": " + paramString + "  ";
    mBar.setText(str);
  }
  
  public void setRefreshEnabled(boolean paramBoolean)
  {
    mBtnUpdate.setEnabled(paramBoolean);
  }
}
