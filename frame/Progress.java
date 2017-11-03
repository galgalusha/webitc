package webitc.frame;

public class Progress
{
  private int mProgress = 0;
  
  public Progress() {}
  
  public int getProgress()
  {
    return mProgress;
  }
  
  public void setProgress(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 100)) {
      return;
    }
    mProgress = paramInt;
  }
}
