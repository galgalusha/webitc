package webitc.gui.ppd;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import webitc.common.StrRes;

class OrgFilter
  extends FileFilter
{
  OrgFilter() {}
  
  public boolean accept(File paramFile)
  {
    return paramFile.isDirectory();
  }
  
  public String getDescription()
  {
    return StrRes.getStr("IDS_FILEDIALOG_ALL_FOLDER");
  }
}
