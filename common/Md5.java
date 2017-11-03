package webitc.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public final class Md5
{
  private static final int BUFFER_SIZE = 1024;
  private static final int S11 = 7;
  private static final int S12 = 12;
  private static final int S13 = 17;
  private static final int S14 = 22;
  private static final int S21 = 5;
  private static final int S22 = 9;
  private static final int S23 = 14;
  private static final int S24 = 20;
  private static final int S31 = 4;
  private static final int S32 = 11;
  private static final int S33 = 16;
  private static final int S34 = 23;
  private static final int S41 = 6;
  private static final int S42 = 10;
  private static final int S43 = 15;
  private static final int S44 = 21;
  private byte[] buffer = null;
  private long count = 0L;
  private byte[] digest = null;
  private InputStream in = null;
  private static byte[] padding = { Byte.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
  private int[] state = null;
  private boolean stringp = false;
  
  public Md5(InputStream paramInputStream)
  {
    stringp = false;
    in = paramInputStream;
    state = new int[4];
    buffer = new byte[64];
    count = 0L;
    state[0] = 1732584193;
    state[1] = -271733879;
    state[2] = -1732584194;
    state[3] = 271733878;
  }
  
  public Md5(String paramString)
  {
    this(paramString, "UTF8");
  }
  
  public Md5(String paramString1, String paramString2)
  {
    byte[] arrayOfByte = null;
    try
    {
      arrayOfByte = paramString1.getBytes(paramString2);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException("no " + paramString2 + " encoding!!!");
    }
    stringp = true;
    in = new ByteArrayInputStream(arrayOfByte);
    state = new int[4];
    buffer = new byte[64];
    count = 0L;
    state[0] = 1732584193;
    state[1] = -271733879;
    state[2] = -1732584194;
    state[3] = 271733878;
  }
  
  private final int F(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 & paramInt2 | (paramInt1 ^ 0xFFFFFFFF) & paramInt3;
  }
  
  private final int FF(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    paramInt1 += F(paramInt2, paramInt3, paramInt4) + paramInt5 + paramInt7;
    paramInt1 = rotate_left(paramInt1, paramInt6);
    paramInt1 += paramInt2;
    return paramInt1;
  }
  
  private final int G(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 & paramInt3 | paramInt2 & (paramInt3 ^ 0xFFFFFFFF);
  }
  
  private final int GG(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    paramInt1 += G(paramInt2, paramInt3, paramInt4) + paramInt5 + paramInt7;
    paramInt1 = rotate_left(paramInt1, paramInt6);
    paramInt1 += paramInt2;
    return paramInt1;
  }
  
  private final int H(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 ^ paramInt2 ^ paramInt3;
  }
  
  private final int HH(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    paramInt1 += H(paramInt2, paramInt3, paramInt4) + paramInt5 + paramInt7;
    paramInt1 = rotate_left(paramInt1, paramInt6);
    paramInt1 += paramInt2;
    return paramInt1;
  }
  
  private final int I(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt2 ^ (paramInt1 | paramInt3 ^ 0xFFFFFFFF);
  }
  
  private final int II(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    paramInt1 += I(paramInt2, paramInt3, paramInt4) + paramInt5 + paramInt7;
    paramInt1 = rotate_left(paramInt1, paramInt6);
    paramInt1 += paramInt2;
    return paramInt1;
  }
  
  private final void decode(int[] paramArrayOfInt, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = 0;
    for (int j = 0; j < paramInt2; j += 4)
    {
      paramArrayOfInt[i] = (paramArrayOfByte[(paramInt1 + j)] & 0xFF | (paramArrayOfByte[(paramInt1 + j + 1)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt1 + j + 2)] & 0xFF) << 16 | (paramArrayOfByte[(paramInt1 + j + 3)] & 0xFF) << 24);
      i++;
    }
  }
  
  private byte[] encode(int[] paramArrayOfInt, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    for (int j = 0; j < paramInt; j += 4)
    {
      arrayOfByte[j] = ((byte)(paramArrayOfInt[i] & 0xFF));
      arrayOfByte[(j + 1)] = ((byte)(paramArrayOfInt[i] >> 8 & 0xFF));
      arrayOfByte[(j + 2)] = ((byte)(paramArrayOfInt[i] >> 16 & 0xFF));
      arrayOfByte[(j + 3)] = ((byte)(paramArrayOfInt[i] >> 24 & 0xFF));
      i++;
    }
    return arrayOfByte;
  }
  
  private byte[] end()
  {
    byte[] arrayOfByte = new byte[8];
    for (int i = 0; i < 8; i++) {
      arrayOfByte[i] = ((byte)(int)(count >>> i * 8 & 0xFF));
    }
    int j = (int)(count >> 3) & 0x3F;
    int k = j < 56 ? 56 - j : 120 - j;
    update(padding, k);
    update(arrayOfByte, 8);
    return encode(state, 16);
  }
  
  public byte[] getDigest()
    throws IOException
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    int i = -1;
    if (digest != null) {
      return digest;
    }
    while ((i = in.read(arrayOfByte)) > 0) {
      update(arrayOfByte, i);
    }
    digest = end();
    return digest;
  }
  
  public String getStringDigest()
  {
    if (digest == null) {
      throw new RuntimeException(getClass().getName() + "[getStringDigest]" + ": called before processing.");
    }
    return stringify(digest);
  }
  
  public byte[] processString()
  {
    if (!stringp) {
      throw new RuntimeException(getClass().getName() + "[processString]" + " not a string.");
    }
    try
    {
      return getDigest();
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(getClass().getName() + "[processString]" + ": implementation error.");
    }
  }
  
  private final int rotate_left(int paramInt1, int paramInt2)
  {
    return paramInt1 << paramInt2 | paramInt1 >>> 32 - paramInt2;
  }
  
  private static String stringify(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer(2 * paramArrayOfByte.length);
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      int j = (paramArrayOfByte[i] & 0xF0) >> 4;
      int k = paramArrayOfByte[i] & 0xF;
      localStringBuffer.append(new Character((char)(j > 9 ? 97 + j - 10 : 48 + j)));
      localStringBuffer.append(new Character((char)(k > 9 ? 97 + k - 10 : 48 + k)));
    }
    return localStringBuffer.toString();
  }
  
  private final void transform(byte[] paramArrayOfByte, int paramInt)
  {
    int i = state[0];
    int j = state[1];
    int k = state[2];
    int m = state[3];
    int[] arrayOfInt = new int[16];
    decode(arrayOfInt, paramArrayOfByte, paramInt, 64);
    i = FF(i, j, k, m, arrayOfInt[0], 7, -680876936);
    m = FF(m, i, j, k, arrayOfInt[1], 12, -389564586);
    k = FF(k, m, i, j, arrayOfInt[2], 17, 606105819);
    j = FF(j, k, m, i, arrayOfInt[3], 22, -1044525330);
    i = FF(i, j, k, m, arrayOfInt[4], 7, -176418897);
    m = FF(m, i, j, k, arrayOfInt[5], 12, 1200080426);
    k = FF(k, m, i, j, arrayOfInt[6], 17, -1473231341);
    j = FF(j, k, m, i, arrayOfInt[7], 22, -45705983);
    i = FF(i, j, k, m, arrayOfInt[8], 7, 1770035416);
    m = FF(m, i, j, k, arrayOfInt[9], 12, -1958414417);
    k = FF(k, m, i, j, arrayOfInt[10], 17, -42063);
    j = FF(j, k, m, i, arrayOfInt[11], 22, -1990404162);
    i = FF(i, j, k, m, arrayOfInt[12], 7, 1804603682);
    m = FF(m, i, j, k, arrayOfInt[13], 12, -40341101);
    k = FF(k, m, i, j, arrayOfInt[14], 17, -1502002290);
    j = FF(j, k, m, i, arrayOfInt[15], 22, 1236535329);
    i = GG(i, j, k, m, arrayOfInt[1], 5, -165796510);
    m = GG(m, i, j, k, arrayOfInt[6], 9, -1069501632);
    k = GG(k, m, i, j, arrayOfInt[11], 14, 643717713);
    j = GG(j, k, m, i, arrayOfInt[0], 20, -373897302);
    i = GG(i, j, k, m, arrayOfInt[5], 5, -701558691);
    m = GG(m, i, j, k, arrayOfInt[10], 9, 38016083);
    k = GG(k, m, i, j, arrayOfInt[15], 14, -660478335);
    j = GG(j, k, m, i, arrayOfInt[4], 20, -405537848);
    i = GG(i, j, k, m, arrayOfInt[9], 5, 568446438);
    m = GG(m, i, j, k, arrayOfInt[14], 9, -1019803690);
    k = GG(k, m, i, j, arrayOfInt[3], 14, -187363961);
    j = GG(j, k, m, i, arrayOfInt[8], 20, 1163531501);
    i = GG(i, j, k, m, arrayOfInt[13], 5, -1444681467);
    m = GG(m, i, j, k, arrayOfInt[2], 9, -51403784);
    k = GG(k, m, i, j, arrayOfInt[7], 14, 1735328473);
    j = GG(j, k, m, i, arrayOfInt[12], 20, -1926607734);
    i = HH(i, j, k, m, arrayOfInt[5], 4, -378558);
    m = HH(m, i, j, k, arrayOfInt[8], 11, -2022574463);
    k = HH(k, m, i, j, arrayOfInt[11], 16, 1839030562);
    j = HH(j, k, m, i, arrayOfInt[14], 23, -35309556);
    i = HH(i, j, k, m, arrayOfInt[1], 4, -1530992060);
    m = HH(m, i, j, k, arrayOfInt[4], 11, 1272893353);
    k = HH(k, m, i, j, arrayOfInt[7], 16, -155497632);
    j = HH(j, k, m, i, arrayOfInt[10], 23, -1094730640);
    i = HH(i, j, k, m, arrayOfInt[13], 4, 681279174);
    m = HH(m, i, j, k, arrayOfInt[0], 11, -358537222);
    k = HH(k, m, i, j, arrayOfInt[3], 16, -722521979);
    j = HH(j, k, m, i, arrayOfInt[6], 23, 76029189);
    i = HH(i, j, k, m, arrayOfInt[9], 4, -640364487);
    m = HH(m, i, j, k, arrayOfInt[12], 11, -421815835);
    k = HH(k, m, i, j, arrayOfInt[15], 16, 530742520);
    j = HH(j, k, m, i, arrayOfInt[2], 23, -995338651);
    i = II(i, j, k, m, arrayOfInt[0], 6, -198630844);
    m = II(m, i, j, k, arrayOfInt[7], 10, 1126891415);
    k = II(k, m, i, j, arrayOfInt[14], 15, -1416354905);
    j = II(j, k, m, i, arrayOfInt[5], 21, -57434055);
    i = II(i, j, k, m, arrayOfInt[12], 6, 1700485571);
    m = II(m, i, j, k, arrayOfInt[3], 10, -1894986606);
    k = II(k, m, i, j, arrayOfInt[10], 15, -1051523);
    j = II(j, k, m, i, arrayOfInt[1], 21, -2054922799);
    i = II(i, j, k, m, arrayOfInt[8], 6, 1873313359);
    m = II(m, i, j, k, arrayOfInt[15], 10, -30611744);
    k = II(k, m, i, j, arrayOfInt[6], 15, -1560198380);
    j = II(j, k, m, i, arrayOfInt[13], 21, 1309151649);
    i = II(i, j, k, m, arrayOfInt[4], 6, -145523070);
    m = II(m, i, j, k, arrayOfInt[11], 10, -1120210379);
    k = II(k, m, i, j, arrayOfInt[2], 15, 718787259);
    j = II(j, k, m, i, arrayOfInt[9], 21, -343485551);
    state[0] += i;
    state[1] += j;
    state[2] += k;
    state[3] += m;
  }
  
  private final void update(byte[] paramArrayOfByte, int paramInt)
  {
    int i = (int)(count >> 3) & 0x3F;
    count += (paramInt << 3);
    int j = 64 - i;
    int k = 0;
    if (paramInt >= j)
    {
      System.arraycopy(paramArrayOfByte, 0, buffer, i, j);
      transform(buffer, 0);
      for (k = j; k + 63 < paramInt; k += 64) {
        transform(paramArrayOfByte, k);
      }
      i = 0;
    }
    else
    {
      k = 0;
    }
    System.arraycopy(paramArrayOfByte, k, buffer, i, paramInt - k);
  }
}
