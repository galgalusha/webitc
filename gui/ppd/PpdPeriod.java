package webitc.gui.ppd;

import java.nio.ByteBuffer;

public class PpdPeriod
{
  char mEndHour = '\000';
  char mEndMin = '\000';
  boolean mIsNightRate = false;
  char mStartHour = '\000';
  char mStartMin = '\000';
  
  public PpdPeriod(boolean paramBoolean)
  {
    mIsNightRate = paramBoolean;
  }
  
  public char getEndHour()
  {
    return mEndHour;
  }
  
  public char getEndMin()
  {
    return mEndMin;
  }
  
  public int getInt()
  {
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[3] = ((byte)getStartHour());
    arrayOfByte[2] = ((byte)getStartMin());
    arrayOfByte[1] = ((byte)getEndHour());
    arrayOfByte[0] = ((byte)getEndMin());
    ByteBuffer localByteBuffer = ByteBuffer.wrap(arrayOfByte);
    int i = localByteBuffer.getInt(0);
    return i;
  }
  
  public char getStartHour()
  {
    return mStartHour;
  }
  
  public char getStartMin()
  {
    return mStartMin;
  }
  
  public void setEndHour(char paramChar)
  {
    if (paramChar < 0) {
      paramChar = '\000';
    }
    if (mIsNightRate == true)
    {
      if (paramChar > '\030') {
        paramChar = '\000';
      }
    }
    else if (paramChar > '\027') {
      paramChar = '\000';
    }
    mEndHour = paramChar;
  }
  
  public void setEndMin(char paramChar)
  {
    if (paramChar < 0) {
      paramChar = '\000';
    }
    if (paramChar > ';') {
      paramChar = '\000';
    }
    mEndMin = paramChar;
  }
  
  public void setInt(int paramInt)
  {
    byte[] arrayOfByte = new byte[4];
    ByteBuffer localByteBuffer = ByteBuffer.wrap(arrayOfByte);
    localByteBuffer.putInt(0, paramInt);
    setStartHour((char)arrayOfByte[3]);
    setStartMin((char)arrayOfByte[2]);
    setEndHour((char)arrayOfByte[1]);
    setEndMin((char)arrayOfByte[0]);
  }
  
  public void setStartHour(char paramChar)
  {
    if (paramChar < 0) {
      paramChar = '\000';
    }
    if (paramChar > '\027') {
      paramChar = '\000';
    }
    mStartHour = paramChar;
  }
  
  public void setStartMin(char paramChar)
  {
    if (paramChar < 0) {
      paramChar = '\000';
    }
    if (paramChar > ';') {
      paramChar = '\000';
    }
    mStartMin = paramChar;
  }
}
