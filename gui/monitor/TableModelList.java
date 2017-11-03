package webitc.gui.monitor;

import javax.swing.table.DefaultTableModel;
import webitc.common.StrRes;
import webitc.data.DataMgr;
import webitc.data.ID;

class TableModelList
  extends DefaultTableModel
{
  public static final int COL_AIRTEMP = 4;
  public static final int COL_DRVMODE = 3;
  public static final int COL_ERR_CODE = 5;
  public static final int COL_NAME = 1;
  public static final int COL_SETPOINT = 2;
  public static final int COL_STATUS = 0;
  public static final int TOTAL_COLUMN = 6;
  private final Object[] mTblData = new Object[6];
  
  public TableModelList()
  {
    addColumn(StrRes.getStr("IDS_COMMON_STATUS"));
    addColumn(StrRes.getStr("IDS_COMMON_NAME"));
    addColumn(StrRes.getStr("IDS_COMMON_SETTEMP"));
    addColumn(StrRes.getStr("IDS_WEBMAIN_LIST_DRVMODE"));
    addColumn(StrRes.getStr("IDS_COMMON_ROOMTEMP"));
    addColumn(StrRes.getStr("IDS_COMMON_ERR_CODE"));
  }
  
  public void addData(ID paramID)
  {
    DataMgr localDataMgr = DataMgr.getInstance();
    mTblData[0] = paramID;
    mTblData[1] = localDataMgr.getShortName(paramID);
    mTblData[2] = paramID;
    mTblData[3] = paramID;
    mTblData[4] = paramID;
    mTblData[5] = paramID;
    addRow(mTblData);
  }
}
