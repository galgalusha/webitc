package webitc.com;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import javax.swing.SwingUtilities;
import webitc.common.FatalException;

public final class SockHttp
{
  private static int CONNECT_STATE_DURING = 2;
  private static int CONNECT_STATE_END = 1;
  private static final int CONNECT_WAIT_TIME = 500;
  private static final int RETRY_COUNT = 3;
  private static final int RETRY_WAIT_TIME = 500;
  private static final int TIMEOUT = 20;
  private static final int TIMEOUT_RETRY_COUNT = 50;
  private URL dbacsURL = null;
  private int mContentLength = 0;
  private boolean mDemomode;
  private static SockHttp mObj;
  private boolean mSocketErr = false;
  private HttpURLConnection mUrlConnect = null;
  private VirtualITC mVirtualITC = null;
  
  private SockHttp(String paramString, int paramInt, boolean paramBoolean)
  {
    mDemomode = paramBoolean;
    try
    {
      if (paramBoolean == true) {
        mVirtualITC = new VirtualITC();
      } else {
        dbacsURL = new URL("http", paramString, paramInt, "/cmd/");
      }
    }
    catch (MalformedURLException localMalformedURLException)
    {
      mSocketErr = true;
    }
    catch (Exception localException) {}
    mObj = this;
  }
  
  public static SockHttp createInstance(String paramString, int paramInt, boolean paramBoolean)
  {
    mObj = new SockHttp(paramString, paramInt, paramBoolean);
    return mObj;
  }
  
  public static SockHttp getInstance()
  {
    return mObj;
  }
  
  private void procRequestReply(ComAbstract paramComAbstract)
    throws Exception
  {
    ByteBuffer localByteBuffer1 = paramComAbstract.getSendBuffer();
    mUrlConnect = ((HttpURLConnection)dbacsURL.openConnection());
    mUrlConnect.setUseCaches(false);
    mUrlConnect.setRequestProperty("Content-Length", Integer.toString(paramComAbstract.getSendSize()));
    mUrlConnect.setRequestProperty("Content-Type", "application/octet-stream");
    mUrlConnect.setRequestMethod("POST");
    mUrlConnect.setDoInput(true);
    mUrlConnect.setDoOutput(true);
    BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(mUrlConnect.getOutputStream());
    localBufferedOutputStream.write(localByteBuffer1.array(), 0, paramComAbstract.getSendSize());
    localBufferedOutputStream.flush();
    mUrlConnect.connect();
    mContentLength = 0;
    int i = mUrlConnect.getResponseCode();
    if (i != 200)
    {
      localBufferedOutputStream.close();
      mUrlConnect.disconnect();
      throw new IOException("Web server returns response code " + i + "  " + mUrlConnect.getResponseMessage());
    }
    mContentLength = mUrlConnect.getContentLength();
    if (mContentLength > 71680) {
      throw new IOException("Max content length is 71680, but received size is " + mContentLength);
    }
    BufferedInputStream localBufferedInputStream = new BufferedInputStream(mUrlConnect.getInputStream());
    ByteBuffer localByteBuffer2 = paramComAbstract.getReceiveBuffer();
    int j = 0;
    while (j < mContentLength)
    {
      for (int k = 0; k < 50; k++)
      {
        if (localBufferedInputStream.available() != 0) {
          break;
        }
        Thread.sleep(20L);
        if ((k == 49) && (localBufferedInputStream.available() == 0))
        {
          localBufferedOutputStream.close();
          localBufferedInputStream.close();
          mUrlConnect.disconnect();
          throw new IOException("Socket Timeout");
        }
      }
      int m = localBufferedInputStream.read(localByteBuffer2.array(), j, mContentLength - j);
      if (m == -1)
      {
        localBufferedOutputStream.close();
        localBufferedInputStream.close();
        mUrlConnect.disconnect();
        throw new IOException("ResponseInputStream returns -1");
      }
      j += m;
    }
    while ((goto 418) || (localBufferedInputStream.read() != -1)) {}
    localBufferedOutputStream.close();
    localBufferedInputStream.close();
    mUrlConnect.disconnect();
    if (!paramComAbstract.afterReceive()) {
      throw new IOException("Command afterReceive() Error");
    }
  }
  
