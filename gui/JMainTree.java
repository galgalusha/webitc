package webitc.gui;

import webitc.gui.common.JScrollTree;

public class JMainTree
  extends JScrollTree
{
  JMainTree()
  {
    setCellRenderer(new TreeRenderer());
    setRootVisible(false);
    setRowHeight(20);
  }
}
