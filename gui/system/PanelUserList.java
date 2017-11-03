package webitc.gui.system;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.gui.common.BorderRes;
import webitc.gui.common.CommonUse;
import webitc.gui.common.ITCListCellRenderer;
import webitc.gui.common.JScrollList;
import webitc.gui.common.PanelAbstract;
import webitc.job.JobGetAllUser;

public class PanelUserList
  extends PanelAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JScrollList mList = new JScrollList();
  private UserListListener mListener = null;
  private Vector mUserList = new Vector(66);
  private int mUserNum = 0;
  
  public PanelUserList()
  {
    try
    {
      jbInit();
      initList();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void addList(UserData paramUserData)
  {
    if (mId == 1)
    {
      mUserList.add(0, paramUserData);
      return;
    }
    if (mId == 0) {
      return;
    }
    for (int i = 1; i < mUserList.size(); i++)
    {
      UserData localUserData = (UserData)mUserList.get(i);
      if (localUserData == null) {
        break;
      }
      if (mName.compareTo(mName) >= 0)
      {
        mUserList.add(i, paramUserData);
        return;
      }
    }
    mUserList.add(paramUserData);
  }
  
  public int getNormalUserNum()
  {
    return mUserNum - 2;
  }
  
  public int getSelectedIndex()
  {
    return mList.getSelectedIndex();
  }
  
  public int getSelectedUserID()
  {
    UserData localUserData = (UserData)mList.getSelectedValue();
    if (localUserData != null) {
      return mId;
    }
    return -1;
  }
  
  public String getSelectedUserName()
  {
    UserData localUserData = (UserData)mList.getSelectedValue();
    if (localUserData == null) {
      return null;
    }
    return mName;
  }
  
  private void initList()
  {
    mList.setCellRenderer(new UserListCellRenderer());
    mList.setShowHorizontalLines(true);
    mList.getScrollPane().setPreferredSize(new Dimension(150, 250));
  }
  
  public boolean isExist(String paramString)
  {
    for (int i = 0; i < mUserList.size(); i++)
    {
      UserData localUserData = (UserData)mUserList.get(i);
      if (localUserData == null) {
        return false;
      }
      if ((paramString.compareToIgnoreCase("admin") == 0) || (paramString.compareToIgnoreCase("service") == 0) || (paramString.compareToIgnoreCase(mName) == 0)) {
        return true;
      }
    }
    return false;
  }
  
  private void jbInit()
  {
    setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_SYSTEM_USERLIST")));
    setLayout(gridBagLayout1);
    add(mList.getScrollPane(), new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(10, 10, 10, 10), 0, 0));
    mList.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent paramAnonymousListSelectionEvent)
      {
        if (paramAnonymousListSelectionEvent.getValueIsAdjusting() == true) {
          return;
        }
        if (mListener != null) {
          mListener.notifyChangeSelect(getSelectedUserID());
        }
      }
    });
  }
  
  public void removeAllRow()
  {
    mUserList.clear();
    mList.setListData(mUserList);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    mList.setEnabled(paramBoolean);
  }
  
  public void setListener(UserListListener paramUserListListener)
  {
    mListener = paramUserListListener;
  }
  
  public void setSelectedIndex(int paramInt)
  {
    int i = mList.getModel().getSize();
    if (i == 0) {
      return;
    }
    if (paramInt == -1) {
      mList.setSelectedIndex(0);
    } else if (paramInt >= i) {
      mList.setSelectedIndex(paramInt - 1);
    } else {
      mList.setSelectedIndex(paramInt);
    }
  }
  
  public void setSelectedUserName(String paramString)
  {
    for (int i = 0; i < mList.getModel().getSize(); i++)
    {
      UserData localUserData = (UserData)mList.getModel().getElementAt(i);
      if (localUserData == null) {
        return;
      }
      if (mName.equals(paramString) == true)
      {
        mList.setSelectedIndex(i);
        return;
      }
    }
    mList.setSelectedIndex(0);
  }
  
  public void updateList()
  {
    JobGetAllUser localJobGetAllUser = new JobGetAllUser();
    Result localResult = ThreadAppCom.getInstance().addJob(localJobGetAllUser);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    if (localJobGetAllUser.getResult() == 1)
    {
      mUserList.clear();
      mUserNum = localJobGetAllUser.getUserNum();
      for (int i = 0; i < mUserNum; i++)
      {
        UserData localUserData = new UserData(localJobGetAllUser.getUserID(i), localJobGetAllUser.getUserName(i));
        addList(localUserData);
      }
      mList.setListData(mUserList);
      return;
    }
    throw new FatalException("PanelUserList.updateList");
  }
  
  class UserData
  {
    public int mId;
    public String mName;
    
    UserData(int paramInt, String paramString)
    {
      mId = paramInt;
      mName = paramString;
    }
  }
  
  class UserListCellRenderer
    extends ITCListCellRenderer
  {
    UserListCellRenderer() {}
    
    public Component getListCellRendererComponent(JList paramJList, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    {
      setColor(paramBoolean1);
      setText(mName);
      return this;
    }
  }
  
  static abstract interface UserListListener
  {
    public abstract void notifyChangeSelect(int paramInt);
  }
}