  public synchronized void requestReply(ComAbstract paramComAbstract)
    throws Exception
  {
    if (mSocketErr == true) {
      throw new AlreadyErrorException();
    }
    if (SwingUtilities.isEventDispatchThread() == true) {
      throw new FatalException("SockHttp.requestReply");
    }
    if (mDemomode == true)
    {
      ReplyInfo localReplyInfo1 = requestReplyDemo(paramComAbstract);
      if (!localReplyInfo1.isOK()) {
        throw localReplyInfo1.getException();
      }
    }
    else
    {
      for (int i = 0; i < 3; i++)
      {
        ReplyInfo localReplyInfo2 = requestReplySub(paramComAbstract);
        if (localReplyInfo2.isOK() == true) {
          break;
        }
        if (localReplyInfo2.getException().getClass().getName().equals("java.net.ConnectException"))
        {
          mSocketErr = true;
          throw localReplyInfo2.getException();
        }
        Thread.sleep(500L);
        if ((i == 2) && (!localReplyInfo2.isOK()))
        {
          mSocketErr = true;
          throw localReplyInfo2.getException();
        }
      }
    }
  }
  
  private ReplyInfo requestReplyDemo(ComAbstract paramComAbstract)
  {
    try
    {
      mVirtualITC.requestReply(paramComAbstract);
      if (!paramComAbstract.afterReceive()) {
        return new ReplyInfo(new IOException("Command afterReceive() Error"));
      }
    }
    catch (Exception localException)
    {
      return new ReplyInfo(localException);
    }
    return new ReplyInfo();
  }
  
  private ReplyInfo requestReplySub(ComAbstract paramComAbstract)
  {
    try
    {
      ConnectionThread localConnectionThread = new ConnectionThread(paramComAbstract);
      localConnectionThread.setDaemon(true);
      localConnectionThread.start();
      for (int i = 0; i < 50; i++)
      {
        if (localConnectionThread.getState() == CONNECT_STATE_END) {
          break;
        }
        Thread.sleep(500L);
        if ((i == 49) && (localConnectionThread.getState() == CONNECT_STATE_DURING)) {
          throw new ConnectException("Connection Timeout : ConnectionThread");
        }
      }
      Exception localException2 = localConnectionThread.getException();
      if (localException2 != null) {
        throw localException2;
      }
      return new ReplyInfo();
    }
    catch (Exception localException1)
    {
      return new ReplyInfo(localException1);
    }
  }
  
  class ConnectionThread
    extends Thread
  {
    private ComAbstract mCom = null;
    Exception mException = null;
    private boolean mInWait = true;
    
    ConnectionThread(ComAbstract paramComAbstract)
    {
      mCom = paramComAbstract;
    }
    
    public Exception getException()
    {
      return mException;
    }
    
    public int getState()
    {
      if (mInWait == true) {
        return SockHttp.CONNECT_STATE_DURING;
      }
      return SockHttp.CONNECT_STATE_END;
    }
    
    public void run()
    {
      if (mCom == null)
      {
        mException = new FatalException("No Command : ConnectionThread");
        mInWait = false;
      }
      else
      {
        try
        {
          mInWait = true;
          SockHttp.this.procRequestReply(mCom);
        }
        catch (Exception localException)
        {
          mException = localException;
        }
        mInWait = false;
      }
    }
  }
  
  class ReplyInfo
  {
    private Exception mException = null;
    private boolean mOK = false;
    
    public ReplyInfo(Exception paramException)
    {
      mOK = false;
      mException = paramException;
    }
    
    public ReplyInfo()
    {
      mOK = true;
      mException = null;
    }
    
    public Exception getException()
    {
      return mException;
    }
    
    public boolean isOK()
    {
      return mOK;
    }
  }
}
