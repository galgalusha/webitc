package webitc.gui.ppd;

public class PpdSumPower
{
  public static final PpdSumPower InvalidPower = new PpdSumPower();
  private long mDayIdle = 0L;
  private long mDayPower = 0L;
  private long mGas = 0L;
  private long mNightIdle = 0L;
  private long mNightPower = 0L;
  private long mNoDistributionPower = 0L;
  
  public PpdSumPower() {}
  
  public void addPower(PpdSumPower paramPpdSumPower)
  {
    mDayPower += paramPpdSumPower.getDayPower();
    mDayIdle += paramPpdSumPower.getDayIdle();
    mNightPower += paramPpdSumPower.getNightPower();
    mNightIdle += paramPpdSumPower.getNightIdle();
    mGas += paramPpdSumPower.getGas();
    mNoDistributionPower += paramPpdSumPower.getNoDistributionPower();
  }
  
  public void addPower(int paramInt, PpdInnerPower paramPpdInnerPower)
  {
    if (paramInt == -1) {
      return;
    }
    mDayPower += paramPpdInnerPower.getDayPower();
    if (paramInt == 1) {
      mNightPower += paramPpdInnerPower.getNightPower();
    } else {
      mGas += paramPpdInnerPower.getNightPower();
    }
    mDayIdle += paramPpdInnerPower.getDayIdle();
    if (paramInt == 1) {
      mNightIdle += paramPpdInnerPower.getNightIdle();
    }
  }
  
  public long getDayIdle()
  {
    return mDayIdle;
  }
  
  public long getDayPower()
  {
    return mDayPower;
  }
  
  public long getGas()
  {
    return mGas;
  }
  
  public long getNightIdle()
  {
    return mNightIdle;
  }
  
  public long getNightPower()
  {
    return mNightPower;
  }
  
  public long getNoDistributionPower()
  {
    return mNoDistributionPower;
  }
  
  public void setNoDistributionPower(long paramLong)
  {
    mNoDistributionPower = paramLong;
  }
  
  public void setTSC(int paramInt1, int paramInt2)
  {
    mDayPower = paramInt1;
    mNightPower = paramInt2;
  }
}
