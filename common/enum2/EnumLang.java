package webitc.common.enum2;

public class EnumLang
  extends Enum2
{
  public static EnumLang CHINESE;
  public static EnumLang ENGLISH;
  public static EnumLang FRENCH;
  public static EnumLang GERMAN;
  public static EnumLang ITALIAN;
  public static final EnumLang JAPANESE = new EnumLang("ja");
  public static EnumLang KOREAN = new EnumLang("kr");
  public static EnumLang SPANISH;
  
  static
  {
    CHINESE = new EnumLang("zh");
    ENGLISH = new EnumLang("en");
    FRENCH = new EnumLang("fr");
    GERMAN = new EnumLang("de");
    ITALIAN = new EnumLang("it");
    SPANISH = new EnumLang("es");
  }
  
  private EnumLang(String paramString)
  {
    super(paramString);
  }
  
  public static EnumLang getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return JAPANESE;
    case 1: 
      return CHINESE;
    case 2: 
      return ENGLISH;
    case 3: 
      return FRENCH;
    case 4: 
      return GERMAN;
    case 5: 
      return ITALIAN;
    case 6: 
      return SPANISH;
    case 7: 
      return KOREAN;
    }
    return ENGLISH;
  }
  
  public static EnumLang getEnum(String paramString)
  {
    if (paramString.compareTo(JAPANESE.toString()) == 0) {
      return JAPANESE;
    }
    if (paramString.compareTo(CHINESE.toString()) == 0) {
      return CHINESE;
    }
    if (paramString.compareTo(ENGLISH.toString()) == 0) {
      return ENGLISH;
    }
    if (paramString.compareTo(FRENCH.toString()) == 0) {
      return FRENCH;
    }
    if (paramString.compareTo(GERMAN.toString()) == 0) {
      return GERMAN;
    }
    if (paramString.compareTo(ITALIAN.toString()) == 0) {
      return ITALIAN;
    }
    if (paramString.compareTo(SPANISH.toString()) == 0) {
      return SPANISH;
    }
    if (paramString.compareTo(KOREAN.toString()) == 0) {
      return KOREAN;
    }
    return ENGLISH;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == JAPANESE.toString()) {
      return 0;
    }
    if (str == CHINESE.toString()) {
      return 1;
    }
    if (str == ENGLISH.toString()) {
      return 2;
    }
    if (str == FRENCH.toString()) {
      return 3;
    }
    if (str == GERMAN.toString()) {
      return 4;
    }
    if (str == ITALIAN.toString()) {
      return 5;
    }
    if (str == SPANISH.toString()) {
      return 6;
    }
    if (str == KOREAN.toString()) {
      return 7;
    }
    return 2;
  }
}
