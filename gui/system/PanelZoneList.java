package webitc.gui.system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import webitc.common.FatalException;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.gui.common.BorderRes;
import webitc.gui.common.CommonUse;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.ZoneTable;
import webitc.job.JobGetWatchZone;

public class PanelZoneList
  extends PanelAbstract
{
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private ZoneTable mTable;
  
  public PanelZoneList(EnumTableID paramEnumTableID, String paramString, boolean paramBoolean)
  {
    try
    {
      mTable = new ZoneTable(paramEnumTableID);
      jbInit(paramString);
      mTable.setSort(paramBoolean);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void addZone(ID paramID)
  {
    mTable.addRow(paramID);
  }
  
  public ID[] getAllZoneIDs()
  {
    int i = mTable.getRowCount();
    ID[] arrayOfID = new ID[i];
    for (int j = 0; j < i; j++) {
      arrayOfID[j] = ((ID)mTable.getValueAt(j, 0));
    }
    return arrayOfID;
  }
  
  public int getRowCount()
  {
    return mTable.getRowCount();
  }
  
  public ID getSelectedZoneID()
  {
    return mTable.getSelectedID();
  }
  
  private void jbInit(String paramString)
    throws Exception
  {
    setBorder(BorderRes.createTitledBorder(paramString));
    setLayout(gridBagLayout1);
    add(mTable.getScrollPane(), new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(10, 5, 10, 5), 0, 0));
  }
  
  public void removeAllRow()
  {
    mTable.removeAllRow();
  }
  
  public void removeZone(ID paramID)
  {
    mTable.removeRow(paramID);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    mTable.setEnabled(paramBoolean);
  }
  
  public void setVisibleSelection(boolean paramBoolean)
  {
    mTable.setVisibleSelection(paramBoolean);
  }
  
  public void updateTable(int paramInt)
  {
    if (paramInt < 0) {
      return;
    }
    mTable.removeAllRow();
    JobGetWatchZone localJobGetWatchZone = new JobGetWatchZone(paramInt);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobGetWatchZone);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    if (localJobGetWatchZone.getResult() == 1)
    {
      int i = localJobGetWatchZone.getZoneNum();
      int[] arrayOfInt = new int[i];
      for (int j = 0; j < i; j++) {
        arrayOfInt[j] = localJobGetWatchZone.getZoneID(j);
      }
      mTable.setZoneList(arrayOfInt);
      return;
    }
    throw new FatalException("PanelZoneList.updateList");
  }
  
  public void updateTable()
  {
    mTable.removeAllRow();
    ArrayList localArrayList = DataMgr.getInstance().getZoneIDList();
    mTable.setZoneList(localArrayList);
  }
}
