package webitc.gui;

import javax.swing.tree.DefaultTreeModel;
import webitc.common.AppletAbst;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.data.ID;
import webitc.gui.common.IconRes;

public class MainTreeModel
  extends DefaultTreeModel
{
  private TreeNodeAbst mMonitorNode;
  private TreeNodeAbst mRootNode = new TreeNodeAbst(0);
  
  public MainTreeModel()
  {
    super(null);
    setRoot(mRootNode);
    mMonitorNode = new TreeNodeAbst(1, StrRes.getStr("IDS_WEBMAIN_MONITOR_ZONE"), IconRes.getIcon(8));
    mRootNode.add(mMonitorNode);
    if (AppletAbst.getInstance().isSimpleMode() == true) {
      return;
    }
    DataUserInfo localDataUserInfo = DataMgr.getInstance().getLoginUser();
    int i = localDataUserInfo.getUserID();
    if ((i == 1) || (i == 0))
    {
      localTreeNodeAbst = new TreeNodeAbst(3, StrRes.getStr("IDS_SCH_SCHEDULE"), IconRes.getIcon(30));
      mRootNode.add(localTreeNodeAbst);
      if (SystemInfo.getPpdDisplayPattern() != 0)
      {
        localTreeNodeAbst = new TreeNodeAbst(4, StrRes.getStr("IDS_PDV"), IconRes.getIcon(31));
        mRootNode.add(localTreeNodeAbst);
      }
    }
    TreeNodeAbst localTreeNodeAbst = new TreeNodeAbst(7, StrRes.getStr("IDD_SYSMAIN_SYSTEM_SETUP"), IconRes.getIcon(32));
    mRootNode.add(localTreeNodeAbst);
  }
  
  public void setZone()
  {
    mMonitorNode.removeAllChildren();
    DataMgr localDataMgr = DataMgr.getInstance();
    ID[] arrayOfID = localDataMgr.getLoginUser().getZoneIDs();
    for (int i = 0; i < arrayOfID.length; i++)
    {
      String str = localDataMgr.getShortName(arrayOfID[i]);
      TreeNodeZone localTreeNodeZone = new TreeNodeZone(arrayOfID[i], str);
      mMonitorNode.add(localTreeNodeZone);
    }
  }
}
