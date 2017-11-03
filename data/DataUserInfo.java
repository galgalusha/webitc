package webitc.data;

import java.util.ArrayList;

public final class DataUserInfo
  implements Comparable
{
  private String mPassword = new String();
  private int mUserID;
  private String mUserName = new String();
  private ArrayList mZoneIDList = new ArrayList();
  
  public DataUserInfo(int paramInt, String paramString1, String paramString2)
  {
    mUserID = paramInt;
    mUserName = paramString1;
    mPassword = paramString2;
  }
  
  public void addZoneID(int paramInt)
  {
    Integer localInteger = new Integer(paramInt);
    mZoneIDList.add(localInteger);
  }
  
  public int compareTo(Object paramObject)
  {
    DataUserInfo localDataUserInfo = (DataUserInfo)paramObject;
    int i = getUserName().compareToIgnoreCase(localDataUserInfo.getUserName());
    if (i != 0) {
      return i;
    }
    return getUserName().compareTo(localDataUserInfo.getUserName());
  }
  
  public String getPassword()
  {
    return mPassword;
  }
  
  public int getUserID()
  {
    return mUserID;
  }
  
  public String getUserName()
  {
    return mUserName;
  }
  
  public ArrayList getZoneArray()
  {
    return mZoneIDList;
  }
  
  public ArrayList getZoneIDArray()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < mZoneIDList.size(); i++)
    {
      int j = ((Integer)mZoneIDList.get(i)).intValue();
      localArrayList.add(new ID(1, j));
    }
    return localArrayList;
  }
  
  public ID[] getZoneIDs()
  {
    ID[] arrayOfID = new ID[mZoneIDList.size()];
    for (int i = 0; i < mZoneIDList.size(); i++)
    {
      int j = ((Integer)mZoneIDList.get(i)).intValue();
      arrayOfID[i] = new ID(1, j);
    }
    return arrayOfID;
  }
}
