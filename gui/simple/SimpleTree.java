package webitc.gui.simple;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.tree.DefaultTreeSelectionModel;

public class SimpleTree
  extends JTree
{
  private final JScrollPane mScrollPane = new JScrollPane();
  private final DefaultTreeSelectionModel mSelectionModel = new DefaultTreeSelectionModel();
  
  public SimpleTree()
  {
    setShowsRootHandles(true);
    mScrollPane.getViewport().add(this, null);
    mSelectionModel.setSelectionMode(1);
    setSelectionModel(mSelectionModel);
    setCellRenderer(new TreeRenderer());
    setRootVisible(false);
    setRowHeight(20);
  }
  
  public JScrollPane getScrollPane()
  {
    return mScrollPane;
  }
}
