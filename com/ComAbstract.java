package webitc.com;

import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
import webitc.common.SystemInfo;

public abstract class ComAbstract
{
  public static final int RET_NG = 0;
  public static final int RET_OK = 1;
  public static final int fMaxReceiveBufferSize = 71680;
  public static final int fMaxSendBufferSize = 15360;
  public static final int fPcHeaderSize = 32;
  public static final boolean isLittleEndian = true;
  protected ByteBuffer mReceiveBuffer = ByteBuffer.wrap(new byte[71680]);
  protected ByteBuffer mSendBuffer = ByteBuffer.wrap(new byte['㰀']);
  
  public ComAbstract()
  {
    resetBuffer();
  }
  
  public abstract boolean afterReceive();
  
  public static String getAscii(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int j = 0; j < paramInt2; j++)
    {
      int i = paramByteBuffer.get(paramInt1);
      if (i == 0) {
        return localStringBuffer.toString();
      }
      localStringBuffer.append((char)i);
      paramInt1++;
    }
    return localStringBuffer.toString();
  }
  
  public static boolean getBoolean(ByteBuffer paramByteBuffer, int paramInt)
  {
    int i = 0;
    return paramByteBuffer.get(paramInt) == 1;
  }
  
  public static float getFloat(ByteBuffer paramByteBuffer, int paramInt)
  {
    float f = 0.0F;
    int i = getInt(paramByteBuffer, paramInt);
    f = Float.intBitsToFloat(i);
    return f;
  }
  
  public static int getInt(ByteBuffer paramByteBuffer, int paramInt)
  {
    int i = 0;
    i = (paramByteBuffer.get(paramInt) & 0xFF) + (paramByteBuffer.get(paramInt + 1) << 8 & 0xFF00) + (paramByteBuffer.get(paramInt + 2) << 16 & 0xFF0000) + (paramByteBuffer.get(paramInt + 3) << 24 & 0xFF000000);
    return i;
  }
  
  protected ByteBuffer getReceiveBuffer()
  {
    return mReceiveBuffer;
  }
  
  protected int getRecvArg1()
  {
    return getInt(mReceiveBuffer, 16);
  }
  
  protected int getRecvArg2()
  {
    return getInt(mReceiveBuffer, 20);
  }
  
  protected int getRecvArg3()
  {
    return getInt(mReceiveBuffer, 24);
  }
  
  protected int getRecvArg4()
  {
    return getInt(mReceiveBuffer, 28);
  }
  
  public Calendar getRecvCalendar()
  {
    Calendar localCalendar = SystemInfo.getLocalTime(getRecvTime());
    return localCalendar;
  }
  
  protected int getRecvCom()
  {
    return getInt(mReceiveBuffer, 4);
  }
  
  protected int getRecvId()
  {
    return getInt(mReceiveBuffer, 8);
  }
  
  protected int getRecvSize()
  {
    return getInt(mReceiveBuffer, 0);
  }
  
  public int getRecvTime()
  {
    return getInt(mReceiveBuffer, 12);
  }
  
  public int getReturn()
  {
    return getRecvArg1();
  }
  
  protected int getSendArg1()
  {
    return getInt(mSendBuffer, 16);
  }
  
  protected int getSendArg2()
  {
    return getInt(mSendBuffer, 20);
  }
  
  protected int getSendArg3()
  {
    return getInt(mSendBuffer, 24);
  }
  
  protected int getSendArg4()
  {
    return getInt(mSendBuffer, 28);
  }
  
  protected ByteBuffer getSendBuffer()
  {
    return mSendBuffer;
  }
  
  protected int getSendCom()
  {
    return getInt(mSendBuffer, 4);
  }
  
  public int getSendId()
  {
    return getInt(mSendBuffer, 8);
  }
  
  protected int getSendSize()
  {
    return getInt(mSendBuffer, 0);
  }
  
  protected int getSendTime()
  {
    return getInt(mSendBuffer, 12);
  }
  
  public static short getShort(ByteBuffer paramByteBuffer, int paramInt)
  {
    short s = 0;
    s = (short)((paramByteBuffer.get(paramInt) & 0xFF) + (paramByteBuffer.get(paramInt + 1) << 8 & 0xFF00));
    return s;
  }
  
  public static String getUcs2(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramInt2 / 2; i++)
    {
      char c = (char)((paramByteBuffer.get(paramInt1) & 0xFF) + (paramByteBuffer.get(paramInt1 + 1) << 8 & 0xFF00));
      if (c == 0) {
        return localStringBuffer.toString();
      }
      localStringBuffer.append(c);
      paramInt1 += 2;
    }
    return localStringBuffer.toString();
  }
  
  public void resetBuffer()
  {
    mSendBuffer.clear();
    mReceiveBuffer.clear();
    setSendSize(0);
    long l = new Date().getTime();
    setSendTime((int)(l / 1000L));
  }
  
  public static void setAscii(String paramString, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("US-ASCII");
      for (int i = 0; i < paramInt2; i++)
      {
        if (arrayOfByte.length <= i) {
          break;
        }
        paramByteBuffer.put(paramInt1, arrayOfByte[i]);
        paramInt1++;
      }
    }
    catch (Exception localException)
    {
      System.out.println(localException.toString());
    }
  }
  
  public static void setBoolean(boolean paramBoolean, ByteBuffer paramByteBuffer, int paramInt)
  {
    if (paramBoolean == true) {
      paramByteBuffer.put(paramInt, (byte)1);
    } else {
      paramByteBuffer.put(paramInt, (byte)0);
    }
  }
  
  public static void setFloat(float paramFloat, ByteBuffer paramByteBuffer, int paramInt)
  {
    int i = Float.floatToIntBits(paramFloat);
    setInt(i, paramByteBuffer, paramInt);
  }
  
  public static void setInt(int paramInt1, ByteBuffer paramByteBuffer, int paramInt2)
  {
    paramByteBuffer.put(paramInt2, (byte)(paramInt1 & 0xFF));
    paramByteBuffer.put(paramInt2 + 1, (byte)(paramInt1 >> 8 & 0xFF));
    paramByteBuffer.put(paramInt2 + 2, (byte)(paramInt1 >> 16 & 0xFF));
    paramByteBuffer.put(paramInt2 + 3, (byte)(paramInt1 >> 24 & 0xFF));
  }
  
  protected void setRecvArg1(int paramInt)
  {
    setInt(paramInt, mReceiveBuffer, 16);
  }
  
  protected void setRecvArg2(int paramInt)
  {
    setInt(paramInt, mReceiveBuffer, 20);
  }
  
  protected void setRecvArg3(int paramInt)
  {
    setInt(paramInt, mReceiveBuffer, 24);
  }
  
  protected void setRecvArg4(int paramInt)
  {
    setInt(paramInt, mReceiveBuffer, 28);
  }
  
  protected void setRecvCom(int paramInt)
  {
    setInt(paramInt, mReceiveBuffer, 4);
  }
  
  protected void setRecvId(int paramInt)
  {
    setInt(paramInt, mReceiveBuffer, 8);
  }
  
  protected void setRecvSize(int paramInt)
  {
    setInt(paramInt, mReceiveBuffer, 0);
  }
  
  protected void setRecvTime(Calendar paramCalendar)
  {
    setInt((int)(paramCalendar.getTimeInMillis() / 1000L), mReceiveBuffer, 12);
  }
  
  protected void setSendArg1(int paramInt)
  {
    setInt(paramInt, mSendBuffer, 16);
  }
  
  protected void setSendArg2(int paramInt)
  {
    setInt(paramInt, mSendBuffer, 20);
  }
  
  protected void setSendArg3(int paramInt)
  {
    setInt(paramInt, mSendBuffer, 24);
  }
  
  protected void setSendArg4(int paramInt)
  {
    setInt(paramInt, mSendBuffer, 28);
  }
  
  protected void setSendCom(int paramInt)
  {
    setInt(paramInt, mSendBuffer, 4);
  }
  
  public void setSendId(int paramInt)
  {
    setInt(paramInt, mSendBuffer, 8);
  }
  
  protected void setSendSize(int paramInt)
  {
    setInt(paramInt, mSendBuffer, 0);
  }
  
  protected void setSendTime(int paramInt)
  {
    setInt(paramInt, mSendBuffer, 12);
  }
  
  public static void setShort(short paramShort, ByteBuffer paramByteBuffer, int paramInt)
  {
    paramByteBuffer.put(paramInt, (byte)(paramShort & 0xFF));
    paramByteBuffer.put(paramInt + 1, (byte)(paramShort >> 8 & 0xFF));
  }
  
  public static void setUcs2(String paramString, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2)
  {
    for (int i = 0; i < paramInt2 / 2; i++)
    {
      j = paramString.length();
      if (paramString.length() <= i) {
        break;
      }
      int k = paramString.charAt(i);
      paramByteBuffer.put(paramInt1++, (byte)(k & 0xFF));
      paramByteBuffer.put(paramInt1++, (byte)((k & 0xFF00) >> 8));
    }
    for (int j = i; j < paramInt2; j++)
    {
      paramByteBuffer.put(paramInt1++, (byte)0);
      paramByteBuffer.put(paramInt1++, (byte)0);
    }
  }
}
