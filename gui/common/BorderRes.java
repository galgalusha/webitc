package webitc.gui.common;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

public class BorderRes
{
  public static final int CALENDAR_BLACK_LINE = 3;
  public static final int CALENDAR_BLUE_LINE = 6;
  public static final int CALENDAR_BROKEN_LINE = 7;
  public static final int CALENDAR_GREEN_LINE = 5;
  public static final int CALENDAR_RED_LINE = 4;
  public static final int COMPOUND_RAISE_LOWER = 0;
  public static final int COMPOUND_RED = 1;
  public static final int ETCHED = 8;
  public static final int ICON_SELECTION = 2;
  private static final int NUM_OF_BORDER = 9;
  private static Border[] mBorderArray = new Border[9];
  
  public BorderRes() {}
  
  public static TitledBorder createTitledBorder(String paramString)
  {
    return new TitledBorder(getBorder(8), paramString);
  }
  
  public static Border getBorder(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= 9)) {
      CommonUse.AppErr(new ArrayIndexOutOfBoundsException(paramInt), "BorderRes.getBorder");
    }
    if (mBorderArray[paramInt] != null) {
      return mBorderArray[paramInt];
    }
    Border localBorder1;
    Border localBorder2;
    switch (paramInt)
    {
    case 0: 
      localBorder1 = BorderFactory.createRaisedBevelBorder();
      localBorder2 = BorderFactory.createLoweredBevelBorder();
      mBorderArray[paramInt] = BorderFactory.createCompoundBorder(localBorder1, localBorder2);
      break;
    case 1: 
      localBorder1 = BorderFactory.createRaisedBevelBorder();
      localBorder2 = BorderFactory.createLoweredBevelBorder();
      Border localBorder3 = BorderFactory.createLineBorder(Color.red);
      CompoundBorder localCompoundBorder = BorderFactory.createCompoundBorder(localBorder1, localBorder2);
      mBorderArray[paramInt] = BorderFactory.createCompoundBorder(localBorder3, localCompoundBorder);
      break;
    case 2: 
      mBorderArray[paramInt] = BorderFactory.createLineBorder(ColorRes.C_SELECTED_B, 2);
      break;
    case 3: 
      mBorderArray[paramInt] = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(ColorRes.CALENDAR_BLACK, 1));
      break;
    case 4: 
      mBorderArray[paramInt] = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(ColorRes.CALENDAR_RED, 1));
      break;
    case 5: 
      mBorderArray[paramInt] = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(ColorRes.CALENDAR_GREEN, 1));
      break;
    case 6: 
      mBorderArray[paramInt] = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(ColorRes.CALENDAR_BLUE, 1));
      break;
    case 7: 
      mBorderArray[paramInt] = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), new BrokenLineBorder());
      break;
    case 8: 
      mBorderArray[paramInt] = BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 151));
    }
    return mBorderArray[paramInt];
  }
}
