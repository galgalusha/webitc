package webitc.job;

import java.io.OutputStream;
import java.io.PrintStream;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumLang;

class EncodingPrintStream
{
  PrintStream mStream = null;
  
  public EncodingPrintStream(OutputStream paramOutputStream)
    throws Exception
  {
    EnumLang localEnumLang = SystemInfo.getLang();
    String str = "ISO-8859-1";
    if (localEnumLang == EnumLang.JAPANESE) {
      str = "Shift_JIS";
    } else if (localEnumLang == EnumLang.CHINESE) {
      str = "GB2312";
    } else if (localEnumLang == EnumLang.KOREAN) {
      str = "KSC5601";
    } else {
      str = "ISO-8859-1";
    }
    mStream = new PrintStream(paramOutputStream, true, str);
  }
  
  public void close()
  {
    mStream.close();
  }
  
  public void println(String paramString)
  {
    mStream.print(paramString + "\n");
  }
}
