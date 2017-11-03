package webitc.gui.system;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.data.ID;
import webitc.gui.common.DlgAbstract;

public class DlgZoneEdit
  extends DlgAbstract
  implements PanelOkCancel.PanelOkCancelListener, PanelVerticalButtons.SelectedListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  PanelVerticalButtons mAddDel = new PanelVerticalButtons(this, StrRes.getStr("IDS_COMMON_LEFT"), StrRes.getStr("IDS_COMMON_RIGHT"), StrRes.getStr("IDS_COMMON_ADD"), StrRes.getStr("IDS_COMMON_DELETE"));
  PanelOkCancel mButtons = new PanelOkCancel(this);
  JLabel mUserLabel = new JLabel();
  JTextField mUserName = new JTextField();
  PanelEditableZoneList mWatchList = new PanelEditableZoneList();
  PanelZoneList mZoneList = new PanelZoneList(EnumTableID.DLG_ZONE_EDIT, StrRes.getStr("IDD_SYSCONFMAIN_MENBER_LIST"), true);
  
  public DlgZoneEdit(String paramString, ID[] paramArrayOfID)
  {
    try
    {
      jbInit(paramString, paramArrayOfID);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void errorPerformed()
  {
    mWatchList.setEnabled(false);
    mZoneList.setEnabled(false);
    mAddDel.setEnabled(1, false);
    mAddDel.setEnabled(2, false);
    mButtons.setEnabled(1, false);
  }
  
  public ID[] getEditedZoneList()
  {
    return mWatchList.getZoneList();
  }
  
  private void jbInit(String paramString, ID[] paramArrayOfID)
    throws Exception
  {
    setTitle(StrRes.getStr("IDS_SYSTEM_EDITZONE"));
    mUserLabel.setText(StrRes.getStr("IDS_COMMON_USERNAME"));
    getContentPane().setLayout(gridBagLayout1);
    mUserName.setMinimumSize(new Dimension(160, 20));
    mUserName.setPreferredSize(new Dimension(160, 20));
    getContentPane().add(mUserLabel, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mWatchList, new GridBagConstraints(0, 1, 2, 1, 0.5D, 1.0D, 10, 1, new Insets(10, 10, 10, 5), 0, 0));
    getContentPane().add(mAddDel, new GridBagConstraints(2, 1, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(10, 0, 0, 0), 0, 0));
    getContentPane().add(mUserName, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mZoneList, new GridBagConstraints(3, 1, 2, 1, 0.5D, 1.0D, 10, 1, new Insets(10, 5, 10, 10), 0, 0));
    getContentPane().add(mButtons, new GridBagConstraints(4, 2, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(10, 10, 6, 12), 0, 0));
    mUserName.setEditable(false);
    mZoneList.updateTable();
    mUserName.setText(paramString);
    mWatchList.setZoneList(paramArrayOfID);
    for (int i = 0; i < paramArrayOfID.length; i++) {
      mZoneList.removeZone(paramArrayOfID[i]);
    }
    setButtonProperties();
  }
  
  public void notifyButton1Selected()
  {
    ID localID = mZoneList.getSelectedZoneID();
    mWatchList.addZone(localID);
    mZoneList.removeZone(localID);
    setButtonProperties();
  }
  
  public void notifyButton2Selected()
  {
    ID localID = mWatchList.getSelectedZoneID();
    mZoneList.addZone(localID);
    mWatchList.removeZone(localID);
    setButtonProperties();
  }
  
  public void notifyCancelSelected()
  {
    closeDialog(false);
  }
  
  public void notifyOkSelected()
  {
    closeDialog(true);
  }
  
  private void setButtonProperties()
  {
    if (mZoneList.getRowCount() == 0) {
      mAddDel.setEnabled(1, false);
    } else {
      mAddDel.setEnabled(1, true);
    }
    if (mWatchList.getRowCount() == 0) {
      mAddDel.setEnabled(2, false);
    } else {
      mAddDel.setEnabled(2, true);
    }
  }
}
