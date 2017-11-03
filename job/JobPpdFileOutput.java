package webitc.job;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import webitc.common.StrRes;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.VZone;
import webitc.frame.JobWithCancel;
import webitc.gui.common.CommonUse;
import webitc.gui.ppd.PpdCollectionData;
import webitc.gui.ppd.PpdInnerPower;
import webitc.gui.ppd.PpdSumPower;
import webitc.gui.ppd.PpdUnitProp;

public class JobPpdFileOutput
  extends JobWithCancel
{
  private static final String PPD_HOURLY_CSV_FILENAME = "HOURLY.CSV";
  private static final String PPD_ZONE_CSV_FILENAME = "ZONE.CSV";
  private int mCurStep = 0;
  private ArrayList mFailedFiles = new ArrayList();
  private final File mFolder;
  private int mReturn = 0;
  private ArrayList mSuccessFiles = new ArrayList();
  private int mSumStep = 0;
  
  public JobPpdFileOutput(File paramFile)
  {
    mFolder = paramFile;
    checkOverWriteForInit();
  }
  
  private boolean OutputHourlyCSV(File paramFile)
  {
    if ((paramFile.exists()) && (!paramFile.canWrite())) {
      return false;
    }
    EncodingPrintStream localEncodingPrintStream = null;
    try
    {
      localEncodingPrintStream = new EncodingPrintStream(new FileOutputStream(paramFile));
    }
    catch (Exception localException)
    {
      return false;
    }
    String str1 = StrRes.getStr("IDS_KBD_SYM_ON_18");
    localEncodingPrintStream.println(StrRes.getStr("IDS_PPDRESULTOUTPUT_HEADER_1"));
    localEncodingPrintStream.println(StrRes.getStr("IDS_PPDRESULTOUTPUT_HEADER_2"));
    localEncodingPrintStream.println(StrRes.getStr("IDS_PPDRESULTOUTPUT_HEADER_3"));
    DataMgr localDataMgr = DataMgr.getInstance();
    PpdCollectionData localPpdCollectionData = PpdCollectionData.getInstance();
    StringBuffer localStringBuffer1 = new StringBuffer("");
    StringBuffer localStringBuffer2 = new StringBuffer("");
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 64; j++)
      {
        localStringBuffer1.append(convStrForCSV(localDataMgr.getShortName(i, j)));
        localStringBuffer1.append(str1);
        int k = i * 64 + j;
        int m = -1;
        PpdUnitProp localPpdUnitProp1 = localPpdCollectionData.getUnitProp(k);
        if (localPpdUnitProp1 != null) {
          m = localPpdUnitProp1.getIndoorType();
        }
        localStringBuffer2.append(m);
        localStringBuffer2.append(str1);
      }
    }
    localEncodingPrintStream.println(localStringBuffer1.toString());
    localEncodingPrintStream.println(localStringBuffer2.toString());
    Calendar localCalendar1 = localPpdCollectionData.getStartTime();
    Calendar localCalendar2 = localPpdCollectionData.getEndTime();
    Calendar localCalendar3 = localCalendar1;
    int n = 0;
    do
    {
      Calendar localCalendar4 = (Calendar)localCalendar3.clone();
      localCalendar4.add(12, 60);
      String str2 = localCalendar4.get(1) + "." + (localCalendar4.get(2) + 1) + "." + localCalendar4.get(5);
      String str3 = localCalendar4.get(11) + ":" + StrRes.itoa(localCalendar4.get(12), true, 2);
      localEncodingPrintStream.println(str2 + str1 + str3);
      boolean bool = localPpdCollectionData.isValidTime(n);
      StringBuffer localStringBuffer3 = new StringBuffer("");
      StringBuffer localStringBuffer4 = new StringBuffer("");
      StringBuffer localStringBuffer5 = new StringBuffer("");
      StringBuffer localStringBuffer6 = new StringBuffer("");
      for (int i1 = 0; i1 < 2; i1++) {
        for (i2 = 0; i2 < 64; i2++)
        {
          i3 = i1 * 64 + i2;
          if (bool)
          {
            PpdUnitProp localPpdUnitProp2 = localPpdCollectionData.getUnitProp(i3);
            if (localPpdUnitProp2 == null)
            {
              localStringBuffer3.append(0);
              localStringBuffer4.append(0);
              localStringBuffer5.append(0);
              localStringBuffer6.append(0);
            }
            else
            {
              PpdInnerPower localPpdInnerPower = localPpdUnitProp2.getPower(n);
              localStringBuffer3.append(localPpdInnerPower.getDayPower());
              localStringBuffer4.append(localPpdInnerPower.getNightPower());
              localStringBuffer5.append(localPpdInnerPower.getDayIdle());
              localStringBuffer6.append(localPpdInnerPower.getNightIdle());
            }
          }
          localStringBuffer3.append(str1);
          localStringBuffer4.append(str1);
          localStringBuffer5.append(str1);
          localStringBuffer6.append(str1);
        }
      }
      localEncodingPrintStream.println(localStringBuffer3.toString());
      localEncodingPrintStream.println(localStringBuffer4.toString());
      localEncodingPrintStream.println(localStringBuffer5.toString());
      localEncodingPrintStream.println(localStringBuffer6.toString());
      localCalendar3.add(12, 60);
      n++;
      int i2 = localCalendar3.get(1);
      int i3 = localCalendar3.get(2);
      int i4 = localCalendar3.get(5);
      int i5 = localCalendar2.get(1);
      int i6 = localCalendar2.get(2);
      int i7 = localCalendar2.get(5);
      if ((i2 > i5) || ((i2 == i5) && (i3 > i6)) || ((i2 == i5) && (i3 == i6) && (i4 > i7))) {
        break;
      }
    } while (upCount());
    localEncodingPrintStream.close();
    return false;
    localEncodingPrintStream.close();
    return true;
  }
  
  private boolean OutputPowerCSV(File paramFile)
  {
    if ((paramFile.exists()) && (!paramFile.canWrite())) {
      return false;
    }
    EncodingPrintStream localEncodingPrintStream = null;
    try
    {
      localEncodingPrintStream = new EncodingPrintStream(new FileOutputStream(paramFile));
    }
    catch (Exception localException)
    {
      return false;
    }
    localEncodingPrintStream.println(StrRes.getStr("IDS_PPDRESULTOUTPUT_CSV_HEADER"));
    String str1 = StrRes.getStr("IDS_KBD_SYM_ON_18");
    PpdCollectionData localPpdCollectionData = PpdCollectionData.getInstance();
    DataMgr localDataMgr = DataMgr.getInstance();
    PpdSumPower localPpdSumPower1 = localPpdCollectionData.getAllSum();
    long l = localPpdSumPower1.getNoDistributionPower();
    localEncodingPrintStream.println(localPpdCollectionData.getStartDateInt() + str1 + localPpdCollectionData.getCollectionDays() + str1 + 0 + str1 + l + str1 + localPpdCollectionData.getCollectionType());
    localEncodingPrintStream.println("");
    localEncodingPrintStream.println(StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_NO") + str1 + StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_NAME") + str1 + StrRes.getStr("IDS_PPDRESULTOUTPUT_HPW") + str1 + StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_DAY_EP") + str1 + StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_NIGHT_EP") + str1 + StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_D_STOP_EP") + str1 + StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_N_STOP_EP") + str1 + StrRes.getStr("IDS_PPDRESULTOUTPUT_GAS_USED") + str1);
    int[] arrayOfInt = localPpdCollectionData.getUnitNumList();
    for (int i = 0; i < arrayOfInt.length; i++)
    {
      int j = arrayOfInt[i];
      PpdUnitProp localPpdUnitProp = localPpdCollectionData.getUnitProp(j);
      int k = j / 64;
      int m = j - k * 64;
      String str2 = convStrForCSV(localDataMgr.getShortName(k, m));
      String str3 = "&H" + Integer.toHexString(localPpdUnitProp.getHorsePower());
      PpdSumPower localPpdSumPower2 = localPpdUnitProp.getSumPower();
      localEncodingPrintStream.println(j + str1 + str2 + str1 + str3 + str1 + localPpdSumPower2.getDayPower() + str1 + localPpdSumPower2.getNightPower() + str1 + localPpdSumPower2.getDayIdle() + str1 + localPpdSumPower2.getNightIdle() + str1 + localPpdSumPower2.getGas());
      if (!upCount())
      {
        localEncodingPrintStream.close();
        return false;
      }
    }
    localEncodingPrintStream.close();
    return true;
  }
  
  private boolean OutputZoneCSV(File paramFile)
  {
    if ((paramFile.exists()) && (!paramFile.canWrite())) {
      return false;
    }
    EncodingPrintStream localEncodingPrintStream = null;
    try
    {
      localEncodingPrintStream = new EncodingPrintStream(new FileOutputStream(paramFile));
    }
    catch (Exception localException)
    {
      return false;
    }
    String str = StrRes.getStr("IDS_KBD_SYM_ON_18");
    localEncodingPrintStream.println(StrRes.getStr("IDS_PPDRESULTOUTPUT_ZONE_ID") + str + StrRes.getStr("IDS_PPDRESULTOUTPUT_ZONE_NAME"));
    DataMgr localDataMgr = DataMgr.getInstance();
    PpdCollectionData localPpdCollectionData = PpdCollectionData.getInstance();
    ArrayList localArrayList1 = localDataMgr.getZoneIDList();
    Object localObject;
    for (int i = 0; i < localArrayList1.size(); i++)
    {
      ID localID1 = (ID)localArrayList1.get(i);
      localObject = localDataMgr.getZoneFromID(localID1);
      if (localObject != null) {
        localEncodingPrintStream.println(fID + str + convStrForCSV(((VZone)localObject).getShortName()));
      }
      if (!upCount())
      {
        localEncodingPrintStream.close();
        return false;
      }
    }
    localEncodingPrintStream.println("");
    localEncodingPrintStream.println(StrRes.getStr("IDS_PPDRESULTOUTPUT_ZONE_ID") + str + StrRes.getStr("IDS_PPDRESULTOUTPUT_IU_NO"));
    for (int j = 0; j < localArrayList1.size(); j++)
    {
      localObject = (ID)localArrayList1.get(j);
      VZone localVZone = localDataMgr.getZoneFromID((ID)localObject);
      if (localVZone != null)
      {
        ArrayList localArrayList2 = localVZone.getPntList();
        for (int k = 0; k < localArrayList2.size(); k++)
        {
          ID localID2 = (ID)localArrayList2.get(k);
          int m = localPpdCollectionData.getUnitNumFromID(localID2);
          if ((m != -1) && (localPpdCollectionData.isTarget(m))) {
            localEncodingPrintStream.println(fID + str + m);
          }
        }
      }
      if (!upCount())
      {
        localEncodingPrintStream.close();
        return false;
      }
    }
    localEncodingPrintStream.close();
    return true;
  }
  
  private void beforeCancel()
  {
    PpdCollectionData localPpdCollectionData = PpdCollectionData.getInstance();
    File localFile1 = new File(mFolder, "ZONE.CSV");
    String str = localPpdCollectionData.getStartDateInt() + "_" + localPpdCollectionData.getEndDateInt() + ".CSV";
    File localFile2 = new File(mFolder, str);
    File localFile3 = new File(mFolder, "HOURLY.CSV");
    if ((localFile1.exists()) && (localFile1.canWrite())) {
      localFile1.delete();
    }
    if ((localFile2.exists()) && (localFile2.canWrite())) {
      localFile2.delete();
    }
    if ((localFile3.exists()) && (localFile3.canWrite())) {
      localFile3.delete();
    }
    mSuccessFiles.clear();
    mFailedFiles.clear();
  }
  
  private void calcSumStep()
  {
    mSumStep = 0;
    mCurStep = 0;
    DataMgr localDataMgr = DataMgr.getInstance();
    PpdCollectionData localPpdCollectionData = PpdCollectionData.getInstance();
    ArrayList localArrayList = localDataMgr.getZoneIDList();
    mSumStep += localArrayList.size() * 2;
    int[] arrayOfInt = localPpdCollectionData.getUnitNumList();
    mSumStep += arrayOfInt.length;
    mSumStep += localPpdCollectionData.getCollectionDays() * 24;
  }
  
  private void checkOverWriteForInit()
  {
    PpdCollectionData localPpdCollectionData = PpdCollectionData.getInstance();
    File localFile1 = new File(mFolder, "ZONE.CSV");
    String str = localPpdCollectionData.getStartDateInt() + "_" + localPpdCollectionData.getEndDateInt() + ".CSV";
    File localFile2 = new File(mFolder, str);
    File localFile3 = new File(mFolder, "HOURLY.CSV");
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(localFile1);
    localArrayList.add(localFile2);
    localArrayList.add(localFile3);
    if (!checkOverwrite(localArrayList)) {
      mReturn = -1;
    }
  }
  
  private boolean checkOverwrite(ArrayList paramArrayList)
  {
    int i = 0;
    for (int j = 0; j < paramArrayList.size(); j++)
    {
      localObject = (File)paramArrayList.get(j);
      if (((File)localObject).exists()) {
        i++;
      }
    }
    if (i == 0) {
      return true;
    }
    Object localObject = new String[i + 3];
    localObject[0] = StrRes.getStr("IDS_PPDRESULTOUTPUT_FILE_EXIST");
    localObject[1] = StrRes.getStr("IDS_PPDRESULTOUTPUT_OVERWRITE");
    localObject[2] = "";
    int k = 0;
    for (int m = 0; m < paramArrayList.size(); m++)
    {
      File localFile1 = (File)paramArrayList.get(m);
      if (localFile1.exists())
      {
        localObject[(k + 3)] = localFile1.getName();
        k++;
      }
    }
    if (CommonUse.showConfirmDlg(localObject, StrRes.getStr("IDS_SYSHISTORY_FILE_OUTPUT")) == true)
    {
      for (int n = 0; n < paramArrayList.size(); n++)
      {
        File localFile2 = (File)paramArrayList.get(n);
        if ((localFile2.exists()) && (localFile2.canWrite())) {
          localFile2.delete();
        }
      }
      return true;
    }
    return false;
  }
  
  private String convStrForCSV(String paramString)
  {
    String str = "\"'";
    str = str + paramString.replace('"', '\'');
    str = str + "\"";
    return str;
  }
  
  public ArrayList getFailedFiles()
  {
    return mFailedFiles;
  }
  
  public int getResult()
  {
    return mReturn;
  }
  
  public ArrayList getSuccessFiles()
  {
    return mSuccessFiles;
  }
  
  protected void runPrivate()
    throws Exception
  {
    if (mReturn == -1) {
      return;
    }
    PpdCollectionData localPpdCollectionData = PpdCollectionData.getInstance();
    File localFile1 = new File(mFolder, "ZONE.CSV");
    String str = localPpdCollectionData.getStartDateInt() + "_" + localPpdCollectionData.getEndDateInt() + ".CSV";
    File localFile2 = new File(mFolder, str);
    File localFile3 = new File(mFolder, "HOURLY.CSV");
    mSuccessFiles.clear();
    mFailedFiles.clear();
    calcSumStep();
    setProgress(0);
    if (OutputZoneCSV(localFile1) == true) {
      mSuccessFiles.add(localFile1.getName());
    } else {
      mFailedFiles.add(localFile1.getName());
    }
    if (isCanceled())
    {
      mReturn = -1;
      beforeCancel();
      return;
    }
    if (mFailedFiles.size() > 0) {
      mFailedFiles.add(localFile2.getName());
    } else if (OutputPowerCSV(localFile2) == true) {
      mSuccessFiles.add(localFile2.getName());
    } else {
      mFailedFiles.add(localFile2.getName());
    }
    if (isCanceled())
    {
      mReturn = -1;
      beforeCancel();
      return;
    }
    if (mFailedFiles.size() > 0) {
      mFailedFiles.add(localFile3.getName());
    } else if (OutputHourlyCSV(localFile3) == true) {
      mSuccessFiles.add(localFile3.getName());
    } else {
      mFailedFiles.add(localFile3.getName());
    }
    if (isCanceled())
    {
      mReturn = -1;
      beforeCancel();
      return;
    }
    setProgress(100);
    mReturn = 1;
  }
  
  private boolean upCount()
  {
    if (isCanceled()) {
      return false;
    }
    mCurStep += 1;
    setProgress(mCurStep * 100 / mSumStep);
    return true;
  }
}
