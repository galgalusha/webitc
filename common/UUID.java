package webitc.common;

import java.io.PrintStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

public final class UUID
{
  private static long DELAY;
  private static String address;
  private static int hostUnique = new Object().hashCode();
  private static long lastTime;
  private static Object mutex = new Object();
  private long time;
  private int unique;
  
  static
  {
    lastTime = System.currentTimeMillis();
    DELAY = 10L;
    try
    {
      String str = InetAddress.getLocalHost().getHostAddress();
      Md5 localMd5 = new Md5(str);
      localMd5.processString();
      address = localMd5.getStringDigest();
    }
    catch (UnknownHostException localUnknownHostException)
    {
      address = generateNoNetworkID();
    }
  }
  
  public UUID()
  {
    synchronized (mutex)
    {
      for (int i = 0; i == 0; i = 1)
      {
        time = System.currentTimeMillis();
        if (time < lastTime + DELAY) {
          try
          {
            Thread.sleep(DELAY);
          }
          catch (InterruptedException localInterruptedException) {}
        }
        lastTime = time;
      }
      unique = hostUnique;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject != null) && ((paramObject instanceof UUID)))
    {
      UUID localUUID = (UUID)paramObject;
      return (unique == unique) && (time == time) && (address.equals(address));
    }
    return false;
  }
  
  private static String generateNoNetworkID()
  {
    String str = Thread.activeCount() + System.getProperty("os.version") + System.getProperty("user.name") + System.getProperty("java.version");
    System.out.println(str);
    Md5 localMd5 = new Md5(str);
    localMd5.processString();
    return localMd5.getStringDigest();
  }
  
  public int getArg1()
  {
    return unique;
  }
  
  public int getArg2()
  {
    return (int)(time & 0xFFFFFFFFFFFFFFFF);
  }
  
  public int getArg3()
  {
    BigInteger localBigInteger = new BigInteger(address, 16);
    return localBigInteger.intValue();
  }
  
  public int getArg4()
  {
    BigInteger localBigInteger = new BigInteger(address, 16);
    return localBigInteger.shiftRight(32).intValue();
  }
  
  public String toString()
  {
    return Integer.toString(unique, 16) + "-" + Long.toString(time, 16) + "-" + address;
  }
}
