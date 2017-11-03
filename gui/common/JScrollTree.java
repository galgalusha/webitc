package webitc.gui.common;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import com.sun.java.swing.plaf.windows.WindowsTreeUI.WindowsTreeCellRenderer;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.plaf.TreeUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

public class JScrollTree
  extends JTree
{
  private final JScrollPane mScrollPane = new JScrollPane();
  private final DefaultTreeSelectionModel mSelectionModel = new DefaultTreeSelectionModel();
  
  public JScrollTree(TreeModel paramTreeModel)
  {
    super(paramTreeModel);
  }
  
  public JScrollTree(TreeNode paramTreeNode, boolean paramBoolean)
  {
    super(paramTreeNode, paramBoolean);
  }
  
  public JScrollTree(TreeNode paramTreeNode)
  {
    super(paramTreeNode);
  }
  
  public JScrollTree(Hashtable paramHashtable)
  {
    super(paramHashtable);
  }
  
  public JScrollTree(Vector paramVector)
  {
    super(paramVector);
  }
  
  public JScrollTree(Object[] paramArrayOfObject)
  {
    super(paramArrayOfObject);
  }
  
  public JScrollTree()
  {
    putClientProperty("JTree.lineStyle", "Angled");
    setRootVisible(true);
    setShowsRootHandles(true);
    mScrollPane.getViewport().add(this, null);
    mSelectionModel.setSelectionMode(1);
    setSelectionModel(mSelectionModel);
  }
  
  public JScrollPane getScrollPane()
  {
    return mScrollPane;
  }
  
  public void setUI(TreeUI paramTreeUI)
  {
    String str1 = System.getProperty("java.version");
    if (((paramTreeUI instanceof WindowsTreeUI)) && (str1.compareTo("1.4.2") >= 0) && (str1.compareTo("1.5.0") < 0)) {
      paramTreeUI = new FixedWindowsTreeUI(null);
    }
    String str2 = System.getProperty("java.version");
    super.setUI(paramTreeUI);
  }
  
  private static class FixedWindowsTreeUI
    extends BasicTreeUI
  {
    FixedWindowsTreeUI(JScrollTree.1 param1)
    {
      this();
    }
    
    private FixedWindowsTreeUI() {}
    
    protected TreeCellRenderer createDefaultCellRenderer()
    {
      void tmp11_8 = new WindowsTreeUI();
      tmp11_8.getClass();
      return new WindowsTreeUI.WindowsTreeCellRenderer(tmp11_8);
    }
    
    protected void ensureRowsAreVisible(int paramInt1, int paramInt2)
    {
      if ((tree != null) && (paramInt1 >= 0) && (paramInt2 < getRowCount(tree)))
      {
        Rectangle localRectangle1 = tree.getVisibleRect();
        Rectangle localRectangle2;
        if (paramInt1 == paramInt2)
        {
          localRectangle2 = getPathBounds(tree, getPathForRow(tree, paramInt1));
          if (localRectangle2 != null)
          {
            x = x;
            width = width;
            tree.scrollRectToVisible(localRectangle2);
          }
        }
        else
        {
          localRectangle2 = getPathBounds(tree, getPathForRow(tree, paramInt1));
          Rectangle localRectangle3 = localRectangle2;
          int i = y;
          int j = i + height;
          for (int k = paramInt1 + 1; k <= paramInt2; k++)
          {
            localRectangle3 = getPathBounds(tree, getPathForRow(tree, k));
            if (y + height > j) {
              break;
            }
          }
          tree.scrollRectToVisible(new Rectangle(x, i, 1, y + height - i));
        }
      }
    }
  }
}
