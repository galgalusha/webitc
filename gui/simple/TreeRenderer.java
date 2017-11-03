package webitc.gui.simple;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import webitc.gui.TreeNodeAbst;
import webitc.gui.common.ColorRes;
import webitc.gui.common.IconRes;

class TreeRenderer
  extends JLabel
  implements TreeCellRenderer
{
  TreeRenderer()
  {
    setOpaque(true);
  }
  
  public Component getTreeCellRendererComponent(JTree paramJTree, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt, boolean paramBoolean4)
  {
    if (paramBoolean1 == true)
    {
      super.setForeground(ColorRes.SIMPLE_MODE_LIST_SELECT_F);
      super.setBackground(ColorRes.SIMPLE_MODE_LIST_SELECT_B);
    }
    else
    {
      super.setForeground(ColorRes.SIMPLE_MODE_LIST_NO_SELECT_F);
      super.setBackground(ColorRes.SIMPLE_MODE_LIST_NO_SELECT_B);
    }
    if (paramObject instanceof TreeNodeAbst != true) {
      return this;
    }
    TreeNodeAbst localTreeNodeAbst = (TreeNodeAbst)paramObject;
    if (localTreeNodeAbst.getName() != null) {
      setText(localTreeNodeAbst.getName() + "  ");
    }
    setIcon(IconRes.getIcon(119));
    return this;
  }
}
