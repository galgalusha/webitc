package webitc.gui;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import webitc.gui.common.ColorRes;

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
      super.setForeground(ColorRes.C_SELECTED_F);
      super.setBackground(ColorRes.C_SELECTED_B);
    }
    else
    {
      super.setForeground(ColorRes.C_NO_SELECTED_F);
      super.setBackground(ColorRes.C_NO_SELECTED_B);
    }
    if (paramObject instanceof TreeNodeAbst != true) {
      return this;
    }
    TreeNodeAbst localTreeNodeAbst = (TreeNodeAbst)paramObject;
    if (localTreeNodeAbst.getName() != null) {
      setText(localTreeNodeAbst.getName());
    }
    if (localTreeNodeAbst.getIcon() != null) {
      setIcon(localTreeNodeAbst.getIcon());
    }
    return this;
  }
}
