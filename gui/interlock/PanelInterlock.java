package webitc.gui.interlock;

import javax.swing.JLabel;
import webitc.gui.common.PanelAbstract;

public class PanelInterlock
  extends PanelAbstract
{
  public PanelInterlock()
  {
    JLabel localJLabel = new JLabel("連動");
    add(localJLabel);
  }
}
