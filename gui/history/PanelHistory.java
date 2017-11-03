package webitc.gui.history;

import javax.swing.JLabel;
import webitc.gui.common.PanelAbstract;

public class PanelHistory
  extends PanelAbstract
{
  public PanelHistory()
  {
    JLabel localJLabel = new JLabel("履歴");
    add(localJLabel);
  }
}
