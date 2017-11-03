package webitc.gui.ppd;

import javax.swing.table.DefaultTableModel;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumPpdCollectionType;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.VZone;
import webitc.gui.common.JScrollTable;

public class PpdTable
  extends JScrollTable
{
  private DefaultTableModel mModel = new DefaultTableModel();
  
  public PpdTable(EnumTableID paramEnumTableID)
  {
    super(paramEnumTableID);
    initPpdTable();
  }
  
  private void initPpdTable()
  {
    setSelectionMode(0);
    updatePpdTable(EnumPpdCollectionType.INNER);
  }
  
  public void updatePpdTable(EnumPpdCollectionType paramEnumPpdCollectionType)
  {
    mModel.setColumnCount(0);
    mModel.setRowCount(0);
    int i = SystemInfo.getPpdDisplayPattern();
    int j = 0;
    if ((paramEnumPpdCollectionType == EnumPpdCollectionType.INNER) || (paramEnumPpdCollectionType == EnumPpdCollectionType.ZONE))
    {
      if (paramEnumPpdCollectionType == EnumPpdCollectionType.INNER)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_NAME"));
        j++;
      }
      else
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULTOUTPUT_ZONE_NAME"));
        j++;
      }
      if ((i & 0x1) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_EP"));
        j++;
      }
      if ((i & 0x2) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_DAY_EP"));
        j++;
      }
      if ((i & 0x4) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_NIGHT_EP"));
        j++;
      }
      if ((i & 0x10) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULTOUTPUT_GAS_USED"));
        j++;
      }
      if ((i & 0x100) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_STOP_EP"));
        j++;
      }
      if ((i & 0x200) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_D_STOP_EP"));
        j++;
      }
      if ((i & 0x400) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_N_STOP_EP"));
        j++;
      }
    }
    else if (paramEnumPpdCollectionType == EnumPpdCollectionType.SUM)
    {
      if ((i & 0x1) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULT_TOTALUSED"));
        j++;
      }
      if ((i & 0x2) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULT_DAYTOTALUSED"));
        j++;
      }
      if ((i & 0x4) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULT_NIGHTTOTALUSED"));
        j++;
      }
      if ((i & 0x10) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULTOUTPUT_TOTAL_GAS"));
        j++;
      }
      if ((i & 0x100) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULT_TOTALIDLE"));
        j++;
      }
      if ((i & 0x200) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULT_DAYTOTALIDLE"));
        j++;
      }
      if ((i & 0x400) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULT_NIGHTTOTALIDLE"));
        j++;
      }
      if ((i & 0x1000) != 0)
      {
        mModel.addColumn(StrRes.getStr("IDS_PPDRESULT_NOTDISTRIBUTABLE"));
        j++;
      }
    }
    else
    {
      return;
    }
    setModel(mModel);
    PpdCollectionData localPpdCollectionData = PpdCollectionData.getInstance();
    DataMgr localDataMgr = DataMgr.getInstance();
    if (localPpdCollectionData == null) {
      return;
    }
    int k;
    Object localObject1;
    int i1;
    PpdSumPower localPpdSumPower;
    if (paramEnumPpdCollectionType == EnumPpdCollectionType.INNER)
    {
      for (k = 0; k < 128; k++) {
        if (localPpdCollectionData.isTarget(k))
        {
          localObject1 = new String[j];
          int n = 0;
          i1 = k / 64;
          int i2 = k - i1 * 64;
          localObject1[n] = localDataMgr.getShortName(i1, i2);
          n++;
          localPpdSumPower = localPpdCollectionData.getUnitSum(k);
          if (((i & 0x1) != 0) || ((i & 0x2) != 0))
          {
            localObject1[n] = PpdCollectionData.getKWattStr(localPpdSumPower.getDayPower());
            n++;
          }
          if ((i & 0x4) != 0)
          {
            localObject1[n] = PpdCollectionData.getKWattStr(localPpdSumPower.getNightPower());
            n++;
          }
          if ((i & 0x10) != 0)
          {
            localObject1[n] = PpdCollectionData.getKWattStr(localPpdSumPower.getGas());
            n++;
          }
          if (((i & 0x100) != 0) || ((i & 0x200) != 0))
          {
            localObject1[n] = PpdCollectionData.getKWattStr(localPpdSumPower.getDayIdle());
            n++;
          }
          if ((i & 0x400) != 0)
          {
            localObject1[n] = PpdCollectionData.getKWattStr(localPpdSumPower.getNightIdle());
            n++;
          }
          mModel.addRow((Object[])localObject1);
        }
      }
    }
    else
    {
      Object localObject2;
      if (paramEnumPpdCollectionType == EnumPpdCollectionType.ZONE)
      {
        for (k = 0; k < localDataMgr.getZoneNum(); k++)
        {
          localObject1 = localDataMgr.getZone(k);
          if (localObject1 != null)
          {
            localObject2 = new String[j];
            i1 = 0;
            ID localID = ((VZone)localObject1).getID();
            localObject2[i1] = ((VZone)localObject1).getShortName();
            i1++;
            localPpdSumPower = localPpdCollectionData.getZoneSum(localID);
            if (((i & 0x1) != 0) || ((i & 0x2) != 0))
            {
              localObject2[i1] = PpdCollectionData.getKWattStr(localPpdSumPower.getDayPower());
              i1++;
            }
            if ((i & 0x4) != 0)
            {
              localObject2[i1] = PpdCollectionData.getKWattStr(localPpdSumPower.getNightPower());
              i1++;
            }
            if ((i & 0x10) != 0)
            {
              localObject2[i1] = PpdCollectionData.getKWattStr(localPpdSumPower.getGas());
              i1++;
            }
            if (((i & 0x100) != 0) || ((i & 0x200) != 0))
            {
              localObject2[i1] = PpdCollectionData.getKWattStr(localPpdSumPower.getDayIdle());
              i1++;
            }
            if ((i & 0x400) != 0)
            {
              localObject2[i1] = PpdCollectionData.getKWattStr(localPpdSumPower.getNightIdle());
              i1++;
            }
            mModel.addRow((Object[])localObject2);
          }
        }
      }
      else if (paramEnumPpdCollectionType == EnumPpdCollectionType.SUM)
      {
        String[] arrayOfString = new String[j];
        int m = 0;
        localObject2 = localPpdCollectionData.getAllSum();
        if (((i & 0x1) != 0) || ((i & 0x2) != 0))
        {
          arrayOfString[m] = PpdCollectionData.getKWattStr(((PpdSumPower)localObject2).getDayPower());
          m++;
        }
        if ((i & 0x4) != 0)
        {
          arrayOfString[m] = PpdCollectionData.getKWattStr(((PpdSumPower)localObject2).getNightPower());
          m++;
        }
        if ((i & 0x10) != 0)
        {
          arrayOfString[m] = PpdCollectionData.getKWattStr(((PpdSumPower)localObject2).getGas());
          m++;
        }
        if (((i & 0x100) != 0) || ((i & 0x200) != 0))
        {
          arrayOfString[m] = PpdCollectionData.getKWattStr(((PpdSumPower)localObject2).getDayIdle());
          m++;
        }
        if ((i & 0x400) != 0)
        {
          arrayOfString[m] = PpdCollectionData.getKWattStr(((PpdSumPower)localObject2).getNightIdle());
          m++;
        }
        if ((i & 0x1000) != 0) {
          arrayOfString[m] = PpdCollectionData.getKWattStr(((PpdSumPower)localObject2).getNoDistributionPower());
        }
        mModel.addRow(arrayOfString);
      }
    }
  }
}
