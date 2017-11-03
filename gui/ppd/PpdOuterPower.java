package webitc.gui.ppd;

public class PpdOuterPower
{
  private long mDayPower = 0L;
  private int mKeitou = 0;
  private long mNightPower = 0L;
  
  public PpdOuterPower(int paramInt1, int paramInt2, int paramInt3)
  {
    mKeitou = paramInt1;
    mDayPower = paramInt2;
    mNightPower = paramInt3;
  }
  
  public void addPower(PpdOuterPower paramPpdOuterPower)
  {
    mDayPower += paramPpdOuterPower.getDayPower();
    mNightPower += paramPpdOuterPower.getNightPower();
  }
  
  public long getDayPower()
  {
    return mDayPower;
  }
  
  public int getKeitou()
  {
    return mKeitou;
  }
  
  public long getNightPower()
  {
    return mNightPower;
  }
}
